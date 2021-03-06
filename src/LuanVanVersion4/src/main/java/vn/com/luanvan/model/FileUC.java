package vn.com.luanvan.model;

// default package
// Generated Jan 31, 2015 7:09:10 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * File generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "file")
public class FileUC implements java.io.Serializable {

	private Integer fileid;
	private Usecase usecase;
	private String name;
	private String type;
	private String size;
	private String link;


	public FileUC() {
	}

	public FileUC(Usecase usecase, String filename) {
		this.usecase = usecase;
		this.name = filename;
	}

	public FileUC(Usecase usecase, String filename, String type, String size, String link) {
		this.usecase = usecase;
		this.name = filename;
		this.type = type;
		this.size = size;
		this.link = link;
	}
	public FileUC(Integer fileid, String filename, String type, String size, String link) {
		this.fileid = fileid;
		this.name = filename;
		this.type = type;
		this.size = size;
		this.link = link;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "FILEID", unique = true, nullable = false)
	public Integer getFileid() {
		return this.fileid;
	}

	public void setFileid(Integer fileid) {
		this.fileid = fileid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USECASEID", nullable = false)
	public Usecase getUsecase() {
		return this.usecase;
	}

	public void setUsecase(Usecase usecase) {
		this.usecase = usecase;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String filename) {
		this.name = filename;
	}

	@Column(name = "TYPE")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "SIZE")
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	@Column(name = "LINK")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


}
