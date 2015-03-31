package vn.com.luanvan.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.com.luanvan.dao.ActorDao;
import vn.com.luanvan.dao.BmtDao;
import vn.com.luanvan.dao.ChucNangDao;
import vn.com.luanvan.dao.DiagramDao;
import vn.com.luanvan.dao.GiaTriLuongDao;
import vn.com.luanvan.dao.HeSoKyThuatDao;
import vn.com.luanvan.dao.HeSoMoiTruongDao;
import vn.com.luanvan.dao.LoaiactorDao;
import vn.com.luanvan.dao.LuongDao;
import vn.com.luanvan.dao.MucDoDao;
import vn.com.luanvan.dao.MucLuongNhaNuocDao;
import vn.com.luanvan.dao.NhomChucNangDao;
import vn.com.luanvan.dao.NhomucDao;
import vn.com.luanvan.dao.PhanLoaiChucNangDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.TrongsonolucDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.dao.UserRoleDao;
import vn.com.luanvan.dao.XepHangKyThuatDao;
import vn.com.luanvan.dao.XepHangMoiTruongDao;
import vn.com.luanvan.model.Chucnang;
import vn.com.luanvan.model.Giatriluong;
import vn.com.luanvan.model.Luong;
import vn.com.luanvan.model.Nhomchucnang;
import vn.com.luanvan.model.Nhomuc;
import vn.com.luanvan.model.Phanloaichucnang;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.Usecase;
import vn.com.luanvan.model.User;
import vn.com.luanvan.model.Mucdo;


@Controller
public class ProjectController {
	@Autowired
	private HttpServletRequest request;
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
	private LuongDao luongDao;
	@Autowired
	private LoaiactorDao loaiactorDao;
	@Autowired
	private ActorDao actorDao;
	@Autowired
	private UsecaseDao usecaseDao;
	@Autowired
	private HeSoKyThuatDao heSoKyThuatDao;
	@Autowired
	private XepHangKyThuatDao xepHangKyThuatDao;
	@Autowired
	private HeSoMoiTruongDao heSoMoiTruongDao;
	@Autowired
	private XepHangMoiTruongDao xepHangMoiTruongDao;
	@Autowired
	private MucLuongNhaNuocDao mucLuongNhaNuocDao;
	@Autowired
	private GiaTriLuongDao giaTriLuongDao;
	@Autowired
	private TrongsonolucDao trongsonolucDao;
	@Autowired
	private NhomucDao nhomucDao;
	@Autowired
	private DiagramDao diagramDao;
	
	/**
	 * 
	 * @param model
	 * @param project
	 * @param principal				Dùng để lấy username của người dùng.
	 * @param redirectAttributes	Truyên biến success tới trang redirect tới.
	 * @return						redirect tới trang background.
	 */
	@RequestMapping(value = "/createProject", method = RequestMethod.GET)
	public String createProject(Model model, Project project,Principal principal, RedirectAttributes redirectAttributes){
		String projectName = request.getParameter("projectName");
		String description = request.getParameter("description");
		String username = principal.getName();
		Date date = new Date();
		if(projectDao.checkProjectName(username ,projectName) == true){
			redirectAttributes.addFlashAttribute("errorName", "Tên của dự án đã tồn tại.");
			return "redirect:/background";
		}else{
			project.setTenproject(projectName);
			project.setMotaproject(description);
			project.setTrangthai(0);
			project.setTrongsonoluc(1);
			User user = new User();
			user.setUsername(username);
			project.setUser(user);
			project.setNgaytao(date);
			projectDao.add(project);
			redirectAttributes.addFlashAttribute("success", "Bạn đã tạo dự án thành công.");
			return "redirect:/background";
		}
		
	}
	
	
	
	/**
	 * 
	 * @param principal		Dùng để lấy biến username người dùng.
	 * @param model
	 * @return				Trả về trang detail-project.
	 */
	@RequestMapping(value="/detailProject", method = RequestMethod.GET)
	public String detailProject( Principal principal, Model model){
		String projectName = (String) request.getParameter("name");
		DecimalFormat df = new DecimalFormat("#.#");
		String username = principal.getName();
		Project project = projectDao.findProjectByName(username, projectName);
		int projectid = project.getProjectid();
		User user = userDao.findUserbyUserName(username);
		
		model.addAttribute("diagrams", diagramDao.getDiagramByProject(project.getProjectid()));
		
		//Biến cho trang yêu cầu chức năng
		List<Phanloaichucnang> listPhanLoai = phanLoaiChucNangDao.getListTenLoai();
		List<Mucdo> listMucDo = mucDoDao.getListMucDo();
		List<Nhomchucnang> listNhomChucNangFromData = nhomChucNangDao.getListNhomChucNang(project);
		if(listNhomChucNangFromData != null){
			model.addAttribute("listNhomChucNangFromData", listNhomChucNangFromData);
		}
		model.addAttribute("listTrongSoNoLuc", trongsonolucDao.getAll());
		model.addAttribute("listPhanLoai", listPhanLoai);
		model.addAttribute("listMucDo", listMucDo);
		
		//Biến cho trang chuyển đổi use case
		model.addAttribute("listNhomUC", nhomucDao.getNhomucByProject(projectid));
		
		//Biến cho toàn trang detailProject
		model.addAttribute("project", project);
		model.addAttribute("user",user);
		
		//Biến cho trang vẽ use case
		model.addAttribute("sodo", project.getSodo());
		
		//Biến cho trang Kỹ thuật công nghệ
		model.addAttribute("listHeSoKyThuat", heSoKyThuatDao.getListHeSoKyThuat());
		model.addAttribute("listXepHangKyThuat", xepHangKyThuatDao.getListXepHangKyThuat(projectid));
		List<Float> ketQuaKT = xepHangKyThuatDao.TinhKetQua(projectid);
		model.addAttribute("ketQuaFromKyThuat", ketQuaKT);
		model.addAttribute("tongKetQuaFromKyThuat", xepHangKyThuatDao.TongKetqua(projectid));
		
		//Biến cho trang Môi trường
		model.addAttribute("listHeSoMoiTruong", heSoMoiTruongDao.getListHeSoMoiTruong());
		model.addAttribute("listXepHangMoiTruong", xepHangMoiTruongDao.getListXepHangMoiTruong(projectid));
		List<Float> ketQuaMT = xepHangMoiTruongDao.TinhKetQuaMoiTruong(projectid);
		model.addAttribute("ketQuaFromMoiTruong", ketQuaMT);
		model.addAttribute("tongKetQuaFromMoiTruong", xepHangMoiTruongDao.TongKetQuaMoiTruong(projectid));
		model.addAttribute("tongOnDinh", df.format(xepHangMoiTruongDao.TongOnDinh(projectid)));
		model.addAttribute("tinhNoiSuy", xepHangMoiTruongDao.tinhNoiSuyLaoDong(projectid));
		
		//Biến cho trang Lương
		model.addAttribute("listLuong", luongDao.getListLuong());
		model.addAttribute("listGiatriluong", giaTriLuongDao.getListGiaTriLuong(projectid));
		model.addAttribute("mucLuongNhaNuoc", mucLuongNhaNuocDao.getList());
		List<Luong> listLuong = luongDao.getListLuong();
		model.addAttribute("ketQuaLuongCoBan", luongDao.TinhLuongCoBan(project.getLuongcoban(), listLuong));
		model.addAttribute("ketQuaLuongPhu", luongDao.TinhLuongPhu(project.getLuongcoban(), listLuong));
		model.addAttribute("ketQuaCPKG", luongDao.TinhCPKG(project.getLuongcoban(), listLuong));
		if(!giaTriLuongDao.getListGiaTriLuong(projectid).isEmpty()){
			List<Giatriluong> listGiaTri = giaTriLuongDao.getListGiaTriLuong(projectid);
			model.addAttribute("ketQuaBaoHiem", luongDao.TinhBaoHiem(project.getLuongcoban(), luongDao.getListLuong(), listGiaTri));
			model.addAttribute("ketQuaTong", luongDao.TinhTong(project.getLuongcoban(), luongDao.getListLuong(), listGiaTri));
			model.addAttribute("ketQuaCP1Ngay", luongDao.TinhCP1Ngay(project.getLuongcoban(), luongDao.getListLuong(), listGiaTri));
			model.addAttribute("ketQuaCP1Gio", luongDao.TinhCP1Gio(project.getLuongcoban(), luongDao.getListLuong(), listGiaTri));
		}
		
		//Biến cho trang Usecase
		model.addAttribute("bmts", bmtDao.getAll());
		model.addAttribute("loaiactors", loaiactorDao.getAll());
		model.addAttribute("soTruongHopSudung", usecaseDao.countBMT(projectid));
		model.addAttribute("diemTungUsecase", usecaseDao.tinhDiemTungUsecase(projectid, bmtDao.getAll()));
		model.addAttribute("tongDiemTungUsecase", usecaseDao.tongDiemTungUsecase(projectid, bmtDao.getAll()));
		model.addAttribute("tongSoTruongHopSudung", usecaseDao.tongBMT(projectid));
		
		//Biến cho trang tác nhân
		model.addAttribute("countActor", actorDao.countActor(projectid));
		model.addAttribute("diemActor", actorDao.tinhDiemTungActor(projectid, loaiactorDao.getAll()));
		model.addAttribute("tongDiemActor", actorDao.tinhTongDiem(projectid, loaiactorDao.getAll()));
		
		//Kiểm tra các biến mà các trang truyền biến redirectAttributes tới.
		String updateTrangThaiSuccess = request.getParameter("updateTrangThaiSuccess");
		if(updateTrangThaiSuccess != null){
			model.addAttribute("updateTrangThaiSuccess", updateTrangThaiSuccess);
		}
		String errorNameThietLap = request.getParameter("errorNameThietLap");
		if(errorNameThietLap != null){
			model.addAttribute("errorNameThietLap", errorNameThietLap);
		}
		String UpdateChucNangSuccess = request.getParameter("UpdateChucNangSuccess");
		if(UpdateChucNangSuccess != null){
			model.addAttribute("UpdateChucNangSuccess", UpdateChucNangSuccess);
		}
		String UpdateChuyenDoiSuccess = request.getParameter("UpdateChuyenDoiSuccess");
		if(UpdateChuyenDoiSuccess != null){
			model.addAttribute("UpdateChuyenDoiSuccess", UpdateChuyenDoiSuccess);
		}
		String updateKyThuatSuccess = request.getParameter("updateKyThuatSuccess");
		if(updateKyThuatSuccess != null){
			model.addAttribute("updateKyThuatSuccess", updateKyThuatSuccess);
		}
		String updateMoiTruongSuccess = request.getParameter("updateMoiTruongSuccess");
		if(updateMoiTruongSuccess != null){
			model.addAttribute("updateMoiTruongSuccess", updateMoiTruongSuccess);
		}
		String updateBangLuongSuccess = request.getParameter("updateBangLuongSuccess");
		if(updateBangLuongSuccess != null){
			model.addAttribute("updateBangLuongSuccess", updateBangLuongSuccess);
		}
		String updateGiaTriPhanMemSuccess = request.getParameter("updateGiaTriPhanMemSuccess");
		if(updateGiaTriPhanMemSuccess != null){
			model.addAttribute("updateGiaTriPhanMemSuccess", updateGiaTriPhanMemSuccess);
		}
		return "detail-project";	
	}
	
	/**
	 * 
	 * @param model
	 * @param project
	 * @param principal		
	 * @param redirectAttributes	Truyền các biến (errorNameThietLap, updateTrangThaiSuccess) cho trang redirect tới.
	 * @return						redirect tới trang detailProject
	 */
	@RequestMapping(value = "/updateProject", method = RequestMethod.GET)
	public ModelAndView  updateProject(ModelAndView model, Project project, Principal principal, RedirectAttributes redirectAttributes) {
		String projectName = request.getParameter("tenProject");
		String projectNameOld = request.getParameter("tenProjectOld");
		String description = request.getParameter("motaProject");
		String username = principal.getName();
		String trangthai = request.getParameter("radioTrangThai");
		project = projectDao.findProjectByName(principal.getName(), projectNameOld);
		project.setTrangthai(Integer.parseInt(trangthai));
		project.setMotaproject(description);
		if(projectName.equals(projectNameOld) == false){
			if(projectDao.checkProjectName(username, projectName) == false){
				project.setTenproject(projectName);
			}else{
				redirectAttributes.addAttribute("errorNameThietLap", "Tên dự án đã tồn tại.");
			}
		}
		projectDao.save(project);
		redirectAttributes.addAttribute("updateTrangThaiSuccess", "Cập nhật thành công!");
		model.addObject("name", project.getTenproject());
		model.setViewName("redirect:/detailProject");
		return model;
		
	}
	/**
	 * 
	 * @param request
	 * @param project
	 * @param principal				Lấy username của người dùng.
	 * @param redirectAttributes	Truyền biến deleteProjectSuccess cho trang redirect tới.
	 * @return						redirect tới trang background.
	 */
	@RequestMapping(value = "/xoaDuAn", method = RequestMethod.GET)
	public String deleteProject( HttpServletRequest request, Project project,Principal principal, RedirectAttributes redirectAttributes){
		String nameProject = request.getParameter("nameProjectForDelete");
		String username = principal.getName();
		project = projectDao.findProjectByName(username, nameProject);
		projectDao.delete(project);
		redirectAttributes.addFlashAttribute("deleteProjectSuccess", "Xóa dự an thành công!");
		return "redirect:/background";
	}
	
}