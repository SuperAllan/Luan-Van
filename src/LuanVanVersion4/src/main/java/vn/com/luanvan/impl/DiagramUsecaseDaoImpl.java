package vn.com.luanvan.impl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.DiagramUsecaseDao;
import vn.com.luanvan.model.DiagramUsecase;
@Repository
public class DiagramUsecaseDaoImpl implements DiagramUsecaseDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void save(DiagramUsecase diagramUsecase) {
		sessionFactory.getCurrentSession().save(diagramUsecase);

	}

	@Transactional
	public void update(DiagramUsecase diagramUsecase) {
		sessionFactory.getCurrentSession().save(diagramUsecase);

	}

	@Transactional
	public void delete(DiagramUsecase diagramUsecase) {
		sessionFactory.getCurrentSession().save(diagramUsecase);

	}
	
	@Transactional
	public DiagramUsecase getDiagramUsecaseByID(int diagramID, int usecaseID) {
		String hql = "from DiagramUsecase as da where da.diagram.diagramid = :diagramID and da.usecase.usecaseid = :usecaseID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("diagramID", diagramID);
		query.setParameter("usecaseID", usecaseID);
		if (query.list().size() > 0) {
			return (DiagramUsecase)query.list().get(0);
		}
		return null;
	}

}
