package vn.com.luanvan.model;

// default package
// Generated Jan 31, 2015 7:09:10 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Bmt generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "bmt")
public class Bmt implements java.io.Serializable {

	private Integer bmtid;
	private String ten;
	private Integer trongso;
	private float hesobmt;
	private Set<Usecase> usecases = new HashSet<Usecase>(0);

	public Bmt() {
	}

	public Bmt(String ten, Integer trongso, Integer hesobmt, Set<Usecase> usecases) {
		this.ten = ten;
		this.trongso = trongso;
		this.hesobmt = hesobmt;
		this.usecases = usecases;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "BMTID", unique = true, nullable = false)
	public Integer getBmtid() {
		return this.bmtid;
	}

	public void setBmtid(Integer bmtid) {
		this.bmtid = bmtid;
	}

	@Column(name = "TEN", length = 100)
	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	@Column(name = "TRONGSO")
	public Integer getTrongso() {
		return this.trongso;
	}

	public void setTrongso(Integer trongso) {
		this.trongso = trongso;
	}

	@Column(name = "HESOBMT")
	public float getHesobmt() {
		return this.hesobmt;
	}

	public void setHesobmt(float hesobmt) {
		this.hesobmt = hesobmt;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bmt")
	public Set<Usecase> getUsecases() {
		return this.usecases;
	}

	public void setUsecases(Set<Usecase> usecases) {
		this.usecases = usecases;
	}

}
