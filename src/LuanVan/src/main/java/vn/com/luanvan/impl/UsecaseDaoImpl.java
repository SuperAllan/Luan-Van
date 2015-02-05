package vn.com.luanvan.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.model.Usecase;
@Repository
public class UsecaseDaoImpl implements UsecaseDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void add(Usecase usecase) {
		sessionFactory.getCurrentSession().save(usecase);
	}

	@Transactional
	public void update(Usecase usecase) {
		sessionFactory.getCurrentSession().update(usecase);
	}

	@Transactional
	public void delete(Usecase usecase) {
		sessionFactory.getCurrentSession().delete(usecase);
	}
	
	@Transactional
	public void deleteByProject(int id) {
		String sql = "delete from Usecase as u where u.project.projectid = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@Transactional
	public Usecase getUsecaseById(String id) {
		String sql = "from Usecase as b where b.usecaseid = :usecaseid";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("usecaseid", id);
		return (Usecase)query.list().get(0);
	}
	
}
