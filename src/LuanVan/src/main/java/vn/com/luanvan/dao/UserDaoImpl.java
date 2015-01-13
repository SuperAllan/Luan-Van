package vn.com.luanvan.dao;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
    private JavaMailSender mailSender;
	
	    
	@SuppressWarnings("unchecked")
	@Transactional
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	/**
	 * @author lonel_000
	 */
	@Transactional
	public void save(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	/**
	 * @author lonel_000
	 */
	@Transactional
	public void add(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	@Transactional
	public final User findUserbyUserName(String username) {
		return (User) sessionFactory.getCurrentSession()
				.get(User.class, username);
	}
	
	@Transactional
	public void sendMail(User user, String pass){
		SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Đăng ký tài khoản");
        email.setText("Xin chào bạn đã đăng ký thành công tài khoản \n"+pass);
        // sends the e-mail
        mailSender.send(email);
	}
	
	@Transactional
	public boolean checkOldPassword (User user, String oldPass){
		if(oldPass.equals(user.getPassword())){
			return true;
		}
		return false;
	}

	
	

	
}