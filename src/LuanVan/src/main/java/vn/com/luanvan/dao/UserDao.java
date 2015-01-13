package vn.com.luanvan.dao;


import vn.com.luanvan.model.User;

public interface UserDao {

	User findByUserName(String username);

	/**
	 * @author lonel_000
	 * @param user
	 */
	void save(User user);

	void add(User user);

	User findUserbyUserName(String username);
	
	void sendMail (User user, String pass);

	boolean checkOldPassword (User user, String oldPass);
}