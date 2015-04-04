package vn.com.luanvan.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.com.luanvan.dao.BmtDao;
import vn.com.luanvan.dao.ChucNangDao;
import vn.com.luanvan.dao.GiaTriLuongDao;
import vn.com.luanvan.dao.LuongDao;
import vn.com.luanvan.dao.MucDoDao;
import vn.com.luanvan.dao.NhomChucNangDao;
import vn.com.luanvan.dao.NhomucDao;
import vn.com.luanvan.dao.PhanLoaiChucNangDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.TrongsonolucDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.dao.XepHangKyThuatDao;
import vn.com.luanvan.dao.XepHangMoiTruongDao;
import vn.com.luanvan.form.FormChucNang;
import vn.com.luanvan.model.Bmt;
import vn.com.luanvan.model.Chucnang;
import vn.com.luanvan.model.Giatriluong;
import vn.com.luanvan.model.GiatriluongId;
import vn.com.luanvan.model.Nhomchucnang;
import vn.com.luanvan.model.Nhomuc;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.Usecase;
import vn.com.luanvan.model.Xephangkythuat;
import vn.com.luanvan.model.XephangkythuatId;
import vn.com.luanvan.model.Xephangmoitruong;
import vn.com.luanvan.model.XephangmoitruongId;

@Controller
public class UocLuongController {
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private ChucNangDao chucNangDao;
	@Autowired
	private NhomChucNangDao nhomChucNangDao;
	@Autowired
	private MucDoDao mucDoDao;
	@Autowired
	private PhanLoaiChucNangDao phanLoaiChucNangDao;
	@Autowired
	private XepHangKyThuatDao xepHangKyThuatDao;
	@Autowired
	private XepHangMoiTruongDao xepHangMoiTruongDao;
	@Autowired
	private LuongDao luongDao;
	@Autowired
	private GiaTriLuongDao giaTriLuongDao;
	@Autowired
	private NhomucDao nhomucDao;
	@Autowired
	private UsecaseDao usecaseDao;
	@Autowired
	private BmtDao bmtDao;
	@Autowired
	private TrongsonolucDao trongsonolucDao;
	
	
	/**
	 * @param request
	 * @param nhoms				Lấy biến nhomChucNang từ trang view.
	 * @param soLuong			Lấy biến soLuongChucNang từ trang view.
	 * @param form				Dùng class FormChucNang tự định nghĩa ở vn.com.luanvan.form.
	 * @param principal			Lấy username người dùng.
	 * @param redirectAttributes	
	 * @return					redirect tới trang detailProject.
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/updateFeatures", method = RequestMethod.GET)
	public String updateFeatures(HttpServletRequest request, @RequestParam("nhomChucNang") String[] nhoms,
			@RequestParam("soLuongChucNang") String[] soLuong, @ModelAttribute FormChucNang form, Principal principal, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		
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
				if(form.getListGhiChu().length > 0){
					if(form.getListGhiChu()[dem] != null){
						currentChucNang.setGhichu(form.getListGhiChu()[dem]);
					}
				}
				currentChucNang.setNhomchucnang(nhomChucNang);
				chucNangDao.save(currentChucNang);
				dem++;
			}
		}
		redirectAttributes.addFlashAttribute("UpdateChucNangSuccess", "Cập nhật chức năng thành công!");
		redirectAttributes.addFlashAttribute("name", project.getTenproject());
		return "redirect:/detailProject";
	}
	/**
	 * @param request				Dùng để lấy biến từ trang view.
	 * @param redirectAttributes	Truyền các biến UpdateChuyenDoiSuccess, name qua trang redirect tới.
	 * @param principal				Dùng để lấy username người dùng.
	 * @param listNhomUCId			Danh sách các ID của nhóm Usecase.
	 * @param listNhomUCName		Danh sách các tên của nhóm Usecase.
	 * @param listUCId				Danh sách các ID của Usecase.
	 * @param listMoTaUC			Danh sách các mô tả Usecase.
	 * @param listBMT				Danh sách các BMT.
	 * @return						redirect tới trang detail.
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/updateChuyenDoiUsecase", method = RequestMethod.GET)
	public String updateChuyenDoiUsecase(HttpServletRequest request, RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam("listNhomUCId") Integer[] listNhomUCId, @RequestParam("listNhomUCName") String[] listNhomUCName, @RequestParam("listUCId") Integer[] listUCId, 
			@RequestParam("listUCName") String[] listUCName, @RequestParam("listMoTaUC") String[] listMoTaUC, @RequestParam("listBMT") Integer[] listBMT) throws UnsupportedEncodingException{
		String tenProject = request.getParameter("tenProject");
		Project project = projectDao.findProjectByName(principal.getName(), tenProject);
		for(int i = 0; i < listNhomUCId.length; i++){
			Nhomuc nhomUC = nhomucDao.getNhomucById(listNhomUCId[i]);
			nhomUC.setTennhom(listNhomUCName[i]);
			nhomucDao.update(nhomUC);
		}
		for(int i = 0; i < listUCId.length; i++){
			Usecase UC = usecaseDao.getUsecaseByID(listUCId[i]);
			UC.setNameofuc(listUCName[i]);
			UC.setMotauc(listMoTaUC[i]);
			Bmt bmt = bmtDao.getBmtById(listBMT[i]);
			UC.setBmt(bmt);
			usecaseDao.update(UC);
		}
		redirectAttributes.addFlashAttribute("UpdateChuyenDoiSuccess", "Cập nhật thành công!");
		redirectAttributes.addFlashAttribute("name", project.getTenproject());
		return "redirect:/detailProject";
	}
	
	/**
	 * 
	 * @param listGiaTri			Lấy biến giaTriXepHang từ trang view.
	 * @param listGhiChu			Lấy biến ghiChuKyThuat từ trang view.
	 * @param principal				Lấy username của người dùng.
	 * @param listIdHeSo			Lấy biến IDHeSo từ trang view.
	 * @param request				Dùng để lấy các biến từ trang view.
	 * @param redirectAttributes
	 * @return						redirect tới trang detailProject.
	 * @throws UnsupportedEncodingException 
	 */	
	@RequestMapping(value="/updateHeSoKyThuat", method = RequestMethod.GET)
	public String updateKyThuat(@RequestParam("giaTriXepHang") String[] listGiaTri, @RequestParam("ghiChuKyThuat") String[] listGhiChu,
			Principal principal,@RequestParam("IDHeSo") String[] listIdHeSo, HttpServletRequest request, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException{
		
		String projectName = request.getParameter("projectNameForKyThuat");
		Project project = projectDao.findProjectByName(principal.getName(), projectName);
		xepHangKyThuatDao.delete(project.getProjectid());
		for(int i = 0; i < listGiaTri.length; i++){
			XephangkythuatId xepHangId = new XephangkythuatId();
			xepHangId.setHesokythuatid(Integer.parseInt(listIdHeSo[i]));
			xepHangId.setProjectid(project.getProjectid());
			Xephangkythuat xepHang = new Xephangkythuat();
			xepHang.setId(xepHangId);
			xepHang.setGiatrixephang(Integer.parseInt(listGiaTri[i]));
			xepHang.setGhichu(listGhiChu[i]);
			xepHangKyThuatDao.save(xepHang);
		}
		redirectAttributes.addFlashAttribute("updateKyThuatSuccess","Cập nhật kỹ thuật công nghệ thành công!");
		redirectAttributes.addFlashAttribute("name", project.getTenproject());
		return "redirect:/detailProject";
	}
	/**
	 * 
	 * @param listGiaTri		Lấy giaTriXepHangMT từ trang view.
	 * @param listOnDinh		Lấy giaTriOnDinh từ trang view.
	 * @param principal			Dùng để lấy username của người dùng.
	 * @param listIdHeSo		Lấy IDMoiTruong từ trang view.
	 * @param request			Lấy các biến truyên từ trang view.
	 * @param redirectAttributes
	 * @return					redirect tới trang detailProject.
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/updateHeSoMoiTruong", method = RequestMethod.GET)
	public String updateMoiTruong(@RequestParam("giaTriXepHangMT") String[] listGiaTri, @RequestParam("giaTriOnDinh") String[] listOnDinh,
			Principal principal,@RequestParam("IDMoiTruong") String[] listIdHeSo, HttpServletRequest request, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException{
		
		String projectName = request.getParameter("projectNameForMoiTruong");
		Project project = projectDao.findProjectByName(principal.getName(), projectName);
		xepHangMoiTruongDao.delete(project.getProjectid());
		for(int i = 0; i < listGiaTri.length; i++){
			XephangmoitruongId xepHangId = new XephangmoitruongId();
			xepHangId.setHesomoitruongid(Integer.parseInt(listIdHeSo[i]));
			xepHangId.setProjectid(project.getProjectid());
			Xephangmoitruong xepHang = new Xephangmoitruong();
			xepHang.setId(xepHangId);
			xepHang.setGiatrixephang(Integer.parseInt(listGiaTri[i]));
			xepHang.setOndinh(Float.parseFloat(listOnDinh[i]));
			xepHangMoiTruongDao.save(xepHang);
		}
		redirectAttributes.addFlashAttribute("updateMoiTruongSuccess","Cập nhật thành công!");
		redirectAttributes.addFlashAttribute("name", project.getTenproject());
		return "redirect:/detailProject";
	}
	/**
	 * 
	 * @param request				Lấy các biến từ trang view.
	 * @param redirectAttributes
	 * @param principal				Dùng để lấy username của người dùng.
	 * @param luongTangThem			Lấy biến luongTangThem từ trang view.
	 * @param pcKhuVuc				Lấy biến pcKhuVuc từ trang view.
	 * @param pcLuuDong				Lấy biến pcLuuDong từ trang view.
	 * @param luongIdForBangLuong	Lấy biến luongIdForBangLuong từ trang view.
	 * @return						redirect tới trang detailProject.
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/updateBangLuong", method = RequestMethod.GET)
	public String updateBangLuong(HttpServletRequest request, RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam("luongTangThem") String[] luongTangThem, @RequestParam("pcKhuVuc") String[] pcKhuVuc, @RequestParam("pcLuuDong") String[] pcLuuDong,
			@RequestParam("luongIdForBangLuong") String[] luongIdForBangLuong) throws UnsupportedEncodingException{
		String projectName = request.getParameter("projectNameForLuong");
		Project project = projectDao.findProjectByName(principal.getName(), projectName);
		int luongNhaNuoc = Integer.parseInt(request.getParameter("mucLuongNhaNuoc"));
		project.setLuongcoban(luongNhaNuoc);
		String bacLuong = request.getParameter("chooseBacLuong");
		project.setBacluong(Integer.parseInt(bacLuong));
		giaTriLuongDao.delete(project.getProjectid());
		for(int i = 0; i < luongIdForBangLuong.length; i++){
			GiatriluongId giaTriId = new GiatriluongId();
			giaTriId.setLuongId(Integer.parseInt(luongIdForBangLuong[i]));
			giaTriId.setProjectid(project.getProjectid());
			Giatriluong giaTri = new Giatriluong();
			giaTri.setId(giaTriId);
			giaTri.setLuongtangthem(Integer.parseInt(luongTangThem[i]));
			giaTri.setPckhuvuc(Integer.parseInt(pcKhuVuc[i]));
			giaTri.setPcluudong(Integer.parseInt(pcLuuDong[i]));
			giaTriLuongDao.save(giaTri);
		}
		projectDao.save(project);
		redirectAttributes.addFlashAttribute("updateBangLuongSuccess","Cập nhật thành công!");
		redirectAttributes.addFlashAttribute("name", project.getTenproject());
		return "redirect:/detailProject";
		
	}
	/**
	 * 
	 * @param request			Dùng để lấy các biến từ trang view.
	 * @param principal			Dùng để lấy username từ người dùng.
	 * @param redirectAttributes
	 * @return					redirect tới trang detailProject.
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/updateGiaTriPhanMem", method = RequestMethod.GET)
	public String updateGiaTriPhanMem(HttpServletRequest request, Principal principal, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException{
		String projectName = request.getParameter("projectNameForGiaTriPhanMem");
		Project project = projectDao.findProjectByName(principal.getName(), projectName);
		String trongso = request.getParameter("selectNoLuc");
		project.setTrongsonoluc(trongsonolucDao.findByGiaTri(Double.parseDouble(trongso)));
		projectDao.save(project);
		redirectAttributes.addFlashAttribute("updateGiaTriPhanMemSuccess","Cập nhật thành công!");
		redirectAttributes.addFlashAttribute("name", project.getTenproject());
		return "redirect:/detailProject";
	}
	
}