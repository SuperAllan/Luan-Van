package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.PhanLoaiChucNangDao;
import vn.com.luanvan.model.Phanloaichucnang;
@Repository
public class PhanLoaiChucNangDaoImpl implements PhanLoaiChucNangDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Phanloaichucnang> getListTenLoai() {
		String hql ="from Phanloaichucnang";
		List<Phanloaichucnang> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Transactional
	public Phanloaichucnang findPhanLoaiByID(Integer id) {
		return (Phanloaichucnang) sessionFactory.getCurrentSession().get(Phanloaichucnang.class, id);
	}
	
}