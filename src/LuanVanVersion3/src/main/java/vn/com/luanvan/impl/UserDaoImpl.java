package vn.com.luanvan.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
    private JavaMailSender mailSender;
	
	    
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
	public final User findUserbyUserName(String username) {
		return (User) sessionFactory.getCurrentSession()
				.get(User.class, username);
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
	public void sendMail(User user){
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			msg.addRecipients(RecipientType.TO, user.getEmail());
			msg.setSubject("Đăng ký tài khoản", "UTF-8");
			msg.setContent("<h3>Xin chào bạn đã đăng ký thành công tài khoản</h3> <br>"
	        		+ "<h3>Tài khoản: "+user.getUsername() +"</h3><br>"
	        		+"<a href=\"http://localhost:8080/luanvan/confirm/id="+user.getIdconfirm()+"\">Nhấn vào đây để kích hoạt</a>", "text/html; charset=UTF-8");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        // sends the e-mail
        mailSender.send(msg);
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

	@Transactional
	public User findUserByIdConfirm(String idconfirm) {
		List<User> user = new ArrayList<User>();
		String hql = "FROM User WHERE idconfirm= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", idconfirm);
		user = query.list();
		return user.get(0);
	}

}