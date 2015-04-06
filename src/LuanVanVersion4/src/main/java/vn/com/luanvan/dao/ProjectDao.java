package vn.com.luanvan.dao;


import java.util.List;

import vn.com.luanvan.model.Project;


public interface ProjectDao{
	
	void add(Project project);
	
	void save(Project project);
	
	void delete(Project project);
	
	boolean checkProjectName(String username, String projectName);
	
	List<Project> getListProject(String username,int status);
	
	Project findProjectByName(String username, String projectName);
	
	Project findProjectByID (int projectID);
}