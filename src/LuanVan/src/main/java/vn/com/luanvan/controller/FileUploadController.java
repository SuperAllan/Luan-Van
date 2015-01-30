package vn.com.luanvan.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.dao.UserRoleDao;
import vn.com.luanvan.model.FileUpload;
import vn.com.luanvan.model.User;


@Controller
public class FileUploadController{
	@Autowired
	private UserRoleDao userroleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	
	@RequestMapping(value="/uploadFile",  method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request, Principal principal){
		String file  = request.getParameter("file");
		String username = principal.getName();
		User user = userDao.findUserbyUserName(username);
		user.setImage(file);
		userDao.save(user);
		
		
		return "redirect:/background";	
	}
}