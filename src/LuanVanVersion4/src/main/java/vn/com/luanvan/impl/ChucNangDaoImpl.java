package vn.com.luanvan.impl;

import java.util.List;

import org.hibernate.Query;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.ChucNangDao;
import vn.com.luanvan.model.Chucnang;
import vn.com.luanvan.model.Nhomchucnang;
import vn.com.luanvan.model.Project;
@Repository
public class ChucNangDaoImpl implements ChucNangDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void save(Chucnang chucnang) {
	sessionFactory.getCurrentSession().save(chucnang);	
		
	}

	@Transactional
	public void deleteData(Integer projectid) {
		String hql = "DELETE Chucnang as a where a.project.projectid= :projectid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectid", projectid);
		query.executeUpdate();
	}

	@Transactional
	public List<Chucnang> getChucNangFromData(Project project) {
		String hql = "from Chucnang as a where a.project.projectid = :projectid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectid", project.getProjectid());
		return query.list();
	}

	
	
}