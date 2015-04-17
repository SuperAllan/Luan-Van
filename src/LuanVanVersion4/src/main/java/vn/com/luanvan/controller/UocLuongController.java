package vn.com.luanvan.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




import vn.com.luanvan.dao.BmtDao;
import vn.com.luanvan.dao.ChucNangDao;
import vn.com.luanvan.dao.GiaTriLuongDao;
import vn.com.luanvan.dao.HeSoMoiTruongDao;
import vn.com.luanvan.dao.LuongDao;
import vn.com.luanvan.dao.MucDoDao;
import vn.com.luanvan.dao.NhomChucNangDao;
import vn.com.luanvan.dao.NhomucDao;
import vn.com.luanvan.dao.PhanLoaiChucNangDao;
import vn.com.luanvan.dao.PhichucnangDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.TrongsonolucDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.dao.XepHangKyThuatDao;
import vn.com.luanvan.dao.XepHangMoiTruongDao;
import vn.com.luanvan.model.Bmt;
import vn.com.luanvan.model.Chucnang;
import vn.com.luanvan.model.Giatriluong;
import vn.com.luanvan.model.GiatriluongId;
import vn.com.luanvan.model.Hesomoitruong;
import vn.com.luanvan.model.Mucdo;
import vn.com.luanvan.model.Nhomchucnang;
import vn.com.luanvan.model.Nhomuc;
import vn.com.luanvan.model.Phanloaichucnang;
import vn.com.luanvan.model.Phichucnang;
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
	@Autowired
	private PhichucnangDao phichucnangDao;
	@Autowired
	private HeSoMoiTruongDao heSoMoiTruongDao;
	
	
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
	@RequestMapping(value="/themNhomChucNang", method = RequestMethod.GET)
	public String themNhomChucNang(HttpServletRequest request, @RequestParam("parentID") Integer parentID,
			Principal principal, RedirectAttributes redirectAttributes, Model model){
		String tenProject = request.getParameter("tenProject");
		String nhoms = request.getParameter("nhomChucNang");
		Project project = projectDao.findProjectByName(principal.getName(), tenProject);
		Nhomchucnang nhom = new Nhomchucnang();
		if(parentID != null){
			nhom = nhomChucNangDao.findNhomChucNangByID(parentID);
			nhom.setTennhom(nhoms);
			nhomChucNangDao.update(nhom);
		}else{
			if(!nhoms.equals("null") && nhoms != null){
			nhom.setTennhom(nhoms);
			nhom.setProject(project);
			nhomChucNangDao.save(nhom);
			}
		}
		
		List<Phanloaichucnang> listPhanLoai = phanLoaiChucNangDao.getListTenLoai();
		List<Mucdo> listMucDo = mucDoDao.getListMucDo();
		model.addAttribute("listPhanLoai", listPhanLoai);
		model.addAttribute("listMucDo", listMucDo);
		model.addAttribute("listNhomChucNang", nhomChucNangDao.getListNhomChucNang(project));
		return "show-chucnang";
	}
	
	@RequestMapping(value="/xoaNhomChucNang", method = RequestMethod.GET)
	public @ResponseBody String xoaNhomChucNang(HttpServletRequest request, @RequestParam("nhomChucNangID") Integer nhomID,
			Principal principal, RedirectAttributes redirectAttributes, Model model){
		Nhomchucnang nhom = nhomChucNangDao.findNhomChucNangByID(nhomID);
		String str =  "{\"chucnangs\":[ ";
		if(nhom.getChucnangs().size() > 0){
			
			for(Chucnang chucs : nhom.getChucnangs()){
				//str+="\"value\":"+chucs.getMayeucau()+",";
				str+="{\"value\":" + chucs.getMayeucau() +"},";
			}
			StringBuilder b = new StringBuilder(str);
			b.replace(str.lastIndexOf(","), str.lastIndexOf(",")+1,"");
			str = b.toString();
		}
		str += "]}";
		nhomChucNangDao.delete(nhom);
		return str;
	}
	
	@RequestMapping(value="/themChucNang", method = RequestMethod.GET)
	public String themChucNang(HttpServletRequest request, @RequestParam("chucNang") String chucNang, @RequestParam("nhomChucNangID") Integer nhomID,
			@RequestParam("parentID") Integer parentID, Principal principal, RedirectAttributes redirectAttributes, Model model) {
		String tenProject = request.getParameter("tenProject");
		Project project = projectDao.findProjectByName(principal.getName(), tenProject);
		Nhomchucnang nhom = nhomChucNangDao.findNhomChucNangByID(nhomID);
		Chucnang chucs;
		if(parentID != null){
			chucs = chucNangDao.findChucNangByID(parentID);
			chucs.setMotayeucau(chucNang);
			chucNangDao.update(chucs);
		}else{
			chucs = new Chucnang();
			chucs.setMotayeucau(chucNang);
			chucs.setNhomchucnang(nhom);
			chucs.setMucdo(mucDoDao.findMucDoByID(1));
			chucs.setPhanloaichucnang(phanLoaiChucNangDao.findPhanLoaiByID(1));
			chucNangDao.save(chucs);
		}
		List<Phanloaichucnang> listPhanLoai = phanLoaiChucNangDao.getListTenLoai();
		List<Mucdo> listMucDo = mucDoDao.getListMucDo();
		model.addAttribute("listPhanLoai", listPhanLoai);
		model.addAttribute("listMucDo", listMucDo);
		model.addAttribute("listNhomChucNang", nhomChucNangDao.getListNhomChucNang(project));
		return "show-chucnang";
	}
	
	@RequestMapping(value="/xoaChucNang", method = RequestMethod.GET)
	public @ResponseBody String xoaChucNang(HttpServletRequest request, @RequestParam("chucNangID") Integer chucID,
			Principal principal, RedirectAttributes redirectAttributes, Model model){
		String str = "OKOKOK";
		Chucnang chucs = chucNangDao.findChucNangByID(chucID);
		chucNangDao.delete(chucs);
		return str;
	}
	
	@RequestMapping(value="/updatePhanLoai", method = RequestMethod.GET)
	public @ResponseBody void updatePhanLoai(HttpServletRequest request, @RequestParam("chucNangID") Integer chucID, @RequestParam("phanLoai") Integer phanLoai,
			Principal principal, RedirectAttributes redirectAttributes, Model model){
		Phanloaichucnang loai = phanLoaiChucNangDao.findPhanLoaiByID(phanLoai);
		Chucnang chucs = chucNangDao.findChucNangByID(chucID);
		chucs.setPhanloaichucnang(loai);
		chucNangDao.update(chucs);
	}
	
	@RequestMapping(value="/updateMucDo", method = RequestMethod.GET)
	public @ResponseBody void updateMucDo(HttpServletRequest request, @RequestParam("chucNangID") Integer chucID, @RequestParam("mucDo") Integer mucDo,
			Principal principal, RedirectAttributes redirectAttributes, Model model){
		Mucdo mucs = mucDoDao.findMucDoByID(mucDo);
		Chucnang chucs = chucNangDao.findChucNangByID(chucID);
		chucs.setMucdo(mucs);;
		chucNangDao.update(chucs);
	}
	
	@RequestMapping(value="/updateGhiChu", method = RequestMethod.GET)
	public @ResponseBody void updateGhiChu(HttpServletRequest request, @RequestParam("chucNangID") Integer chucID, @RequestParam("ghiChu") String ghiChu,
			Principal principal, RedirectAttributes redirectAttributes, Model model){
		Chucnang chucs = chucNangDao.findChucNangByID(chucID);
		chucs.setGhichu(ghiChu);
		chucNangDao.update(chucs);
	}
	
	@RequestMapping(value="/updatePhiChucNang", method = RequestMethod.GET)
	public String updatePhiChucNang(@RequestParam("listPhiChucNang")String[] listPhiChucNang, Principal principal, HttpServletRequest request,
			RedirectAttributes redirectAttributes){
		String tenProject = request.getParameter("tenProject");
		Project project = projectDao.findProjectByName(principal.getName(), tenProject);
		int projectID = project.getProjectid();
		if(phichucnangDao.getListPhiChucNangByProjectID(projectID).size() > 0){
			phichucnangDao.deleteAll(projectID);
		}
		Phichucnang phiChucNang = new Phichucnang();
		phiChucNang.setProject(project);
		for(int i = 0; i < listPhiChucNang.length; i++){
			phiChucNang.setMotayeucau(listPhiChucNang[i]);
			phichucnangDao.save(phiChucNang);
		}
		redirectAttributes.addFlashAttribute("UpdatePhiChucNangSuccess", "Cập nhật phi chức năng thành công!");
		redirectAttributes.addFlashAttribute("name", tenProject);
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
			@RequestParam("listUCName") String[] listUCName, @RequestParam("listMoTaUC") String[] listMoTaUC, @RequestParam("listBMT") Integer[] listBMT, @RequestParam("listTinhTien") Boolean[] listTinhTien) throws UnsupportedEncodingException{
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
			UC.setTinhtien(listTinhTien[i]);
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
	public String updateMoiTruong(@RequestParam("giaTriXepHangMT") Integer[] listGiaTri, 
			Principal principal,@RequestParam("IDMoiTruong") Integer[] listIdHeSo, HttpServletRequest request, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException{
		
		String projectName = request.getParameter("projectNameForMoiTruong");
		Project project = projectDao.findProjectByName(principal.getName(), projectName);
		xepHangMoiTruongDao.delete(project.getProjectid());
		List<Hesomoitruong> listHSMT = heSoMoiTruongDao.getListHeSoMoiTruong();
		for(int i = 0; i < listGiaTri.length; i++){
			XephangmoitruongId xepHangId = new XephangmoitruongId();
			xepHangId.setHesomoitruongid(listIdHeSo[i]);
			xepHangId.setProjectid(project.getProjectid());
			Xephangmoitruong xepHang = new Xephangmoitruong();
			xepHang.setId(xepHangId);
			int giatri = listGiaTri[i];
			xepHang.setGiatrixephang(giatri);
			float ketqua = listHSMT.get(i).getTrongso() * giatri;
			if(ketqua <= 0 ){
				xepHang.setOndinh(0);
			}else if(ketqua > 0 && ketqua <= 1){
				xepHang.setOndinh(0.05f);
			}else if(ketqua > 1 && ketqua <= 2){
				xepHang.setOndinh(0.1f);
			}else if(ketqua > 2 && ketqua <= 3){
				xepHang.setOndinh(0.6f);	
			}else{
				xepHang.setOndinh(1);
			}
			//xepHang.setOndinh();
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
		project.setLuong(luongDao.findLuongByBac(Integer.parseInt(bacLuong)));
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