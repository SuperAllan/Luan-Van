package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.UIUsecaseDao;
import vn.com.luanvan.model.UIUsecase;
@Repository
public class UIUsecaseDaoImpl implements UIUsecaseDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void save(UIUsecase uiUsecase) {
		sessionFactory.getCurrentSession().save(uiUsecase);
	}

	@Transactional
	public void delete(UIUsecase uiUsecase) {
		sessionFactory.getCurrentSession().delete(uiUsecase);

	}

	@Transactional
	public void update(UIUsecase uiUsecase) {
		sessionFactory.getCurrentSession().update(uiUsecase);

	}
	
	@Transactional
	public UIUsecase getUIUsecaseByUIIDUsecseID(int uiid, int usecaseid) {
		String hql = "from UIUsecase as u where u.ui.uiid = :uiid and u.usecase.usecaseid = :usecaseid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("uiid", uiid);
		query.setParameter("usecaseid", usecaseid);
		if (query.list().size() > 0) {
			return (UIUsecase)query.list().get(0);
		} else {
			return null;
		}
	}
	@Transactional
	public void deleteAllByID(int uiid) {
		String hql = "delete from UIUsecase as u where u.ui.uiid = :uiid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("uiid", uiid);
		query.executeUpdate();
	}
	
	@Transactional
	public boolean hasUsecaseIDOfUIID(int usecaseID, int uiID) {
		String hql = "from UIUsecase as u where u.usecase.usecaseid = :usecaseID and u.ui.uiid = :uiID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("usecaseID", usecaseID);
		query.setParameter("uiID", uiID);
		if (query.list().size() > 0) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<UIUsecase> getUIUsecaseByUsecaseID(int usecaseid) {
		String hql = "from UIUsecase as u where u.usecase.usecaseid = :usecaseid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("usecaseid", usecaseid);
		return query.list();
	}
}
