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
	public void deleteAll() {
		String sql = "delete from Phanloai";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.executeUpdate();
	}
	
	@Transactional
	public void deleteByUsecaseActor(String ucid, int actorid) {
		String sql = "delete from Phanloai as p where p.actor.actorid = :actorid and p.usecase.usecaseid = :ucid";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("actorid", actorid);
		query.setParameter("ucid", ucid);
		query.executeUpdate();
	}

	@Transactional
	public Phanloai getPhanLoaibyUsecase(String usecaseID) {
		String hql = "FROM Phanloai as a WHERE a.usecase.usecaseid= :usecaseID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("usecaseID", usecaseID);
		return (Phanloai) query.list().get(0);
	}
}
