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
				<option>${listMuc.muc}</option>
			</c:forEach>
		</select>
	</div>
	<div class="pull-right">Đơn vị tính: đồng</div>
	<table class="table table-bordered">
		<thead>
			<tr class="text-center" style="font-weight: bold;">
				<td>STT</td>
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
			<tr class="text-center">
				<td>
					<c:choose>
					<c:when test="${project.bacluong == 0 && status.index == 0}">
						<input type="radio" name="chooseLuong" value="${ketQuaCP1Gio[status.index]}" checked>
					</c:when>
					<c:when test="${project.bacluong == ketQuaCP1Gio[status.index]}">
						<input type="radio" name="chooseLuong" value="${ketQuaCP1Gio[status.index]}" checked>
					</c:when>
					<c:otherwise>
						<input type="radio" name="chooseLuong" value="${ketQuaCP1Gio[status.index]}">
					</c:otherwise>
					</c:choose>		
				${show.luongid}
				<input type="hidden" name="luongIdForBangLuong" value="${show.luongid}">
				</td>
				<td>${show.bac}</td>
				<td>${show.heso}</td>
				<td class="formatKetQua ketQuaLuongCoBan">${ketQuaLuongCoBan[status.index]}</td>
				<td><input type="number" class="form-control luongTangThem" name="luongTangThem" min="0" value="${listGiatriluong[status.index].luongtangthem}" style="width: 100px;" step="50000"></td>
				<td class="formatKetQua">${ketQuaLuongPhu[status.index]}</td>
				<td><input type="number" class="form-control pcKhuVuc" name="pcKhuVuc" min="0" value="${listGiatriluong[status.index].pckhuvuc}" style="width: 100px;" step="50000"></td>
				<td><input type="number" class="form-control pcLuuDong" name="pcLuuDong" min="0" value="${listGiatriluong[status.index].pcluudong}" style="width: 100px;"  step="50000"></td>
				<td class="formatKetQua">${ketQuaCPKG[status.index]}</td>
				<td class="formatKetQua">${ketQuaBaoHiem[status.index]}</td>
				<td class="formatKetQua">${ketQuaTong[status.index]}</td>
				<td class="formatKetQua">${ketQuaCP1Ngay[status.index]}</td>
				<td class="formatKetQua">${ketQuaCP1Gio[status.index]}</td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
	<button style="submit" class="btn btn-primary" onclick="checkBangLuong();">Cập nhật</button>
</form>
<script src="<c:url value="/resources/js/bangluong.js" />" ></script>
<script src="<c:url value="/resources/js/numeral.min.js" />" ></script>