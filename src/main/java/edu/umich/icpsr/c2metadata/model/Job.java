package edu.umich.icpsr.c2metadata.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "C2M_JOBS_T")
@SequenceGenerator(name = "C2M_JOBS_SEQ", sequenceName = "C2M_JOBS_SEQ")
@Proxy(lazy = false)
public class Job {

    @Id
	@GeneratedValue(generator = "C2M_JOBS_SEQ", strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	@Column(name = "EMAIL")
    private String email;
	@Column(name = "START_DATE")
    private Timestamp startDate;
	@Column(name = "END_DATE")
    private Timestamp endDate;
	@Column(name = "ERROR")
    private String error;
	@Transient
	private List<Task> tasks;
	@OneToMany(mappedBy="jobId",fetch=FetchType.EAGER)
    private List<C2MFile> files;
    
	public Job() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<C2MFile> getFiles() {
		return files;
	}

	public void setFiles(List<C2MFile> files) {
		this.files = files;
	}
	
	
}
