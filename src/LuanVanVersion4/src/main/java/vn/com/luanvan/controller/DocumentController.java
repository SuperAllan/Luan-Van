package vn.com.luanvan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import vn.com.luanvan.dao.NhomucDao;
import vn.com.luanvan.dao.PhanLoaiChucNangDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.TrongsonolucDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.dao.UserRoleDao;
import vn.com.luanvan.dao.XepHangKyThuatDao;
import vn.com.luanvan.dao.XepHangMoiTruongDao;
import vn.com.luanvan.model.Bmt;
import vn.com.luanvan.model.Chucnang;
import vn.com.luanvan.model.DocumentExcel;
import vn.com.luanvan.model.Giatriluong;
import vn.com.luanvan.model.Hesokythuat;
import vn.com.luanvan.model.Hesomoitruong;
import vn.com.luanvan.model.Loaiactor;
import vn.com.luanvan.model.Luong;
import vn.com.luanvan.model.Nhomchucnang;
import vn.com.luanvan.model.Nhomuc;
import vn.com.luanvan.model.Phanloai;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.Usecase;
import vn.com.luanvan.model.User;
import vn.com.luanvan.model.Xephangkythuat;
import vn.com.luanvan.model.XephangkythuatId;
import vn.com.luanvan.model.Xephangmoitruong;

import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.Cell;
import com.lowagie.text.Paragraph;


@Controller
public class DocumentController{
	@Autowired
	private ServletContext servletContext;
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
	
	/**
	 * Hàm xuất file PDF 
	 * @param projectName	Tên của dự án
	 * @param request		
	 * @param response	
	 * @param principal		
	 * @return				Trả về trang pdfView với biến lists.
	 */
	@RequestMapping(value="/downloadPDF", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView downloadPFD(HttpServletRequest request, HttpServletResponse response, Principal principal){
		String projectName = request.getParameter("project");
		Project project = projectDao.findProjectByName(principal.getName(), projectName); 
		List<Object> lists = new ArrayList<Object>();
		lists.add(project);
		return new ModelAndView("pdfView", "lists", lists); 

	}
	
	@Autowired(required = false)
	@SuppressWarnings({ "unused" })
	@RequestMapping(value="/downloadDoc", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public void downloadDoc(HttpServletRequest request, HttpServletResponse response, Principal principal) throws IOException, XmlException{
		String path = request.getSession().getServletContext().getRealPath("/");
		User user  = userDao.findUserbyUserName(principal.getName());
		String projectName = request.getParameter("project");
		Project project = projectDao.findProjectByName(user.getUsername(), projectName);
		int projectID = project.getProjectid();
		String Times = "Times New Roman";
		
		//Tao bang yeu cau chuc nang
		List<Nhomchucnang> nhomChucNang = nhomChucNangDao.getListNhomChucNang(project);
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		XWPFDocument document = new XWPFDocument(new FileInputStream(path+"resources/document/Template.docx"));
		
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFParagraph paragraphRight = document.createParagraph();
		paragraphRight.setAlignment(ParagraphAlignment.RIGHT);
		
		XWPFRun title = paragraph.createRun();
		title.setText("Trường Đại Học Cần Thơ");
		title.addBreak();
		title.setText("Trung Tâm Điện Tử Tin Học");
		title.setBold(true);
		title.setFontFamily("Times New Roman");
		title.setFontSize(14);
		title.addBreak();
		
		XWPFRun address = paragraph.createRun();
		address.setText("Địa chỉ: "+user.getAddress());
		address.addBreak();
		address.setText("Điện thoại: "+user.getPhone());
		address.setItalic(true);
		address.setFontFamily(Times);
		address.setFontSize(12);
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		address.addBreak();
		XWPFRun nameOfProject = paragraph.createRun();
		nameOfProject.setText("Dự toán phần mềm");
		nameOfProject.addBreak();
		nameOfProject.setText(project.getTenproject());
		nameOfProject.setBold(true);
		nameOfProject.setFontFamily(Times);
		nameOfProject.setFontSize(14);
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		nameOfProject.addBreak();
		
		XWPFRun createTime = paragraphRight.createRun();
		createTime.setText("Phiên bản x.x");
		createTime.addBreak();
		createTime.setText(dateFormat.format(currentDate));
		createTime.setBold(true);
		createTime.setFontFamily(Times);
		createTime.setFontSize(12);
		paragraph.isPageBreak();
		
		XWPFParagraph heading1ChucNang = document.createParagraph();
		heading1ChucNang.setStyle("Heading1");
		XWPFRun contentUocLuong = heading1ChucNang.createRun();
		contentUocLuong.setText("2. Ước lượng giá trị  phần mềm");
		XWPFParagraph heading2ChucNang = document.createParagraph();
		heading2ChucNang.setStyle("Heading2");
		XWPFRun runBangChucNang = heading2ChucNang.createRun();
		runBangChucNang.setText("2.1 Bảng sắp xếp thứ tự ưu tiên các yêu cầu chức năng của phần mềm");
		XWPFParagraph contentBangChucNang = document.createParagraph();
		contentBangChucNang.setSpacingAfter(12);
		XWPFRun runContentBangChucNang =  contentBangChucNang.createRun();
		runContentBangChucNang.setFontFamily(Times);
		runContentBangChucNang.setFontSize(13);
		runContentBangChucNang.setText("Phần mêm cần xây dựng các nhóm chức năng sau:");
		runContentBangChucNang.addBreak();
		for(Nhomchucnang nhom : nhomChucNang){
			runContentBangChucNang.setText("   - "+nhom.getTennhom());
			runContentBangChucNang.addBreak();
		}
		runContentBangChucNang.setText("Sau đây là các chức năng cụ thể của các nhóm chức năng");
		
		XWPFTable tableChucNang = document.createTable(1,4);
		tableChucNang.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
		XWPFTableRow row0ChucNang = tableChucNang.getRow(0);
		List<XWPFTableCell> cell0 = row0ChucNang.getTableCells();
		for(int i = 0; i < cell0.size(); i++){
		    XWPFParagraph para = row0ChucNang.getCell(i).getParagraphs().get(0);
	        para.setAlignment(ParagraphAlignment.CENTER);
	        para.setSpacingBefore(120);
	        para.setSpacingAfter(120);
	        para.setStyle("Strong");
	        XWPFRun rh = para.createRun();
	        rh.setBold(true);
	        rh.setFontSize(13);
			rh.setFontFamily(Times);
			if(i == 0){
				rh.setText("TT");
			}else
			if( i == 1){
				rh.setText("Mô tả yêu cầu");
			}else
			if( i == 2){
				rh.setText("Phân loại");
			}else{
				rh.setText("Mức độ");
			}
		}
		
		List<Integer> listRow = new ArrayList<Integer>();
		// May need to call this method, not sure 
		int countRow = 1;
		int stt = 1; 
		for(Nhomchucnang nhomCN : nhomChucNang){
			XWPFTableRow nhomCNRow = tableChucNang.createRow();
			XWPFParagraph para =  nhomCNRow.getCell(0).getParagraphs().get(0);
			XWPFRun rh = para.createRun();
			para.setAlignment(ParagraphAlignment.LEFT);
			para.setSpacingBefore(120);
			rh.setText(nhomCN.getTennhom());
			rh.setFontSize(13);
			rh.setBold(true);
			rh.setFontFamily(Times);
			listRow.add(countRow);
		    countRow++;
			for(Chucnang chucNang : nhomCN.getChucnangs()){
				XWPFTableRow chucNangRow = tableChucNang.createRow();
				
				XWPFParagraph para1 = chucNangRow.getCell(0).getParagraphs().get(0);
				XWPFRun rh1 = para1.createRun();
				para1.setAlignment(ParagraphAlignment.CENTER);
				para1.setSpacingBefore(120);
				rh1.setText(String.valueOf(stt));
				rh1.setFontSize(13);
				rh1.setFontFamily(Times);
				
				para1 = chucNangRow.getCell(1).getParagraphs().get(0);
				rh1 = para1.createRun();
				para1.setAlignment(ParagraphAlignment.LEFT);
				para1.setSpacingBefore(120);
				rh1.setText(chucNang.getMotayeucau());
				rh1.setFontSize(13);
				rh1.setFontFamily(Times);
				//
				para1 = chucNangRow.getCell(2).getParagraphs().get(0);
				rh1 = para1.createRun();
				para1.setAlignment(ParagraphAlignment.LEFT);
				para1.setSpacingBefore(120);
				rh1.setText(chucNang.getPhanloaichucnang().getTenloai());
				rh1.setFontSize(13);
				rh1.setFontFamily(Times);
				
				para1 = chucNangRow.getCell(3).getParagraphs().get(0);
				rh1 = para1.createRun();
				para1.setAlignment(ParagraphAlignment.LEFT);
				para1.setSpacingBefore(120);
				rh1.setText(chucNang.getMucdo().getMotamucdo());
				rh1.setFontSize(13);
				rh1.setFontFamily(Times);
				stt++;
				countRow++;
			}
		}
     
		for(Integer count: listRow){
			XWPFTableRow row = tableChucNang.getRow(count);
			XWPFTableCell cell = row.getCell(0);
			cell.getCTTc().addNewTcPr();
			cell.getCTTc().getTcPr().addNewGridSpan();
			cell.getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf(4L));
			for(int i = 1; i < 3; i++){
			XWPFTableCell removed = row.getCell(i);
			removed.getCTTc().newCursor().removeXml();
			row.removeCell(i);
			}
		}
		for(Integer count : listRow){
			XWPFTableRow row = tableChucNang.getRow(count);
			XWPFTableCell removed = row.getCell(1);
			removed.getCTTc().newCursor().removeXml();
			row.removeCell(1);
		}
		XWPFParagraph heading2ChuyenDoi = document.createParagraph();
		heading2ChuyenDoi.setStyle("Heading2");
		XWPFRun runChuyenDoi = heading2ChuyenDoi.createRun();
		runChuyenDoi.setText("2.2 Bảng chuyển đổi yêu cầu chức năng sang trường hợp sử dụng");
		XWPFTable tableChuyenDoi = document.createTable(1,6);
		tableChuyenDoi.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
		XWPFTableRow row0ChuyenDoi = tableChuyenDoi.getRow(0);
		List<XWPFTableCell> cell0ChuyenDoi = row0ChuyenDoi.getTableCells();
		for(int i = 0; i < cell0ChuyenDoi.size(); i++){
		    XWPFParagraph para = row0ChuyenDoi.getCell(i).getParagraphs().get(0);
	        para.setAlignment(ParagraphAlignment.CENTER);
	        para.setSpacingBefore(120);
	        para.setSpacingAfter(120);
	        XWPFRun rh = para.createRun();
	        rh.setBold(true);
	        rh.setFontSize(13);
			rh.setFontFamily(Times);
			if(i == 0){
				rh.setText("TT");
			}else
			if(i == 1){
				rh.setText("Tên Use-case");
			}else
			if(i == 2){
				rh.setText("Tác nhân chính");
			}else
			if(i == 3){
				rh.setText("Tác nhân phụ");
			}else
			if(i == 4){
				rh.setText("Mô tả Use-case");
			}else{
				rh.setText("Mức độ cần thiết");
			}
		}
		List<Nhomuc> nhomUC = nhomucDao.getNhomucByProject(projectID);
		List<Integer> listRowChuyenDoiRemoved = new ArrayList<Integer>();
		// May need to call this method, not sure 
		countRow = 1;
		stt = 1; 
		for(Nhomuc nhom : nhomUC){
			XWPFTableRow RowChuyenDoi = tableChuyenDoi.createRow();
			XWPFParagraph para =  RowChuyenDoi.getCell(0).getParagraphs().get(0);
			XWPFRun rh = para.createRun();
			para.setAlignment(ParagraphAlignment.LEFT);
			para.setSpacingBefore(120);
			rh.setText(nhom.getTennhom());
			rh.setFontSize(13);
			rh.setBold(true);
			rh.setFontFamily(Times);
			listRowChuyenDoiRemoved.add(countRow);
		    countRow++;
			for(Usecase usecase : nhom.getUsecases()){
				XWPFTableRow usecaseRow = tableChuyenDoi.createRow();
				XWPFParagraph para1 = usecaseRow.getCell(0).getParagraphs().get(0);
				XWPFRun rh1 = para1.createRun();
				para1.setAlignment(ParagraphAlignment.CENTER);
				para1.setSpacingBefore(120);
				rh1.setText(String.valueOf(stt));
				rh1.setFontSize(13);
				rh1.setBold(true);
				rh1.setFontFamily(Times);
				
				para1 = usecaseRow.getCell(1).getParagraphs().get(0);
				rh1 = para1.createRun();
				para1.setAlignment(ParagraphAlignment.LEFT);
				para1.setSpacingBefore(120);
				rh1.setText(usecase.getNameofuc());
				rh1.setFontSize(13);
				rh1.setFontFamily(Times);
				//
				para1 = usecaseRow.getCell(2).getParagraphs().get(0);
				rh1 = para1.createRun();
				para1.setAlignment(ParagraphAlignment.LEFT);
				para1.setSpacingBefore(120);
				String phanloaiChinh = "";
				Set<Phanloai> phanLoai =  usecase.getPhanloais();
				for(Phanloai pl : phanLoai){
					if(pl.getVaitro() == 1){
						phanloaiChinh += pl.getActor().getNameofactor()+", ";
					}
				}
				rh1.setText(phanloaiChinh);
				rh1.setFontSize(13);
				rh1.setUnderline(UnderlinePatterns.SINGLE);
				rh1.setFontFamily(Times);
				
				para1 = usecaseRow.getCell(3).getParagraphs().get(0);
				rh1 = para1.createRun();
				para1.setAlignment(ParagraphAlignment.LEFT);
				para1.setSpacingBefore(120);
				String phanloaiPhu = "";
				for(Phanloai pl : phanLoai){
					if(pl.getVaitro() == 0){
						phanloaiPhu += pl.getActor().getNameofactor()+", ";
					}
				}
				rh1.setText(phanloaiPhu);
				rh1.setFontSize(13);
				rh1.setFontFamily(Times);
				
				para1 = usecaseRow.getCell(4).getParagraphs().get(0);
				rh1 = para1.createRun();
				para1.setAlignment(ParagraphAlignment.LEFT);
				para1.setSpacingBefore(120);
				rh1.setText(usecase.getMotauc());
				rh1.setFontSize(13);
				rh1.setFontFamily(Times);
				//
				//
				para1 = usecaseRow.getCell(5).getParagraphs().get(0);
				rh1 = para1.createRun();
				para1.setAlignment(ParagraphAlignment.LEFT);
				para1.setSpacingBefore(120);
				rh1.setText(usecase.getBmt().getTen());
				rh1.setFontSize(13);
				rh1.setFontFamily(Times);
				
				stt++;
				countRow++;
			}
		}
		//Xoa cac merge du thua bang Chuyen Doi Usecase
		for(Integer count: listRowChuyenDoiRemoved){
			XWPFTableRow row = tableChuyenDoi.getRow(count);
			XWPFTableCell cell = row.getCell(0);
			cell.getCTTc().addNewTcPr();
			cell.getCTTc().getTcPr().addNewGridSpan();
			cell.getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf(7L));
			for(int i = 1; i < 4; i++){
			XWPFTableCell removed = row.getCell(i);
			removed.getCTTc().newCursor().removeXml();
			row.removeCell(i);
			}
		}
		// xoa merge lan cuoi
		for(Integer count : listRowChuyenDoiRemoved){
			XWPFTableRow row = tableChuyenDoi.getRow(count);
			XWPFTableCell removed = row.getCell(1);
			removed.getCTTc().newCursor().removeXml();
			row.removeCell(1);
		}
		// xoa merge lan cuoi
		for(Integer count : listRowChuyenDoiRemoved){
			XWPFTableRow row = tableChuyenDoi.getRow(count);
			XWPFTableCell removed = row.getCell(1);
			removed.getCTTc().newCursor().removeXml();
			row.removeCell(1);
		}
		// Tao bang tinh diem tac nhan
		XWPFParagraph heading2TacNhan = document.createParagraph();
		heading2TacNhan.setStyle("Heading2");
		XWPFRun runTacNhan = heading2TacNhan.createRun();
		runTacNhan.setText("2.3 Bảng tính toán điểm các tác nhân (actors) tương tác, trao đổi thông tin với phần mềm");
		XWPFTable tableTacNhan = document.createTable(1,6);
		tableTacNhan.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
		XWPFTableRow row0TacNhan= tableTacNhan.getRow(0);
		List<XWPFTableCell> cell0TacNhan = row0TacNhan.getTableCells();
		for(int i = 0; i < cell0TacNhan.size(); i++){
		    XWPFParagraph para = row0TacNhan.getCell(i).getParagraphs().get(0);
	        para.setAlignment(ParagraphAlignment.CENTER);
	        para.setSpacingBefore(120);
	        para.setSpacingAfter(120);
	        XWPFRun rh = para.createRun();
	        rh.setBold(true);
	        rh.setFontSize(13);
			rh.setFontFamily(Times);
			if(i == 0){
				rh.setText("TT");
			}else
			if(i == 1){
				rh.setText("Loại Actor");
			}else
			if(i == 2){
				rh.setText("Mô tả");
			}else
			if(i == 3){
				rh.setText("Số tác nhân");
			}else
			if(i == 4){
				rh.setText("Trọng số");
			}else{
				rh.setText("Điểm của từng loại tác nhân");
			}
		}
		List<Loaiactor> loaiActor = loaiactorDao.getAll();
		List<Integer> countActor = actorDao.countActor(projectID);
		List<Integer> diemActor = actorDao.tinhDiemTungActor(projectID, loaiActor);
		List<Integer> listRowTacNhanRemoved = new ArrayList<Integer>();
		// May need to call this method, not sure 
		for(int i = 0; i < loaiActor.size(); i++){
			XWPFTableRow rowTacNhan = tableTacNhan.createRow();
			// column stt
			XWPFParagraph para =  rowTacNhan.getCell(0).getParagraphs().get(0);
			XWPFRun rh = para.createRun();
			para.setAlignment(ParagraphAlignment.CENTER);
			para.setSpacingBefore(120);
			rh.setText(String.valueOf(loaiActor.get(i).getLoaiactorid()));
			rh.setFontSize(13);
			rh.setFontFamily(Times);
			// column loai
			para =  rowTacNhan.getCell(1).getParagraphs().get(0);
			rh = para.createRun();
			para.setAlignment(ParagraphAlignment.LEFT);
			para.setSpacingBefore(120);
			rh.setText(loaiActor.get(i).getLoai());
			rh.setFontSize(13);
			rh.setFontFamily("Times New Roman");
			//column mo ta loai actor
			para =  rowTacNhan.getCell(2).getParagraphs().get(0);
			rh = para.createRun();
			para.setAlignment(ParagraphAlignment.LEFT);
			para.setSpacingBefore(120);
			rh.setText(loaiActor.get(i).getMotaloaiactor());
			rh.setFontSize(13);
			rh.setFontFamily(Times);
			//so tac nhan 
			para =  rowTacNhan.getCell(3).getParagraphs().get(0);
			rh = para.createRun();
			para.setAlignment(ParagraphAlignment.CENTER);
			para.setSpacingBefore(120);
			rh.setText(String.valueOf(countActor.get(i)));
			rh.setFontSize(13);
			rh.setFontFamily(Times);
			// trong so cua tac nhan
			para =  rowTacNhan.getCell(4).getParagraphs().get(0);
			rh = para.createRun();
			para.setAlignment(ParagraphAlignment.CENTER);
			para.setSpacingBefore(120);
			rh.setText(String.valueOf(loaiActor.get(i).getTrongso()));
			rh.setFontSize(13);
			rh.setFontFamily(Times);
			// tinh diem tac nhan
			para =  rowTacNhan.getCell(5).getParagraphs().get(0);
			rh = para.createRun();
			para.setAlignment(ParagraphAlignment.CENTER);
			para.setSpacingBefore(120);
			rh.setText(String.valueOf(diemActor.get(i)));
			rh.setFontSize(13);
			rh.setFontFamily(Times);
		}
		
		// Tao bang truong hop su dung
		XWPFParagraph heading2TruongHop = document.createParagraph();
		heading2TruongHop.setStyle("Heading2");
		XWPFRun runTruongHop = heading2TruongHop.createRun();
		runTruongHop.setText("2.4 Bảng tính toán điểm các trường hợp sử dụng");
		XWPFTable tableTruongHop = document.createTable(1,6);
		tableTruongHop.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
		XWPFTableRow row0TruongHop= tableTruongHop.getRow(0);
		List<XWPFTableCell> cell0TruongHop = row0TruongHop.getTableCells();
		for(int i = 0; i < cell0TruongHop.size(); i++){
		    XWPFParagraph para = row0TruongHop.getCell(i).getParagraphs().get(0);
	        para.setAlignment(ParagraphAlignment.CENTER);
	        para.setSpacingBefore(120);
	        para.setSpacingAfter(120);
	        XWPFRun rh = para.createRun();
	        rh.setBold(true);
	        rh.setFontSize(13);
			rh.setFontFamily(Times);
			if(i == 0){
				rh.setText("TT");
			}else
			if(i == 1){
				rh.setText("Loại");
			}else
			if(i == 2){
				rh.setText("Số trường hợp sử dụng");
			}else
			if(i == 3){
				rh.setText("Trọng số");
			}else
			if(i == 4){
				rh.setText("Hệ số BMT");
			}else{
				rh.setText("Điểm của từng loại Use-case");
			}
		}
		List<Bmt> listBmt = bmtDao.getAll();
		List<Integer> countBmt = usecaseDao.countBMT(projectID);
		List<Integer> diemBmt = usecaseDao.tinhDiemTungUsecase(projectID, listBmt);
		int tongBMT = usecaseDao.tongBMT(projectID);
		int tongDiemBMT = usecaseDao.tongDiemTungUsecase(projectID, listBmt);
		stt = 1;
		for(int i = 0; i < listBmt.size(); i++){
			if (i == 0 || i == 3 || i == 6){
				XWPFTableRow rowTruongHop = tableTruongHop.createRow();
				XWPFParagraph para = rowTruongHop.getCell(0).getParagraphs().get(0);
		        para.setAlignment(ParagraphAlignment.CENTER);
		        para.setSpacingBefore(120);
		        XWPFRun rh = para.createRun();
		        rh.setFontSize(13);
				rh.setFontFamily(Times);
				rh.setText(String.valueOf(stt));
				
				para = rowTruongHop.getCell(1).getParagraphs().get(0);
		        para.setAlignment(ParagraphAlignment.CENTER);
		        para.setSpacingBefore(120);
		        rh = para.createRun();
		        rh.setFontSize(13);
		        rh.setBold(true);
				rh.setFontFamily(Times);
				if(i == 0){
					rh.setText("B");
				}else
				if(i == 3){
					rh.setText("M");
				}else{
					rh.setText("T");
				}
				para = rowTruongHop.getCell(4).getParagraphs().get(0);
		        para.setAlignment(ParagraphAlignment.CENTER);
		        para.setSpacingBefore(120);
		        rh = para.createRun();
		        rh.setFontSize(13);
		        rh.setBold(true);
				rh.setFontFamily(Times);
				if(i == 0){
					rh.setText("1");
				}else
				if(i == 3){
					rh.setText("1.2");
				}else{
					rh.setText("1.5");
				}
				stt++;
			}
			XWPFTableRow rowTruongHop1 = tableTruongHop.createRow();
			XWPFParagraph para1 = rowTruongHop1.getCell(1).getParagraphs().get(0);
	        para1.setAlignment(ParagraphAlignment.LEFT);
	        para1.setSpacingBefore(120);
	        XWPFRun rh1 = para1.createRun();
	        rh1.setFontSize(13);
			rh1.setFontFamily(Times);
			rh1.setText(listBmt.get(i).getTen());
			
			para1 = rowTruongHop1.getCell(2).getParagraphs().get(0);
	        para1.setAlignment(ParagraphAlignment.CENTER);
	        para1.setSpacingBefore(120);
	        rh1 = para1.createRun();
	        rh1.setBold(true);
	        rh1.setFontSize(13);
			rh1.setFontFamily(Times);
			rh1.setText(String.valueOf(countBmt.get(i)));
			
			para1 = rowTruongHop1.getCell(3).getParagraphs().get(0);
	        para1.setAlignment(ParagraphAlignment.CENTER);
	        para1.setSpacingBefore(120);
	        rh1 = para1.createRun();
	        rh1.setFontSize(13);
			rh1.setFontFamily(Times);
			rh1.setText(String.valueOf(listBmt.get(i).getTrongso()));
			
			para1 = rowTruongHop1.getCell(5).getParagraphs().get(0);
	        para1.setAlignment(ParagraphAlignment.CENTER);
	        para1.setSpacingBefore(120);
	        rh1 = para1.createRun();
	        rh1.setFontSize(13);
			rh1.setFontFamily(Times);
			rh1.setText(String.valueOf(diemBmt.get(i)));
		}
		//row tong cong trong tinh diem usecase (row cuoi cung)
		XWPFTableRow rowTruongHop = tableTruongHop.createRow();
		XWPFParagraph para = rowTruongHop.getCell(1).getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        para.setSpacingBefore(120);
        XWPFRun rh = para.createRun();
        rh.setFontSize(13);
        rh.setBold(true);
		rh.setFontFamily(Times);
		rh.setText("Cộng (1+2+3)");
		
		para = rowTruongHop.getCell(2).getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        para.setSpacingBefore(120);
        rh = para.createRun();
        rh.setFontSize(13);
        rh.setBold(true);
		rh.setFontFamily(Times);
		rh.setText(String.valueOf(tongBMT));
		
		para = rowTruongHop.getCell(5).getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        para.setSpacingBefore(120);
        rh = para.createRun();
        rh.setFontSize(13);
        rh.setBold(true);
		rh.setFontFamily(Times);
		rh.setText(String.valueOf(tongDiemBMT));
		
		// Tao bang ky thuat cong nghe
		XWPFParagraph heading2KyThuat = document.createParagraph();
		heading2KyThuat.setStyle("Heading2");
		XWPFRun runKyThuat = heading2KyThuat.createRun();
		runKyThuat.setText("2.5 Bảng tính toán hệ số phức tạp kỹ thuật-công nghệ");
		XWPFTable tableKyThuat = document.createTable(1,5);
		tableKyThuat.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
		XWPFTableRow row0KyThuat = tableKyThuat.getRow(0);
		List<XWPFTableCell> cell0KyThuat = row0KyThuat.getTableCells();
		for(int i = 0; i < cell0KyThuat.size(); i++){
		    XWPFParagraph paraKT = row0KyThuat.getCell(i).getParagraphs().get(0);
		    paraKT.setAlignment(ParagraphAlignment.CENTER);
		    paraKT.setSpacingBefore(120);
		    paraKT.setSpacingAfter(120);
	        XWPFRun rhKT = paraKT.createRun();
	        rhKT.setBold(true);
	        rhKT.setFontSize(13);
	        rhKT.setFontFamily(Times);
			if(i == 0){
				rhKT.setText("TT");
			}else
			if(i == 1){
				rhKT.setText("Các hệ số");
			}else
			if(i == 2){
				rhKT.setText("Trọng số");
			}else
			if(i == 3){
				rhKT.setText("Giá trị xếp hạng");
			}else{
				rhKT.setText("Kết quả");
			}
		}
		float ketQuaKyThuat = xepHangKyThuatDao.TongKetqua(projectID);
		List<Hesokythuat> listKyThuat = heSoKyThuatDao.getListHeSoKyThuat();
		List<Float> listKetQua = xepHangKyThuatDao.TinhKetQua(projectID);
		List<Xephangkythuat> listXepHang = xepHangKyThuatDao.getListXepHangKyThuat(projectID);
		//Cell 0  row 1 của bảng kỹ thuật
		XWPFTableRow rowKyThuat = tableKyThuat.createRow();
		XWPFParagraph paraKyThuat = rowKyThuat.getCell(0).getParagraphs().get(0);
		paraKyThuat.setAlignment(ParagraphAlignment.CENTER);
		paraKyThuat.setSpacingBefore(120);
        XWPFRun rhKyThuat = paraKyThuat.createRun();
        rhKyThuat.setFontSize(13);
        rhKyThuat.setBold(true);
        rhKyThuat.setFontFamily(Times);
        rhKyThuat.setText("I");
        //cell 1 row 1 của bảng kỹ thuật
		paraKyThuat = rowKyThuat.getCell(1).getParagraphs().get(0);
		paraKyThuat.setAlignment(ParagraphAlignment.LEFT);
		paraKyThuat.setSpacingBefore(120);
        rhKyThuat = paraKyThuat.createRun();
        rhKyThuat.setFontSize(13);
        rhKyThuat.setBold(true);
        rhKyThuat.setFontFamily(Times);
        rhKyThuat.setText("Hệ số KT-CN (TFW)");
        //cell 4 row 1 của bảng kỹ thuật
        paraKyThuat = rowKyThuat.getCell(4).getParagraphs().get(0);
		paraKyThuat.setAlignment(ParagraphAlignment.LEFT);
		paraKyThuat.setSpacingBefore(120);
        rhKyThuat = paraKyThuat.createRun();
        rhKyThuat.setFontSize(13);
        rhKyThuat.setBold(true);
        rhKyThuat.setFontFamily(Times);
        rhKyThuat.setText(String.valueOf(ketQuaKyThuat));
        for(int i = 0; i < listKyThuat.size(); i++){
        	XWPFTableRow row = tableKyThuat.createRow();
    		XWPFParagraph paraKT = row.getCell(0).getParagraphs().get(0);
    		paraKT.setAlignment(ParagraphAlignment.CENTER);
    		paraKT.setSpacingBefore(120);
            XWPFRun rhKT = paraKT.createRun();
            rhKT.setFontSize(13);
            rhKT.setFontFamily(Times);
            rhKT.setText(String.valueOf(listKyThuat.get(i).getHesokythuatid()));
            
            paraKT = row.getCell(1).getParagraphs().get(0);
    		paraKT.setAlignment(ParagraphAlignment.LEFT);
    		paraKT.setSpacingBefore(120);
            rhKT = paraKT.createRun();
            rhKT.setFontSize(13);
            rhKT.setFontFamily(Times);
            rhKT.setText(listKyThuat.get(i).getTenheso());
            
            paraKT = row.getCell(2).getParagraphs().get(0);
    		paraKT.setAlignment(ParagraphAlignment.CENTER);
    		paraKT.setSpacingBefore(120);
            rhKT = paraKT.createRun();
            rhKT.setFontSize(13);
            rhKT.setFontFamily(Times);
            rhKT.setText(String.valueOf(listKyThuat.get(i).getTrongso()));
            
            paraKT = row.getCell(3).getParagraphs().get(0);
    		paraKT.setAlignment(ParagraphAlignment.CENTER);
    		paraKT.setSpacingBefore(120);
            rhKT = paraKT.createRun();
            rhKT.setFontSize(13);
            rhKT.setFontFamily(Times);
            rhKT.setText(String.valueOf(listXepHang.get(i).getGiatrixephang()));
            
            paraKT = row.getCell(4).getParagraphs().get(0);
    		paraKT.setAlignment(ParagraphAlignment.CENTER);
    		paraKT.setSpacingBefore(120);
            rhKT = paraKT.createRun();
            rhKT.setFontSize(13);
            rhKT.setFontFamily(Times);
            rhKT.setText(String.valueOf(listKetQua.get(i)));
            
        }
        //Tao bang ky thuat cong nghe
 		XWPFParagraph heading2MoiTruong = document.createParagraph();
 		heading2MoiTruong.setStyle("Heading2");
 		XWPFRun runMoiTruong = heading2MoiTruong.createRun();
 		runMoiTruong.setText("2.6 Bảng tính toán hệ số tác động môi trường, nhóm làm việc, hệ số phức tạp về môi trường");
 		XWPFTable tableMoiTruong = document.createTable(1,6);
 		tableKyThuat.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
 		XWPFTableRow row0MoiTruong = tableMoiTruong.getRow(0);
 		List<XWPFTableCell> cell0MoiTruong = row0MoiTruong.getTableCells();
 		for(int i = 0; i < cell0MoiTruong.size(); i++){
 		    XWPFParagraph paraMT = row0MoiTruong.getCell(i).getParagraphs().get(0);
 		    paraMT.setAlignment(ParagraphAlignment.CENTER);
 		    paraMT.setSpacingBefore(120);
 		    paraMT.setSpacingAfter(120);
 	        XWPFRun rhMT = paraMT.createRun();
 	        rhMT.setBold(true);
 	        rhMT.setFontSize(13);
 	        rhMT.setFontFamily(Times);
 			if(i == 0){
 				rhMT.setText("TT");
 			}else
 			if(i == 1){
 				rhMT.setText("Các hệ số tác động môi trường");
 			}else
 			if(i == 2){
 				rhMT.setText("Giá trị xếp hạng");
 			}else
 			if(i == 3){
 				rhMT.setText("Trọng số");
 			}else
 			if(i == 4){
 				rhMT.setText("Kết quả");
 			}else{
 				rhMT.setText("Độ ổn định kinh nghiệm");
 			}
 		}
 		float ketQuaMoiTruong = xepHangMoiTruongDao.TongKetQuaMoiTruong(projectID);
 		List<Hesomoitruong> listMoiTruong = heSoMoiTruongDao.getListHeSoMoiTruong();
 		List<Xephangmoitruong> listGiaTri = xepHangMoiTruongDao.getListXepHangMoiTruong(projectID);
 		List<Float> listKetQuaMT = xepHangMoiTruongDao.TinhKetQuaMoiTruong(projectID);
 		float tongOnDinh = xepHangMoiTruongDao.TongOnDinh(projectID);
 		float noiSuy = xepHangMoiTruongDao.tinhNoiSuyLaoDong(projectID);
 		//Cell 0  row 1 của bảng môi trường
		XWPFTableRow rowMoiTruong = tableMoiTruong.createRow();
		XWPFParagraph paraMoiTruong = rowMoiTruong.getCell(0).getParagraphs().get(0);
		paraMoiTruong.setAlignment(ParagraphAlignment.CENTER);
		paraMoiTruong.setSpacingBefore(120);
        XWPFRun rhMoiTruong = paraMoiTruong.createRun();
        rhMoiTruong.setFontSize(13);
        rhMoiTruong.setBold(true);
        rhMoiTruong.setFontFamily(Times);
        rhMoiTruong.setText("I");
        //cell 1 row 1 của bảng môi trường
        paraMoiTruong = rowMoiTruong.getCell(1).getParagraphs().get(0);
        paraMoiTruong.setAlignment(ParagraphAlignment.LEFT);
        paraMoiTruong.setSpacingBefore(120);
        rhMoiTruong = paraMoiTruong.createRun();
        rhMoiTruong.setFontSize(13);
        rhMoiTruong.setBold(true);
        rhMoiTruong.setFontFamily(Times);
        rhMoiTruong.setText("Hệ số tác động môi trường và nhóm làm việc (EFW).");
        //cell 4 row 1 của bảng môi trường
        paraMoiTruong = rowMoiTruong.getCell(4).getParagraphs().get(0);
        paraMoiTruong.setAlignment(ParagraphAlignment.CENTER);
        paraMoiTruong.setSpacingBefore(120);
		rhMoiTruong = paraMoiTruong.createRun();
		rhMoiTruong.setFontSize(13);
		rhMoiTruong.setBold(true);
		rhMoiTruong.setFontFamily(Times);
		rhMoiTruong.setText(String.valueOf(ketQuaMoiTruong));
 		
 		for(int i = 0; i < listMoiTruong.size(); i++){
 			XWPFTableRow row = tableMoiTruong.createRow();
    		XWPFParagraph paraMT = row.getCell(0).getParagraphs().get(0);
    		paraMT.setAlignment(ParagraphAlignment.CENTER);
    		paraMT.setSpacingBefore(120);
            XWPFRun rhMT = paraMT.createRun();
            rhMT.setFontSize(13);
            rhMT.setFontFamily(Times);
            rhMT.setText(String.valueOf(listMoiTruong.get(i).getHesomoitruongid()));
            
            paraMT = row.getCell(1).getParagraphs().get(0);
    		paraMT.setAlignment(ParagraphAlignment.CENTER);
    		paraMT.setSpacingBefore(120);
            rhMT = paraMT.createRun();
            rhMT.setFontSize(13);
            rhMT.setFontFamily(Times);
            rhMT.setText(String.valueOf(listMoiTruong.get(i).getMotahesomt()));
            
            paraMT = row.getCell(2).getParagraphs().get(0);
    		paraMT.setAlignment(ParagraphAlignment.CENTER);
    		paraMT.setSpacingBefore(120);
            rhMT = paraMT.createRun();
            rhMT.setFontSize(13);
            rhMT.setFontFamily(Times);
            rhMT.setText(String.valueOf(listGiaTri.get(i).getGiatrixephang()));
            
            paraMT = row.getCell(3).getParagraphs().get(0);
    		paraMT.setAlignment(ParagraphAlignment.CENTER);
    		paraMT.setSpacingBefore(120);
            rhMT = paraMT.createRun();
            rhMT.setFontSize(13);
            rhMT.setFontFamily(Times);
            rhMT.setText(String.valueOf(listMoiTruong.get(i).getTrongso()));
            
            paraMT = row.getCell(4).getParagraphs().get(0);
    		paraMT.setAlignment(ParagraphAlignment.CENTER);
    		paraMT.setSpacingBefore(120);
            rhMT = paraMT.createRun();
            rhMT.setFontSize(13);
            rhMT.setFontFamily(Times);
            rhMT.setText(String.valueOf(listKetQuaMT.get(i)));
            
            paraMT = row.getCell(5).getParagraphs().get(0);
    		paraMT.setAlignment(ParagraphAlignment.CENTER);
    		paraMT.setSpacingBefore(120);
            rhMT = paraMT.createRun();
            rhMT.setFontSize(13);
            rhMT.setFontFamily(Times);
            rhMT.setText(String.valueOf(listGiaTri.get(i).getOndinh()));
 		}
 		for(int i = 0; i < 2; i++){
 			XWPFTableRow rowMT = tableMoiTruong.createRow();
 			XWPFParagraph paraMT = rowMT.getCell(0).getParagraphs().get(0);
 			paraMT.setAlignment(ParagraphAlignment.CENTER);
 			paraMT.setSpacingBefore(120);
 	        XWPFRun rhMT = paraMT.createRun();
 	        rhMT.setFontSize(13);
 	        rhMT.setBold(true);
 	        rhMT.setFontFamily(Times);
 	        if(i == 0){
 	        	rhMT.setText("II");
 	        }else{
 	        	rhMT.setText("III");
 	        }
 	       
 	        paraMT = rowMT.getCell(1).getParagraphs().get(0);
 	        paraMT.setAlignment(ParagraphAlignment.LEFT);
 	        paraMT.setSpacingBefore(120);
 	        rhMT = paraMT.createRun();
 	        rhMT.setFontSize(13);
 	        rhMT.setBold(true);
 	        rhMT.setFontFamily(Times);
 	        if(i == 0){
 	     	rhMT.setText("Độ ổn định về kinh nghiệm (ES)");
 	        }else{
 	        	rhMT.setText("Nội suy thời gian lao động (P)");
 	        }
 	        //cell 4 row 1 của bảng môi trường
 	        paraMT = rowMT.getCell(5).getParagraphs().get(0);
 	        paraMT.setAlignment(ParagraphAlignment.CENTER);
 	        paraMT.setSpacingBefore(120);
 	        rhMT = paraMT.createRun();
 	       	rhMT.setFontSize(13);
 	       	rhMT.setBold(true);
 	       	rhMT.setFontFamily(Times);
 			if(i == 0){
 				rhMT.setText(String.valueOf(tongOnDinh));
 			}else{
 				rhMT.setText(String.valueOf(noiSuy));
 			}
 		}
 		
 		//Tao bang luong
 		XWPFParagraph heading2Luong = document.createParagraph();
 		heading2Luong.setStyle("Heading2");
 		XWPFRun runLuong = heading2Luong.createRun();
 		runLuong.setText("2.7 Mức lương lao động bình quân đối với việc phát triển");
 		XWPFTable tableLuong = document.createTable(1,12);
 		tableLuong.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
 		XWPFTableRow row0Luong = tableLuong.getRow(0);
 		List<XWPFTableCell> cell0Luong = row0Luong.getTableCells();
 		for(int i = 0; i < cell0Luong.size(); i++){
 		    XWPFParagraph paraLuong = row0Luong.getCell(i).getParagraphs().get(0);
 		 	paraLuong.setAlignment(ParagraphAlignment.CENTER);
 		 	paraLuong.setSpacingBefore(120);
 		 	paraLuong.setSpacingAfter(120);
 	        XWPFRun rhLuong = paraLuong.createRun();
 	        rhLuong.setBold(true);
 	        rhLuong.setFontSize(13);
 	        rhLuong.setFontFamily(Times);
 			if(i == 0){
 				rhLuong.setText("Bậc");
 			}else
 			if(i == 1){
 				rhLuong.setText("Hệ số");
 			}else
 			if(i == 2){
 				rhLuong.setText("Lương cơ bản");
 			}else
 			if(i == 3){
 				rhLuong.setText("Lương tăng thêm");
 			}else
 			if(i == 4){
 				rhLuong.setText("Lương phụ");
 			}else
 			if(i == 5){
 				rhLuong.setText("PC khu vực");
 			}else
 			if(i == 6){
 				rhLuong.setText("PC lưu động");
 			}else
 			if(i == 7){
 				rhLuong.setText("CPKG");
 			}else
 			if(i == 8){
 				rhLuong.setText("Bảo hiểm");
 			}else
 			if(i == 9){
 				rhLuong.setText("Tổng");
 			}else
 			if(i == 10){
 				rhLuong.setText("CP 1 ngày công");
 			}else{
 				rhLuong.setText("CP 1 giờ công");
 			}
 		}
 		int luong = project.getLuongcoban();
 		List<Luong> listLuong = luongDao.getListLuong();
 		List<Giatriluong> listGiaTriLuong = giaTriLuongDao.getListGiaTriLuong(projectID);
 		List<Integer> luongCoBan = luongDao.TinhLuongCoBan(luong, listLuong);
 		List<Integer> luongPhu = luongDao.TinhLuongPhu(luong, listLuong);
 		List<Integer> cpkg = luongDao.TinhCPKG(luong, listLuong);
 		List<Integer> baoHiem = luongDao.TinhBaoHiem(luong, listLuong, listGiaTriLuong);
 		List<Integer> tong = luongDao.TinhTong(luong, listLuong, listGiaTriLuong);
 		List<Integer> cp1Ngay = luongDao.TinhCP1Ngay(luong, listLuong, listGiaTriLuong);
 		List<Integer> cp1Gio = luongDao.TinhCP1Gio(luong, listLuong, listGiaTriLuong);
 		for(int i = 0; i < listLuong.size(); i++){
 			XWPFTableRow rowMT = tableLuong.createRow();
 			XWPFParagraph paraMT = rowMT.getCell(0).getParagraphs().get(0);
 			paraMT.setAlignment(ParagraphAlignment.CENTER);
 			paraMT.setSpacingBefore(120);
 	        XWPFRun rhMT = paraMT.createRun();
 	        rhMT.setFontSize(13);
 	        rhMT.setFontFamily(Times);
 	        if(project.getBacluong() == listLuong.get(i).getBac()){
 	        	rhMT.setColor("00FF00");
 	        }
 	        rhMT.setText(String.valueOf(listLuong.get(i).getBac()));
 	        for(int j = 1; j < 12; j++){
 	        	paraMT = rowMT.getCell(j).getParagraphs().get(0);
 				paraMT.setAlignment(ParagraphAlignment.CENTER);
 				paraMT.setSpacingBefore(120);
 		        rhMT = paraMT.createRun();
 		        rhMT.setFontSize(13);
 		        rhMT.setFontFamily(Times);
 		        if(project.getBacluong() == listLuong.get(i).getBac()){
 	 	        	rhMT.setColor("00FF00");
 	 	        }
 		        switch(j){
 		        	case 1: rhMT.setText(String.valueOf(listLuong.get(i).getHeso())); break;
 		        	case 2: rhMT.setText(String.valueOf(luongCoBan.get(i))); break;
 		        	case 3: rhMT.setText(String.valueOf(listGiaTriLuong.get(i).getLuongtangthem())); break;
 		        	case 4: rhMT.setText(String.valueOf(luongPhu.get(i))); break;
 		        	case 5: rhMT.setText(String.valueOf(listGiaTriLuong.get(i).getPckhuvuc())); break;
 		        	case 6: rhMT.setText(String.valueOf(listGiaTriLuong.get(i).getPcluudong())); break;
 		        	case 7: rhMT.setText(String.valueOf(cpkg.get(i))); break;
 		        	case 8: rhMT.setText(String.valueOf(baoHiem.get(i))); break;
 		        	case 9: rhMT.setText(String.valueOf(tong.get(i))); break;
 		        	case 10: rhMT.setText(String.valueOf(cp1Ngay.get(i))); break;
 		        	case 11: rhMT.setText(String.valueOf(cp1Gio.get(i))); break;
 		        	default: break;
 		        }
 		        
 	        }
			
	        
	        
 		}
 		
		FileOutputStream out = new FileOutputStream(
				   new File("D:/create_table.docx"));
		document.write(out);
		out.close();
		
	}
	
	/**
	 * Hàm xuất file excel.
	 * @param projectName				Tên dự án
	 * @param request
	 * @param response
	 * @param principal
	 * @return							Trả về trang excelView với biến listSheep
	 */
	@RequestMapping(value="/downloadExcel", method = RequestMethod.GET, produces="text/plain; charset=utf-8")
	public ModelAndView downloadExcel(@RequestParam("project") String projectName, HttpServletRequest request, HttpServletResponse response, Principal principal){
		Project project = projectDao.findProjectByName(principal.getName(), projectName);
		int projectid = project.getProjectid();
		int mucLuongNhaNuoc = project.getLuongcoban();
		
		List<DocumentExcel> listSheep  = new ArrayList<DocumentExcel>();
		listSheep.add(new DocumentExcel("Phụ lục I", "BẢNG SẮP XẾP THỨ TỰ ƯU TIÊN CÁC YÊU CẦU CHỨC NĂNG CỦA PHẦN MỀM", project.getTenproject(), nhomChucNangDao.getListNhomChucNang(project)));
		
		listSheep.add(new DocumentExcel("Phụ lục II", "BẢNG CHUYỂN ĐỔI YÊU CẦU CHỨC NĂNG SANG TRƯỜNG HỢP SỬ DỤNG (USE-CASE)", project.getTenproject(), nhomucDao.getNhomucByProject(project.getProjectid())));
		
		List<Object> phuLuc3 = new ArrayList<Object>();
		phuLuc3.add(loaiactorDao.getAll());
		phuLuc3.add(actorDao.countActor(projectid));
		phuLuc3.add(actorDao.tinhDiemTungActor(projectid, loaiactorDao.getAll()));
		phuLuc3.add(actorDao.tinhTongDiem(projectid, loaiactorDao.getAll()));
		listSheep.add(new DocumentExcel("Phụ lục III", "BẢNG TÍNH TOÁN ĐIỂM CÁC TÁC NHÂN (ACTORS) TƯƠNG TÁC TRAO ĐỔI THÔNG TIN VỚI PHẦN MỀM", project.getTenproject(), phuLuc3));
		
		List<Object> phuLuc4 = new ArrayList<Object>();
		phuLuc4.add(usecaseDao.countBMT(projectid));
		phuLuc4.add(usecaseDao.tinhDiemTungUsecase(projectid, bmtDao.getAll()));
		phuLuc4.add(usecaseDao.tongDiemTungUsecase(projectid, bmtDao.getAll()));
		phuLuc4.add(usecaseDao.tongBMT(projectid));
		phuLuc4.add(bmtDao.getAll());
		listSheep.add(new DocumentExcel("Phụ lục IV", "BẢNG TÍNH TOÁN ĐIỂM CÁC TRƯỜNG HỢP SỬ DỤNG (USE-CASES)", project.getTenproject(), phuLuc4));
		
		List<Object> phuLuc5 = new ArrayList<Object>();
		phuLuc5.add(heSoKyThuatDao.getListHeSoKyThuat());
		phuLuc5.add(xepHangKyThuatDao.TinhKetQua(projectid));
		phuLuc5.add(xepHangKyThuatDao.TongKetqua(projectid));
		phuLuc5.add(xepHangKyThuatDao.getListXepHangKyThuat(projectid));
		listSheep.add(new DocumentExcel("Phụ lục V", "BẢNG TÍNH TOÁN HỆ SỐ PHỨC TẠP KỸ THUẬT-CÔNG NGHỆ", project.getTenproject(), phuLuc5));
		
		List<Object> phuLuc6 = new ArrayList<Object>();
		phuLuc6.add(heSoMoiTruongDao.getListHeSoMoiTruong());
		phuLuc6.add(xepHangMoiTruongDao.getListXepHangMoiTruong(projectid));
		phuLuc6.add(xepHangMoiTruongDao.TinhKetQuaMoiTruong(projectid));
		phuLuc6.add(xepHangMoiTruongDao.tinhNoiSuyLaoDong(projectid));
		phuLuc6.add(xepHangMoiTruongDao.TongKetQuaMoiTruong(projectid));
		phuLuc6.add(xepHangMoiTruongDao.TongOnDinh(projectid));
		listSheep.add(new DocumentExcel("Phụ lục VI", "BẢNG TÍNH TOÁN HỆ SỐ PHỨC VỀ MÔI TRƯỜNG", project.getTenproject(), phuLuc6));
		List<Object> bangLuong = new ArrayList<Object>();
		List<Luong> luongs = luongDao.getListLuong();
		List<Giatriluong> listGiaTri = giaTriLuongDao.getListGiaTriLuong(projectid);
		bangLuong.add(luongs);
		bangLuong.add(listGiaTri);
		bangLuong.add(luongDao.TinhCPKG(mucLuongNhaNuoc, luongs));
		bangLuong.add(luongDao.TinhBaoHiem(mucLuongNhaNuoc, luongs, listGiaTri));
		bangLuong.add(luongDao.TinhTong(mucLuongNhaNuoc, luongs, listGiaTri));
		bangLuong.add(luongDao.TinhCP1Ngay(mucLuongNhaNuoc, luongs, listGiaTri));
		bangLuong.add(luongDao.TinhCP1Gio(mucLuongNhaNuoc, luongs, listGiaTri));
		bangLuong.add(luongDao.TinhLuongCoBan(mucLuongNhaNuoc, luongs));
		bangLuong.add(luongDao.TinhLuongPhu(mucLuongNhaNuoc, luongs));
		bangLuong.add(mucLuongNhaNuoc);
		listSheep.add(new DocumentExcel("BẢNG LƯƠNG", "", project.getTenproject(), bangLuong));
		
		List<Object> phuLuc7 = new ArrayList<Object>();
		phuLuc7.add(actorDao.tinhTongDiem(projectid, loaiactorDao.getAll()));
		phuLuc7.add(usecaseDao.tongDiemTungUsecase(projectid, bmtDao.getAll()));
		phuLuc7.add(xepHangKyThuatDao.TongKetqua(projectid));
		phuLuc7.add(xepHangMoiTruongDao.TongKetQuaMoiTruong(projectid));
		phuLuc7.add(xepHangMoiTruongDao.tinhNoiSuyLaoDong(projectid));
		phuLuc7.add(project.getTrongsonoluc());
		phuLuc7.add(luongDao.TinhCP1Gio(mucLuongNhaNuoc, luongs, listGiaTri).get(project.getBacluong()-1));
		listSheep.add(new DocumentExcel("Phụ lục VII", "BẢNG TÍNH TOÁN GIÁ TRỊ PHẦN MỀM", project.getTenproject(), phuLuc7));
		
		List<Object> phuLuc8 = new ArrayList<Object>();
		phuLuc8.add(actorDao.tinhTongDiem(projectid, loaiactorDao.getAll()));
		phuLuc8.add(usecaseDao.tongDiemTungUsecase(projectid, bmtDao.getAll()));
		phuLuc8.add(xepHangKyThuatDao.TongKetqua(projectid));
		phuLuc8.add(xepHangMoiTruongDao.TongKetQuaMoiTruong(projectid));
		phuLuc8.add(xepHangMoiTruongDao.tinhNoiSuyLaoDong(projectid));
		phuLuc8.add(project.getTrongsonoluc());
		phuLuc8.add(luongDao.TinhCP1Gio(mucLuongNhaNuoc, luongs, listGiaTri).get(project.getBacluong()-1));
		listSheep.add(new DocumentExcel("Phụ lục VIII", "BẢNG TỔNG HỢP CHI PHÍ PHẦN MỀM", project.getTenproject(), phuLuc8));
		return new ModelAndView("excelView", "listSheep", listSheep);
		
	}
}