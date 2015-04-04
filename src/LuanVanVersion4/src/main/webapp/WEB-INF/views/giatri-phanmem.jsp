<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/resources/css/giatri-phanmem.css" />" rel="stylesheet">

	
	<div class="text-center">
		<h3>BẢNG TÍNH TOÁN GIÁ TRỊ PHẦN MỀM</h3>
	</div>
<form action="/luanvan/updateGiaTriPhanMem" method="get" role="form">
	<input type="hidden" name="projectNameForGiaTriPhanMem" value="${project.tenproject}">
	<table class="table table-bordered">
		<thead>
			<tr class="text-center" style="font-weight: bold;">
				<td>TT</td>
				<td>Hạng mục</td>
				<td>Diễn giải</td>
				<td>Giá trị</td>
				<td>Ghi chú</td>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td class="text-center text-bold">I</td>
			<td class="text-bold">Tính điểm trường hợp sử dụng (Use-cases)</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="text-center">1</td>
			<td>Điểm actor (TAW)</td>
			<td></td>
			<td>${tongDiemActor}</td>
			<td></td>
		</tr>
		<tr>
			<td class="text-center">2</td>
			<td>Điểm Use-cases (TBF)</td>
			<td></td>
			<td>${tongDiemTungUsecase}</td>
			<td></td>
		</tr>
		<tr>
			<td class="text-center">3</td>
			<td>Tính điểm UUCP</td>
			<td class="text-center text-bold">UUCP=TAW + TBF</td>
			<td>${tongDiemActor + tongDiemTungUsecase}</td>
			<td>Giá trị điểm trường hợp sử dụng trước khi hiệu chỉnh</td>
		</tr>
		<tr>
			<td class="text-center">4</td>
			<td>Hệ số phức tạp về KT-CN (TCF)</td>
			<td class="text-center text-bold">TCF=0.6+(0.01*TFW)</td>
			<td>${(tongKetQuaFromKyThuat * 0.01) + 0.6} </td>
			<td>0.6, 0.01: Trọng số đo chuẩn</td>
		</tr>
		<tr>
			<td class="text-center">5</td>
			<td>Hệ số phức tạp về môi trường (EF)</td>
			<td class="text-center text-bold">EF=1.4+(-0.03*EFW)</td>
			<td>${1.4 + (-0.03 * tongKetQuaFromMoiTruong)}</td>
			<td>1.4, 0.03: Trọng số đo chuẩn</td>
		</tr>
		<tr>
			<td class="text-center">6</td>
			<td>Tính điểm AUCP</td>
			<td class="text-center text-bold">AUCP = UUCP*TCF*EF</td>
			<td id="AUCP">${((tongDiemActor + tongDiemTungUsecase) * ((tongKetQuaFromKyThuat * 0.01) + 0.6) * (1.4 + (-0.03 * tongKetQuaFromMoiTruong)))} </td>
			<td>Giá trị điểm trường hợp sử dụng sau khi hiệu chỉnh</td>
		</tr>
		<tr>
			<td class="text-center text-bold">II</td>
			<td class="text-bold">Nội suy thời gian lao động (P)</td>
			<td class="text-center text-bold">P=người/giờ/AUCP</td>
			<td id="noiSuyP">${tinhNoiSuy}</td>
			<td></td>
		</tr>
		<tr>
			<td class="text-center text-bold">III</td>
			<td class="text-bold">Giá trị nỗ lực thực tế (E)</td>
			<td class="text-center text-bold">E= <select id="selectNoLuc" name="selectNoLuc">
													<c:forEach items="${listTrongSoNoLuc}" var="list">
														<c:choose>
															<c:when test="${project.trongsonoluc.trongsoid == list.trongsoid}">
																<option value="${list.giatri}" selected>${list.mota}</option>
															</c:when>
															<c:otherwise>
																<option value="${list.giatri}">${list.mota}</option>
															</c:otherwise>
														</c:choose>
														
													</c:forEach>
												</select> *AUCP
			</td>
			<td id="noLucE"></td>
			<td></td>
		</tr>
		<tr>
			<td class="text-center text-bold">IV</td>
			<td class="text-bold">Mức lương lao động bình quân (H)</td>
			<td class="text-center text-bold">H=người/giờ</td>
			<td id="chooseLuongFromBangLuong" class="formatKetQua"></td>
			<td></td>
		</tr>
		<tr>
			<td class="text-center text-bold">V</td>
			<td class="text-bold">Định giá phần mềm nội bộ (G)</td>
			<td class="text-center text-bold">G=1.4*E*P*H</td>
			<td id="dinhGiaG" class="formatKetQua"></td>
			<td></td>
		</tr>
		</tbody>
	</table>
	<button type="submit" class="btn btn-primary">Lưu</button>
</form>
<script src="<c:url value="/resources/js/giatri-phanmem.js" />" ></script>