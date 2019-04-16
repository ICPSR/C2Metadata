package edu.umich.icpsr.c2metadata.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import edu.umich.icpsr.c2metadata.model.C2MFile;
import edu.umich.icpsr.c2metadata.model.Job;
import edu.umich.icpsr.c2metadata.service.C2MetadataService;
import edu.umich.icpsr.c2metadata.service.JobService;
import edu.umich.icpsr.commons.ThreadContextVariables;
import edu.umich.icpsr.commons.config.Config;
import edu.umich.icpsr.commons.dao.BusinessObjectDAO;

@Controller
public class C2MetadataController {

	private static final Logger LOG = Logger.getLogger(C2MetadataController.class);

	@Autowired
	private Config config;

	@Autowired
	@Qualifier("jobService")
	private JobService jobService;

	@Autowired
	@Qualifier("archonnexDAO")
	private BusinessObjectDAO archonnexDAO;

	@Autowired
	@Qualifier("c2metadataService")
	private C2MetadataService c2metadataService;
public static final String  FILE_PARAM="ddiFile";
public static final String  SCRIPT_FILENAME_PARAM="script_filename";
public static final String  DDI_FILENAME_PARAM="ddi_filename";
	@RequestMapping("/endToEnd")
	public ModelAndView endToEnd(
			MultipartHttpServletRequest multipartRequest,
			@RequestParam("mode") String mode,
			@RequestParam("type") String type,
			HttpServletRequest httpReq, 
			HttpServletResponse response) {
		LOG.info("Executing endToEnd.");
		ModelAndView mv = new ModelAndView("endToEnd");
		try {
			String codePath = saveAndReturnPath(multipartRequest,"codeFile");
			//String ddiPath = saveAndReturnPath(multipartRequest,"ddiFile");
			String ddiPath = null;
			List<Integer> fileDetails= processFilePaths(multipartRequest);		
			
	c2metadataService.sendMessage(codePath, ddiPath, ThreadContextVariables.getSessionUser().getEmail(), mode,type,fileDetails);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return mv;
	}
	
	@RequestMapping(value = "/parse/spss", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public void parseSpss(
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest req, 
			HttpServletResponse resp
			) {
		LOG.info("Executing parseSpss.");
		Job job = jobService.createJob(ThreadContextVariables.getSessionUser().getEmail()); 
		String codePath = saveAndReturnPath(multipartRequest,"codeFile");
		c2metadataService.saveFilesToFedora(job.getId(), new String[] {codePath}, "input");
		String sdtlString = c2metadataService.parseSpss(codePath,job,null);
		if(sdtlString!=null && !sdtlString.isEmpty()) {
			convertStringToDownload("spss-sdtl.json", sdtlString, resp);
		} 
		c2metadataService.fileCleanup(new String[] {codePath});
		job.setEndDate(new Timestamp(System.currentTimeMillis()));
		archonnexDAO.save(job);
	}
	
	@RequestMapping(value = "/parse/stata", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public void parseStata(
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest req, 
			HttpServletResponse resp
			) {
		LOG.info("Executing parseStata.");
		Job job = jobService.createJob(ThreadContextVariables.getSessionUser().getEmail()); 
		try {
			String codePath = saveAndReturnPath(multipartRequest,"codeFile");
			String ddiPath = saveAndReturnPath(multipartRequest,"ddiFile");
			c2metadataService.saveFilesToFedora(job.getId(), new String[] {codePath,ddiPath}, "input");
			String sdtlString = c2metadataService.parseStata(codePath,ddiPath,job);
			if(sdtlString!=null && !sdtlString.isEmpty()) {
				convertStringToDownload("stata-sdtl.json", sdtlString, resp);
			} 
			c2metadataService.fileCleanup(new String[] {codePath,ddiPath});
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		job.setEndDate(new Timestamp(System.currentTimeMillis()));
		archonnexDAO.save(job);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public void updateWithStata(
			MultipartHttpServletRequest multipartRequest,
			@RequestParam("mode") String mode,
			HttpServletRequest req, 
			HttpServletResponse resp
			) {
		LOG.info("Executing updateWithStata.");
		Job job = jobService.createJob(ThreadContextVariables.getSessionUser().getEmail()); 
		try {
			String codePath = saveAndReturnPath(multipartRequest,"codeFile");
			String ddiPath = saveAndReturnPath(multipartRequest,"ddiFile");
			c2metadataService.saveFilesToFedora(job.getId(), new String[] {codePath,ddiPath}, "input");
			String ddiString = c2metadataService.updateDdi(codePath, ddiPath, job, mode);
			if(ddiString!=null && !ddiString.isEmpty()) {
				convertStringToDownload("revised-ddi.xml", ddiString, resp);
			} 
			c2metadataService.fileCleanup(new String[] {codePath,ddiPath});
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		job.setEndDate(new Timestamp(System.currentTimeMillis()));
		archonnexDAO.save(job);
	}

	@RequestMapping(value = "/codebook/html", method = RequestMethod.POST)
	public void generateCodebook(
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest req,
			HttpServletResponse resp) {
		LOG.info("Executing generateCodebook.");
		Job job = jobService.createJob(ThreadContextVariables.getSessionUser().getEmail()); 
		try {
			String ddiPath = saveAndReturnPath(multipartRequest,"ddiFile");
			c2metadataService.saveFilesToFedora(job.getId(), new String[] {ddiPath}, "input");
			String html = c2metadataService.generateCodebook(ddiPath,job, false);
			if(html!=null && !html.isEmpty()) {
				convertStringToDownload("codebook.html", html, resp);
			} 
			c2metadataService.fileCleanup(new String[] {ddiPath});
		} catch(Exception e){
			LOG.error(e);
		}
		job.setEndDate(new Timestamp(System.currentTimeMillis()));
		archonnexDAO.save(job);
	}
		
	private String saveAndReturnPath(MultipartHttpServletRequest multipartRequest, String fileName) {
		LOG.info("Executing saveAndReturnPath for fileName = " + fileName);
		try {
			MultipartFile multiPartFile = multipartRequest.getFile(fileName);
			String filePath = config.getValue("shared.network.upload.dir") + "/" + UUID.randomUUID().toString();
			new File(filePath).mkdirs();
			String revisedFileName = "code.txt";
			if(fileName.equals("ddiFile")) {
				revisedFileName = "ddi.xml";
			}
			filePath += "/" + revisedFileName;
			LOG.info("About to save file to " + filePath);
			multiPartFile.transferTo(new File(filePath));
			LOG.info("Saved file to " + filePath);
			return filePath;
		} catch (Exception e) {
			LOG.error("Failed while attempting to save input file to temp. directory.");
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
	private List<Integer> processFilePaths(MultipartHttpServletRequest multipartRequest) {
		LOG.info("Executing ddi extraction");
		List<Integer> fileList = new ArrayList<>();
		Job job = jobService.createJob(ThreadContextVariables.getSessionUser().getEmail()); 
		try {
			for(int i=0;i<4;i++){
				C2MFile file= new C2MFile();
				String name= FILE_PARAM+i;
			MultipartFile multiPartFile = multipartRequest.getFile(name);
			if(multiPartFile==null||multiPartFile.getSize()==0){
				break;
			}
			String filePath = config.getValue("shared.network.upload.dir") + "/" + UUID.randomUUID().toString();
			new File(filePath).mkdirs();
			
			filePath += "/" + multiPartFile.getOriginalFilename();
			
			multiPartFile.transferTo(new File(filePath));
			
			file.setFilePath(filePath);
			file.setFileName(multiPartFile.getOriginalFilename());
			file.setScriptFileName(multipartRequest.getParameter(SCRIPT_FILENAME_PARAM+i));
			file.setDdiFileName(multipartRequest.getParameter(DDI_FILENAME_PARAM+i));
			file.setJobId(job.getId());
			archonnexDAO.save(file);
			fileList.add(file.getFileId());
			LOG.info("Saved file to " + filePath);
			}
			return fileList;
		} catch (Exception e) {
			LOG.error("Failed while attempting to save input file to temp. directory.");
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	@ResponseBody private static void convertStringToDownload(
			String filename,
			String fileContent,
			HttpServletResponse response
			){
		try {
			OutputStream out = response.getOutputStream();
			response.setContentType("text/plain; charset=utf-8");
			response.addHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
			out.write(fileContent.getBytes(Charset.forName("UTF-8")));
			out.flush();
			out.close();
		  } catch (IOException e) {
			  LOG.error("Error while trying to trigger citation export.");
			  LOG.error(e);
		  }		
	}


}
