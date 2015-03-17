package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.TrongsonolucDao;
import vn.com.luanvan.model.Trongsonoluc;

@Repository
public class TrongsonolucDaoImpl implements TrongsonolucDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public List<Trongsonoluc> getAll() {
		String hql = "FROM Trongsonoluc";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Transactional
	public Trongsonoluc findByGiaTri(double giaTri) {
		String hql = "FROM Trongsonoluc WHERE giatri= :giaTri";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("giaTri", giaTri);
		return (Trongsonoluc) query.list().get(0);
	}
	
}