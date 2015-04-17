package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.DiagramDao;
import vn.com.luanvan.model.Diagram;

@Repository
public class DiagramDaoImpl implements DiagramDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void save(Diagram diagram) {
		sessionFactory.getCurrentSession().save(diagram);
	}

	@Transactional
	public void update(Diagram diagram) {
		sessionFactory.getCurrentSession().update(diagram);
	}

	@Transactional
	public void delete(Diagram diagram) {
		sessionFactory.getCurrentSession().delete(diagram);

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Diagram> getDiagramByProject(int projectID) {
		String hql = "from Diagram as d where d.project.projectid = :projectID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectID", projectID);
		return query.list();
	}

	@Transactional
	public Diagram getDiagramByName(String name, int projectID) {
		String hql = "from Diagram as d where d.namediagram = :namediagram and d.project.projectid = :projectID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("namediagram", name);
		query.setParameter("projectID", projectID);
		if (query.list().size() > 0) {
			return (Diagram)query.list().get(0);
		}
		return null;
	}

}
