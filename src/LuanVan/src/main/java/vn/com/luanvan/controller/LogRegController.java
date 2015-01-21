package vn.com.luanvan.controller;


import java.math.BigInteger;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.dao.UserRoleDao;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.User;
import vn.com.luanvan.model.UserRole;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LogRegController {
	@Autowired
	private UserRoleDao userroleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
 
		ModelAndView model = new ModelAndView();
		
		model.setViewName("home");
		return model;
 
	}
 
	@RequestMapping(value = "/admin**", method = RequestMethod.POST)
	public ModelAndView adminPage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");
 
		return model;
 
	}

	//Spring Security see this :
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
 
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
 
		return model;
 
	}
	
	//for 403 access denied page
		@RequestMapping(value = "/403", method = RequestMethod.POST)
		public ModelAndView accesssDenied() {
	 
		  ModelAndView model = new ModelAndView();
	 
		  //check if user is login
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();	
			model.addObject("username", userDetail.getUsername());
		  }
	 
		  model.setViewName("403");
		  return model;
		}
		
		@RequestMapping(value = "/dangky", method = RequestMethod.POST ,produces = "text/plain;charset=UTF-8")
		public String dangKy(HttpServletRequest http, User user, Model model) {
			SecureRandom random = new SecureRandom();
			String pass = new BigInteger(80,random).toString(32);
			String username = http.getParameter("username");
			String email = http.getParameter("email");
			String strUser = "Tài khoản đã được sử dụng.";
			String strEmail = "Email đã được sử dụng.";
			if(userDao.findByUserName(username) == true){
				model.addAttribute("strUser",strUser);
				return "home";
			}
			else if(userDao.checkEmail(email) == true){
				model.addAttribute("strEmail",strEmail);
				return "home";
			}
			else{
				user.setUsername(username);
				user.setPassword(pass);
				user.setEmail(email);
				user.setEnabled(true);
				userDao.add(user);
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				userRole.setRole("ROLE_USER");
				userroleDao.add(userRole);
				userDao.sendMail(user);
		        return "home";
			}
		}
		@RequestMapping(value = "/doimatkhau", method = RequestMethod.POST)
		public String changePassword(HttpServletRequest http, User user,Model model ) {
			user = userDao.findUserbyUserName(http.getParameter("username"));
			if(userDao.checkOldPassword(user, http.getParameter("oldPassword"))){
				user.setPassword(http.getParameter("newPassword"));
				userDao.save(user);
				model.addAttribute("successChangePassword", "Bạn đã thay đổi mật khẩu thành công");
			}
			else{
				model.addAttribute("errorOldPass", "Nhập mật khẩu cũ không chính xác");
			}
			return "background";
		}
		
		
		@RequestMapping(value = "/background", method = RequestMethod.GET)
		public String background(Model model, User user,Principal principal){
			String name = principal.getName();
			user = userDao.findUserbyUserName(name);
			List<Project> making = projectDao.getListProject(name, 0);
			List<Project> finish = projectDao.getListProject(name, 1);
			List<Project> stopping = projectDao.getListProject(name, 2);
			model.addAttribute("username", user.getUsername());
			model.addAttribute("listMaking", making);
			model.addAttribute("listFinish", finish);
			model.addAttribute("listStopping", stopping);
			return "background";
		}
		
}