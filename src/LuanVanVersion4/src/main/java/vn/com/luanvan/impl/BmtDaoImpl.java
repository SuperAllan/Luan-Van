package vn.com.luanvan.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.BmtDao;
import vn.com.luanvan.model.Bmt;

@Repository
public class BmtDaoImpl implements BmtDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public Bmt getBmtById(int bmtID) {
		String sql = "from Bmt as b where b.bmtid = :bmtid";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("bmtid", bmtID);
		return (Bmt)query.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Bmt> getAll() {
		String sql = "from Bmt";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		return (List<Bmt>)query.list();
	}

}
