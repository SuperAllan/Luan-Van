package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.HeSoMoiTruongDao;
import vn.com.luanvan.model.Hesomoitruong;
@Repository
public class HeSoMoiTruongDaoImpl implements HeSoMoiTruongDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List<Hesomoitruong> getListHeSoMoiTruong() {
		String hql = "FROM Hesomoitruong";
		List<Hesomoitruong> lists = sessionFactory.getCurrentSession().createQuery(hql).list();
		return lists;
	}
	
}