package vn.com.luanvan.dao;

import vn.com.luanvan.model.DiagramActor;

public interface DiagramActorDao {
	void save(DiagramActor diagramActor);
	void update(DiagramActor diagramActor);
	void delete(DiagramActor diagramActor);
	DiagramActor getDiagramActorByID (int diagramID, int actorID);
}
