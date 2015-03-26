package vn.com.luanvan.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "diagram", catalog = "luanvan")
public class Diagram {
	private int diagramid;
	private String namediagram;
	private String path;
	private String image;
	private Project project;
	private Set<DiagramActor> diagramActors = new HashSet<DiagramActor>(0);
	
	public Diagram(){}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "diagramid", unique = true, nullable = false)
	public int getDiagramid() {
		return diagramid;
	}
	public void setDiagramid(int diagramid) {
		this.diagramid = diagramid;
	}
	
	@Column(name = "namediagram")
	public String getNamediagram() {
		return namediagram;
	}
	public void setNamediagram(String namediagram) {
		this.namediagram = namediagram;
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
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "diagram", cascade = CascadeType.REMOVE)
	public Set<DiagramActor> getDiagramActors() {
		return diagramActors;
	}
	public void setDiagramActors(Set<DiagramActor> diagramActors) {
		this.diagramActors = diagramActors;
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
