package edu.umich.icpsr.c2metadata.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.camel.impl.DefaultProducerTemplate;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.c2metadata.sdtl.pojo.Program;
import org.c2metadata.sdtl.pojo.command.Invalid;
import org.c2metadata.sdtl.pojo.command.TransformBase;
import org.c2metadata.sdtl.pojo.command.UnknownTransform;
import org.c2metadata.sdtl.pojo.command.Unsupported;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.umich.icpsr.c2metadata.model.C2MFile;
import edu.umich.icpsr.c2metadata.model.Job;
import edu.umich.icpsr.c2metadata.model.OutputType;
import edu.umich.icpsr.c2metadata.model.Task;
import edu.umich.icpsr.c2metadata.util.CodebookBuilder;
import edu.umich.icpsr.c2metadata.util.DDIUtils;
import edu.umich.icpsr.commons.config.Config;
import edu.umich.icpsr.commons.dao.BusinessObjectDAO;
import edu.umich.icpsr.commons.dao.FedoraDAO;
import edu.umich.icpsr.commons.dao.FusekiDAO;
import edu.umich.icpsr.commons.dao.RDFNamespaces;
import edu.umich.icpsr.commons.service.EmailService;
import edu.umich.icpsr.commons.util.StringUtils;
import edu.umich.icpsr.ddi2.schema.CodeBookType;
import edu.umich.icpsr.ddi2.schema.VarType;
import us.mtna.dataset.updater.Ddi25OutputGenerator;
import us.mtna.transform.cogs.json.PseudocodeService;

public class C2MetadataService {

	private static final Logger LOG = Logger.getLogger(C2MetadataService.class);

	@Autowired
	private Config config;

	@Autowired
	@Qualifier("archonnexDAO")
	private BusinessObjectDAO archonnexDAO;

	@Autowired
	@Qualifier("fedoraDAO")
	private FedoraDAO fedoraDAO;

	@Autowired
	@Qualifier("fusekiDAO")
	private FusekiDAO fusekiDAO;

	@Autowired
	@Qualifier("jobService")
	private JobService jobService;

	@Autowired
	private DefaultProducerTemplate producer;

	@Autowired
	@Qualifier("codebookBuilder")
	private CodebookBuilder codebookBuilder;

	@Autowired
	@Qualifier("emailService")
	private EmailService emailService;
	private RestTemplate restTemplate = new RestTemplate();

	@PostConstruct
	public void init() {

		// LoggingInterceptor logInterceptor = new LoggingInterceptor();
		// List<ClientHttpRequestInterceptor> interceptors = new
		// ArrayList<ClientHttpRequestInterceptor>();
		// interceptors.add(logInterceptor);
		restTemplate = new RestTemplate();

		// restTemplate.setInterceptors(interceptors);

	}

	private HashMap<String, Boolean> getServerStatus() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("stata", false);
		map.put("spss", false);
		if (hostAvailabilityCheck("http://52.90.103.106", 8080)) {
			map.put("spss", true);
		}
		if (hostAvailabilityCheck("http://ekstern.nsd.no", 80)) {
			map.put("stata", true);
		}
		return map;
	}
	public void endToEnd(String codePath, String ddiPath, String email, String mode, List<C2MFile> fileList) {
		this.endToEnd(codePath, ddiPath, email, mode, null, fileList);
	}

	public void endToEnd(String codePath, String ddiPath, String email, String mode,String type, List<C2MFile> fileList) {
		LOG.info("Executing endToEnd.");
		boolean error = false;
		Job job;
		List<String> filepaths = new ArrayList<>();
		filepaths.add(codePath);
		Integer jobId = null;

		for (C2MFile file : fileList) {
			filepaths.add(file.getFilePath());
			jobId = file.getJobId();
		}
		job = archonnexDAO.findById(Job.class, jobId);
		saveFilesToFedora(job.getId(), filepaths.toArray(new String[filepaths.size()]), "input");
		String sdtlString = null;
		String updatedDdiPath = null;
		String codebookPath = null;
		if (mode.equals("spss")) {
			sdtlString = parseSpss_new(codePath, job, fileList);

		} else if (mode.equals("stata")) {
			sdtlString = parseStata(codePath, ddiPath, job, fileList);
		}
		if (sdtlString == null || sdtlString.isEmpty()) {
			job.setError("Failed to retrieve SDTL from " + mode.toUpperCase() + " parser.");
			job.setEndDate(new Timestamp(System.currentTimeMillis()));
			archonnexDAO.save(job);
			error = true;
		}
		if (!error) {
			updatedDdiPath = updateDdi(sdtlString, fileList, job);
		}
		if (updatedDdiPath == null || updatedDdiPath.isEmpty()) {
			if (job.getError() == null) {
				job.setError("Failed to retrieve revised XML from updater.");
			}
			job.setEndDate(new Timestamp(System.currentTimeMillis()));
			archonnexDAO.save(job);
			error = true;
		}
		if (!error) {
			codebookPath = generateCodebook(updatedDdiPath, job, true);
		}
		if (codebookPath == null || codebookPath.isEmpty()) {
			if (job.getError() == null) {
				job.setError("Failed to generate HTML codebook.");
			}
			error = true;
		}
		job.setEndDate(new Timestamp(System.currentTimeMillis()));
		archonnexDAO.save(job);
		saveFileDtails(job, fileList);
		sendEmail(job, error);
		fileCleanup(new String[] { codePath, ddiPath, updatedDdiPath, codebookPath });
	}

//	static {
//		restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
//			@Override
//			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
//					throws IOException {
//				
//				return execution.execute(request, body);
//			}
//		});
//	};

	public String parseSpss(String codePath, Job job, List<C2MFile> fileList) {
		LOG.info("Executing parseSpss for job " + job.getId() + ", codePath = " + codePath);
		String output = null;
		Task task = jobService.createTask(job.getId());
		try {
			File codeFile = loadFileFromFilePath(codePath);
			if (codeFile == null) {
				task.setError("Unable to load code file from " + codePath);
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				return null;
			}
			String code = FileUtils.readFileToString(codeFile);
			output = restTemplate.postForObject(config.getValue("spss.parser.url"), code, String.class);
			if (output == null || output.isEmpty()) {
				task.setError("Received null/empty string from SPSS parser.");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendSpssParserError(job, task, task.getError(), null, null);
				return null;
			} else if (output.equals("404 Not Found")) {
				task.setError("SPSS parser returned a 404 error.");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendSpssParserError(job, task, task.getError(), null, null);
				return null;
			}
			task.setEndDate(new Timestamp(System.currentTimeMillis()));
			task.setOutputType(OutputType.SPSS.getLabel());
			archonnexDAO.save(task);
			String sdtlPath = config.getValue("shared.network.upload.dir") + "/" + UUID.randomUUID().toString()
					+ "/sdtl.json";
			File outputFile = new File(sdtlPath);
			FileUtils.writeStringToFile(outputFile, output);
			saveFilesToFedora(job.getId(), new String[] { sdtlPath }, "output");
			fileCleanup(new String[] { sdtlPath });
			String isUnsupported = testForUnsupported(output);
			if (isUnsupported == null) {
				task.setError("SPSS parser returned an invalid SDTL");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendSpssParserError(job, task, task.getError(), output, fileList, false, null);
				return null;
			}
			if (isUnsupported == "INVALID") {
				task.setError("SPSS parser returned SDTL that included unsupported/unknown commands.");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendSpssParserError(job, task, task.getError(), output, fileList, false, null);
				return null; // This is the line that
			}
			if (isUnsupported == "UNKNOWN") {
				task.setError("SPSS parser returned SDTL that included unsupported/unknown commands.");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendSpssParserError(job, task, task.getError(), output, fileList, true, null);
				return null; // This is the line that
			}
			/*
			 * Program sdtl = convertStringToProgram(output); boolean isUnsupported =
			 * testForUnsupported(sdtl); if(isUnsupported) { task.
			 * setError("SPSS parser returned SDTL that included unsupported/unknown commands."
			 * ); task.setEndDate(new Timestamp(System.currentTimeMillis()));
			 * archonnexDAO.save(task); sendSpssParserError(job, task, task.getError(),
			 * output); return null; // This is the line that prevents end-to-end from
			 * continuing with unsupported/unknown commands }
			 */
		} catch (Exception e) {
			task.setError("SPSS parser server failed to respond; timeout occurred.");
			task.setEndDate(new Timestamp(System.currentTimeMillis()));
			archonnexDAO.save(task);
			sendSpssParserError(job, task, task.getError(), output, fileList, false, null);
			LOG.error(e.getMessage(), e);
			return null;
		}
		return output;
	}

	public String parseSpss_new(String codePath, Job job, List<C2MFile> fileList) {
		LOG.info("Executing parseSpss for job " + job.getId() + ", codePath = " + codePath);
		String output = null;
		String jsonstring = null;
		Task task = jobService.createTask(job.getId());
		try {
			File codeFile = loadFileFromFilePath(codePath);
			List<Map<String, Object>> jsonArray = new ArrayList<>();

			for (C2MFile c2mFile : fileList) {
				File ddiFile = loadFileFromFilePath(c2mFile.getFilePath());
				CodeBookType codebook = DDIUtils.loadFromXml(ddiFile);
				String ddiVariables = compileVariableList(codebook);
				c2mFile.setVariables(ddiVariables);
				jsonArray.add(c2mFile.toMap());
			}
			if (codeFile == null) {
				task.setError("Unable to load code file from " + codePath);
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				return null;
			}
			String code = FileUtils.readFileToString(codeFile);
			code = cleanStataCode(code);
			Map<String, Object> uriIvariables = new HashMap<String, Object>();
			uriIvariables.put("data_file_descriptions", jsonArray);
			uriIvariables.put("spss", code);
			Map<String, Map<String, Object>> params = new HashMap<>();
			params.put("parameters", uriIvariables);
			// output =
			// restTemplate.postForObject(config.getValue("spss.parser.url"),
			// code, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			final String jsonContent = objectMapper.writeValueAsString(params);
			jsonstring = jsonContent;
			output = restTemplate.execute(config.getValue("spss.parser.url"), HttpMethod.POST, new RequestCallback() {
				@Override
				public void doWithRequest(ClientHttpRequest request) throws IOException {
					request.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
					StreamUtils.copy(jsonContent.getBytes(), request.getBody());
				}
			}, new ResponseExtractor<String>() {
				@Override
				public String extractData(ClientHttpResponse response) throws IOException {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					StreamUtils.copy(response.getBody(), bos);
					return new String(bos.toByteArray());
				}
			});
			if (output == null || output.isEmpty()) {
				task.setError("Received null/empty string from SPSS parser.");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendSpssParserError(job, task, task.getError(), null, jsonstring);
				return null;
			} else if (output.equals("404 Not Found")) {
				task.setError("SPSS parser returned a 404 error.");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendSpssParserError(job, task, task.getError(), null, jsonstring);
				return null;
			}
			task.setEndDate(new Timestamp(System.currentTimeMillis()));
			task.setOutputType(OutputType.SPSS.getLabel());
			archonnexDAO.save(task);
			String sdtlPath = config.getValue("shared.network.upload.dir") + "/" + UUID.randomUUID().toString()
					+ "/sdtl.json";
			File outputFile = new File(sdtlPath);
			FileUtils.writeStringToFile(outputFile, output);
			saveFilesToFedora(job.getId(), new String[] { sdtlPath }, "output");
			fileCleanup(new String[] { sdtlPath });

			String isUnsupported = testForUnsupported(output);

			if (isUnsupported == "INVALID") {
				task.setError("SPSS parser returned SDTL that included unsupported/unknown commands.");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendSpssParserError(job, task, task.getError(), output, fileList, true, jsonstring);
				return null; // This is the line that
			}
//			 if(isUnsupported=="UNKNOWN") {
//				  task.
//				  setError("SPSS parser returned SDTL that included unsupported/unknown commands."
//				 ); task.setEndDate(new Timestamp(System.currentTimeMillis()));
//				  archonnexDAO.save(task); 
//				  sendSpssParserError(job, task,
//				  task.getError(), output,fileList,true); return null; // This is the line that
//				 }
		} catch (Exception e) {
			task.setError("Failed to parse using SPSS parser. Reason :" + e.getMessage());
			task.setEndDate(new Timestamp(System.currentTimeMillis()));
			archonnexDAO.save(task);
			sendSpssParserError(job, task, task.getError(), output, fileList, false, jsonstring);
			LOG.error(e.getMessage(), e);
			return null;
		}
		return output;
	}

	public String parseStata(String codePath, String ddiPath, Job job) {

		C2MFile c2mFile = new C2MFile();
		c2mFile.setFilePath(ddiPath);
		c2mFile.setFileName("ddi.xml");
		List<C2MFile> fileList = new ArrayList<C2MFile>();
		fileList.add(c2mFile);
		return parseStata(codePath, ddiPath, job, fileList);

	}

	public String parseStata(String codePath, String ddiPath, Job job, List<C2MFile> fileList) {
		LOG.info("Executing parseStata for job " + job.getId() + ", codePath = " + codePath + "; ddiPath = " + ddiPath);
		String output = null;
		String jsonstring = null;
		Task task = jobService.createTask(job.getId());
		try {
			File codeFile = loadFileFromFilePath(codePath);
			List<Map<String, Object>> jsonArray = new ArrayList<>();

			for (C2MFile c2mFile : fileList) {
				File ddiFile = loadFileFromFilePath(c2mFile.getFilePath());
				CodeBookType codebook = DDIUtils.loadFromXml(ddiFile);
				String ddiVariables = compileVariableList(codebook);
				c2mFile.setVariables(ddiVariables);
				jsonArray.add(c2mFile.toMap());
			}

			String code = FileUtils.readFileToString(codeFile);
			code = cleanStataCode(code);
			Map<String, Object> uriIvariables = new HashMap<String, Object>();
			uriIvariables.put("data_file_descriptions", jsonArray);
			uriIvariables.put("stata", code);
			Map<String, Map<String, Object>> params = new HashMap<>();
			params.put("parameters", uriIvariables);
			// output =
			// restTemplate.postForObject(config.getValue("spss.parser.url"),
			// code, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			final String jsonContent = objectMapper.writeValueAsString(params);
			jsonstring = jsonContent;
			LOG.info(jsonContent);
			output = restTemplate.execute(config.getValue("stata.parser.url"), HttpMethod.POST, new RequestCallback() {
				@Override
				public void doWithRequest(ClientHttpRequest request) throws IOException {
					request.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
					StreamUtils.copy(jsonContent.getBytes(), request.getBody());
				}
			}, new ResponseExtractor<String>() {
				@Override
				public String extractData(ClientHttpResponse response) throws IOException {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					StreamUtils.copy(response.getBody(), bos);
					return new String(bos.toByteArray());
				}
			});
			if (output == null || output.isEmpty()) {
				task.setError("Received null/empty string from Stata parser.");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendStataParserError(job, task, task.getError(), null, jsonContent);
				return null;
			}
			task.setEndDate(new Timestamp(System.currentTimeMillis()));
			task.setOutputType(OutputType.STATA.getLabel());
			archonnexDAO.save(task);
			String sdtlPath = config.getValue("shared.network.upload.dir") + "/" + UUID.randomUUID().toString()
					+ "/sdtl.json";
			File outputFile = new File(sdtlPath);
			FileUtils.writeStringToFile(outputFile, output);
			saveFilesToFedora(job.getId(), new String[] { sdtlPath }, "output");
			fileCleanup(new String[] { sdtlPath });
			String isUnsupported = testForUnsupported(output);
			if (isUnsupported == null) {
				task.setError("Stata parser returned an invalid SDTL");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendStataParserError(job, task, task.getError(), output, fileList, false, jsonContent);
				return null;
			}
			/*
			 * Program sdtl = convertStringToProgram(output); boolean isUnsupported =
			 * testForUnsupported(sdtl); if(isUnsupported) { task.
			 * setError("Stata parser returned SDTL that included unsupported/unknown commands."
			 * ); task.setEndDate(new Timestamp(System.currentTimeMillis()));
			 * archonnexDAO.save(task); sendStataParserError(job, task, task.getError(),
			 * output); return null; // This is the line that prevents end-to-end from
			 * continuing with unsupported/unknown commands }
			 */
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			task.setError("Failed to parse using Stata parser. Reason: " + e.getMessage());
			task.setEndDate(new Timestamp(System.currentTimeMillis()));
			archonnexDAO.save(task);
			sendStataParserError(job, task, task.getError(), output, fileList, false, jsonstring);
			LOG.error(e.getMessage(), e);
			return null;
		}
		return output;
	}

	private String compileVariableList(CodeBookType codebook) {
		String variables = "";
		if (codebook.getDataDscr() != null && codebook.getDataDscr().get(0).getVar() != null) {
			for (VarType var : codebook.getDataDscr().get(0).getVar()) {
				variables += var.getName() + " ";
			}
		}
		return variables;
	}

	private String cleanStataCode(String input) {
		// LOG.info("Executing cleanStataCode.");
		String output = input.replace("\r\n", "\n"); // converts Windows
														// carriage return to
														// linux style
		return output;
	}

	public String updateDdi(String codePath, String ddiPath, Job job, String mode) {
		LOG.info("Executing updateDdi.");
		String sdtlString = null;
		if (mode.equals("spss")) {
			sdtlString = parseSpss(codePath, job, null);
		} else if (mode.equals("stata")) {
			sdtlString = parseStata(codePath, ddiPath, job);
		}
		if (sdtlString == null || sdtlString.isEmpty()) {
			job.setError("Failed to retrieve SDTL from SPSS parser.");
			job.setEndDate(new Timestamp(System.currentTimeMillis()));
			archonnexDAO.save(job);
			return null;
		}
		String revisedDdiPath = updateDdi(sdtlString, ddiPath, job);
		try {
			File revisedDdi = loadFileFromFilePath(revisedDdiPath);
			return FileUtils.readFileToString(revisedDdi, "UTF-8");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	public String updateDdi(String sdtlString, String ddiPath, Job job) {
		LOG.info("Executing updateDdi.");
		Task task = jobService.createTask(job.getId());
		try {
			Program sdtl = convertStringToProgram(sdtlString);
			if (sdtl == null) {
				task.setError("Unable to convert SDTL json to java object model.");
				archonnexDAO.save(task);
				return null;
			}
			// create file
			saveErrors(sdtl, task);
			File xmlFile = loadFileFromFilePath(ddiPath);
			String output = null;
			try {
				PseudocodeService serviceImpl = new PseudocodeServiceImpl(); // instantiate
																				// your
																				// implementation
				Ddi25OutputGenerator outputGenerator = new Ddi25OutputGenerator();
				outputGenerator.setPseudocodeService(serviceImpl);
				// output = outputGenerator.getUpdatedXmlAsString(sdtl, new
				// FileInputStream(xmlFile));
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				sendUpdaterError(job, e);
				return null;
			}
			if (output == null || output.isEmpty()) {
				addToErrorLog("Received null/empty string from updater.", task);
			}
			// TODO add more error states
			task.setEndDate(new Timestamp(System.currentTimeMillis()));
			task.setOutputType(OutputType.DDI.getLabel());
			archonnexDAO.save(task);
			String revisedDdiPath = config.getValue("shared.network.upload.dir") + "/" + UUID.randomUUID().toString()
					+ "/revised-ddi.xml";
			File outputFile = new File(revisedDdiPath);
			FileUtils.writeStringToFile(outputFile, output);
			saveFilesToFedora(job.getId(), new String[] { revisedDdiPath }, "output");
			return revisedDdiPath;
			// xmlStream.close();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public String updateDdi(String sdtlString, List<C2MFile> fileList, Job job) {
		LOG.info("Executing updateDdi.");
		Task task = jobService.createTask(job.getId());
		try {
			Program sdtl = convertStringToProgram(sdtlString);
			if (sdtl == null) {
				task.setError("Unable to convert SDTL json to java object model.");
				archonnexDAO.save(task);
				return null;
			}
			// create file
			saveErrors(sdtl, task);
			List<FileInputStream> xmlinputStreams = new ArrayList<FileInputStream>();
			for (C2MFile c2mFile : fileList) {
				File xmlFile = loadFileFromFilePath(c2mFile.getFilePath());
				xmlinputStreams.add(new FileInputStream(xmlFile));
			}
			// File xmlFile = loadFileFromFilePath(ddiPath);
			String output = null;
			try {
				PseudocodeService serviceImpl = new PseudocodeServiceImpl(); // instantiate
																				// your
																				// implementation
				Ddi25OutputGenerator outputGenerator = new Ddi25OutputGenerator();
				outputGenerator.setPseudocodeService(serviceImpl);
				output = outputGenerator.getUpdatedXmlAsString(sdtl,
						xmlinputStreams.toArray(new FileInputStream[xmlinputStreams.size()]));

			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				sendUpdaterError(job, e);
				return null;
			}
			if (output == null || output.isEmpty()) {
				addToErrorLog("Received null/empty string from updater.", task);
			}
			// TODO add more error states
			task.setEndDate(new Timestamp(System.currentTimeMillis()));
			task.setOutputType(OutputType.DDI.getLabel());
			archonnexDAO.save(task);
			String revisedDdiPath = config.getValue("shared.network.upload.dir") + "/" + UUID.randomUUID().toString()
					+ "/revised-ddi.xml";
			File outputFile = new File(revisedDdiPath);
			FileUtils.writeStringToFile(outputFile, output);
			saveFilesToFedora(job.getId(), new String[] { revisedDdiPath }, "output");
			return revisedDdiPath;
			// xmlStream.close();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	private void saveErrors(Program sdtl, Task task) {
		for (TransformBase command : sdtl.getCommands()) {
			if (command.getUnknownProperties() != null && command.getUnknownProperties().length > 0) {
				addToErrorLog("Uknown properties: " + Arrays.asList(command.getUnknownProperties()).toString(), task);
			}
			if (command instanceof UnknownTransform) {
				addToErrorLog("Unknown Command found.", task);
				System.out.println("\tCommand is unknown");
			} else if (command instanceof Unsupported) {
				addToErrorLog("Unsupported Command found.", task);
			}
		}
	}

	private void addToErrorLog(String error, Task task) {
		String errors = "";
		if (task.getError() == null || task.getError().isEmpty()) {
			errors += error;
		} else {
			errors = task.getError() + " " + error;
		}
		task.setError(errors);
		archonnexDAO.save(task);
	}

	private Program convertStringToProgram(String sdtlString) {
		// LOG.info("Executing convertStringToProgram.");
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.findAndRegisterModules();
			Program program = mapper.readValue(sdtlString, Program.class);
			return program;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			// This means that the SPSS/Stata parser probably sent soemthing
			// that isn't SDTL. Need to send error message to:
			// JEremy/Dan/Ornulf/Ole.
			return null;
		}
	}

	private String testForUnsupported(String sdtlString) {
		// String isUnsupported = "";
		Program sdtl = this.convertStringToProgram(sdtlString);
		if (sdtl == null) {
			return "";
		}
		List<TransformBase> commands = sdtl.getCommands();
		if (commands == null || commands.size() < 1) {
			return "";
		}
		for (TransformBase command : commands) {
//			if (UnknownTransform.class.isAssignableFrom(command.getClass())) {
//				return "UNKNOWN";
//			}
//			if (Unsupported.class.isAssignableFrom(command.getClass())) {
//				return "UNSUPPORTED";
//			}
			if (Invalid.class.isAssignableFrom(command.getClass())) {
				return "INVALID";
			}
		}
		return "";
	}

	public String generateCodebook(String ddiPath, Job job, boolean path) {
		LOG.info("Executing generateCodebook.");
		Task task = jobService.createTask(job.getId());
		String codebookPath = null;
		try {
			File ddiFile = loadFileFromFilePath(ddiPath);
			CodeBookType codebook = DDIUtils.loadFromXml(ddiFile);
			String html = codebookBuilder.buildCodebook(codebook);
			if (html == null || html.isEmpty()) {
				task.setError("Codebook generator returned no text.");
				task.setEndDate(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(task);
				sendCodebookError(job, null);
				return null;
			} else {
				codebookPath = config.getValue("shared.network.upload.dir") + "/" + UUID.randomUUID().toString()
						+ "/codebook.html";
				File codebookFile = new File(codebookPath);
				FileUtils.writeStringToFile(codebookFile, html);
				saveFilesToFedora(job.getId(), new String[] { codebookPath }, "output");
				fileCleanup(new String[] { codebookPath });
			}
			task.setEndDate(new Timestamp(System.currentTimeMillis()));
			task.setOutputType(OutputType.HTML.getLabel());
			archonnexDAO.save(task);
			if (path) {
				return codebookPath;
			}
			return html;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			task.setError("Codebook generator returned an unknown error.");
			task.setEndDate(new Timestamp(System.currentTimeMillis()));
			archonnexDAO.save(task);
			sendCodebookError(job, e);
		}
		return null;
	}

	private void sendSpssParserError(Job job, Task task, String error, String output, String jsonString) {
		LOG.info("Executing sendSpssParserError.");
		String[] email = new String[] { "jeremy@colectica.com", "dan@colectica.com" };
		if (config.getValue("build.environment").equals("local")) {
			email = new String[] { "kseelam@umich.edu" };
		} else if (error.equals("SPSS parser server failed to respond; timeout occurred.")) {
			email = new String[] { "sth@umich.edu" };
		}
		String subject = "C2Metadata: Error generated by SPSS parser";
		String message = "Job " + job.getId() + " generated an error while attempting to parse the SPSS:\n\n";
		message += error;
		message += "\n\n";
		message += "The job generated the following input files:\n\n";
		message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/code.txt\n";
		message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/ddi.xml\n";
		if (output != null) {
			message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/output/sdtl.json\n\n";
		}
		if (jsonString != null) {
			message += "\n\n Parser URL : http://52.90.103.106:8080/api/spsstosdtl";
			message += "\n";
			message += "\n\n JSON Input is";
			message += "\n";
			message += jsonString;
		}
		emailService.sendEmail(email, subject, message, "c2metadata@umich.edu");
	}

	private void sendSpssParserError(Job job, Task task, String error, String output, List<C2MFile> fileList,
			boolean includeUser, String jsonString) {
		LOG.info("Executing sendSpssParserError.");
		List<String> emailList = new ArrayList<>();

		// String[] email = new String[] { , };
		if (config.getValue("build.environment").equals("local")) {
			emailList.add("kseelam@umich.edu");
		} else {
			emailList.add("jeremy@colectica.com");
			emailList.add("dan@colectica.com");
			emailList.add("sth@umich.edu");
			emailList.add("altergc@umich.edu");
			emailList.add("kseelam@umich.edu");
		}
		if (includeUser) {
			emailList.add(job.getEmail());
		}
		String subject = "C2Metadata: Error generated by SPSS parser";
		String message = "Job " + job.getId() + " generated an error while attempting to parse the SPSS:\n\n";
		message += error;
		message += "\n\n";
		message += "The job generated the following input files:\n\n";
		message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/code.txt\n";
		if (fileList != null) {
			for (C2MFile file : fileList) {
				message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/" + file.getFileName()
						+ "\n";
			}
		} else {
			message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/ddi.xml\n";
		}
		if (output != null) {
			message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/output/sdtl.json\n\n";
		}
		if (jsonString != null) {
			message += "\n\n Parser URL : http://52.90.103.106:8080/api/spsstosdtl";
			message += "\n";
			message += "\n\n JSON Input is";
			message += "\n";
			message += jsonString;
		}
		emailService.sendEmail(emailList.toArray(new String[emailList.size()]), subject, message,
				"c2metadata@umich.edu");
	}

	private void sendStataParserError(Job job, Task task, String error, String output, List<C2MFile> fileList,
			boolean includeUser, String jsonString) {
		LOG.info("Executing sendStataParserError.");
		List<String> emailList = new ArrayList<>();

		if (config.getValue("build.environment").equals("local")) {
			emailList.add("kseelam@umich.edu");
		} else {
			emailList.add("ornulf.risnes@nsd.uib.no");
			emailList.add("Ole.Voldsater@nsd.no");
			emailList.add("altergc@umich.edu");
			emailList.add("kseelam@umich.edu");
		}
		if (includeUser) {
			emailList.add(job.getEmail());
		}
		String subject = "C2Metadata: Error generated by Stata parser";
		String message = "Job " + job.getId() + " generated an error while attempting to parse the Stata:\n\n";
		message += error;
		message += "\n\n";
		message += "The job generated the following input files:\n\n";
		message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/code.txt\n";
		if (fileList != null) {
			for (C2MFile file : fileList) {
				message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/" + file.getFileName()
						+ "\n";
			}
		} else {
			message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/ddi.xml\n";
		}
		if (output != null) {
			message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/output/sdtl.json\n\n";
		}
		if (output != null) {
			message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/output/sdtl.json\n\n";
		}
		if (jsonString != null) {
			message += "\n\n Parser URL : http://ekstern.nsd.no/metacap/stata2vtl";
			message += "\n";
			message += "\n\n JSON Input is";
			message += "\n";
			message += jsonString;
		}
		emailService.sendEmail(emailList.toArray(new String[emailList.size()]), subject, message,
				"c2metadata@umich.edu");
	}

	private void sendStataParserError(Job job, Task task, String error, String output, String jsonString) {
		LOG.info("Executing sendStataParserError.");
		String[] email = new String[] { "ornulf.risnes@nsd.uib.no", "Ole.Voldsater@nsd.no" };
		if (config.getValue("build.environment").equals("local")) {
			email = new String[] { "matvey@umich.edu" };
		}
		String subject = "C2Metadata: Error generated by Stata parser";
		String message = "Job " + job.getId() + " generated an error while attempting to parse the Stata:\n\n";
		message += error;
		message += "\n\n";
		message += "The job generated the following input files:\n\n";
		message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/code.txt\n";
		message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/ddi.xml\n";
		if (output != null) {
			message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/output/sdtl.json\n\n";
		}
		if (jsonString != null) {
			message += "\n\n Parser URL : http://ekstern.nsd.no/metacap/stata2vtl";
			message += "\n";
			message += "\n\n JSON Input is";
			message += "\n";
			message += jsonString;
		}
		emailService.sendEmail(email, subject, message, "c2metadata@umich.edu");
	}

	private void sendUpdaterError(Job job, Exception e) {
		LOG.info("Executing sendUpdaterError.");
		String[] email = new String[] { "carson.hunter@mtna.us", "j.gager@mtna.us" };
		if (config.getValue("build.environment").equals("local")) {
			email = new String[] { "matvey@umich.edu" };
		}
		String subject = "C2Metadata: Exception generated by updater";
		String message = "Job " + job.getId()
				+ " generated an exception from Ddi25OutputGenerator.getUpdatedXmlAsString:\n\n";
		message += ExceptionUtils.getStackTrace(e);
		message += "\n\n";
		message += "The job generated the following input files:\n\n";
		message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/code.txt\n";
		message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/ddi.xml\n";
		message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/output/sdtl.json\n\n";
		// LOG.info("Sending email to: " + Arrays.asList(email).toString() + ",
		// subject: " + subject + ".");
		// LOG.info(message);
		emailService.sendEmail(email, subject, message, "c2metadata@umich.edu");
	}

	private void sendCodebookError(Job job, Exception e) {
		LOG.info("Executing sendCodebookError.");
		String[] email = new String[] { "matvey@umich.edu" };
		String subject = "C2Metadata: Error generated by Codebook generator";
		String message = "Job " + job.getId()
				+ " generated an error while attempting to generate an HTML codebook.\n\n";
		if (e != null) {
			message += ExceptionUtils.getStackTrace(e);
			message += "\n\n";
		}
		message += "The job generated the following input files:\n\n";
		message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/input/ddi.xml\n";
		emailService.sendEmail(email, subject, message, "c2metadata@umich.edu");
	}

	private void sendEmail(Job job, boolean error) {
		LOG.info("Executing sendEmail for job " + job.getId() + ".");
		String[] email = new String[] { job.getEmail() };
		String subject = "C2Metadata: Files Processed";
		String message = "";
		if (!error) {
			message = "You can download your revised DDI XML at:\n\n";
			message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/output/revised-ddi.xml\n\n";
			message += "You can download the HTML codebook at:\n\n";
			message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/output/codebook.html\n\n";
		} else {
			boolean htmlCreated = false;
			boolean xmlCreated = false;
			message = "We encountered errors while attempting to process your files. Our server reported:\n\n";
			message += "* " + job.getError() + "\n";
			for (Task task : retrieveTasks(job)) {
				if (task.getError() != null) {
					message += "* " + task.getError() + "\n";
				}
				if (task.getOutputType() != null && task.getOutputType().equals("html") && task.getError() == null) {
					htmlCreated = true;
				}
				if (task.getOutputType() != null && task.getOutputType().equals("xml") && task.getError() == null) {
					xmlCreated = true;
				}
			}
			message += "\nOur developers will look into the matter to determine what went wrong. (JOB_ID " + job.getId()
					+ ")\n\n";
			if (xmlCreated) {
				message = "You can download your revised DDI XML at:\n\n";
				message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/output/ddi.xml\n\n";
			}
			if (htmlCreated) {
				message += "You can download the HTML codebook at:\n\n";
				message += config.getValue("app.url") + "/download/jobs/" + job.getId() + "/output/codebook.html\n\n";
			}
		}
		// LOG.info("Sending email to: " + Arrays.asList(email).toString() + ",
		// subject: " + subject + ".");
		// LOG.info(message);
		emailService.sendEmail(email, subject, message, "c2metadata@umich.edu");
	}

	private List<Task> retrieveTasks(Job job) {
		String hql = "from Task where jobId = " + job.getId() + " order by id asc";
		List<Task> tasks = archonnexDAO.findByQueryString(Task.class, hql);
		return tasks;
	}

	public void saveFilesToFedora(int jobId, String[] filePaths, String mode) {
		Job job = archonnexDAO.findById(Job.class, jobId);
		if (job != null) {
			saveFilesToFedora(job, filePaths, mode);
		}
	}

	public void saveFilesToFedora(Job job, String[] filePaths, String mode) {
		for (String tempFilePath : filePaths) {
			try {
				File file = loadFileFromFilePath(tempFilePath);
				FileInputStream fis = new FileInputStream(file);
				String md5 = DigestUtils.md5Hex(fis);
				fis.close();
				String uuid = UUID.randomUUID().toString();
				String escFileName = StringUtils.escapeFilePathNames(file.getName());
				String filePath = "/c2metadata/jobs/" + job.getId() + "/" + mode + "/" + escFileName;
				fedoraDAO.deleteObject(filePath, true);
				fedoraDAO.saveDirectory(filePath);
				HashMap<String, Object> props = new HashMap<String, Object>();
				props.put(RDFNamespaces.PREFIX_icpsr + ":containerType", "file");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileContentType ", "");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileName", escFileName);
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileLastModified", new Date(System.currentTimeMillis()));
				props.put(RDFNamespaces.PREFIX_icpsr + ":uploadedBy", job.getEmail());
				props.put(RDFNamespaces.PREFIX_icpsr + ":MD5", md5);
				props.put(RDFNamespaces.PREFIX_icpsr + ":resourceUUID", uuid);
				props.put(RDFNamespaces.PREFIX_dcterms + ":title", escFileName);
				props.put("a", "pcdm:File");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileSize", file.length());
				fedoraDAO.insertProperties(filePath, props);
				fedoraDAO.saveFile(filePath + "/binary", file, file.getName());
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	public void saveFileDtails(Job job, List<C2MFile> fileList) {
		for (C2MFile file : fileList) {
			try {

				String fileUrl = "/c2metadata/jobs/" + job.getId() + "/input/" + file.getFileName();
				file.setFileUrl(fileUrl);
				file.setJobId(job.getId());
				file.setCreatedTs(new Timestamp(System.currentTimeMillis()));
				archonnexDAO.save(file);

			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	private File loadFileFromFilePath(String filePath) {
		// LOG.info("Executing loadFileFromFilePath for filePath = " +
		// filePath);
		try {
			Path path = Paths.get(filePath);
			File file = new File(path.toString());
			return file;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public void fileCleanup(String[] filePaths) {
		// LOG.info("Executing fileCleanup.");
		for (String filePath : filePaths) {
			if (filePath != null && !filePath.isEmpty()) {
				String folderPath = filePath.substring(0, filePath.lastIndexOf("/"));
				File file = loadFileFromFilePath(filePath);
				file.delete();
				File folder = new File(folderPath);
				folder.delete();
			}
		}
	}

	public static boolean hostAvailabilityCheck(String serverAddress, int port) {
		try (Socket s = new Socket(serverAddress, port)) {
			return true;
		} catch (IOException ex) {
			/* ignore */
		}
		return false;
	}

	public void sendMessage(String codePath, String ddiPath, String email, String mode, String type,List<Integer> fileDetails) {
		LOG.info("Executing sendMessage.");
		try {
			StringBuilder ids = new StringBuilder();
			for (int i = 0; i < fileDetails.size(); i++) {
				ids.append(fileDetails.get(i).toString());
				if (i != (fileDetails.size() - 1)) {
					ids.append("-");
				}
			}
			HashMap<String, Object> properties = new HashMap<String, Object>();
			properties.put("CODE_PATH", codePath);
			properties.put("DDI_PATH", "");
			properties.put("EMAIL", email);
			properties.put("MODE", mode);
			properties.put("TYPE", type);

			properties.put("FILE_LIST", ids.toString());
			producer.sendBodyAndHeaders("direct:c2metadata", null, properties);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
