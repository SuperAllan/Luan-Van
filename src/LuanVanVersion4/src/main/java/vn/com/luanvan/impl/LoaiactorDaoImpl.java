package vn.com.luanvan.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.LoaiactorDao;
import vn.com.luanvan.model.Loaiactor;
@Repository
public class LoaiactorDaoImpl implements LoaiactorDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public Loaiactor getLoaiactorById(int loaiActorID) {
		String sql = "from Loaiactor as b where b.loaiactorid = :loaiactorid";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("loaiactorid", loaiActorID);
		return (Loaiactor)query.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Loaiactor> getAll() {
		String sql = "from Loaiactor";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		return (List<Loaiactor>)query.list();
	}
}
