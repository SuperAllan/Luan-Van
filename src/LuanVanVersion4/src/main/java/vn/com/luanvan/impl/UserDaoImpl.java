package vn.com.luanvan.impl;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username).list();

		if (users.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public User findUserbyUserName(String username) {
		String hql = "FROM User WHERE username= :username";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		return (User) query.list().get(0);
	}
	

	/**
	 * @author phuong
	 */
	@Transactional
	public void save(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	/**
	 * @author phuong
	 */
	@Transactional
	public void add(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	
	@Transactional
	public boolean checkOldPassword (User user, String oldPass){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(encoder.matches(oldPass, user.getPassword())){
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean checkEmailInDatabase(String email) {
		List<User> users = new ArrayList<User>();
		users = sessionFactory.getCurrentSession().createQuery("from User where email=?").setParameter(0, email).list();
		if (users.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public User findUserByIdConfirm(String idconfirm) {
		List<User> user = new ArrayList<User>();
		String hql = "FROM User WHERE idconfirm= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", idconfirm);
		user = query.list();
		return user.get(0);
	}

	@Transactional
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

}