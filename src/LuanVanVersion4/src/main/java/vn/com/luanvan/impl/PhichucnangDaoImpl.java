package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.PhichucnangDao;
import vn.com.luanvan.model.Phichucnang;

@Repository
public class PhichucnangDaoImpl implements PhichucnangDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void delete(Phichucnang phichucnang) {
		sessionFactory.getCurrentSession().delete(phichucnang);
	}

	@Transactional
	public void save(Phichucnang phichucnang) {
		sessionFactory.getCurrentSession().save(phichucnang);
	}
	
	@Transactional
	public void update(Phichucnang phichucnang) {
		sessionFactory.getCurrentSession().update(phichucnang);
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Phichucnang> getListPhiChucNangByProjectID(int projectID) {
		String hql = "FROM Phichucnang WHERE project.projectid= :ID order by motayeucau";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("ID", projectID);
		return query.list();
	}

	@Transactional
	public void deleteAll(int projectID) {
		String hql = "DELETE Phichucnang WHERE project.projectid= :ID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("ID", projectID);
		query.executeUpdate();
		
	}

	@Transactional
	public Phichucnang findByID(int id) {
		return (Phichucnang) sessionFactory.getCurrentSession().get(Phichucnang.class, id);
	}

	@Transactional
	public Phichucnang fintByProjectID(int projectID, String motayeucau) {
		String hql = "FROM Phichucnang WHERE project.projectid= :ID and motayeucau= :mota";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("ID", projectID);
		query.setParameter("mota", motayeucau);
		return (Phichucnang) query.list().get(0);
	}


	

}
