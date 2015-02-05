package vn.com.luanvan.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.com.luanvan.dao.BmtDao;
import vn.com.luanvan.dao.ChucNangDao;
import vn.com.luanvan.dao.LoaiactorDao;
import vn.com.luanvan.dao.MucDoDao;
import vn.com.luanvan.dao.NhomChucNangDao;
import vn.com.luanvan.dao.PhanLoaiChucNangDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.dao.UserRoleDao;
import vn.com.luanvan.form.FormChucNang;
import vn.com.luanvan.model.Chucnang;
import vn.com.luanvan.model.Nhomchucnang;
import vn.com.luanvan.model.Phanloaichucnang;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.User;
import vn.com.luanvan.model.Mucdo;


@Controller
public class ProjectController {
	@Autowired
	private UserRoleDao userroleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private PhanLoaiChucNangDao phanLoaiChucNangDao;
	@Autowired
	private MucDoDao mucDoDao;
	@Autowired
	private ChucNangDao chucNangDao;
	@Autowired
	private NhomChucNangDao nhomChucNangDao;
	@Autowired
	private BmtDao bmtDao;
	@Autowired
	private LoaiactorDao loaiactorDao;
	
	@RequestMapping(value = "/formCreateProject", method = RequestMethod.GET)
	public String formCreateProject(Model model) {
		return "create-project";
	}
	
	@RequestMapping(value = "/createProject", method = RequestMethod.GET)
	public String createProject(Model model, HttpServletRequest request, Project project,Principal principal) throws ParseException {
		String projectName = request.getParameter("projectName");
		String description = request.getParameter("description");
		String username = principal.getName();
		Date date = new Date();
		if(projectDao.checkProjectName(username ,projectName) == true){
			model.addAttribute("errorName", "Tên của dự án đã tồn tại.");
			return "create-project";
		}else{
			project.setTenproject(projectName);
			project.setMotaproject(description);
			project.setTrangthai(0);
			User user = new User();
			user.setUsername(username);
			project.setUser(user);
			project.setNgaytao(date);
			projectDao.add(project);
			model.addAttribute("success", "Bạn đã tạo dự án thành công.");
			return "create-project";
		}
		
	}
	
	@RequestMapping(value="/detailProject/name={projectName}", method = RequestMethod.GET)
	public String detailProject(@PathVariable("projectName") String projectName, Principal principal, Model model){
		String username = principal.getName();
		Project project = projectDao.findProjectByName(username, projectName);
		User user = userDao.findUserbyUserName(username);
		List<Phanloaichucnang> listPhanLoai = phanLoaiChucNangDao.getListTenLoai();
		List<Mucdo> listMucDo = mucDoDao.getListMucDo();
		List<Nhomchucnang> listNhomChucNangFromData = nhomChucNangDao.getListNhomChucNang(project);
		if(listNhomChucNangFromData != null){
			model.addAttribute("listNhomChucNangFromData", listNhomChucNangFromData);
		}
		model.addAttribute("listPhanLoai", listPhanLoai);
		model.addAttribute("listMucDo", listMucDo);
		model.addAttribute("project", project);
		model.addAttribute("user",user);
		model.addAttribute("sodo", project.getSodo());
		model.addAttribute("bmts", bmtDao.getAll());
		model.addAttribute("loaiactors", loaiactorDao.getAll());
		return "detail-project";
	}
	
	@RequestMapping(value="/updateFeatures", method = RequestMethod.GET)
	public String updateFeatures(HttpServletRequest request,@RequestParam("nhomChucNang") String[] nhoms,
			@RequestParam("soLuongChucNang") String[] soLuong, @ModelAttribute FormChucNang form,  Principal principal,RedirectAttributes redirectAttributes){
		String tenProject = request.getParameter("tenProject");
		Project project = projectDao.findProjectByName(principal.getName(), tenProject);
			if(project != null){
				chucNangDao.deleteData(project.getProjectid());
				List<Nhomchucnang> lists = nhomChucNangDao.getListNhomChucNang(project);
				for(Nhomchucnang nhom : lists){
					if(nhom.getChucnangs().size() < 1){
						nhomChucNangDao.delete(nhom);
					}
				}
			}
		int dem = 0;
		int nhom = -1;
		for(int i = 0; i < soLuong.length; i++){
			nhom++;
			Nhomchucnang nhomChucNang = new Nhomchucnang(nhoms[nhom]);
			nhomChucNang.setProject(project);
			nhomChucNangDao.save(nhomChucNang);
			Chucnang currentChucNang = new Chucnang();
			for(int j = 0; j < Integer.parseInt(soLuong[i]); j++){
				currentChucNang.setMotayeucau(form.getListChucNang()[dem]);
				currentChucNang.setProject(projectDao.findProjectByName(principal.getName(), tenProject));
				currentChucNang.setMucdo(mucDoDao.findMucDoByID(Integer.parseInt(form.getListMucDo()[dem])));
				currentChucNang.setPhanloaichucnang(phanLoaiChucNangDao.findPhanLoaiByID(Integer.parseInt(form.getListPhanLoai()[dem])));
				currentChucNang.setGhichu(form.getListGhiChu()[dem]);
				currentChucNang.setNhomchucnang(nhomChucNang);
				chucNangDao.save(currentChucNang);
				dem++;
			}
		}
		redirectAttributes.addFlashAttribute("UpdateChucNangSuccess", "Cập nhật chức năng thành công!");
		return "redirect:/detailProject/name="+project.getTenproject()+"";
	}
	
	
}