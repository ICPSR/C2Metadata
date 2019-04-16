package edu.umich.icpsr.c2metadata.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.umich.icpsr.c2metadata.exception.AccessDeniedException;
import edu.umich.icpsr.c2metadata.model.Job;
import edu.umich.icpsr.c2metadata.model.Task;
import edu.umich.icpsr.commons.ThreadContextVariables;
import edu.umich.icpsr.commons.config.Config;
import edu.umich.icpsr.commons.dao.BusinessObjectDAO;
import edu.umich.icpsr.commons.dao.FedoraDAO;
import edu.umich.icpsr.commons.dao.FusekiDAO;
import edu.umich.icpsr.commons.dao.RDFNamespaces;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger LOG = Logger.getLogger(AdminController.class);
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
	private Config config;

	@RequestMapping("/index")
	public ModelAndView adminView(HttpServletRequest httpReq, HttpServletResponse response) {
		checkAdmin();
		ModelAndView mv = new ModelAndView("admin");
		mv.addObject("appUrl", config.getValue("app.url"));
		return mv;
	}

	@RequestMapping("/recentFailedJobs")
	public @ResponseBody List<Job> failedJobList() {
		checkAdmin();
		List<Job> jobsList = archonnexDAO.findByQueryString(Job.class,
				"from Job where error is not null order by id desc");

		if (jobsList == null) {
			jobsList = new ArrayList<Job>();
		}
		return jobsList;
	}

	@RequestMapping("/recentCompletedJobs")
	public @ResponseBody List<Job> completedJobList() {
		checkAdmin();
		List<Job> jobsList = archonnexDAO.findByQueryString(Job.class, "from Job where error is null order by id desc");

		if (jobsList == null) {
			jobsList = new ArrayList<Job>();
		}
		return jobsList;
	}

	@RequestMapping("/viewTasks")
	public ModelAndView viewTasks(@RequestParam Integer jobId) {
		checkAdmin();
		ModelAndView mv = new ModelAndView("TasksView");
		mv.addObject("appUrl", config.getValue("app.url"));
		mv.addObject("jobId", jobId);
		return mv;
	}

	@RequestMapping("/tasks")
	public @ResponseBody List<Task> getTaskList(@RequestParam Integer jobId) {
		checkAdmin();
		List<Task> taskList = null;
		try {
			taskList = archonnexDAO.findByQueryString(Task.class, "from Task where jobId=" + jobId);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		return taskList;
	}

	@RequestMapping("/adminupload")
	public ModelAndView uploadView() {
		checkAdmin();
		ModelAndView mv = new ModelAndView("adminUpload");
		return mv;
	}

	@RequestMapping("/uploaddocs")
	public ModelAndView uploadDocs(MultipartHttpServletRequest multipartRequest, HttpServletRequest httpReq,
			HttpServletResponse response) {
		checkAdmin();

		ModelAndView mv = new ModelAndView("adminUpload");
		try {
			
			MultipartFile functionLibraryDoc = multipartRequest.getFile("functionsLibrary");
			MultipartFile expressionLibraryDoc = multipartRequest.getFile("expressionsLibrary");
			if (functionLibraryDoc != null) {
				String functionFilePath = "/c2metadata/admin/config/functionLibrary.json";
				fedoraDAO.deleteObject(functionFilePath, true);
				fedoraDAO.saveDirectory(functionFilePath);
				HashMap<String, Object> props = new HashMap<String, Object>();
				String filePath = config.getValue("shared.network.upload.dir") + "/library/functions";
				new File(filePath).mkdirs();

				filePath += "/functionLibrary.json";
				LOG.info("About to save file to " + filePath);
				File file = new File(filePath);
				functionLibraryDoc.transferTo(file);
				FileInputStream fis = new FileInputStream(file);
				String md5 = DigestUtils.md5Hex(fis);
				fis.close();
				props.put(RDFNamespaces.PREFIX_icpsr + ":containerType", "file");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileContentType ", "");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileName", "functionLibrary.json");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileLastModified", new Date(System.currentTimeMillis()));
				props.put(RDFNamespaces.PREFIX_icpsr + ":uploadedBy",
						ThreadContextVariables.getSessionUser().getEmail());
				props.put(RDFNamespaces.PREFIX_icpsr + ":MD5", md5);
				props.put(RDFNamespaces.PREFIX_icpsr + ":resourceUUID", UUID.randomUUID().toString());
				props.put(RDFNamespaces.PREFIX_dcterms + ":title", "functionLibrary.json");
				props.put("a", "pcdm:File");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileSize", functionLibraryDoc.getSize());
				fedoraDAO.insertProperties(functionFilePath, props);
				fedoraDAO.saveFile(functionFilePath + "/binary", file, "functionLibrary.json");
			}
			if (expressionLibraryDoc != null) {
				String expressionsFilePath = "/c2metadata/admin/config/expressionLibrary.json";
				fedoraDAO.deleteObject(expressionsFilePath, true);
				fedoraDAO.saveDirectory(expressionsFilePath);
				HashMap<String, Object> props = new HashMap<String, Object>();
				String filePath = config.getValue("shared.network.upload.dir") + "/library/expressions";
				new File(filePath).mkdirs();

				filePath += "/expressionLibrary.json";
				LOG.info("About to save file to " + filePath);
				File file = new File(filePath);
				expressionLibraryDoc.transferTo(file);
				FileInputStream fis = new FileInputStream(file);
				String md5 = DigestUtils.md5Hex(fis);
				fis.close();
				props.put(RDFNamespaces.PREFIX_icpsr + ":containerType", "file");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileContentType ", "");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileName", "expressionLibrary.json");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileLastModified", new Date(System.currentTimeMillis()));
				props.put(RDFNamespaces.PREFIX_icpsr + ":uploadedBy",
						ThreadContextVariables.getSessionUser().getEmail());
				props.put(RDFNamespaces.PREFIX_icpsr + ":MD5", md5);
				props.put(RDFNamespaces.PREFIX_icpsr + ":resourceUUID", UUID.randomUUID().toString());
				props.put(RDFNamespaces.PREFIX_dcterms + ":title", "expressionLibrary.json");
				props.put("a", "pcdm:File");
				props.put(RDFNamespaces.PREFIX_icpsr + ":fileSize", expressionLibraryDoc.getSize());
				fedoraDAO.insertProperties(expressionsFilePath, props);
				fedoraDAO.saveFile(expressionsFilePath + "/binary", file, "expressionLibrary.json");
			}

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			mv.addObject("message", "Error occured while uploading the libraries");
			mv.addObject("status", "Error");
			return mv;
		}

mv.addObject("message", "Libraries uploaded successfully");
return mv;
	}

	private void checkAdmin() {
		if (!ThreadContextVariables.getSessionUser().getSecurityCache().isActivitiGroupMember("c2m-admins")) {
			throw new AccessDeniedException();
		}
	}
}
