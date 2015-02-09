package vn.com.luanvan.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.PhanloaiDao;
import vn.com.luanvan.model.Phanloai;

@Repository
public class PhanloaiDaoImpl implements PhanloaiDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void add(Phanloai phanloai) {
		sessionFactory.getCurrentSession().save(phanloai);

	}

	@Transactional
	public void update(Phanloai phanloai) {
		sessionFactory.getCurrentSession().update(phanloai);
	}

	@Transactional
	public void delete(Phanloai phanloai) {
		sessionFactory.getCurrentSession().delete(phanloai);
	}

	@Transactional
	public void getPhanLoaiByUsecaseAndActor(int ucid, int actorid) {
		// TODO Auto-generated method stub

	}
	
	@Transactional
	public void deleteAll() {
		String sql = "delete from Phanloai";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.executeUpdate();
	}

}
