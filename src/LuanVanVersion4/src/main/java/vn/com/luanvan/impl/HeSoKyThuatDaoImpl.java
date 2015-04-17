package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.HeSoKyThuatDao;
import vn.com.luanvan.model.Hesokythuat;
@Repository
public class HeSoKyThuatDaoImpl implements HeSoKyThuatDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Hesokythuat> getListHeSoKyThuat() {
		String hql = "FROM Hesokythuat";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
}