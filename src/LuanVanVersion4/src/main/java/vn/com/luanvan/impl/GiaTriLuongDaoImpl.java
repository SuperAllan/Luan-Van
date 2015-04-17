package vn.com.luanvan.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.GiaTriLuongDao;
import vn.com.luanvan.model.Giatriluong;

@Repository
public class GiaTriLuongDaoImpl implements GiaTriLuongDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void delete(Integer projectid) {
		String hql = "DELETE FROM Giatriluong as a where a.project.projectid = :projectid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectid", projectid);
		query.executeUpdate();
	}

	@Transactional
	public void save(Giatriluong giatri) {
		sessionFactory.getCurrentSession().save(giatri);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Giatriluong> getListGiaTriLuong(Integer projectid) {
			String hql = "FROM Giatriluong as a where a.project.projectid = :projectid";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("projectid", projectid);
			return query.list();
	}
	
}