package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Diagram;

public interface DiagramDao {
	
	void save(Diagram diagram);
	
	void update(Diagram diagram);
	
	void delete(Diagram diagram);
	
	List<Diagram> getDiagramByProject(int projectID);
	
	Diagram getDiagramByName(String name, int projectId);

}
