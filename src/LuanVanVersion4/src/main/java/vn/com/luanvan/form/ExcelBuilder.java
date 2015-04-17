package vn.com.luanvan.form;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;

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
import vn.com.luanvan.model.Trongsonoluc;
import vn.com.luanvan.model.Usecase;
import vn.com.luanvan.model.Xephangkythuat;
import vn.com.luanvan.model.Xephangmoitruong;

@SuppressWarnings("deprecation")
public class ExcelBuilder extends AbstractExcelView{
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<DocumentExcel> lists = (List<DocumentExcel>) model.get("listSheep");
		String fileName = lists.get(0).getNameOfProject();
		response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileName, "UTF-8")+".xls");
		// create a new Excel sheet
        // create style for header cells
        	createPhuLucI(lists.get(0), workbook);
        	createPhuLucII(lists.get(1), workbook);
        	createPhuLucIII(lists.get(2), workbook);
        	createPhuLucIV(lists.get(3), workbook);
        	createPhuLucV(lists.get(4), workbook);
        	createPhuLucVI(lists.get(5), workbook);
        	createPhuLucBangLuong(lists.get(6), workbook);
        	createPhuLucVII(lists.get(7), workbook);
        	createPhuLucVIII(lists.get(8), workbook);
	}
	
	@SuppressWarnings({ "static-access", "unchecked"})
	void createPhuLucI(DocumentExcel documentExcel, HSSFWorkbook workbook){
		CellStyle styleBold = workbook.createCellStyle();
        Font fontBold = workbook.createFont();
        fontBold.setFontName("Arial");
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setFont(fontBold);
        
		CellStyle styleBlue = workbook.createCellStyle();
		styleBlue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		styleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleBlue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBlue.setAlignment(styleBlue.ALIGN_CENTER);
		styleBlue.setFont(fontBold);
		
		CellStyle textCenter = workbook.createCellStyle();
        textCenter.setAlignment(textCenter.ALIGN_CENTER);
        
        CellStyle textCenterBold = workbook.createCellStyle();
        textCenterBold.setAlignment(textCenterBold.ALIGN_CENTER);
        textCenterBold.setFont(fontBold);
        
        CellStyle styleGreen = workbook.createCellStyle();
        styleGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        styleGreen.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styleGreen.setFont(fontBold);
		
		HSSFSheet sheet = workbook.createSheet(documentExcel.getNameOfWorkSheet());
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
    	HSSFRow row1 = sheet.createRow(1);
    	row1.createCell(1).setCellValue(documentExcel.getNameOfWorkSheet());
    	row1.getCell(1).setCellStyle(textCenterBold);
    	
    	sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));
    	HSSFRow row2 = sheet.createRow(2);
    	row2.createCell(1).setCellValue(documentExcel.getTitle());
    	row2.getCell(1).setCellStyle(textCenterBold);
    	
    	sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 4));
    	HSSFRow row3 = sheet.createRow(3);
    	row3.createCell(2).setCellValue("Tên dự án: "+documentExcel.getNameOfProject());
    	row3.getCell(2).setCellStyle(styleBold);
        
    	HSSFRow sheet1Row5 = sheet.createRow(5);
    	sheet1Row5.createCell(0).setCellValue("TT");
    	sheet1Row5.createCell(1).setCellValue("Mô tả yêu cầu");
    	sheet1Row5.createCell(2).setCellValue("Phân loại");
    	sheet1Row5.createCell(3).setCellValue("Mức độ");
    	sheet1Row5.createCell(4).setCellValue("Ghi chú");
    	sheet1Row5.getCell(0).setCellStyle(styleBlue);
    	sheet1Row5.getCell(1).setCellStyle(styleBlue);
    	sheet1Row5.getCell(2).setCellStyle(styleBlue);
    	sheet1Row5.getCell(3).setCellStyle(styleBlue);
    	sheet1Row5.getCell(4).setCellStyle(styleBlue);
    	int countRow = 6;
    	int stt = 1;
    	List<Nhomchucnang> listNhomChucNang = documentExcel.getData();
    	for(int i = 0; i < listNhomChucNang.size(); i++){
    		HSSFRow sheet1RowNhom = sheet.createRow(countRow);
    		sheet1RowNhom.createCell(1).setCellValue(listNhomChucNang.get(i).getTennhom());
    		sheet1RowNhom.getCell(1).setCellStyle(styleGreen);
    		Set<Chucnang> listChucNang = listNhomChucNang.get(i).getChucnangs();
    		int countRowChucNang = countRow + 1;
    		for(Chucnang chucnang : listChucNang){
    			HSSFRow sheet1RowChucNang = sheet.createRow(countRowChucNang);
    			sheet1RowChucNang.createCell(0).setCellValue(stt);
    			sheet1RowChucNang.getCell(0).setCellStyle(textCenter);
    			sheet1RowChucNang.createCell(1).setCellValue(chucnang.getMotayeucau());
    			sheet1RowChucNang.createCell(2).setCellValue(chucnang.getPhanloaichucnang().getTenloai());
    			sheet1RowChucNang.createCell(3).setCellValue(chucnang.getMucdo().getMotamucdo());
    			sheet1RowChucNang.createCell(4).setCellValue(chucnang.getGhichu());
    			countRowChucNang++;
    			stt++;
    		}
    		stt = stt++;
    		countRow = countRowChucNang;
        	
    	}
    	sheet.setColumnWidth(0, 1700);
    	sheet.setColumnWidth(1, 16000);
    	sheet.setColumnWidth(2, 4700);
    	sheet.setColumnWidth(3, 2800);
    	sheet.setColumnWidth(4, 2400);
    	
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	void createPhuLucII(DocumentExcel documentExcel, HSSFWorkbook workbook){
		CellStyle styleBold = workbook.createCellStyle();
        Font fontBold = workbook.createFont();
        fontBold.setFontName("Arial");
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setFont(fontBold);
        
		CellStyle styleBlue = workbook.createCellStyle();
		styleBlue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		styleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleBlue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBlue.setAlignment(styleBlue.ALIGN_CENTER);
		styleBlue.setFont(fontBold);
		
		CellStyle textCenter = workbook.createCellStyle();
        textCenter.setAlignment(textCenter.ALIGN_CENTER);
        
        CellStyle textCenterBold = workbook.createCellStyle();
        textCenterBold.setAlignment(textCenterBold.ALIGN_CENTER);
        textCenterBold.setFont(fontBold);
        
        CellStyle styleGreen = workbook.createCellStyle();
        styleGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        styleGreen.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styleGreen.setFont(fontBold);
		
        CellStyle wrapText = workbook.createCellStyle();
        wrapText.setWrapText(true);
        
        CellStyle textMiddle = workbook.createCellStyle();
        textMiddle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        
		HSSFSheet sheet = workbook.createSheet(documentExcel.getNameOfWorkSheet());
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
    	HSSFRow row1 = sheet.createRow(1);
    	row1.createCell(1).setCellValue(documentExcel.getNameOfWorkSheet());
    	row1.getCell(1).setCellStyle(textCenterBold);
    	
    	sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));
    	HSSFRow row2 = sheet.createRow(2);
    	row2.createCell(1).setCellValue(documentExcel.getTitle());
    	row2.getCell(1).setCellStyle(textCenterBold);
    	
    	sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 4));
    	HSSFRow row3 = sheet.createRow(3);
    	row3.createCell(2).setCellValue("Tên dự án: "+documentExcel.getNameOfProject());
    	row3.getCell(2).setCellStyle(styleBold);
        
    	HSSFRow sheet1Row5 = sheet.createRow(5);
    	sheet1Row5.createCell(0).setCellValue("TT");
    	sheet1Row5.createCell(1).setCellValue("Tên Use-case");
    	sheet1Row5.createCell(2).setCellValue("Tác nhân chính");
    	sheet1Row5.createCell(3).setCellValue("Tác nhân phụ");
    	sheet1Row5.createCell(4).setCellValue("Mô tả Use-case");
    	sheet1Row5.createCell(5).setCellValue("Tính tiền");
    	sheet1Row5.createCell(6).setCellValue("Mức độ cần thiết");
    	sheet1Row5.getCell(0).setCellStyle(styleBlue);
    	sheet1Row5.getCell(1).setCellStyle(styleBlue);
    	sheet1Row5.getCell(2).setCellStyle(styleBlue);
    	sheet1Row5.getCell(3).setCellStyle(styleBlue);
    	sheet1Row5.getCell(4).setCellStyle(styleBlue);
    	sheet1Row5.getCell(5).setCellStyle(styleBlue);
    	sheet1Row5.getCell(6).setCellStyle(styleBlue);
    	int countRow = 6;
    	int stt = 1;
    	List<Nhomuc> listNhomUC = documentExcel.getData();
    	for(int i = 0; i < listNhomUC.size(); i++){
    		HSSFRow sheet1RowNhom = sheet.createRow(countRow);
    		sheet1RowNhom.createCell(1).setCellValue(listNhomUC.get(i).getTennhom());
    		sheet1RowNhom.getCell(1).setCellStyle(styleGreen);
    		Set<Usecase> listUsecase = listNhomUC.get(i).getUsecases();
    		int countRowChucNang = countRow + 1;
    		for(Usecase UC : listUsecase){
    			HSSFRow sheet2Row = sheet.createRow(countRowChucNang);
    			sheet2Row.createCell(0).setCellValue(stt);
    			sheet2Row.getCell(0).setCellStyle(textMiddle);
    			sheet2Row.createCell(1).setCellValue(UC.getNameofuc());
    			sheet2Row.getCell(1).setCellStyle(textMiddle);
    			String ActorChinh = "";
    			String ActorPhu = "";
    			for(Phanloai phanLoai : UC.getPhanloais()){
    				if(phanLoai.getVaitro() == 1){
    					ActorChinh += phanLoai.getActor().getNameofactor()+" \n";
    					
    				}else{
    					ActorPhu += phanLoai.getActor().getNameofactor()+" \n";
    					
    				}
    			}
    			sheet2Row.createCell(2).setCellValue(ActorChinh);
    			sheet2Row.getCell(2).setCellStyle(textMiddle);
    			sheet2Row.createCell(3).setCellValue(ActorPhu);
    			sheet2Row.getCell(3).setCellStyle(textMiddle);
    			sheet2Row.createCell(4).setCellValue(UC.getMotauc());
    			sheet2Row.getCell(4).setCellStyle(wrapText);
    			if(UC.getTinhtien() == true){
    				sheet2Row.createCell(5).setCellValue("Có");
    				sheet2Row.getCell(5).setCellStyle(textMiddle);
    			}else{
    				sheet2Row.createCell(5).setCellValue("Không");
    			}
    			sheet2Row.createCell(6).setCellValue(UC.getBmt().getTen());
    			sheet2Row.getCell(6).setCellStyle(textMiddle);
    			countRowChucNang++;
    			stt++;
    		}
    		stt = stt++;
    		countRow = countRowChucNang;
    	}
    	sheet.setColumnWidth(1, 16000);
    	sheet.setColumnWidth(2, 5300);
    	sheet.setColumnWidth(3, 5300);
    	sheet.setColumnWidth(4, 8700);
    	sheet.setColumnWidth(5, 2100);
    	sheet.setColumnWidth(6, 3200);
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	void createPhuLucIII(DocumentExcel documentExcel, HSSFWorkbook workbook){
		CellStyle styleBold = workbook.createCellStyle();
        Font fontBold = workbook.createFont();
        fontBold.setFontName("Arial");
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setFont(fontBold);
        
		CellStyle styleBlue = workbook.createCellStyle();
		styleBlue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		styleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleBlue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBlue.setAlignment(styleBlue.ALIGN_CENTER);
		styleBlue.setFont(fontBold);
		
		CellStyle textCenter = workbook.createCellStyle();
        textCenter.setAlignment(textCenter.ALIGN_CENTER);
        
        CellStyle textCenterBold = workbook.createCellStyle();
        textCenterBold.setAlignment(textCenterBold.ALIGN_CENTER);
        textCenterBold.setFont(fontBold);
        
        CellStyle wrapText = workbook.createCellStyle();
        wrapText.setWrapText(true);
        
		HSSFSheet hssfSheet = workbook.createSheet(documentExcel.getNameOfWorkSheet());
		hssfSheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
    	HSSFRow row1 = hssfSheet.createRow(1);
    	row1.createCell(1).setCellValue(documentExcel.getNameOfWorkSheet());
    	row1.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));
    	HSSFRow row2 = hssfSheet.createRow(2);
    	row2.createCell(1).setCellValue(documentExcel.getTitle());
    	row2.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 5));
    	HSSFRow row3 = hssfSheet.createRow(3);
    	row3.createCell(3).setCellValue("Tên dự án: "+documentExcel.getNameOfProject());
    	row3.getCell(3).setCellStyle(styleBold);
        
    	HSSFRow sheetRow5 = hssfSheet.createRow(5);
    	sheetRow5.createCell(0).setCellValue("TT");
    	sheetRow5.createCell(1).setCellValue("Loại Actor");
    	sheetRow5.createCell(2).setCellValue("Mô tả");
    	sheetRow5.createCell(3).setCellValue("Số tác nhân");
    	sheetRow5.createCell(4).setCellValue("Trọng số");
    	sheetRow5.createCell(5).setCellValue("Điểm từng loại tác nhân");
    	sheetRow5.createCell(6).setCellValue("Ghi chú");
    	sheetRow5.getCell(0).setCellStyle(styleBlue);
    	sheetRow5.getCell(1).setCellStyle(styleBlue);
    	sheetRow5.getCell(2).setCellStyle(styleBlue);
    	sheetRow5.getCell(3).setCellStyle(styleBlue);
    	sheetRow5.getCell(4).setCellStyle(styleBlue);
    	sheetRow5.getCell(5).setCellStyle(styleBlue);
    	sheetRow5.getCell(6).setCellStyle(styleBlue);
    	int countRow = 6;
    	int stt = 1;
    	List<Object> listObject = documentExcel.getData();
    	List<Loaiactor> listLoai = (List<Loaiactor>) listObject.get(0);
    	List<Integer> countActor = (List<Integer>) listObject.get(1);
    	List<Integer> diemActor = (List<Integer>) listObject.get(2);
    	Integer tongDiem = (Integer) listObject.get(3);
    	for(Loaiactor loai : listLoai){
    		HSSFRow sheet3Row = hssfSheet.createRow(countRow);
    		sheet3Row.createCell(0).setCellValue(stt);
    		sheet3Row.getCell(0).setCellStyle(textCenter);
    		sheet3Row.createCell(1).setCellValue(loai.getLoai());
    		sheet3Row.createCell(2).setCellValue(loai.getMotaloaiactor());
    		sheet3Row.createCell(3).setCellValue(Integer.parseInt(String.valueOf(countActor.get(stt-1))));
    		sheet3Row.getCell(3).setCellStyle(textCenter);
    		sheet3Row.createCell(4).setCellValue(loai.getTrongso());
    		sheet3Row.getCell(4).setCellStyle(textCenter);
    		sheet3Row.createCell(5).setCellValue(diemActor.get(stt-1));
    		sheet3Row.getCell(5).setCellStyle(textCenter);
    		sheet3Row.createCell(6).setCellValue("");
    		stt++;
    		countRow++;
    	}
    	HSSFRow sheet3Row = hssfSheet.createRow(countRow);
    	sheet3Row.createCell(1).setCellValue("Cộng (1+2+3)");
		sheet3Row.getCell(1).setCellStyle(textCenterBold);
		sheet3Row.createCell(2).setCellValue("TAW");
		sheet3Row.getCell(2).setCellStyle(textCenterBold);
		sheet3Row.createCell(5).setCellValue(tongDiem);
		sheet3Row.getCell(5).setCellStyle(textCenterBold);
		
    	hssfSheet.setColumnWidth(1, 3000);
    	hssfSheet.setColumnWidth(2, 12000);
    	hssfSheet.setColumnWidth(3, 2800);
    	hssfSheet.setColumnWidth(4, 2400);
    	hssfSheet.setColumnWidth(5, 7300);
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	void createPhuLucIV(DocumentExcel documentExcel, HSSFWorkbook workbook){
		CellStyle styleBold = workbook.createCellStyle();
        Font fontBold = workbook.createFont();
        fontBold.setFontName("Arial");
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setFont(fontBold);
        
		CellStyle styleBlue = workbook.createCellStyle();
		styleBlue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		styleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleBlue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBlue.setAlignment(styleBlue.ALIGN_CENTER);
		styleBlue.setFont(fontBold);
		
		CellStyle textCenter = workbook.createCellStyle();
        textCenter.setAlignment(textCenter.ALIGN_CENTER);
        
        CellStyle textCenterBold = workbook.createCellStyle();
        textCenterBold.setAlignment(textCenterBold.ALIGN_CENTER);
        textCenterBold.setFont(fontBold);
        
        
		HSSFSheet hssfSheet = workbook.createSheet(documentExcel.getNameOfWorkSheet());
		hssfSheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
    	HSSFRow row1 = hssfSheet.createRow(1);
    	row1.createCell(1).setCellValue(documentExcel.getNameOfWorkSheet());
    	row1.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));
    	HSSFRow row2 = hssfSheet.createRow(2);
    	row2.createCell(1).setCellValue(documentExcel.getTitle());
    	row2.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 5));
    	HSSFRow row3 = hssfSheet.createRow(3);
    	row3.createCell(3).setCellValue("Tên dự án: "+documentExcel.getNameOfProject());
    	row3.getCell(3).setCellStyle(styleBold);
        
    	HSSFRow sheetRow5 = hssfSheet.createRow(5);
    	sheetRow5.createCell(0).setCellValue("TT");
    	sheetRow5.createCell(1).setCellValue("Loại");
    	sheetRow5.createCell(2).setCellValue("Số trường hợp sử dụng");
    	sheetRow5.createCell(3).setCellValue("Trọng số");
    	sheetRow5.createCell(4).setCellValue("Hệ số BMT");
    	sheetRow5.createCell(5).setCellValue("Điểm từng loại Use-case");
    	sheetRow5.createCell(6).setCellValue("Ghi chú");
    	sheetRow5.getCell(0).setCellStyle(styleBlue);
    	sheetRow5.getCell(1).setCellStyle(styleBlue);
    	sheetRow5.getCell(2).setCellStyle(styleBlue);
    	sheetRow5.getCell(3).setCellStyle(styleBlue);
    	sheetRow5.getCell(4).setCellStyle(styleBlue);
    	sheetRow5.getCell(5).setCellStyle(styleBlue);
    	sheetRow5.getCell(6).setCellStyle(styleBlue);
    	
    	List<Object> listObject = documentExcel.getData();
    	List<Integer> countBMT = (List<Integer>) listObject.get(0);
    	List<Integer> diemTungUsecase = (List<Integer>) listObject.get(1);
    	Integer tongDiemUsecase = (Integer) listObject.get(2);
    	Integer tongBMT =  (Integer) listObject.get(3);
    	List<Bmt> listBMT = (List<Bmt>) listObject.get(4);
    	HSSFRow sheetRow6 = hssfSheet.createRow(6);
    	sheetRow6.createCell(0).setCellValue(1);
    	sheetRow6.getCell(0).setCellStyle(textCenter);
    	sheetRow6.createCell(1).setCellValue("B");
    	sheetRow6.getCell(1).setCellStyle(styleBold);
    	sheetRow6.createCell(4).setCellValue(1);
    	sheetRow6.getCell(4).setCellStyle(textCenter);
    	int countFor = 3;
    	int countRow = 7;
    	for(int i  = 0; i < countFor; i++){
    		HSSFRow sheetRow = hssfSheet.createRow(countRow);
    		sheetRow.createCell(1).setCellValue(listBMT.get(i).getTen());
    		sheetRow.createCell(2).setCellValue(Integer.parseInt(String.valueOf(countBMT.get(i))));
    		sheetRow.createCell(3).setCellValue(listBMT.get(i).getTrongso());
    		sheetRow.getCell(3).setCellStyle(textCenter);
    		sheetRow.createCell(5).setCellValue(diemTungUsecase.get(i));
    		sheetRow.getCell(5).setCellStyle(textCenter);
    		countRow++;
    	}
    	HSSFRow sheetRow10 = hssfSheet.createRow(10);
    	sheetRow10.createCell(0).setCellValue(2);
    	sheetRow10.getCell(0).setCellStyle(textCenter);
    	sheetRow10.createCell(1).setCellValue("M");
    	sheetRow10.getCell(1).setCellStyle(styleBold);
    	sheetRow10.createCell(4).setCellValue(1.2);
    	sheetRow10.getCell(4).setCellStyle(textCenter);
    	countRow = 11;
    	countFor = 6;
    	for(int i  = 3; i < countFor; i++){
    		HSSFRow sheetRow = hssfSheet.createRow(countRow);
    		sheetRow.createCell(1).setCellValue(listBMT.get(i).getTen());
    		sheetRow.createCell(2).setCellValue(Integer.parseInt(String.valueOf(countBMT.get(i))));
    		sheetRow.createCell(3).setCellValue(listBMT.get(i).getTrongso());
    		sheetRow.getCell(3).setCellStyle(textCenter);
    		sheetRow.createCell(5).setCellValue(diemTungUsecase.get(i));
    		sheetRow.getCell(5).setCellStyle(textCenter);
    		countRow++;
    	}
    	HSSFRow sheetRow14 = hssfSheet.createRow(14);
    	sheetRow14.createCell(0).setCellValue(4);
    	sheetRow14.getCell(0).setCellStyle(textCenter);
    	sheetRow14.createCell(1).setCellValue("T");
    	sheetRow14.getCell(1).setCellStyle(styleBold);
    	sheetRow14.createCell(4).setCellValue(1.5);
    	sheetRow14.getCell(4).setCellStyle(textCenter);
    	countRow = 15;
    	countFor = 9;
    	for(int i  = 6; i < countFor; i++){
    		HSSFRow sheetRow = hssfSheet.createRow(countRow);
    		sheetRow.createCell(1).setCellValue(listBMT.get(i).getTen());
    		sheetRow.createCell(2).setCellValue(Integer.parseInt(String.valueOf(countBMT.get(i))));
    		sheetRow.createCell(3).setCellValue(listBMT.get(i).getTrongso());
    		sheetRow.getCell(3).setCellStyle(textCenter);
    		sheetRow.createCell(5).setCellValue(diemTungUsecase.get(i));
    		sheetRow.getCell(5).setCellStyle(textCenter);
    		countRow++;
    	}
    	HSSFRow sheetRow18 = hssfSheet.createRow(18);
    	sheetRow18.createCell(1).setCellValue("Cộng (1+2+3)");
    	sheetRow18.getCell(1).setCellStyle(styleBold);
    	sheetRow18.createCell(2).setCellValue(tongBMT);
    	sheetRow18.getCell(2).setCellStyle(textCenterBold);
    	sheetRow18.createCell(5).setCellValue(tongDiemUsecase);
    	sheetRow18.getCell(5).setCellStyle(textCenterBold);
    	hssfSheet.setColumnWidth(1, 3200);
    	hssfSheet.setColumnWidth(2, 5400);
    	hssfSheet.setColumnWidth(4, 2800);
    	hssfSheet.setColumnWidth(5, 5800);
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	void createPhuLucV(DocumentExcel documentExcel, HSSFWorkbook workbook){
		CellStyle styleBold = workbook.createCellStyle();
        Font fontBold = workbook.createFont();
        fontBold.setFontName("Arial");
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setFont(fontBold);
        
		CellStyle styleBlue = workbook.createCellStyle();
		styleBlue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		styleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleBlue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBlue.setAlignment(styleBlue.ALIGN_CENTER);
		styleBlue.setFont(fontBold);
		
		CellStyle textCenter = workbook.createCellStyle();
        textCenter.setAlignment(textCenter.ALIGN_CENTER);
        
        CellStyle textCenterBold = workbook.createCellStyle();
        textCenterBold.setAlignment(textCenterBold.ALIGN_CENTER);
        textCenterBold.setFont(fontBold);
        
		HSSFSheet hssfSheet = workbook.createSheet(documentExcel.getNameOfWorkSheet());
		hssfSheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
    	HSSFRow row1 = hssfSheet.createRow(1);
    	row1.createCell(1).setCellValue(documentExcel.getNameOfWorkSheet());
    	row1.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));
    	HSSFRow row2 = hssfSheet.createRow(2);
    	row2.createCell(1).setCellValue(documentExcel.getTitle());
    	row2.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 4));
    	HSSFRow row3 = hssfSheet.createRow(3);
    	row3.createCell(2).setCellValue("Tên dự án: "+documentExcel.getNameOfProject());
    	row3.getCell(2).setCellStyle(styleBold);
        
    	HSSFRow sheetRow5 = hssfSheet.createRow(5);
    	sheetRow5.createCell(0).setCellValue("TT");
    	sheetRow5.createCell(1).setCellValue("Các hệ số");
    	sheetRow5.createCell(2).setCellValue("Trọng số");
    	sheetRow5.createCell(3).setCellValue("Giá trị xếp hạng");
    	sheetRow5.createCell(4).setCellValue("Kết quả");
    	sheetRow5.createCell(5).setCellValue("Ghi chú");
    	sheetRow5.getCell(0).setCellStyle(styleBlue);
    	sheetRow5.getCell(1).setCellStyle(styleBlue);
    	sheetRow5.getCell(2).setCellStyle(styleBlue);
    	sheetRow5.getCell(3).setCellStyle(styleBlue);
    	sheetRow5.getCell(4).setCellStyle(styleBlue);
    	sheetRow5.getCell(5).setCellStyle(styleBlue);
    	
    	List<Object> listObject = documentExcel.getData();
    	List<Hesokythuat> listHeso = (List<Hesokythuat>) listObject.get(0);
    	List<Float> tinhXepHang = (List<Float>) listObject.get(1);
    	float tongXepHang =  (Float) listObject.get(2);
    	List<Xephangkythuat> listXepHang = (List<Xephangkythuat>) listObject.get(3);
    	HSSFRow sheetRow6 = hssfSheet.createRow(6);
    	sheetRow6.createCell(0).setCellValue("I");
    	sheetRow6.getCell(0).setCellStyle(textCenterBold);
    	sheetRow6.createCell(1).setCellValue("Hệ số KT-CN (TFW)");
    	sheetRow6.getCell(1).setCellStyle(styleBold);
    	sheetRow6.createCell(4).setCellValue(tongXepHang);
    	sheetRow6.getCell(4).setCellStyle(textCenterBold);
    	int countRow = 7;
    		for(int i = 0; i < listHeso.size(); i++){
    		HSSFRow sheetRow = hssfSheet.createRow(countRow);
    		sheetRow.createCell(0).setCellValue(listHeso.get(i).getHesokythuatid());
    		sheetRow.getCell(0).setCellStyle(textCenter);
    		sheetRow.createCell(1).setCellValue(listHeso.get(i).getTenheso());
    		sheetRow.createCell(2).setCellValue(listHeso.get(i).getTrongso());
    		if(listXepHang.size() != 0){
    		sheetRow.createCell(3).setCellValue(listXepHang.get(i).getGiatrixephang());
    		}else{
    			sheetRow.createCell(3).setCellValue(0);
    		}
    		if(tinhXepHang.size() != 0){
    		sheetRow.createCell(4).setCellValue(tinhXepHang.get(i));
    		}else{
    			sheetRow.createCell(4).setCellValue(0);
    		}
    		sheetRow.getCell(3).setCellStyle(textCenter);
    		if(listXepHang.size() != 0){
    		sheetRow.createCell(5).setCellValue(listXepHang.get(i).getGhichu());
    		}else{
    			sheetRow.createCell(5).setCellValue("");
    		}
    		countRow++;
    		}
    		hssfSheet.setColumnWidth(1, 22800);
    		hssfSheet.setColumnWidth(3, 3900);
	}
	
	@SuppressWarnings({ "unchecked", "static-access"})
	void createPhuLucVI(DocumentExcel documentExcel, HSSFWorkbook workbook){
		CellStyle styleBold = workbook.createCellStyle();
        Font fontBold = workbook.createFont();
        fontBold.setFontName("Arial");
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setFont(fontBold);
        
		CellStyle styleBlue = workbook.createCellStyle();
		styleBlue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		styleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleBlue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBlue.setAlignment(styleBlue.ALIGN_CENTER);
		styleBlue.setFont(fontBold);
		
		CellStyle textCenter = workbook.createCellStyle();
        textCenter.setAlignment(textCenter.ALIGN_CENTER);
        
        CellStyle textCenterBold = workbook.createCellStyle();
        textCenterBold.setAlignment(textCenterBold.ALIGN_CENTER);
        textCenterBold.setFont(fontBold);
        
		HSSFSheet hssfSheet = workbook.createSheet(documentExcel.getNameOfWorkSheet());
		hssfSheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
    	HSSFRow row1 = hssfSheet.createRow(1);
    	row1.createCell(1).setCellValue(documentExcel.getNameOfWorkSheet());
    	row1.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));
    	HSSFRow row2 = hssfSheet.createRow(2);
    	row2.createCell(1).setCellValue(documentExcel.getTitle());
    	row2.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 4));
    	HSSFRow row3 = hssfSheet.createRow(3);
    	row3.createCell(2).setCellValue("Tên dự án: "+documentExcel.getNameOfProject());
    	row3.getCell(2).setCellStyle(styleBold);
        
    	HSSFRow sheetRow5 = hssfSheet.createRow(5);
    	sheetRow5.createCell(0).setCellValue("TT");
    	sheetRow5.createCell(1).setCellValue("Các hệ số tác động môi trường");
    	sheetRow5.createCell(2).setCellValue("Giá trị xếp hạng");
    	sheetRow5.createCell(3).setCellValue("Trọng số");
    	sheetRow5.createCell(4).setCellValue("Kết quả");
    	sheetRow5.createCell(5).setCellValue("Độ ổn định kinh nghiệm");
    	sheetRow5.getCell(0).setCellStyle(styleBlue);
    	sheetRow5.getCell(1).setCellStyle(styleBlue);
    	sheetRow5.getCell(2).setCellStyle(styleBlue);
    	sheetRow5.getCell(3).setCellStyle(styleBlue);
    	sheetRow5.getCell(4).setCellStyle(styleBlue);
    	sheetRow5.getCell(5).setCellStyle(styleBlue);
    	
    	List<Object> listObject = documentExcel.getData();
    	List<Hesomoitruong> listMoiTruong = (List<Hesomoitruong>) listObject.get(0);
    	List<Xephangmoitruong> listXepHangMT = (List<Xephangmoitruong>) listObject.get(1);
    	List<Float> tinhMoiTruong =  (List<Float>) listObject.get(2);
    	float tongNoisuy = (Float) listObject.get(3);
    	float tongMoiTruong = (Float) listObject.get(4);
    	float tongOnDinh = (Float) listObject.get(5);
    	HSSFRow sheetRow6 = hssfSheet.createRow(6);
    	sheetRow6.createCell(0).setCellValue("I");
    	sheetRow6.getCell(0).setCellStyle(textCenterBold);
    	sheetRow6.createCell(1).setCellValue("Hệ số tác động môi trường và nhóm làm việc (EFW).");
    	sheetRow6.getCell(1).setCellStyle(styleBold);
    	sheetRow6.createCell(4).setCellValue(tongMoiTruong);
    	sheetRow6.getCell(4).setCellStyle(textCenterBold);
    	int countRow = 7;
    		for(int i = 0; i < listMoiTruong.size(); i++){
    		HSSFRow sheetRow = hssfSheet.createRow(countRow);
    		sheetRow.createCell(0).setCellValue(listMoiTruong.get(i).getHesomoitruongid());
    		sheetRow.getCell(0).setCellStyle(textCenter);
    		sheetRow.createCell(1).setCellValue(listMoiTruong.get(i).getMotaheso());
    		if(listXepHangMT.size() != 0){
    		sheetRow.createCell(2).setCellValue(listXepHangMT.get(i).getGiatrixephang());
    		}else{
    			sheetRow.createCell(2).setCellValue(0);
    		}
    		sheetRow.createCell(3).setCellValue(listMoiTruong.get(i).getTrongso());
    		if(tinhMoiTruong.size() != 0){
    		sheetRow.createCell(4).setCellValue(tinhMoiTruong.get(i));
    		}else{
    			sheetRow.createCell(4).setCellValue(0);
    		}
    		if(listXepHangMT.size() != 0){
    		sheetRow.createCell(5).setCellValue(listXepHangMT.get(i).getOndinh());
    		}else{
    			sheetRow.createCell(5).setCellValue(0);
    		}
    		countRow++;
    		}
    		HSSFRow sheetRow15 = hssfSheet.createRow(countRow);
    		sheetRow15.createCell(0).setCellValue("II");
    		sheetRow15.getCell(0).setCellStyle(textCenterBold);
    		sheetRow15.createCell(1).setCellValue("Độ ổn định về kinh nghiệm (ES)");
    		sheetRow15.getCell(1).setCellStyle(styleBold);
    		sheetRow15.createCell(5).setCellValue(tongOnDinh);
    		sheetRow15.getCell(5).setCellStyle(styleBold);
    		
    		HSSFRow sheetRow16 = hssfSheet.createRow(countRow+1);
    		sheetRow16.createCell(0).setCellValue("III");
    		sheetRow16.getCell(0).setCellStyle(textCenterBold);
    		sheetRow16.createCell(1).setCellValue("Nội suy thời gian lao động (P)");
    		sheetRow16.getCell(1).setCellStyle(styleBold);
    		sheetRow16.createCell(5).setCellValue(tongNoisuy);
    		sheetRow16.getCell(5).setCellStyle(styleBold);
    		
    		hssfSheet.setColumnWidth(1, 24800);
    		hssfSheet.setColumnWidth(2, 3900);
    		hssfSheet.setColumnWidth(5, 5600);
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	void createPhuLucBangLuong(DocumentExcel documentExcel, HSSFWorkbook workbook){
		CellStyle formatSeparator = workbook.createCellStyle();
		formatSeparator.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		
		CellStyle styleBold = workbook.createCellStyle();
        Font fontBold = workbook.createFont();
        fontBold.setFontName("Arial");
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setFont(fontBold);
        
		CellStyle styleBlue = workbook.createCellStyle();
		styleBlue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		styleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleBlue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBlue.setAlignment(styleBlue.ALIGN_CENTER);
		styleBlue.setFont(fontBold);
		
		CellStyle textCenter = workbook.createCellStyle();
        textCenter.setAlignment(textCenter.ALIGN_CENTER);
        
        CellStyle textCenterGreen = workbook.createCellStyle();
        textCenterGreen.setAlignment(textCenterGreen.ALIGN_CENTER);
        textCenterGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        
        CellStyle textCenterBold = workbook.createCellStyle();
        textCenterBold.setAlignment(textCenterBold.ALIGN_CENTER);
        textCenterBold.setFont(fontBold);
        
		HSSFSheet hssfSheet = workbook.createSheet(documentExcel.getNameOfWorkSheet());
		hssfSheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 8));
    	HSSFRow row1 = hssfSheet.createRow(1);
    	row1.createCell(4).setCellValue(documentExcel.getNameOfWorkSheet());
    	row1.getCell(4).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 8));
    	HSSFRow row2 = hssfSheet.createRow(2);
    	row2.createCell(6).setCellValue("Tên dự án: "+documentExcel.getNameOfProject());
    	row2.getCell(6).setCellStyle(textCenterBold);
    	
    	HSSFRow sheetRow5 = hssfSheet.createRow(5);
    	sheetRow5.createCell(0).setCellValue("TT");
    	sheetRow5.createCell(1).setCellValue("Bậc");
    	sheetRow5.createCell(2).setCellValue("Hệ số");
    	sheetRow5.createCell(3).setCellValue("Lương cơ bản");
    	sheetRow5.createCell(4).setCellValue("Lương tăng thêm");
    	sheetRow5.createCell(5).setCellValue("Lương phụ");
    	sheetRow5.createCell(6).setCellValue("PC Khu vực");
    	sheetRow5.createCell(7).setCellValue("PC Lưu động");
    	sheetRow5.createCell(8).setCellValue("CPKG");
    	sheetRow5.createCell(9).setCellValue("BHXH \n KPCĐ \n BHYT \n BHTN");
    	sheetRow5.createCell(10).setCellValue("Tổng");
    	sheetRow5.createCell(11).setCellValue("CP 1 ngày công");
    	sheetRow5.createCell(12).setCellValue("CP 1 giờ công");
    	sheetRow5.getCell(0).setCellStyle(styleBlue);
    	sheetRow5.getCell(1).setCellStyle(styleBlue);
    	sheetRow5.getCell(2).setCellStyle(styleBlue);
    	sheetRow5.getCell(3).setCellStyle(styleBlue);
    	sheetRow5.getCell(4).setCellStyle(styleBlue);
    	sheetRow5.getCell(5).setCellStyle(styleBlue);
    	sheetRow5.getCell(6).setCellStyle(styleBlue);
    	sheetRow5.getCell(7).setCellStyle(styleBlue);
    	sheetRow5.getCell(8).setCellStyle(styleBlue);
    	sheetRow5.getCell(9).setCellStyle(styleBlue);
    	sheetRow5.getCell(10).setCellStyle(styleBlue);
    	sheetRow5.getCell(11).setCellStyle(styleBlue);
    	sheetRow5.getCell(12).setCellStyle(styleBlue);
    	
    	List<Object> listObject = documentExcel.getData();
    	List<Luong> luongs = (List<Luong>) listObject.get(0);
    	List<Giatriluong> giaTriLuong = (List<Giatriluong>) listObject.get(1);
    	List<Integer> listCPKG = (List<Integer>) listObject.get(2);
    	List<Integer> listBaoHiem = (List<Integer>) listObject.get(3);
    	List<Integer> listTong = (List<Integer>) listObject.get(4);
    	List<Integer> listCP1Ngay = (List<Integer>) listObject.get(5);
    	List<Integer> listCP1Gio = (List<Integer>) listObject.get(6);
    	List<Integer> listLuongCoBan = (List<Integer>) listObject.get(7);
    	List<Integer> listLuongPhu = (List<Integer>) listObject.get(8);
    	int mucLuongNhaNuoc = (Integer) listObject.get(9);
    	Project project =  (Project) listObject.get(10);
    	int countRow = 6;
		for(int i = 0; i < luongs.size(); i++){
    		HSSFRow sheetRow = hssfSheet.createRow(countRow);
    		sheetRow.createCell(0).setCellValue(luongs.get(i).getLuongid());
    		if(project.getLuong().getBac() == luongs.get(i).getBac()){
    			sheetRow.getCell(0).setCellStyle(textCenterGreen);
    		}else{
    			sheetRow.getCell(0).setCellStyle(textCenter);
    		}
    		
    		sheetRow.createCell(1).setCellValue(luongs.get(i).getBac());
    		if(project.getLuong().getBac() == luongs.get(i).getBac()){
    			sheetRow.getCell(1).setCellStyle(textCenterGreen);
    		}else{
    			sheetRow.getCell(1).setCellStyle(textCenter);
    		}
    		sheetRow.createCell(2).setCellValue(luongs.get(i).getHeso());
    		if(project.getLuong().getBac() == luongs.get(i).getBac()){
    			sheetRow.getCell(2).setCellStyle(textCenterGreen);
    		}else{
    			sheetRow.getCell(2).setCellStyle(textCenter);
    		}
    		if(listLuongCoBan.size() != 0){
    		sheetRow.createCell(3).setCellValue(listLuongCoBan.get(i));
    		sheetRow.getCell(3).setCellStyle(formatSeparator);
    		}else{
    			sheetRow.createCell(3).setCellValue(0);
    		}
    		sheetRow.createCell(4).setCellValue(giaTriLuong.get(i).getLuongtangthem());
    		sheetRow.getCell(4).setCellStyle(formatSeparator);
    		if(listLuongPhu.size() != 0){
    		sheetRow.createCell(5).setCellValue(listLuongPhu.get(i));
    		sheetRow.getCell(5).setCellStyle(formatSeparator);
    		}else{
    			sheetRow.createCell(5).setCellValue(0);
    		}
    		sheetRow.createCell(6).setCellValue(giaTriLuong.get(i).getPckhuvuc());
    		sheetRow.getCell(6).setCellStyle(formatSeparator);
    		sheetRow.createCell(7).setCellValue(giaTriLuong.get(i).getPcluudong());
    		sheetRow.getCell(7).setCellStyle(formatSeparator);
    		if(listCPKG.size() != 0){
    		sheetRow.createCell(8).setCellValue(listCPKG.get(i));
    		sheetRow.getCell(8).setCellStyle(formatSeparator);
    		}else{
    			sheetRow.createCell(8).setCellValue(0);
    		}
    		if(listBaoHiem.size() != 0){
    		sheetRow.createCell(9).setCellValue(listBaoHiem.get(i));
    		sheetRow.getCell(9).setCellStyle(formatSeparator);
    		}else{
    			sheetRow.createCell(9).setCellValue(0);
    		}
    		if(listTong.size() != 0){
    		sheetRow.createCell(10).setCellValue(listTong.get(i));
    		sheetRow.getCell(10).setCellStyle(formatSeparator);
    		}else{
    			sheetRow.createCell(10).setCellValue(0);
    		}
    		if(listCP1Ngay.size() != 0){
    		sheetRow.createCell(11).setCellValue(listCP1Ngay.get(i));
    		sheetRow.getCell(11).setCellStyle(formatSeparator);
    		}else{
    			sheetRow.createCell(11).setCellValue(0);
    		}
    		if(listCP1Gio.size() != 0){
    		sheetRow.createCell(12).setCellValue(listCP1Gio.get(i));
    		sheetRow.getCell(12).setCellStyle(formatSeparator);
    		}else{
    			sheetRow.createCell(12).setCellValue(0);
    		}
    		countRow++;
    	}
    		HSSFRow sheetRow20 = hssfSheet.createRow(countRow+1);
    		
    		sheetRow20.createCell(1).setCellValue("Lương cơ bản:");
    		sheetRow20.getCell(1).setCellStyle(styleBold);
    		sheetRow20.createCell(2).setCellValue(mucLuongNhaNuoc);
    		sheetRow20.getCell(2).setCellStyle(formatSeparator);
    		
    		hssfSheet.setColumnWidth(3, 3700);
    		hssfSheet.setColumnWidth(4, 4300);
    		hssfSheet.setColumnWidth(5, 3000);
    		hssfSheet.setColumnWidth(6, 2800);
    		hssfSheet.setColumnWidth(7, 3200);
    		hssfSheet.setColumnWidth(8, 2400);
    		hssfSheet.setColumnWidth(9, 2800);
    		hssfSheet.setColumnWidth(10, 2600);
    		hssfSheet.setColumnWidth(11, 3800);
    		hssfSheet.setColumnWidth(12, 3500);
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	void createPhuLucVII(DocumentExcel documentExcel, HSSFWorkbook workbook){
		CellStyle formatSeparator = workbook.createCellStyle();
		formatSeparator.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		
		CellStyle styleBold = workbook.createCellStyle();
        Font fontBold = workbook.createFont();
        fontBold.setFontName("Arial");
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setFont(fontBold);
        
		CellStyle styleBlue = workbook.createCellStyle();
		styleBlue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		styleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleBlue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBlue.setAlignment(styleBlue.ALIGN_CENTER);
		styleBlue.setFont(fontBold);
		
		CellStyle textCenter = workbook.createCellStyle();
        textCenter.setAlignment(textCenter.ALIGN_CENTER);
        
        CellStyle textCenterBold = workbook.createCellStyle();
        textCenterBold.setAlignment(textCenterBold.ALIGN_CENTER);
        textCenterBold.setFont(fontBold);
		
		HSSFSheet hssfSheet = workbook.createSheet(documentExcel.getNameOfWorkSheet());
		hssfSheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
    	HSSFRow row1 = hssfSheet.createRow(1);
    	row1.createCell(1).setCellValue(documentExcel.getNameOfWorkSheet());
    	row1.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));
    	HSSFRow row2 = hssfSheet.createRow(2);
    	row2.createCell(1).setCellValue(documentExcel.getTitle());
    	row2.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 5));
    	HSSFRow row3 = hssfSheet.createRow(3);
    	row3.createCell(3).setCellValue("Tên dự án: "+documentExcel.getNameOfProject());
    	row3.getCell(3).setCellStyle(styleBold);
        
    	HSSFRow sheetRow5 = hssfSheet.createRow(5);
    	sheetRow5.createCell(0).setCellValue("Hạng mục");
    	sheetRow5.createCell(1).setCellValue("Diễn giải");
    	sheetRow5.createCell(2).setCellValue("Giá trị");
    	sheetRow5.createCell(3).setCellValue("Ghi chú");
    	sheetRow5.getCell(0).setCellStyle(styleBlue);
    	sheetRow5.getCell(1).setCellStyle(styleBlue);
    	sheetRow5.getCell(2).setCellStyle(styleBlue);
    	sheetRow5.getCell(3).setCellStyle(styleBlue);
    	HSSFRow sheetRow6 = hssfSheet.createRow(6);
    	sheetRow6.createCell(0).setCellValue("Tính điểm trường hợp sử dụng (Use-cases)");
    	sheetRow6.getCell(0).setCellStyle(styleBold);
    	
    	List<Object> listObject = documentExcel.getData();
    	Integer tongActor = (Integer) listObject.get(0);
    	Integer tongUsecase = (Integer) listObject.get(1);
    	float tongKyThuat = (Float) listObject.get(2);
    	float tongMoiTruong = (Float) listObject.get(3);
    	float noiSuy = (Float) listObject.get(4);
    	Trongsonoluc trongSo = (Trongsonoluc) listObject.get(5);
    	Integer luongBinhQuan = (Integer) listObject.get(6);
    	
    	
    	HSSFRow sheetRow7 = hssfSheet.createRow(7);
    	sheetRow7.createCell(0).setCellValue("Điểm actor (TAW)");
    	sheetRow7.createCell(1).setCellValue("Phụ lục III");
    	sheetRow7.getCell(1).setCellStyle(textCenter);
    	sheetRow7.createCell(2).setCellValue(tongActor);
    	sheetRow7.createCell(3).setCellValue("");
    	
    	HSSFRow sheetRow8 = hssfSheet.createRow(8);
    	sheetRow8.createCell(0).setCellValue("Điểm Use-cases (TBF)");
    	sheetRow8.createCell(1).setCellValue("Phụ lục IV");
    	sheetRow8.getCell(1).setCellStyle(textCenter);
    	sheetRow8.createCell(2).setCellValue(tongUsecase);
    	sheetRow8.createCell(3).setCellValue("");
    	
    	HSSFRow sheetRow9 = hssfSheet.createRow(9);
    	sheetRow9.createCell(0).setCellValue("Tính điểm UUCP");
    	sheetRow9.createCell(1).setCellValue("UUCP=TAW + TBF");
    	sheetRow9.getCell(1).setCellStyle(textCenter);
    	sheetRow9.createCell(2).setCellValue(tongUsecase+tongActor);
    	sheetRow9.createCell(3).setCellValue("Giá trị điểm trường hợp sử dụng trước khi hiệu chỉnh");
    	
    	HSSFRow sheetRow10 = hssfSheet.createRow(10);
    	sheetRow10.createCell(0).setCellValue("Hệ số phức tạp về KT-CN (TCF)");
    	sheetRow10.createCell(1).setCellValue("TCF=0.6+(0.01*TFW)");
    	sheetRow10.getCell(1).setCellStyle(textCenter);
    	sheetRow10.createCell(2).setCellValue(0.6+0.01*tongKyThuat);
    	sheetRow10.createCell(3).setCellValue("0.6, 0.01: Trọng số đo chuẩn");
    	
    	HSSFRow sheetRow11 = hssfSheet.createRow(11);
    	sheetRow11.createCell(0).setCellValue("Hệ số phức tạp về môi trường (EF)");
    	sheetRow11.createCell(1).setCellValue("EF=1.4+(-0.03*EFW)");
    	sheetRow11.getCell(1).setCellStyle(textCenter);
    	sheetRow11.createCell(2).setCellValue(1.4+(-0.03*tongMoiTruong));
    	sheetRow11.createCell(3).setCellValue("1.4, 0.03: Trọng số đo chuẩn");
    	
    	float AUCP = (float) ((tongUsecase+tongActor) * (0.6+0.01*tongKyThuat) * (1.4+((-0.03)*tongMoiTruong)));
    	HSSFRow sheetRow12 = hssfSheet.createRow(12);
    	sheetRow12.createCell(0).setCellValue("Tính điểm AUCP");
    	sheetRow12.createCell(1).setCellValue("AUCP = UUCP*TCF*EF");
    	sheetRow12.getCell(1).setCellStyle(textCenter);
    	sheetRow12.createCell(2).setCellValue(AUCP);
    	sheetRow12.createCell(3).setCellValue("Giá trị điểm trường hợp sử dụng sau khi hiệu chỉnh");
    	
    	HSSFRow sheetRow13 = hssfSheet.createRow(13);
    	sheetRow13.createCell(0).setCellValue("Nội suy thời gian lao động");
    	sheetRow13.getCell(0).setCellStyle(styleBold);
    	sheetRow13.createCell(1).setCellValue("P=người/giờ/AUCP");
    	sheetRow13.getCell(1).setCellStyle(textCenter);
    	sheetRow13.createCell(2).setCellValue(noiSuy);
    	sheetRow13.createCell(3).setCellValue("");
    	
    	HSSFRow sheetRow14 = hssfSheet.createRow(14);
    	sheetRow14.createCell(0).setCellValue("Giá trị nỗ lực thực tế (E)");
    	sheetRow14.getCell(0).setCellStyle(styleBold);
    	sheetRow14.createCell(1).setCellValue("E="+trongSo.getMota()+"*AUCP");
    	sheetRow14.getCell(1).setCellStyle(textCenter);
    	sheetRow14.createCell(2).setCellValue(trongSo.getGiatri() * AUCP);
    	sheetRow14.createCell(3).setCellValue("6/6-10/6");
    	
    	HSSFRow sheetRow15 = hssfSheet.createRow(15);
    	sheetRow15.createCell(0).setCellValue("Mức lương lao động bình quân (H)");
    	sheetRow15.getCell(0).setCellStyle(styleBold);
    	sheetRow15.createCell(1).setCellValue("H=người/giờ");
    	sheetRow15.getCell(1).setCellStyle(textCenter);
    	sheetRow15.createCell(2).setCellValue(luongBinhQuan);
    	sheetRow15.createCell(3).setCellValue("");
    	
    	HSSFRow sheetRow16 = hssfSheet.createRow(16);
    	sheetRow16.createCell(0).setCellValue("Định giá phần mềm nội bộ (G)");
    	sheetRow16.getCell(0).setCellStyle(styleBold);
    	sheetRow16.createCell(1).setCellValue("G=1.4*E*P*H");
    	sheetRow16.getCell(1).setCellStyle(textCenter);
    	sheetRow16.createCell(2).setCellValue(1.4*luongBinhQuan * noiSuy * (trongSo.getGiatri() * AUCP));
    	sheetRow16.getCell(2).setCellStyle(formatSeparator);
    	sheetRow16.createCell(3).setCellValue("");
    	
    	hssfSheet.setColumnWidth(0, 9900);
		hssfSheet.setColumnWidth(1, 5200);
		hssfSheet.setColumnWidth(2, 3800);
		hssfSheet.setColumnWidth(3, 11300);
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	void createPhuLucVIII(DocumentExcel documentExcel, HSSFWorkbook workbook){
		CellStyle formatSeparator = workbook.createCellStyle();
		formatSeparator.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		
		CellStyle styleBold = workbook.createCellStyle();
        Font fontBold = workbook.createFont();
        fontBold.setFontName("Arial");
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setFont(fontBold);
        
		CellStyle styleBlue = workbook.createCellStyle();
		styleBlue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		styleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleBlue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBlue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBlue.setAlignment(styleBlue.ALIGN_CENTER);
		styleBlue.setFont(fontBold);
		
		CellStyle textCenter = workbook.createCellStyle();
        textCenter.setAlignment(textCenter.ALIGN_CENTER);
        
        CellStyle textCenterBold = workbook.createCellStyle();
        textCenterBold.setAlignment(textCenterBold.ALIGN_CENTER);
        textCenterBold.setFont(fontBold);
		
		HSSFSheet hssfSheet = workbook.createSheet(documentExcel.getNameOfWorkSheet());
		hssfSheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
    	HSSFRow row1 = hssfSheet.createRow(1);
    	row1.createCell(1).setCellValue(documentExcel.getNameOfWorkSheet());
    	row1.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));
    	HSSFRow row2 = hssfSheet.createRow(2);
    	row2.createCell(1).setCellValue(documentExcel.getTitle());
    	row2.getCell(1).setCellStyle(textCenterBold);
    	
    	hssfSheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 5));
    	HSSFRow row3 = hssfSheet.createRow(3);
    	row3.createCell(3).setCellValue("Tên dự án: "+documentExcel.getNameOfProject());
    	row3.getCell(3).setCellStyle(styleBold);
        
    	HSSFRow sheetRow5 = hssfSheet.createRow(5);
    	sheetRow5.createCell(0).setCellValue("TT");
    	sheetRow5.createCell(1).setCellValue("Khoản mục chi phí");
    	sheetRow5.createCell(2).setCellValue("Cách tính");
    	sheetRow5.createCell(3).setCellValue("Giá trị");
    	sheetRow5.createCell(4).setCellValue("Ký hiệu");
    	sheetRow5.createCell(5).setCellValue("Ghi chú");
    	sheetRow5.getCell(0).setCellStyle(styleBlue);
    	sheetRow5.getCell(1).setCellStyle(styleBlue);
    	sheetRow5.getCell(2).setCellStyle(styleBlue);
    	sheetRow5.getCell(3).setCellStyle(styleBlue);
    	sheetRow5.getCell(4).setCellStyle(styleBlue);
    	sheetRow5.getCell(5).setCellStyle(styleBlue);
    	
    	List<Object> listObject = documentExcel.getData();
    	Integer tongActor = (Integer) listObject.get(0);
    	Integer tongUsecase = (Integer) listObject.get(1);
    	float tongKyThuat = (Float) listObject.get(2);
    	float tongMoiTruong = (Float) listObject.get(3);
    	float noiSuy = (Float) listObject.get(4);
    	Trongsonoluc trongSo = (Trongsonoluc) listObject.get(5);
    	Integer luongBinhQuan = (Integer) listObject.get(6);
    	int G = (int) (1.4*luongBinhQuan*noiSuy*(trongSo.getGiatri() * ((tongUsecase+tongActor) * (0.6+0.01*tongKyThuat) * (1.4+(-0.03*tongMoiTruong)))));
    	int C = (int) (G*0.65);
    	int TL = (int) ((G+C)*0.06);
    	HSSFRow sheetRow6 = hssfSheet.createRow(6);
    	sheetRow6.createCell(0).setCellValue(1);
    	sheetRow6.getCell(0).setCellStyle(textCenter);
    	sheetRow6.createCell(1).setCellValue("Giá trị phần mềm");
    	sheetRow6.createCell(2).setCellValue("1.4 x E x P x H");
    	sheetRow6.getCell(2).setCellStyle(textCenter);
    	sheetRow6.createCell(3).setCellValue(G);
    	sheetRow6.getCell(3).setCellStyle(formatSeparator);
    	sheetRow6.createCell(4).setCellValue("G");
    	sheetRow6.getCell(4).setCellStyle(textCenterBold);
    	sheetRow6.createCell(5).setCellValue("");
    	
    	HSSFRow sheetRow7 = hssfSheet.createRow(7);
    	sheetRow7.createCell(0).setCellValue(2);
    	sheetRow7.getCell(0).setCellStyle(textCenter);
    	sheetRow7.createCell(1).setCellValue("Chi phí chung");
    	sheetRow7.createCell(2).setCellValue("G*65%");
    	sheetRow7.getCell(2).setCellStyle(textCenter);
    	sheetRow7.createCell(3).setCellValue(C);
    	sheetRow7.getCell(3).setCellStyle(formatSeparator);
    	sheetRow7.createCell(4).setCellValue("C");
    	sheetRow7.getCell(4).setCellStyle(textCenterBold);
    	sheetRow7.createCell(5).setCellValue("Mục 4.2 qui định chi phí chung (65%)");
    	
    	HSSFRow sheetRow8 = hssfSheet.createRow(8);
    	sheetRow8.createCell(0).setCellValue(3);
    	sheetRow8.getCell(0).setCellStyle(textCenter);
    	sheetRow8.createCell(1).setCellValue("Thu nhập chịu thuế tính trước");
    	sheetRow8.createCell(2).setCellValue("(G+C)x 6%");
    	sheetRow8.getCell(2).setCellStyle(textCenter);
    	sheetRow8.createCell(3).setCellValue(TL);
    	sheetRow8.getCell(3).setCellStyle(formatSeparator);
    	sheetRow8.createCell(4).setCellValue("TL");
    	sheetRow8.getCell(4).setCellStyle(textCenterBold);
    	sheetRow8.createCell(5).setCellValue("Mục 4.3 thu nhập chịu thuế tính trước (6%)");
    	
    	HSSFRow sheetRow9 = hssfSheet.createRow(9);
    	sheetRow9.createCell(0).setCellValue(4);
    	sheetRow9.getCell(0).setCellStyle(textCenter);
    	sheetRow9.createCell(1).setCellValue("Chi phí phần mềm");
    	sheetRow9.createCell(2).setCellValue("G+C+TL");
    	sheetRow9.getCell(2).setCellStyle(textCenter);
    	sheetRow9.createCell(3).setCellValue(G+C+TL);
    	sheetRow9.getCell(3).setCellStyle(formatSeparator);
    	sheetRow9.createCell(4).setCellValue("GPM");
    	sheetRow9.getCell(4).setCellStyle(textCenterBold);
    	sheetRow9.createCell(5).setCellValue("");
    	
    	HSSFRow sheetRow10 = hssfSheet.createRow(10);
    	sheetRow10.createCell(1).setCellValue("Tổng cộng");
    	sheetRow10.getCell(1).setCellStyle(styleBold);
    	sheetRow10.createCell(3).setCellValue(G+C+TL);
    	sheetRow10.getCell(3).setCellStyle(formatSeparator);
    	
		hssfSheet.setColumnWidth(1, 6300);
		hssfSheet.setColumnWidth(2, 4200);
		hssfSheet.setColumnWidth(3, 3500);
		hssfSheet.setColumnWidth(5, 9400);
	}
}