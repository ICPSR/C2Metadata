package edu.umich.icpsr.c2metadata.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "C2M_TASKS_T")
@SequenceGenerator(name = "C2M_TASKS_SEQ", sequenceName = "C2M_TASKS_SEQ")
@Proxy(lazy = false)
public class Task {

    @Id
	@GeneratedValue(generator = "C2M_TASKS_SEQ", strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	@Column(name = "JOB_ID")
    private int jobId;
	@Column(name = "START_DATE")
    private Timestamp startDate;
	@Column(name = "END_DATE")
    private Timestamp endDate;
	@Column(name = "ERROR")
    private String error;
	@Column(name = "OUTPUT_TYPE")
    private String outputType;

	public Task() {}

	public Task(int jobId) {
		this.jobId = jobId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
	
}
