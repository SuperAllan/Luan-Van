package vn.com.luanvan.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

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
import vn.com.luanvan.model.DocumentExcel;
import vn.com.luanvan.model.Giatriluong;
import vn.com.luanvan.model.Luong;
import vn.com.luanvan.model.Project;

import org.apache.fop.apps.FOPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value="/downloadPDF/project={projectName}", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView downloadPFD(@PathVariable("projectName")String projectName, HttpServletRequest request, HttpServletResponse response, Principal principal){
		Project project = projectDao.findProjectByName(principal.getName(), projectName); 
		List<Object> lists = new ArrayList<Object>();
		lists.add(project);
		return new ModelAndView("pdfView", "lists", lists); 
//		Project project = projectDao.findProjectByName(principal.getName(), projectName);
//		String nameOfFileXML = project.getTenproject()+" "+project.getUser().getUsername()+".xml";
//		String nameOfFilePDF = project.getTenproject()+" "+project.getUser().getUsername()+".pdf";
//		String contextPath = request.getSession().getServletContext().getRealPath("");
//	    String filePath = contextPath + File.separator + "resources" + File.separator + "document" + File.separator;
//	    File file = new File(filePath+nameOfFileXML);
//		JAXBContext context = JAXBContext.newInstance(DocumentText.class);
//	    Marshaller m = context.createMarshaller();
//	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//	    DocumentText object = new DocumentText();
//	    object.setCode("CA");
//	    object.setName("Cath");
//	    m.marshal(object, file);
//		String xmlNameFile = nameOfFileXML;
//		String xslNameFile = "PDFView.xsl";
//		File xmlFile = new File(filePath+xmlNameFile);
//		File xslFile = new File(filePath+xslNameFile);
//		File pdfFile = new File(filePath+nameOfFilePDF);
//
//		FopFactory fopFactory = FopFactory.newInstance();
//		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
//
//		OutputStream out = new FileOutputStream(pdfFile);
//		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
//	    // Setup XSLT
//	    TransformerFactory factory = TransformerFactory.newInstance();
//	    Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
//	    Source src = new StreamSource(xmlFile);
//	    transformer.setParameter("versionParam", "1.0");
//	    Result res = new SAXResult(fop.getDefaultHandler());
//	    transformer.transform(src, res);
//		File fileOut = new File(pdfFile.getAbsolutePath());
// 
//		// set response headers (it is always better to write header's before writing to response stream)
//		response.setContentType(request.getSession().getServletContext().getMimeType(file.getAbsolutePath()));
//		response.setHeader("Content-type","application/pdf");
//		// if Content-Disposition header value is set to attachment, file can be downloaded as attachment
//		//response.setHeader("Content-Disposition", "attachment; filename=\"" + fileOut.getName());
//		response.setHeader("Content-Disposition", "inline; filename=\"" + fileOut.getName().replace(" "+project.getUser().getUsername(), ""));
//		// Copy input file's InputStream to response's OutputStream
//		InputStream inputStream = null;
//		OutputStream outputStream = null;
//		try {			
//			inputStream = new FileInputStream(fileOut);
//			outputStream = response.getOutputStream();
// 
//			int read = 0;
//			final byte[] bytes = new byte[1024];
//			while ((read = inputStream.read(bytes)) != -1) {
//				outputStream.write(bytes, 0, read);
//			}
// 
//		} catch (FileNotFoundException fne) {
//			fne.printStackTrace();
//		} finally {
//			if (outputStream != null) {
//				outputStream.close();
//			}
//			if (inputStream != null) {
//				inputStream.close();
//			}
//		}
//		if(xmlFile.exists()){
//			xmlFile.delete();
//		}
//		if(pdfFile.exists()){
//			pdfFile.delete();
//		}
	}
	
	@RequestMapping(value="/downloadDoc/project={projectName}", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public void downloadDoc(@PathVariable("projectName")String projectName, HttpServletRequest request, HttpServletResponse response) throws JAXBException, FOPException, TransformerException, IOException{
//		TransformerFactory tFactory=TransformerFactory.newInstance();
//		String contextPath = request.getSession().getServletContext().getRealPath("");
//	    String filePath = contextPath + File.separator + "resources" + File.separator + "document" + File.separator;
//	    File file = new File(filePath+"fileDoc.xml");
//		JAXBContext context = JAXBContext.newInstance(DocumentText.class);
//	    Marshaller m = context.createMarshaller();
//	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//	    DocumentText object = new DocumentText();
//	    object.setCode("CA");
//	    object.setName("Cath");
//	    m.marshal(object, file);
//		String xmlNameFile = "fileDoc.xml";
//		String xslNameFile = "WordView.xsl";
//		File xmlFile = new File(filePath+xmlNameFile);
//		File xslFile = new File(filePath+xslNameFile);
//		File docFile = new File(filePath+"Document.doc");
//		Source xslDoc=new StreamSource(new File(xslFile.getAbsolutePath()));
//	    Source xmlDoc=new StreamSource(new File(xmlFile.getAbsolutePath()));
//		OutputStream fileOut = new FileOutputStream(docFile);
//		Transformer trasform = tFactory.newTransformer(xslDoc);
//        trasform.transform(xmlDoc, new StreamResult(fileOut));
//		// set response headers (it is always better to write header's before writing to response stream)
//		response.setContentType(request.getSession().getServletContext().getMimeType(file.getAbsolutePath()));
//		response.setHeader("Content-type","application/doc");
//		// if Content-Disposition header value is set to attachment, file can be downloaded as attachment
//		response.setHeader("Content-Disposition", "inline; filename=\"" + docFile.getName());
		
	}
	
	@RequestMapping(value="/downloadExcel/project={projectName}", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView downloadExcel(@PathVariable("projectName")String projectName,HttpServletRequest request, HttpServletResponse response, Principal principal) throws JAXBException, FOPException, TransformerException, IOException{
	
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
		phuLuc7.add(trongsonolucDao.findByGiaTri(project.getTrongsonoluc()));
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