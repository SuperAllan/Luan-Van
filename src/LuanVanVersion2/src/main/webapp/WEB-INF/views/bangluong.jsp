<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="get" action="/luanvan/updateBangLuong" role="form">
	<input type="hidden" name="projectNameForLuong" value="${project.tenproject}">
	<div class="text-center" style="margin-top: 10px;  ">
		<span>Chọn mức lương cơ bản:</span> 
		<input type="text" class="form-control" value="${project.luongcoban}" style="width: 100px; display: inline;" id="luongNhaNuoc" name="mucLuongNhaNuoc" required="required">
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
				<td>${show.luongid}</td>
				<td>${show.bac}</td>
				<td>${show.heso}</td>
				<td class="formatKetQua">${ketQuaLuongCoBan[status.index]}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
	<button style="submit" class="btn btn-primary">Cập nhật</button>
</form>
<script src="<c:url value="/resources/js/bangluong.js" />" ></script>
<script src="<c:url value="/resources/js/numeral.min.js" />" ></script>