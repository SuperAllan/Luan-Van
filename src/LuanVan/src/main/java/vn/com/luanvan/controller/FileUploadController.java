package vn.com.luanvan.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

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
	public String onSubmit(HttpServletRequest request, 
			@ModelAttribute("formUpload") FileUpload formUpload, Principal principal){
		String locationSave = request.getSession().getServletContext().getRealPath("/") 
				+ "/resources/file/";
		MultipartFile file = formUpload.getFile();
        if(null != file) {
        	System.out.print(file.getOriginalFilename());
            String username = principal.getName();
            String fileName = file.getOriginalFilename();
            if (!(fileName + locationSave).equals(locationSave)) {
            	File pathFile = new File(locationSave + fileName);
                try {
					file.transferTo(pathFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                User user = userDao.findUserbyUserName(username);
                user.setImage("/resources/file/" + fileName);
                userDao.save(user);
            }
        }
		
		return "redirect:/background";	
	}
}