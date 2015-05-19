package vn.com.luanvan.impl;


import org.hibernate.Query;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.ChucNangDao;
import vn.com.luanvan.model.Chucnang;
@Repository
public class ChucNangDaoImpl implements ChucNangDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Chucnang chucnang) {
	sessionFactory.getCurrentSession().save(chucnang);	
		
	}

	@Transactional
	public void update(Chucnang chucnang) {
		sessionFactory.getCurrentSession().update(chucnang);
		
	}

	@Transactional
	public void delete(Chucnang chucnang) {
		sessionFactory.getCurrentSession().delete(chucnang);
	}
	
	@Transactional
	public Chucnang findChucNangByID(int id) {
		return (Chucnang) sessionFactory.getCurrentSession().get(Chucnang.class, id);
	}

	@Transactional
	public Chucnang findByNhomID(int nhomid, String mota) {
		String hql = "FROM Chucnang as a where a.nhomchucnang.nhomid= :nhomid and a.motayeucau= :mota";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nhomid", nhomid);
		query.setParameter("mota", mota);
		return (Chucnang) query.list().get(0);
	}

}