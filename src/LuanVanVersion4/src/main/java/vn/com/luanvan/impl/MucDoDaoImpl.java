package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.MucDoDao;
import vn.com.luanvan.model.Mucdo;
@Repository
public class MucDoDaoImpl implements MucDoDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	
	public List<Mucdo> getListMucDo() {
		String hql="from Mucdo";
		List<Mucdo> lists = sessionFactory.getCurrentSession().createQuery(hql).list();
		return lists;
	}

	@Transactional
	public Mucdo findMucDoByID(Integer id) {
		return (Mucdo) sessionFactory.getCurrentSession().get(Mucdo.class, id);
	}
	
}