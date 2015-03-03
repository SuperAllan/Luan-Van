package vn.com.luanvan.controller;


import java.math.BigInteger;
import java.security.Principal;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			String pass = http.getParameter("password");
			String username = http.getParameter("username");
			String email = http.getParameter("email");
			String strUser = "Tài khoản đã được sử dụng.";
			String strEmail = "Email đã được sử dụng.";
			if(userDao.findByUserName(username) == true){
				model.addAttribute("strUser",strUser);
				return "home";
			}
			else if(userDao.checkEmailInDatabase(email) == true){
				model.addAttribute("strEmail",strEmail);
				return "home";
			}
			else{
				UUID id = UUID.randomUUID();
				user.setUsername(username);
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedpass = passwordEncoder.encode(pass);
				user.setPassword(hashedpass);
				user.setEmail(email);
				user.setIdconfirm(id.toString());
				user.setEnabled(false);
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
		public String changePassword(HttpServletRequest http, User user,RedirectAttributes redirectAttributes) {
			user = userDao.findUserbyUserName(http.getParameter("username"));
			if(userDao.checkOldPassword(user, http.getParameter("oldPassword"))){
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashPass = passwordEncoder.encode(http.getParameter("newPassword"));
				user.setPassword(hashPass);
				userDao.save(user);
				redirectAttributes.addFlashAttribute("successChangePassword", "Bạn đã thay đổi mật khẩu thành công");
			}
			else{
				redirectAttributes.addFlashAttribute("errorOldPass", "Nhập mật khẩu cũ không chính xác");
			}
			return "redirect:/background";
		}
		
		
		@RequestMapping(value = "/background", method = RequestMethod.GET)
		public String background(Model model, User user,Principal principal) throws ParseException{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String name = principal.getName();
			user = userDao.findUserbyUserName(name);
			List<Project> making = projectDao.getListProject(name, 0);
			List<Project> finish = projectDao.getListProject(name, 1);
			List<Project> stopping = projectDao.getListProject(name, 2);
			
			model.addAttribute("user", user);
			model.addAttribute("listMaking", making);
			model.addAttribute("listFinish", finish);
			model.addAttribute("listStopping", stopping);
			return "background";
		}
		
		@RequestMapping(value = "/updateInformation", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public String updateInformation(HttpServletRequest request, User user, Principal principal, RedirectAttributes redirectAttributes){
			String username = principal.getName();
			user = userDao.findUserbyUserName(username);
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("updateEmail");
			String address = request.getParameter("address");
			if(name != "" || name != null){
				user.setFullname(name);
			}
			if(phone != "" || phone != null){
				user.setPhone(phone);
			}
			if(address != "" || address != null){
				user.setAddress(address);;
			}
			if(email != "" || email != null){
				user.setEmail(email);
			}
			userDao.save(user);
			redirectAttributes.addFlashAttribute("updateInforSuccess", "Cập nhật thông tin  thành công");
			return "redirect:/background";
		}
		
		@RequestMapping(value="/confirm/id={idconfirm}", method = RequestMethod.GET)
		public String confirmEmail(@PathVariable("idconfirm")String idconfirm, User user){
			user = userDao.findUserByIdConfirm(idconfirm);
			user.setEnabled(true);
			userDao.save(user);
			return "confirm-mail";
			
		}
}