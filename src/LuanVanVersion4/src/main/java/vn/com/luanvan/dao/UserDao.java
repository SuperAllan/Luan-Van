package vn.com.luanvan.dao;


import vn.com.luanvan.model.User;

public interface UserDao {

	boolean findByUserName(String username);

	void save(User user);

	void add(User user);

	User findUserbyUserName(String username);
	
	void sendMail (User user);

	boolean checkOldPassword (User user, String oldPass);
	
	boolean checkEmailInDatabase(String email);
	
	User findUserByIdConfirm(String idconfirm);
	
	void delete(User user);
}