package vn.com.luanvan.form;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import vn.com.luanvan.dao.PhanloaiDao;
import vn.com.luanvan.model.Phanloai;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.Usecase;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class PDFBuilder extends AbstractPdfView{
	@Autowired
	private PhanloaiDao phanLoaiDao;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("application/pdf");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=PDF.pdf");
		
		Date currentDate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		
		String contextPath = request.getSession().getServletContext().getRealPath("");
		String fontPath = contextPath + File.separator + "resources" + File.separator + "fonts" + File.separator + "times.ttf";
		
		BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font32Bold = new Font(bf, 32, Font.BOLD);
		Font font20Bold = new Font(bf, 20, Font.BOLD);
		Font font18Bold = new Font(bf, 18, Font.BOLD);
		Font font16Bold = new Font(bf, 16, Font.BOLD);
		Font font14Bold = new Font(bf, 14, Font.BOLD);
		Font font13 = new Font(bf, 13);
		Font font12 = new Font(bf, 12);
		
		List<Object> lists = (List<Object>) model.get("lists");
		Project project = (Project) lists.get(0);
		// document open
		document.open();
		document.setPageSize(PageSize.A4);
		Chunk title = new Chunk("Đặc tả yêu cầu phần mềm \n\n\n\n");
		Chunk cho = new Chunk("cho \n\n\n\n");
		Chunk nameOfProject = new Chunk(project.getTenproject()+"\n\n\n\n");
		Chunk version = new Chunk("Phiên bản X.X được phê chuẩn \n\n\n\n");
		Chunk author = new Chunk("Được chuẩn bị bởi: "+project.getUser().getUsername()+"\n\n\n\n");
		Chunk organization = new Chunk("<Tổ chức>\n\n\n\n");
		Chunk date = new Chunk("Ngày tạo ra tài liệu\n\n"+formatDate.format(currentDate));
		
		// Khoi tao trang bia
        Paragraph coverPage = new Paragraph();
        title.setFont(font32Bold);
        cho.setFont(font20Bold);
        nameOfProject.setFont(font32Bold);
        version.setFont(font14Bold);
        author.setFont(font14Bold);
        organization.setFont(font14Bold);
        date.setFont(font14Bold);
        coverPage.setFont(font32Bold);
        coverPage.setAlignment(Paragraph.ALIGN_RIGHT);
        coverPage.add(title);
        coverPage.add(cho);
        coverPage.add(nameOfProject);
        coverPage.add(version);
        coverPage.add(author);
        coverPage.add(organization);
        coverPage.add(date);
        document.add(coverPage);
        //ket thuc khoi tao trang bia
        document.newPage();
        
        Paragraph pageI = new Paragraph();
        Paragraph moTa = new Paragraph("1 Mô tả\n", font18Bold);
        Paragraph contentMoTa = new Paragraph(project.getMotaproject()+"\n\n", font13);
        Paragraph cacChucNang = new Paragraph("2 Các chức năng của sản phẩm\n", font18Bold);
        Paragraph detailCachNang = new Paragraph("3 Các tính năng của hệ thống\n", font18Bold);
        Set<Usecase> usecase = project.getUsecases();
        
        pageI.add(moTa);
        pageI.add(contentMoTa);
        pageI.add(cacChucNang);
    	for(Usecase chucNang : usecase){
    		Chunk contentChucNang = new Chunk("   - "+chucNang.getNameofuc().replaceAll("[\\r|\\n]", " ")+"\n", font12);
    		pageI.add(contentChucNang);
    	}
    	pageI.add(detailCachNang);
    	int count = 1;
    	for(Usecase chucNang : usecase){
    		Paragraph detailContent = new Paragraph("3."+count+" "+chucNang.getNameofuc().replaceAll("[\\r|\\n]", " ")+"\n", font16Bold);
    		pageI.add(detailContent);
    		int count2 = 1;
    		Paragraph detailMoTa = new Paragraph("   3."+count+"."+count2+" "+"Mô tả và mức độ \n", font14Bold);
    		pageI.add(detailMoTa);
    		if(chucNang.getMotauc() == null || chucNang.getMotauc() == "" || chucNang.getMotauc() == " "){
    			Paragraph contentMoTa1 = new Paragraph("Mô tả: \n <Cung cấp một mô tả ngắn gọn về tính năng và chỉ ra mức độ của nó (đơn giản, trung bình, phức tạp). "
    			+ "Ta cũng có thể đưa vào cách xếp mức ưu tiên dựa vào các tiêu chí như lợi ích, chi phí, rủi ro (mỗi cái được xếp loại từ thấp – 1 đến cao - 9).>\n", font13);
	    		pageI.add(contentMoTa1);
    		}else{
    			Paragraph contentMoTa1 = new Paragraph("Mô tả: "+chucNang.getMotauc()+"\n", font13);
	    		pageI.add(contentMoTa1);
    		}
    		Paragraph contentMoTa2 = new Paragraph("Mức độ: "+chucNang.getBmt().getTen()+"\n", font13);
    		pageI.add(contentMoTa2);
    		count2++;
    		Paragraph tacNhan = new Paragraph("   3."+count+"."+count2+" Tác nhân \n", font14Bold);
    		pageI.add(tacNhan);
    		Set<Phanloai> listPhanLoai = (Set<Phanloai>) chucNang.getPhanloais();
    		for(Phanloai phanLoai : listPhanLoai){
    		Paragraph contentTacNhan = new Paragraph("     -"+phanLoai.getActor().getNameofactor()+"\n", font13);
    		pageI.add(contentTacNhan);
    		}
    		count++;
    	}
    	pageI.setSpacingBefore(1);
    	pageI.setSpacingAfter(1);
        document.add(pageI);
        
		document.close();
	}
	
	
	
}