package vn.com.luanvan.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.model.UserRole;

@Repository
public class UserRoleDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void add(UserRole userrole) {
		sessionFactory.getCurrentSession().save(userrole);
	}
}
