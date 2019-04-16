package edu.umich.icpsr.c2metadata.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.umich.icpsr.c2metadata.model.Job;
import edu.umich.icpsr.commons.ThreadContextVariables;
import edu.umich.icpsr.commons.dao.BusinessObjectDAO;
import edu.umich.icpsr.commons.dao.FedoraDAO;
import edu.umich.icpsr.commons.exception.ServerException;
import edu.umich.icpsr.commons.security.model.User;

@Controller
public class DownloadController {

	private static final Logger LOG = Logger.getLogger(DownloadController.class);

	@Autowired
	@Qualifier("archonnexDAO")
	private BusinessObjectDAO archonnexDAO;

	@Autowired
	@Qualifier("fedoraDAO")
	private FedoraDAO fedoraDAO;

	// /c2metadata/download/job/38/codebook
	// /c2metadata/download/job/1/ddi
	
	@RequestMapping("/download/jobs/{jobId}/{mode}/{fileName:.+}")
	public void downloadFile(
			HttpServletRequest req, 
			final HttpServletResponse res, 
			@PathVariable int jobId, 
			@PathVariable String mode, 
			@PathVariable String fileName) throws Exception {
		LOG.info("Executing downloadFile.");
		User user = ThreadContextVariables.getSessionUser();
		if(user==null || user.getEmail()==null || user.getEmail().isEmpty()) {
			LOG.error("Attempt to download but could not obtain user email.");
			throw new ServerException();
		}
		if(!mode.equals("input") && !mode.equals("output")) {
			LOG.error("Variable mode can only be input or output, not " + mode + ".");
			throw new ServerException();
		}
		Job job = archonnexDAO.findById(Job.class, jobId);
		if(job==null) {
			LOG.error("Attempt to download but could not find jobId = " + jobId + ".");
			throw new ServerException();
		}
		if(!user.getEmail().equals(job.getEmail()) && !user.getSecurityCache().isActivitiGroupMember("c2m-admins")) { 
			LOG.error("Attempt to download jobId = " + jobId + " by " + user.getEmail() + " but that's not the owner of the job.");
			throw new ServerException();
		}
		try {
			String binaryPath = "/c2metadata/jobs/" + jobId + "/" + mode + "/" + fileName + "/binary";
			res.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
			fedoraDAO.downloadBinary(binaryPath, res.getOutputStream(), false);
		} catch (Exception e) {
			LOG.error("", e);
			throw new ServerException();
		}
	}
	@RequestMapping("/download/jobs/{jobId}/{fileName:.+}")
	public void downloadFilewithPath(
			HttpServletRequest req, 
			final HttpServletResponse res, 
			@PathVariable int jobId,
			@RequestParam String path,
			@PathVariable String fileName) throws Exception {
		LOG.info("Executing downloadFile.");
		User user = ThreadContextVariables.getSessionUser();
		if(user==null || user.getEmail()==null || user.getEmail().isEmpty()) {
			LOG.error("Attempt to download but could not obtain user email.");
			throw new ServerException();
		}
		
		Job job = archonnexDAO.findById(Job.class, jobId);
		if(job==null) {
			LOG.error("Attempt to download but could not find jobId = " + jobId + ".");
			throw new ServerException();
		}
		if(!user.getEmail().equals(job.getEmail()) && !user.getSecurityCache().isActivitiGroupMember("c2m-admins")) { 
			LOG.error("Attempt to download jobId = " + jobId + " by " + user.getEmail() + " but that's not the owner of the job.");
			throw new ServerException();
		}
		try {
			String binaryPath = path + "/binary";
			res.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
			fedoraDAO.downloadBinary(binaryPath, res.getOutputStream(), false);
		} catch (Exception e) {
			LOG.error("", e);
			throw new ServerException();
		}
	}
}
