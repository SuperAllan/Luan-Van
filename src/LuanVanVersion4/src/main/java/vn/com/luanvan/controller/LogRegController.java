package vn.com.luanvan.controller;


import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.mail.javamail.JavaMailSender;
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
	@Autowired
    private JavaMailSender mailSender;
	
	/**
	 * Trang chủ
	 * @param model			
	 * @param principal		Dùng để lấy username của người dùng.
	 * @return				Trả về trang home với biến user.
	 */
	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model, Principal principal) {
		try{
		if(principal.getName() != null || !principal.getName().isEmpty()){
			User user = userDao.findUserbyUserName(principal.getName());
			model.addAttribute("user", user);
		}
		}catch(NullPointerException e){
			e.getStackTrace();
		}
			return "home";
	}
 
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Đăng nhập sai tài khoản hoặc mật khẩu!");
		}
		if (logout != null) {
			model.addObject("msg", "Đăng xuất thành công.");
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
		/**
		 * 
		 * @param request	Lấy các biến truyền vào từ trang View.
		 * @param user		
		 * @param model
		 * @return			Trả về trang home với biến successRegister.
		 * @throws MessagingException 
		 * @throws IOException 
		 */
		//@Transactional(rollbackOn = Exception.class)
		@RequestMapping(value = "/dangky", method = RequestMethod.POST)
		public String dangKy(HttpServletRequest request, User user, Model model, HttpServletResponse response, Principal principal) {
			String pass = request.getParameter("password");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
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
				String subject = "Đăng ký tài khoản";
				String content = "<h3>Xin chào, bạn đã đăng ký thành công tài khoản</h3> <br>"
		        		+"<a href=\"http://localhost:8080/luanvan/confirm/id="+user.getIdconfirm()+"\">Nhấn vào đây để kích hoạt</a>";
				 
				try {
				MimeMessage message = mailSender.createMimeMessage(); 
				message.setFrom(new InternetAddress("luanvan111327@gmail.com"));
				message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail())); 		
				message.setSubject(subject, "UTF-8");
				message.setContent(content, "text/html; charset=UTF-8"); 
				model.addAttribute("successRegister","Đăng ký thành công");
				model.addAttribute("userInActive", user);
			    //sending message  
				mailSender.send(message);
				} catch (Exception e) {
					userroleDao.delete(userRole);
					userDao.delete(user);
					System.err.print("asdasdasd");
					e.printStackTrace();
				} 
				
		        return "home";
			}
		}
		/**
		 * 
		 * @param request				Lấy các biến từ trang view.
		 * @param user
		 * @param redirectAttributes	Truyền các biến (successChangePassword, errorOldPass) qua cho trang redirect tới.
		 * @return						redirect tới trang background.
		 */
		@RequestMapping(value = "/doimatkhau", method = RequestMethod.POST)
		public String changePassword(HttpServletRequest request, User user, RedirectAttributes redirectAttributes) {
			user = userDao.findUserbyUserName(request.getParameter("username"));
			if(userDao.checkOldPassword(user, request.getParameter("oldPassword"))){
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashPass = passwordEncoder.encode(request.getParameter("newPassword"));
				user.setPassword(hashPass);
				userDao.save(user);
				redirectAttributes.addFlashAttribute("successChangePassword", "Bạn đã thay đổi mật khẩu thành công");
			}
			else{
				redirectAttributes.addFlashAttribute("errorOldPass", "Nhập mật khẩu cũ không chính xác");
			}
			return "redirect:/background";
		}
		
		/**
		 * 
		 * @param model
		 * @param user
		 * @param principal		Dùng lấy username của người dùng.
		 * @return				Trả về trang background.
		 */
		@RequestMapping(value = "/background", method = RequestMethod.GET)
		public String background(Model model, User user, Principal principal){
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
		/**
		 * 
		 * @param request				Lấy các biến từ trang view.
		 * @param user
		 * @param principal				Dùng để lấy username của người dùng
		 * @param redirectAttributes	Truyên biến updateInforSuccess cho trang redirect tới.
		 * @return						redirect tới trang background.
		 */
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
		/**
		 * Hàm kích hoạt tài khoản.
		 * @param idconfirm		Mỗi người dùng sẽ có idconfirm khác nhau.
		 * @param user
		 * @return				Trả về trang confirm-mail.
		 */
		@RequestMapping(value="/confirm/id={idconfirm}", method = RequestMethod.GET)
		public String confirmEmail(@PathVariable("idconfirm")String idconfirm, User user){
			user = userDao.findUserByIdConfirm(idconfirm);
			user.setEnabled(true);
			userDao.save(user);
			return "confirm-mail";
		}
		
		@RequestMapping(value="/sendMailAgain", method = RequestMethod.POST)
		public String sendMailAgain(HttpServletRequest request, Model model){
			String username = request.getParameter("userInActiveForSendMail");
			System.out.print(username);
			if(username != null || username != ""){
				User user = userDao.findUserbyUserName(username);
				if(user.isEnabled() == false){
					String subject = "Đăng ký tài khoản";
					String content = "<h3>Xin chào, bạn đã đăng ký thành công tài khoản</h3> <br>"
			        		+"<a href=\"http://localhost:8080/luanvan/confirm/id="+user.getIdconfirm()+"\">Nhấn vào đây để kích hoạt</a>";
					try{
					MimeMessage message = mailSender.createMimeMessage();  
					message.setFrom(new InternetAddress("luanvan111327@gmail.com"));
					message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail())); 		
					message.setSubject(subject, "UTF-8");
					message.setContent(content, "text/html; charset=UTF-8"); 
				    //sending message  
					mailSender.send(message);
					}catch(Exception e){
						return "403";
					}
					model.addAttribute("successSendMail","Email đã được gửi. Xin kiểm tra lại!");
				}
			}
			return "home";
			
		}
		
}