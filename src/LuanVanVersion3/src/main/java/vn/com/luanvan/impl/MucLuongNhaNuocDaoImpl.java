package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.MucLuongNhaNuocDao;
import vn.com.luanvan.model.Mucluongnhanuoc;

@Repository
public class MucLuongNhaNuocDaoImpl implements MucLuongNhaNuocDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List<Mucluongnhanuoc> getList() {
		String hql = " FROM Mucluongnhanuoc";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
}