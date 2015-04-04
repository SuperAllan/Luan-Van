package vn.com.luanvan.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@SuppressWarnings("rawtypes")
@XmlRootElement
public class DocumentExcel{
	private String nameOfWorkSheet;
	private String title;
	private String nameOfProject;
	private List data;
	
	public DocumentExcel(String nameOfWorkSheet, String title,
			String nameOfProject, List data) {
		this.nameOfWorkSheet = nameOfWorkSheet;
		this.title = title;
		this.nameOfProject = nameOfProject;
		this.data = data;
	}
	
	
	public DocumentExcel(String nameOfWorkSheet, String title,
			String nameOfProject) {
		this.nameOfWorkSheet = nameOfWorkSheet;
		this.title = title;
		this.nameOfProject = nameOfProject;
	}
	public String getNameOfWorkSheet() {
		return nameOfWorkSheet;
	}
	public void setNameOfWorkSheet(String nameOfWorkSheet) {
		this.nameOfWorkSheet = nameOfWorkSheet;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNameOfProject() {
		return nameOfProject;
	}
	public void setNameOfProject(String nameOfProject) {
		this.nameOfProject = nameOfProject;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
}