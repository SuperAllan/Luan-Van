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

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Actor generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "actor")
public class Actor implements java.io.Serializable {

	private Integer actorid;
	private Loaiactor loaiactor;
	private String motaactor;
	private String nameofactor;
	private Project project;
	private Set<Phanloai> phanloais = new HashSet<Phanloai>(0);

	public Actor() {
	}

	public Actor(Loaiactor loaiactor, String nameofactor) {
		this.loaiactor = loaiactor;
		this.nameofactor = nameofactor;
	}

	public Actor(Loaiactor loaiactor, String motamucdo,
			String nameofactor, Set<Phanloai> phanloais) {
		this.loaiactor = loaiactor;
		this.motaactor = motamucdo;
		this.nameofactor = nameofactor;
		this.phanloais = phanloais;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ACTORID", unique = true, nullable = false)
	public Integer getActorid() {
		return this.actorid;
	}

	public void setActorid(Integer actorid) {
		this.actorid = actorid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOAIACTORID", nullable = false)
	public Loaiactor getLoaiactor() {
		return this.loaiactor;
	}

	public void setLoaiactor(Loaiactor loaiactor) {
		this.loaiactor = loaiactor;
	}

	@Column(name = "MOTAACTOR")
	public String getMotaactor() {
		return this.motaactor;
	}

	public void setMotaactor(String motaactor) {
		this.motaactor = motaactor;
	}

	@Column(name = "NAMEOFACTOR", nullable = false)
	public String getNameofactor() {
		return this.nameofactor;
	}

	public void setNameofactor(String nameofactor) {
		this.nameofactor = nameofactor;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "actor", cascade = CascadeType.REMOVE)
	public Set<Phanloai> getPhanloais() {
		return this.phanloais;
	}

	public void setPhanloais(Set<Phanloai> phanloais) {
		this.phanloais = phanloais;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECTID", nullable = false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
