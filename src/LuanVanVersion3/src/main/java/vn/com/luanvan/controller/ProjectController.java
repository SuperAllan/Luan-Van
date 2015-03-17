package vn.com.luanvan.controller;

import java.security.Principal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import vn.com.luanvan.dao.ActorDao;
import vn.com.luanvan.dao.BmtDao;
import vn.com.luanvan.dao.ChucNangDao;
import vn.com.luanvan.dao.GiaTriLuongDao;
import vn.com.luanvan.dao.HeSoKyThuatDao;
import vn.com.luanvan.dao.HeSoMoiTruongDao;
import vn.com.luanvan.dao.LoaiactorDao;
import vn.com.luanvan.dao.LuongDao;
import vn.com.luanvan.dao.MucDoDao;
import vn.com.luanvan.dao.MucLuongNhaNuocDao;
import vn.com.luanvan.dao.NhomChucNangDao;
import vn.com.luanvan.dao.PhanLoaiChucNangDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.TrongsonolucDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.dao.UserRoleDao;
import vn.com.luanvan.dao.XepHangKyThuatDao;
import vn.com.luanvan.dao.XepHangMoiTruongDao;
import vn.com.luanvan.model.Giatriluong;
import vn.com.luanvan.model.Luong;
import vn.com.luanvan.model.Nhomchucnang;
import vn.com.luanvan.model.Phanloaichucnang;
import vn.com.luanvan.model.Project;
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
	
	@RequestMapping(value = "/formCreateProject", method = RequestMethod.GET)
	public String formCreateProject(Model model) {
		return "create-project";
	}
	
	@RequestMapping(value = "/createProject", method = RequestMethod.GET)
	public String createProject(Model model, Project project,Principal principal, RedirectAttributes redirectAttributes) throws ParseException {
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
			User user = new User();
			user.setUsername(username);
			project.setUser(user);
			project.setNgaytao(date);
			projectDao.add(project);
			redirectAttributes.addFlashAttribute("success", "Bạn đã tạo dự án thành công.");
			return "redirect:/background";
		}
		
	}
	
	@RequestMapping(value="/detailProject", method = RequestMethod.GET)
	public String detailProject( Principal principal, Model model){
		String projectName = (String) request.getParameter("name");
		DecimalFormat df = new DecimalFormat("#.#");
		String username = principal.getName();
		Project project = projectDao.findProjectByName(username, projectName);
		int projectid = project.getProjectid();
		User user = userDao.findUserbyUserName(username);
		List<Phanloaichucnang> listPhanLoai = phanLoaiChucNangDao.getListTenLoai();
		List<Mucdo> listMucDo = mucDoDao.getListMucDo();
		List<Nhomchucnang> listNhomChucNangFromData = nhomChucNangDao.getListNhomChucNang(project);
		if(listNhomChucNangFromData != null){
			model.addAttribute("listNhomChucNangFromData", listNhomChucNangFromData);
		}
		model.addAttribute("listTrongSoNoLuc", trongsonolucDao.getAll());
		model.addAttribute("listPhanLoai", listPhanLoai);
		model.addAttribute("listMucDo", listMucDo);
		model.addAttribute("project", project);
		model.addAttribute("user",user);
		model.addAttribute("sodo", project.getSodo());
		model.addAttribute("listHeSoKyThuat", heSoKyThuatDao.getListHeSoKyThuat());
		model.addAttribute("listHeSoMoiTruong", heSoMoiTruongDao.getListHeSoMoiTruong());
		model.addAttribute("listXepHangKyThuat", xepHangKyThuatDao.getListXepHangKyThuat(projectid));
		model.addAttribute("listXepHangMoiTruong", xepHangMoiTruongDao.getListXepHangMoiTruong(projectid));
		model.addAttribute("listGiatriluong", giaTriLuongDao.getListGiaTriLuong(projectid));
		model.addAttribute("bmts", bmtDao.getAll());
		model.addAttribute("mucLuongNhaNuoc", mucLuongNhaNuocDao.getList());
		model.addAttribute("listLuong", luongDao.getListLuong());
		model.addAttribute("loaiactors", loaiactorDao.getAll());
		List<Float> ketQuaKT = xepHangKyThuatDao.TinhKetQua(projectid);
		model.addAttribute("ketQuaFromKyThuat", ketQuaKT);
		List<Float> ketQuaMT = xepHangMoiTruongDao.TinhKetQuaMoiTruong(projectid);
		model.addAttribute("ketQuaFromMoiTruong", ketQuaMT);
		model.addAttribute("tongKetQuaFromMoiTruong", xepHangMoiTruongDao.TongKetQuaMoiTruong(projectid));
		model.addAttribute("tongKetQuaFromKyThuat", xepHangKyThuatDao.TongKetqua(projectid));
		model.addAttribute("tongOnDinh", df.format(xepHangMoiTruongDao.TongOnDinh(projectid)));
		model.addAttribute("tinhNoiSuy", xepHangMoiTruongDao.tinhNoiSuyLaoDong(projectid));
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
		model.addAttribute("countActor", actorDao.countActor(projectid));
		model.addAttribute("diemActor", actorDao.tinhDiemTungActor(projectid, loaiactorDao.getAll()));
		model.addAttribute("tongDiemActor", actorDao.tinhTongDiem(projectid, loaiactorDao.getAll()));
		model.addAttribute("soTruongHopSudung", usecaseDao.countBMT(projectid));
		model.addAttribute("diemTungUsecase", usecaseDao.tinhDiemTungUsecase(projectid, bmtDao.getAll()));
		model.addAttribute("tongDiemTungUsecase", usecaseDao.tongDiemTungUsecase(projectid, bmtDao.getAll()));
		model.addAttribute("tongSoTruongHopSudung", usecaseDao.tongBMT(projectid));
		
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