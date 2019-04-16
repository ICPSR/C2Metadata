package edu.umich.icpsr.c2metadata.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;
import org.json.JSONException;


@Entity
@Table(name = "C2M_FILE_T")
@Proxy(lazy = false)
@SequenceGenerator(name = "C2M_FILE_SEQ", sequenceName = "C2M_FILE_SEQ")
public class C2MFile implements Serializable {
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 4634441229756867297L;
	@Id
	@Column(name = "FILE_ID")
	@GeneratedValue(generator="C2M_FILE_SEQ",strategy=GenerationType.AUTO)
	private Integer fileId;
	@Column(name = "JOB_ID")
	private Integer jobId;
	@Column(name = "CREATED_TS")
	private java.sql.Timestamp createdTs;
	@Column(name = "FILE_PATH")
	private String filePath;
	@Column(name = "SCRIPT_FILE_NAME")
	private String scriptFileName;
	@Column(name = "FILE_NAME")
	private String fileName;
	@Column(name = "DDI_FILE_NAME")
	private String ddiFileName;
	@Column(name = "FILE_URL")
	private String fileUrl;
	@Transient
	private String variables;

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {

		this.fileId = fileId;
	}

	public Integer getJobId() {
		return this.jobId;
	}

	public void setJobId(Integer jobId) {

		this.jobId = jobId;
	}

	public java.sql.Timestamp getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(java.sql.Timestamp createdTs) {

		this.createdTs = createdTs;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {

		this.filePath = filePath;
	}

	public String getScriptFileName() {
		return this.scriptFileName;
	}

	public void setScriptFileName(String scriptFileName) {

		this.scriptFileName = scriptFileName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {

		this.fileName = fileName;
	}

	public String getDdiFileName() {
		return this.ddiFileName;
	}

	public void setDdiFileName(String ddiFileName) {

		this.ddiFileName = ddiFileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Map<String,Object> toMap(){
		Map<String,Object>  object= new HashMap<>();
		try{
		object.put("input_file_name", this.scriptFileName);
		object.put("DDI_XML_file", this.fileName);
		object.put("file_name_DDI", this.ddiFileName);
		object.put("variables", this.variables);
		}catch(JSONException e){
			
		}
		return object;
	}

}