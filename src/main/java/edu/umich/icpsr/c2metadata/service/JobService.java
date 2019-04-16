package edu.umich.icpsr.c2metadata.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import edu.umich.icpsr.c2metadata.model.Job;
import edu.umich.icpsr.c2metadata.model.Task;
import edu.umich.icpsr.commons.dao.BusinessObjectDAO;
import edu.umich.icpsr.commons.dao.FedoraDAO;
import edu.umich.icpsr.commons.dao.FusekiDAO;
import edu.umich.icpsr.commons.model.FileResource;

public class JobService {

	private static final Logger LOG = Logger.getLogger(JobService.class);

	@Autowired
	@Qualifier("archonnexDAO")
	private BusinessObjectDAO archonnexDAO;

	@Autowired
	@Qualifier("fedoraDAO")
	private FedoraDAO fedoraDAO;

	@Autowired
	@Qualifier("fusekiDAO")
	private FusekiDAO fusekiDAO;

	public Job createJob(String email) {
		LOG.info("Executing createJob.");
		Job job = new Job();
		job.setStartDate(new Timestamp(System.currentTimeMillis()));
		job.setEmail(email); 
		archonnexDAO.save(job);
		LOG.info("Created job " + job.getId() + ".");
		LOG.debug("Preparing to create directories.");
		fedoraDAO.saveDirectory("/c2metadata/jobs/" + String.valueOf(job.getId()));
		fedoraDAO.saveDirectory("/c2metadata/jobs/" + String.valueOf(job.getId()) + "/input");
		fedoraDAO.saveDirectory("/c2metadata/jobs/" + String.valueOf(job.getId()) + "/output");
		LOG.debug("Directories built.");
		return job;
	}
	
	public Task createTask(int jobId){
		LOG.info("Executing createTask.");
		Task task = new Task(jobId);
		task.setStartDate(new Timestamp(System.currentTimeMillis()));
		task.setJobId(jobId);
		archonnexDAO.save(task);
		return task;
	}
	
	public List<FileResource> retrieveFileListFromFuseki(int jobId, String mode){
		Job job = archonnexDAO.findById(Job.class, jobId);
		if(job!=null) {
			return retrieveFileListFromFuseki(job,mode);
		} else {
			return null;
		}
	}	
		
	public List<FileResource> retrieveFileListFromFuseki(Job job, String mode){
		LOG.info("Execute retrieveFileListFromFuseki.");
		if(!mode.equals("input") && !mode.equals("output")) {
			LOG.error("Variable mode must be either input or output, not " + mode + ".");
			return null;
		}
		try {
			String uri = fedoraDAO.getFedoraUrl() + "/c2metadata/jobs/" + job.getId() + "/" + mode;
			List<FileResource> fileResources = fusekiDAO.getFileResources(uri, null, null, null, false);
			return fileResources;
		} catch(Exception e) {
			LOG.error(e.getMessage(),e);
			return null;
		}
	}	
	
}
