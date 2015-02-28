package vn.com.luanvan.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.fabric.xmlrpc.base.Array;

import vn.com.luanvan.dao.ChucNangDao;
import vn.com.luanvan.dao.GiaTriLuongDao;
import vn.com.luanvan.dao.LuongDao;
import vn.com.luanvan.dao.MucDoDao;
import vn.com.luanvan.dao.NhomChucNangDao;
import vn.com.luanvan.dao.PhanLoaiChucNangDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.XepHangKyThuatDao;
import vn.com.luanvan.dao.XepHangMoiTruongDao;
import vn.com.luanvan.form.FormChucNang;
import vn.com.luanvan.model.Chucnang;
import vn.com.luanvan.model.Giatriluong;
import vn.com.luanvan.model.GiatriluongId;
import vn.com.luanvan.model.Hesokythuat;
import vn.com.luanvan.model.Luong;
import vn.com.luanvan.model.Nhomchucnang;
import vn.com.luanvan.model.Project;
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
	
	
	@RequestMapping(value="/updateFeatures", method = RequestMethod.GET)
		public String updateFeatures(HttpServletRequest request,@RequestParam("nhomChucNang") String[] nhoms,
				@RequestParam("soLuongChucNang") String[] soLuong, @ModelAttribute FormChucNang form, Principal principal, RedirectAttributes redirectAttributes){
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
	
	@RequestMapping(value="/updateHeSoKyThuat", method = RequestMethod.GET)
	public String updateKyThuat(@RequestParam("giaTriXepHang") String[] listGiaTri, @RequestParam("ghiChuKyThuat") String[] listGhiChu,
			Principal principal,@RequestParam("IDHeSo") String[] listIdHeSo, HttpServletRequest request, RedirectAttributes redirectAttributes){
		
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
		return "redirect:/detailProject/name="+project.getTenproject()+"";
	}
	
	@RequestMapping(value="/updateHeSoMoiTruong", method = RequestMethod.GET)
	public String updateMoiTruong(@RequestParam("giaTriXepHangMT") String[] listGiaTri, @RequestParam("giaTriOnDinh") String[] listOnDinh,
			Principal principal,@RequestParam("IDMoiTruong") String[] listIdHeSo, HttpServletRequest request, RedirectAttributes redirectAttributes){
		
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
		return "redirect:/detailProject/name="+project.getTenproject()+"";
	}
	@RequestMapping(value="/updateBangLuong", method = RequestMethod.GET)
	public String updateBangLuong(HttpServletRequest request, RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam("luongTangThem") String[] luongTangThem, @RequestParam("pcKhuVuc") String[] pcKhuVuc, @RequestParam("pcLuuDong") String[] pcLuuDong,
			@RequestParam("luongIdForBangLuong") String[] luongIdForBangLuong){
		String projectName = request.getParameter("projectNameForLuong");
		Project project = projectDao.findProjectByName(principal.getName(), projectName);
		int luongNhaNuoc = Integer.parseInt(request.getParameter("mucLuongNhaNuoc"));
		project.setLuongcoban(luongNhaNuoc);
		String bacLuong = request.getParameter("chooseBacLuong");
		project.setBacluong(Integer.parseInt(bacLuong));
		giaTriLuongDao.delete(project.getProjectid());
		for(int i = 0; i < luongTangThem.length; i++){
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
		
		return "redirect:/detailProject/name="+project.getTenproject()+"";
		
	}
	
	@RequestMapping(value="/updateGiaTriPhanMem", method = RequestMethod.GET)
	public String updateGiaTriPhanMem(HttpServletRequest request, Principal principal){
		String projectName = request.getParameter("projectNameForGiaTriPhanMem");
		Project project = projectDao.findProjectByName(principal.getName(), projectName);
		String trongso = request.getParameter("selectNoLuc");
		project.setTrongsonoluc(Double.parseDouble(trongso));
		projectDao.save(project);
		return "redirect:/detailProject/name="+project.getTenproject()+"";
	}
	
}