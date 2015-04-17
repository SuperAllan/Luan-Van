package vn.com.luanvan.impl;

import java.util.List;

import org.hibernate.Query;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.ChucNangDao;
import vn.com.luanvan.model.Chucnang;
import vn.com.luanvan.model.Project;
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
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Chucnang> getChucNangFromData(Project project) {
		String hql = "from Chucnang as a where a.project.projectid = :projectid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectid", project.getProjectid());
		return query.list();
	}

	@Transactional
	public Chucnang findChucNangByID(int id) {
		return (Chucnang) sessionFactory.getCurrentSession().get(Chucnang.class, id);
	}

}