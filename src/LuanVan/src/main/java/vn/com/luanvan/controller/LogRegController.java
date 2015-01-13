package vn.com.luanvan.controller;


import java.math.BigInteger;
import java.security.SecureRandom;

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

import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.dao.UserRoleDao;
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
	
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;
 
	}
 
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
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
		@RequestMapping(value = "/403", method = RequestMethod.GET)
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
		
		@RequestMapping(value = "/dangky", method = RequestMethod.POST)
		public String dangKy(HttpServletRequest http, User user) {
			SecureRandom random = new SecureRandom();
			String pass = new BigInteger(130,random).toString(32);
			user.setUsername(http.getParameter("username"));
			user.setPassword(pass);
			user.setEmail(http.getParameter("email"));
			user.setEnabled(true);
			userDao.add(user);
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole("ROLE_USER");
			userroleDao.add(userRole);
			userDao.sendMail(user,pass);
	        return "redirect:/welcome";
			
		}
		@RequestMapping(value = "/doimatkhau", method = RequestMethod.POST)
		public String changePassword(HttpServletRequest http, User user, Model model) {
			String errorOldPass = null;
			user = userDao.findByUserName(http.getParameter("username"));
			if(userDao.checkOldPassword(user, http.getParameter("oldPassword"))){
				user.setPassword(http.getParameter("newPassword"));
				userDao.save(user);
			}
			else{
				model.addAttribute("Nhập mật khẩu cũ không chính xác", errorOldPass);
			}
			return "redirect:/welcome";
		
		}
}