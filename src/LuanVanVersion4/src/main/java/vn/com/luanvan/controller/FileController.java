package vn.com.luanvan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;













import vn.com.luanvan.dao.FileUCDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.dao.UserRoleDao;
import vn.com.luanvan.model.FileUC;
import vn.com.luanvan.model.Usecase;
import vn.com.luanvan.model.User;


@Controller
public class FileController{
	@Autowired
	private UserRoleDao userroleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private UsecaseDao usecaseDao;
	@Autowired
	private FileUCDao fileUCDao;
	
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
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile( MultipartHttpServletRequest request, HttpServletResponse response,
    		@RequestParam("usecaseID")String usecaseID, Model model){
		
		//String locationSave = request.getSession().getServletContext().getRealPath("/") +File.separator+"resources"+File.separator+"files"+File.separator;
		String rootPath = System.getProperty("catalina.home");
		String locationSave = rootPath + File.separator + "fileUpload" + File.separator;
		Date date = new Date();
		Iterator<String> itr =  request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		Usecase usecase = usecaseDao.getUsecaseByID(Integer.parseInt(usecaseID));
		try {
			FileUC file = new FileUC();
			File fileCheck = new File(locationSave+mpf.getOriginalFilename());
			String fileName = mpf.getOriginalFilename();
			String type = fileName.substring(fileName.lastIndexOf(".")+1);
			file.setName(fileName);
			file.setType(type);
			float size = mpf.getBytes().length;
			if((size/1024) > 1024){
				float temp = (size/1024)/1024;
				file.setSize(String.format("%.2f",temp)+"Mb");
			}else{
				float temp = size/1024;
				file.setSize(String.format("%.2f",temp)+"Kb");
			}
            if(fileCheck.exists()){
            	file.setLink(date.getTime()+"_"+mpf.getOriginalFilename());
            	mpf.transferTo(new File(locationSave+file.getLink()));
            }else{
            	file.setLink(mpf.getOriginalFilename());
            	mpf.transferTo(new File(locationSave+file.getLink()));
            }
            file.setUsecase(usecase);
            fileUCDao.save(file);
			model.addAttribute("resultFile", file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "show-list-file";
    }
	
	@RequestMapping(value="/downloadFile", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, HttpServletRequest request) throws IOException{
		//String locationSave = request.getSession().getServletContext().getRealPath("/") +File.separator+"resources"+File.separator+"files"+File.separator;
		String rootPath = System.getProperty("catalina.home");
		String locationSave = rootPath + File.separator + "fileUpload" + File.separator;
		String fileid = request.getParameter("fileid");
		FileUC fileUC = fileUCDao.findFileByID(Integer.parseInt(fileid));
		File fileDownload = new File(locationSave+fileUC.getLink());
		FileInputStream inputStream = new FileInputStream(fileDownload);
		response.setCharacterEncoding("UTF-8");
		response.setContentType(fileUC.getType());
	    response.setContentLength((int) fileDownload.length());
        response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileUC.getName(), "UTF-8"));
        OutputStream outStream = response.getOutputStream();
        
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.close();
	}
	
	@RequestMapping(value="/deleteFile", method = RequestMethod.GET)
	public void deleteFile(HttpServletResponse response, HttpServletRequest request) throws IOException{
		//String locationSave = request.getSession().getServletContext().getRealPath("/") +File.separator+"resources"+File.separator+"files"+File.separator;
		String rootPath = System.getProperty("catalina.home");
		String locationSave = rootPath + File.separator + "fileUpload" + File.separator;
		String fileid = request.getParameter("fileid");
		FileUC fileUC = fileUCDao.findFileByID(Integer.parseInt(fileid));
		File fileDelete = new File(locationSave+fileUC.getLink());
		if(fileDelete.exists()){
			fileDelete.delete();
			fileUCDao.delete(fileUC);
			System.out.print("delete ok");
		}
		
	}
}