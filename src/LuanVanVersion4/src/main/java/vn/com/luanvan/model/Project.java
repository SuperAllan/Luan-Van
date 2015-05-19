package vn.com.luanvan.model;

// default package
// Generated Jan 31, 2015 7:09:10 PM by Hibernate Tools 4.3.1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Project generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "project")
public class Project implements java.io.Serializable {

	private Integer projectid;
	private User user;
	private int trangthai;
	private Date ngaytao;
	private String tenproject;
	private String motaproject;
	private int luongcoban;
	private Trongsonoluc trongsonoluc;
	private Luong luong;
	private Set<Phichucnang> phichucnangs = new HashSet<Phichucnang>(0);
	private Set<Xephangmoitruong> xephangmoitruong = new HashSet<Xephangmoitruong>(0);
	private Set<Nhomchucnang> nhomchucnangs = new HashSet<Nhomchucnang>(0);
	private Set<Nhomuc> nhomucs = new HashSet<Nhomuc>(0);
	private Set<Actor> actors = new HashSet<Actor>(0);
	private Set<Xephangkythuat> xephangkythuats = new HashSet<Xephangkythuat>(0);
	private Set<Giatriluong> giatriluongs = new HashSet<Giatriluong>(0);

	public Project() {
	}

	public Project( User user, int trangthai, Date ngaytao,
			String tenproject, int luongcoban, Trongsonoluc trongsonoluc, Luong luong) {
		this.user = user;
		this.trangthai = trangthai;
		this.ngaytao = ngaytao;
		this.tenproject = tenproject;
		this.luongcoban = luongcoban;
		this.trongsonoluc = trongsonoluc;
		this.luong = luong;
	}

	public Project( User user, int trangthai, Date ngaytao, int luongcoban, Trongsonoluc trongsonoluc, Luong luong,
			String tenproject, Set<Phichucnang> phichucnangs, Set<Xephangmoitruong> xephangnhoms,
			Set<Actor> actors,Set<Xephangkythuat> xephangkythuats) {
		this.user = user;
		this.trangthai = trangthai;
		this.ngaytao = ngaytao;
		this.tenproject = tenproject;
		this.phichucnangs = phichucnangs;
		this.xephangmoitruong = xephangnhoms;
		this.xephangkythuats = xephangkythuats;
		this.luongcoban = luongcoban;
		this.trongsonoluc = trongsonoluc;
		this.luong = luong;
		this.actors = actors;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PROJECTID", unique = true, nullable = false)
	public Integer getProjectid() {
		return this.projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "TRANGTHAI", nullable = false)
	public int getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAYTAO", nullable = false, length = 10)
	public Date getNgaytao() {
		return this.ngaytao;
	}

	public void setNgaytao(Date ngaytao) {
		this.ngaytao = ngaytao;
	}

	@Column(name = "TENPROJECT", nullable = false)
	public String getTenproject() {
		return this.tenproject;
	}

	public void setTenproject(String tenproject) {
		this.tenproject = tenproject;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Phichucnang> getPhichucnangs() {
		return this.phichucnangs;
	}

	public void setPhichucnangs(Set<Phichucnang> phichucnangs) {
		this.phichucnangs = phichucnangs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.REMOVE)
	public Set<Xephangmoitruong> getXephangnhoms() {
		return this.xephangmoitruong;
	}

	public void setXephangnhoms(Set<Xephangmoitruong> xephangnhoms) {
		this.xephangmoitruong = xephangnhoms;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Nhomchucnang> getNhomchucnangs() {
		return this.nhomchucnangs;
	}

	public void setNhomchucnangs(Set<Nhomchucnang> chucnangs) {
		this.nhomchucnangs = chucnangs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.REMOVE)
	public Set<Actor> getActors() {
		return this.actors;
	}

	public void setActors(Set<Actor> Actors) {
		this.actors = Actors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.REMOVE)
	public Set<Xephangkythuat> getXephangkythuats() {
		return this.xephangkythuats;
	}

	public void setXephangkythuats(Set<Xephangkythuat> xephangkythuats) {
		this.xephangkythuats = xephangkythuats;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.REMOVE)
	public Set<Giatriluong> getGiatriluongs() {
		return this.giatriluongs;
	}

	public void setGiatriluongs(Set<Giatriluong> giatriluongs) {
		this.giatriluongs = giatriluongs;
	}

	@Column(name = "MOTAPROJECT")
	public String getMotaproject() {
		return this.motaproject;
	}

	public void setMotaproject(String mota) {
		this.motaproject = mota;
	}
	
	@Column(name = "LUONGCOBAN", nullable=true)
	public int getLuongcoban() {
		return this.luongcoban;
	}

	public void setLuongcoban(int luong) {
		this.luongcoban = luong;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TRONGSOID")
	public Trongsonoluc getTrongsonoluc() {
		return this.trongsonoluc;
	}

	public void setTrongsonoluc(Trongsonoluc trongso) {
		this.trongsonoluc = trongso;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LUONGID")
	public Luong getLuong() {
		return this.luong;
	}

	public void setLuong(Luong luong) {
		this.luong = luong;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Nhomuc> getNhomucs() {
		return this.nhomucs;
	}

	public void setNhomucs(Set<Nhomuc> usecase) {
		this.nhomucs = usecase;
	}
}
