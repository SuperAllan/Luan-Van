package vn.com.luanvan.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.dao.UserRoleDao;
import vn.com.luanvan.model.User;


@Controller
public class FileUploadController{
	@Autowired
	private UserRoleDao userroleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	
	/**
	 * Hàm lưu trữ image vào database
	 * @param request		Dùng để lấy biến truyền vào từ trang view
	 * @param principal		Dùng để lấy tên username của người dùng
	 * @return				redirect tới trang background 
	 */
	@RequestMapping(value="/uploadImage",  method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request, Principal principal){
		String file  = request.getParameter("file");
		String username = principal.getName();
		User user = userDao.findUserbyUserName(username);
		user.setImage(file);
		userDao.save(user);
		return "redirect:/background";	
	}
	
	@RequestMapping(value="/uploadFile",  method = RequestMethod.POST)
	public String uploadFile(HttpServletRequest request, Principal principal){
		String file = request.getParameter("file_source");
		
		return null;
		
	}
	
}