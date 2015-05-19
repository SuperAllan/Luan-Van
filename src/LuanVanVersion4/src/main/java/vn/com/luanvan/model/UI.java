package vn.com.luanvan.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ui")
public class UI {
	private int uiid;
	private String nameui;
	private String path;
	private String image;
	private Project project;
	
	public UI() {}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "uiid", unique = true, nullable = false)
	public int getUiid() {
		return uiid;
	}
	public void setUiid(int uiid) {
		this.uiid = uiid;
	}
	@Column(name = "nameui")
	public String getNameui() {
		return nameui;
	}
	public void setNameui(String nameui) {
		this.nameui = nameui;
	}
	@Column(name = "path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Column(name = "image")
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECTID", nullable = false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
