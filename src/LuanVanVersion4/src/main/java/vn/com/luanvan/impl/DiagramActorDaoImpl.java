package vn.com.luanvan.impl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.DiagramActorDao;
import vn.com.luanvan.model.Actor;
import vn.com.luanvan.model.Diagram;
import vn.com.luanvan.model.DiagramActor;
@Repository
public class DiagramActorDaoImpl implements DiagramActorDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void save(DiagramActor diagramActor) {
		sessionFactory.getCurrentSession().save(diagramActor);

	}

	@Transactional
	public void update(DiagramActor diagramActor) {
		sessionFactory.getCurrentSession().update(diagramActor);

	}

	@Transactional
	public void delete(DiagramActor diagramActor) {
		sessionFactory.getCurrentSession().delete(diagramActor);

	}
	
	@Transactional
	public DiagramActor getDiagramActorByID(int diagramID, int actorID) {
		String hql = "from DiagramActor as da where da.diagram.diagramid = :diagramID and da.actor.actorid = :actorID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("diagramID", diagramID);
		query.setParameter("actorID", actorID);
		if (query.list().size() > 0) {
			return (DiagramActor)query.list().get(0);
		}
		return null;
	}

}
