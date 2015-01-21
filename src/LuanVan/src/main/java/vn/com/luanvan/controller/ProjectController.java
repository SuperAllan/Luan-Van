package vn.com.luanvan.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.dao.UserRoleDao;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.User;


@Controller
public class ProjectController {
	@Autowired
	private UserRoleDao userroleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	
	
	
	@RequestMapping(value = "/formCreateProject", method = RequestMethod.GET)
	public String formCreateProject(Model model) {
		return "create-project";
	}
	
	@RequestMapping(value = "/createProject", method = RequestMethod.GET)
	public String createProject(Model model, HttpServletRequest request, Project project,Principal principal) {
		String projectName = request.getParameter("projectName");
		String description = request.getParameter("description");
		String username = principal.getName();
		if(projectDao.checkProjectName(username ,projectName) == true){
			model.addAttribute("errorName", "Tên của dự án đã tồn tại.");
			return "create-project";
		}else{
			project.setName(projectName);
			project.setDescription(description);
			project.setStatus(0);
			User user = new User();
			user.setUsername(username);
			project.setUser(user);
			projectDao.add(project);
			model.addAttribute("success", "Bạn đã tạo dự án thành công.");
			return "create-project";
		}
		
	}
	
}