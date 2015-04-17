package vn.com.luanvan.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.UIDao;
import vn.com.luanvan.model.UI;

@Repository
public class UIDaoImpl implements UIDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void add(UI ui) {
		sessionFactory.getCurrentSession().save(ui);
	}

	@Transactional
	public void update(UI ui) {
		sessionFactory.getCurrentSession().update(ui);
	}

	@Transactional
	public void delete(UI ui) {
		sessionFactory.getCurrentSession().delete(ui);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<UI> getUIByProject(int projectID) {
		String sql = "from UI as a where a.project.projectid = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", projectID);
		return (List<UI>)query.list();
	}
	
	@Transactional
	public UI getUIByName(String nameui, int projectid) {
		String sql = "from UI as b where b.nameui = :nameui and b.project.projectid = :projectid";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("nameui", nameui);
		query.setParameter("projectid", projectid);
		if (query.list().size() > 0) {
			return (UI)query.list().get(0);
		}
		return null;
	}
	
}
