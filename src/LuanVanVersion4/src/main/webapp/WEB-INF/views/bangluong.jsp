<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="get" action="/luanvan/updateBangLuong" role="form">
	<input type="hidden" name="projectNameForLuong" value="${project.tenproject}">
	<div class="text-center" style="margin-top: 10px;  ">
		<span>Chọn mức lương cơ bản:</span> 
		<input type="number" class="form-control" value="${project.luongcoban}" min="0" step="50000" style="width: 150px; display: inline;" id="luongNhaNuoc" name="mucLuongNhaNuoc" required="required">
		<select class="form-control" id="selectLuongNhaNuoc" style="width: 150px; display: inline;">
			<option>---</option>
			<c:forEach items="${mucLuongNhaNuoc}" var="listMuc">
				<option value="${listMuc.muc}" class="formatKetQua">${listMuc.muc}</option>
			</c:forEach>
		</select>
	</div>
	<div class="pull-right">Đơn vị tính: đồng</div>
	<table class="table table-hover text-center">
		<thead>
			<tr class="text-center" style="font-weight: bold; vertical-align: middle;">
				<td>Bậc</td>
				<td>Hệ số</td>
				<td>Lương cơ bản</td>
				<td>Lương tăng thêm</td>
				<td>Lương phụ</td>
				<td>PC Khu vực</td>
				<td>PC Lưu động</td>
				<td>CPKG</td>
				<td>
					BHXH<br>
					KPCĐ<br>
					BHYT<br>
					BHTN
				</td>
				<td>Tổng</td>
				<td>CP 1 ngày công</td>
				<td>CP 1 giờ công</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${listLuong}" var="show" varStatus="status">
			<tr class="text-center" style="vertical-align: middle;">
				<td style="border: none;">
					<c:choose>
						<c:when test="${project.luong.bac == 0 && status.index == 0}">
							<input type="radio" name="chooseBacLuong" value="${show.bac}" checked>
							<input type="hidden" value="${ketQuaCP1Gio[status.index]}" id="CP1GioForBacLuong${show.bac}">
						</c:when>
						<c:when test="${project.luong.bac == show.bac}">
							<input type="radio" name="chooseBacLuong" value="${show.bac}" checked>
							<input type="hidden" value="${ketQuaCP1Gio[status.index]}" id="CP1GioForBacLuong${show.bac}">
						</c:when>
						<c:otherwise>
							<input type="radio" name="chooseBacLuong" value="${show.bac}">
							<input type="hidden" value="${ketQuaCP1Gio[status.index]}" id="CP1GioForBacLuong${show.bac}">
						</c:otherwise>
					</c:choose>	
				${show.bac}
				<input type="hidden" name="luongIdForBangLuong" value="${show.luongid}">
				</td>
				<td style="border: none;">${show.heso}</td>
				<td class="formatKetQua ketQuaLuongCoBan" style="border: none;">${ketQuaLuongCoBan[status.index]}</td>
				<td style="border: none;"><input type="number" class="form-control luongTangThem text-center" name="luongTangThem" min="0" value="${listGiatriluong[status.index].luongtangthem}" step="50000"></td>
				<td class="formatKetQua" style="border: none;">${ketQuaLuongPhu[status.index]}</td>
				<td style="border: none;"><input type="number" class="form-control pcKhuVuc text-center" name="pcKhuVuc" min="0" value="${listGiatriluong[status.index].pckhuvuc}" step="50000"></td>
				<td style="border: none;"><input type="number" class="form-control pcLuuDong text-center" name="pcLuuDong" min="0" value="${listGiatriluong[status.index].pcluudong}"  step="50000"></td>
				<td class="formatKetQua" style="border: none;">${ketQuaCPKG[status.index]}</td>
				<td class="formatKetQua" style="border: none;">${ketQuaBaoHiem[status.index]}</td>
				<td class="formatKetQua" style="border: none;">${ketQuaTong[status.index]}</td>
				<td class="formatKetQua" style="border: none;">${ketQuaCP1Ngay[status.index]}</td>
				<td class="formatKetQua" style="border: none;">${ketQuaCP1Gio[status.index]}</td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
	<button style="submit" class="btn btn-primary" onclick="checkBangLuong();">Cập nhật</button>
</form>
<script src="<c:url value="/resources/js/bangluong.js" />" ></script>
<script src="<c:url value="/resources/js/numeral.min.js" />" ></script>