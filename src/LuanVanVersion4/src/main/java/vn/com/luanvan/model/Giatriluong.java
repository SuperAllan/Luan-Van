package vn.com.luanvan.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "giatriluong")
public class Giatriluong implements java.io.Serializable {

	private GiatriluongId id;
	private Luong luong;
	private Project project;
	private int luongtangthem;
	private int pckhuvuc;
	private int pcluudong;

	public Giatriluong() {
	}

	public Giatriluong(GiatriluongId id, Luong luong,
			Project project, int luongtangthem, int pckhuvuc, int pcluudong) {
		this.id = id;
		this.luong = luong;
		this.project = project;
		this.luongtangthem = luongtangthem;
		this.pckhuvuc = pckhuvuc;
		this.pcluudong = pcluudong;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectid", column = @Column(name = "PROJECTID", nullable = false)),
			@AttributeOverride(name = "luongid", column = @Column(name = "LUONGID", nullable = false)) })
	public GiatriluongId getId() {
		return this.id;
	}

	public void setId(GiatriluongId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LUONGID", nullable = false, insertable = false, updatable = false)
	public Luong getLuong() {
		return this.luong;
	}

	public void setLuong(Luong luong) {
		this.luong = luong;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECTID", nullable = false, insertable = false, updatable = false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "LUONGTANGTHEM")
	public int getLuongtangthem() {
		return this.luongtangthem;
	}

	public void setLuongtangthem(int luongtangthem) {
		this.luongtangthem = luongtangthem;
	}
	@Column(name = "PCKHUVUC")
	public int getPckhuvuc() {
		return pckhuvuc;
	}

	public void setPckhuvuc(int pckhuvuc) {
		this.pckhuvuc = pckhuvuc;
	}
	@Column(name = "PCLUUDONG")
	public int getPcluudong() {
		return pcluudong;
	}

	public void setPcluudong(int pcluudong) {
		this.pcluudong = pcluudong;
	}

	

	

}
