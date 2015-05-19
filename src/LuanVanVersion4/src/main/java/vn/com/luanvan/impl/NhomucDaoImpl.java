package vn.com.luanvan.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.NhomucDao;
import vn.com.luanvan.model.Nhomuc;

@Repository
public class NhomucDaoImpl implements NhomucDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void add(Nhomuc nhomuc) {
		sessionFactory.getCurrentSession().save(nhomuc);
	}

	@Transactional
	public void update(Nhomuc nhomuc) {
		sessionFactory.getCurrentSession().update(nhomuc);

	}

	@Transactional
	public void delete(Nhomuc nhomuc) {
		sessionFactory.getCurrentSession().delete(nhomuc);
	}

	@Transactional
	public void deleteByProject(int id) {
		String sql = "delete from Nhomuc as a where a.project.projectid = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@Transactional
	public boolean hasNhomuc(String tenNhom, int projectId) {
		String sql = "from Nhomuc as a where a.tennhom = :tenNhom and a.project.projectid= :projectId";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("tenNhom", tenNhom);
		query.setParameter("projectId", projectId);
		if (query.list().size() > 0) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public Nhomuc getNhomucByName(String tenNhom, int projectId) {
		String sql = "from Nhomuc as a where a.tennhom = :tenNhom and a.project.projectid= :projectId";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("tenNhom", tenNhom);
		query.setParameter("projectId", projectId);
		return (Nhomuc)query.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Nhomuc> getNhomucByProject(int projectId) {
		String sql = "from Nhomuc as a where a.project.projectid = :projectId order by tennhom";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("projectId", projectId);
		return query.list();
	}

	@Transactional
	public Nhomuc getNhomucById(int nhomUCId) {
		String hql = "from Nhomuc as a where a.nhomucid = :nhomUCId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nhomUCId", nhomUCId);
		return (Nhomuc) query.list().get(0);
	}
}
