package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.luanvan.dao.NhomChucNangDao;
import vn.com.luanvan.model.Nhomchucnang;
import vn.com.luanvan.model.Project;


public class NhomChucNangDaoImpl implements NhomChucNangDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void save(Nhomchucnang nhom) {
		sessionFactory.getCurrentSession().save(nhom);
	}

	@Transactional
	public List<Nhomchucnang> getListNhomChucNang() {
		String hql = "FROM Nhomchucnang";
		List<Nhomchucnang> lists = sessionFactory.getCurrentSession().createQuery(hql).list();
		return lists;
	}

	@Transactional
	public void delete(Nhomchucnang nhom) {
		sessionFactory.getCurrentSession().delete(nhom);
	}

	@Transactional
	public List<Nhomchucnang> getListNhomChucNang(Project project) {
		String hql = "FROM Nhomchucnang as a where a.project.projectid = :projectid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectid", project.getProjectid());
		return query.list();
	}
	
}