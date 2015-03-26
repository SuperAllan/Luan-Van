package vn.com.luanvan.dao;

import vn.com.luanvan.model.DiagramUsecase;

public interface DiagramUsecaseDao {
	void save(DiagramUsecase diagramUsecase);
	void update(DiagramUsecase diagramUsecase);
	void delete(DiagramUsecase diagramUsecase);
	
	DiagramUsecase getDiagramUsecaseByID(int diagramID, int usecaseID);
}
