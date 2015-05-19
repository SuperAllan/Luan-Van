package vn.com.luanvan.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.luanvan.dao.NhomucDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.model.Nhomuc;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.Usecase;
import vn.com.luanvan.model.User;

@Controller
public class UsecaseController{
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private NhomucDao nhomucDao;
	@Autowired
	private UsecaseDao usecaseDao;
	@Autowired
	private UserDao userDao;
	
	
	class CustomComparator implements Comparator<Usecase> {
	    public int compare(Usecase p1, Usecase p2){
	            return p1.getNameofuc().compareTo(p2.getNameofuc());
	    }
	}
	/**
	 * 
	 * @param request	
	 * @param principal	Sử dụng lấy username người dùng.
	 * @param model		Lưu trữ đối tượng trả về trang view.
	 * @return			Trả về trang detail-usecase.
	 */
	@RequestMapping(value="/detailUsecase", method = RequestMethod.GET)
	public String detailUsecase(HttpServletRequest request, Principal principal, Model model){
		String projectName = request.getParameter("name");
		Project project = projectDao.findProjectByName(principal.getName() , projectName);
		if (request.getParameter("usecaseid") != null) {
			model.addAttribute("usecaseid", request.getParameter("usecaseid"));
		}
		if(request.getParameter("nameDiagram") != null){
			model.addAttribute("nameDiagram", request.getParameter("nameDiagram"));
		}
		User user = userDao.findUserbyUserName(principal.getName());
		model.addAttribute("project", project);
		model.addAttribute("user", user);
		return "detail-usecase";
	}
	/**
	 * 
	 * @param request
	 * @param principal
	 * @param model
	 * @return			Trả về trang show-detail-usecase.
	 */
	@RequestMapping(value="/showDetailUsecase", method = RequestMethod.GET)
	public String showDetailUsecase(HttpServletRequest request, Principal principal, Model model){
		String usecaseID = request.getParameter("usecaseid");
		Usecase usecase = usecaseDao.getUsecaseByID(Integer.parseInt(usecaseID));
		model.addAttribute("usecase", usecase);
		return "show-detail-usecase";
	}
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/searchUsecase", method = RequestMethod.GET)
	public String searchYeuCau( Model model, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String resultNull = "Không có dữ liệu";
		String searchContent = request.getParameter("searchContent");
		searchContent = searchContent.trim();
		String projectID = request.getParameter("projectID"); 
		List<Usecase> listUsecase = usecaseDao.searchUsecase(searchContent, Integer.parseInt(projectID));
		List<Nhomuc> listNhomUC = new ArrayList<Nhomuc>();
		if(listUsecase.size() > 0){
			listNhomUC.add(listUsecase.get(0).getNhomuc());
			for( int j = 1; j < listUsecase.size(); j++){
				int dem = 0;
				Nhomuc nhomUC = listUsecase.get(j).getNhomuc();
				for(int i = 0; i < listNhomUC.size(); i++){
					if(listNhomUC.get(i).getNhomucid() == nhomUC.getNhomucid()){
						dem++;
					}
				}
				if(dem < 1){
					listNhomUC.add(nhomUC);
				}
			}
			for(int i = 0; i < listNhomUC.size(); i++){
				Set<Usecase> usecaseTemp = new HashSet<Usecase>();
				for(int j = 0; j < listUsecase.size(); j++){
					if(listNhomUC.get(i).getNhomucid() == listUsecase.get(j).getNhomuc().getNhomucid()){
						usecaseTemp.add(listUsecase.get(j));
					}
				}
				TreeSet<Usecase> sortSet = new TreeSet<Usecase>(new CustomComparator());
				sortSet.addAll(usecaseTemp);
				listNhomUC.get(i).setUsecases(sortSet);
			}
			model.addAttribute("resultNhomUC", listNhomUC);
		}else{
			model.addAttribute("resultNull", resultNull);
		}
		return "show-list-usecase";
	}
	/**
	 * 
	 * @param usecaseID
	 * @param listCauHoi
	 */
	@RequestMapping(value ="/updateCauHoi", produces="text/plain; charset=utf-8")
	public @ResponseBody void updateCauHoi(@RequestParam("usecaseID") String usecaseID, 
			@RequestParam("listCauHoi") String listCauHoi){
		Usecase usecase = usecaseDao.getUsecaseByID(Integer.parseInt(usecaseID));
		usecase.setCauhoi(listCauHoi);
		usecaseDao.update(usecase);
	}
}