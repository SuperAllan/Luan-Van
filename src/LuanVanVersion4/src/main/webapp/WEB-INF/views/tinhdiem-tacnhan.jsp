<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="get" action="/luanvan/updateTinhDiemActor" role="form">
	<input type="hidden" name="projectNameForLuong" value="${project.tenproject}">
	
	<div class="text-center">
		<h3>BẢNG TÍNH TOÁN ĐIỂM CÁC TÁC NHÂN (ACTORS) TƯƠNG TÁC <br>
		TRAO ĐỔI THÔNG TIN VỚI PHẦN MỀM</h3>
	</div>
	<table class="table table-bordered">
		<thead>
			<tr class="text-center" style="font-weight: bold;">
				<td>TT</td>
				<td>Loại Actor</td>
				<td>Mô tả</td>
				<td>Số tác nhân</td>
				<td>Trọng số</td>
				<td>Điểm của từng loại tác nhân</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${loaiactors}" var="list" varStatus="status">
			<tr>
				<td class="text-center">${list.loaiactorid}</td>
				<td>${list.loai}</td>
				<td>${list.motaloaiactor}</td>
				<td class="text-center">${countActor[status.index]}</td>
				<td class="text-center">${list.trongso}</td>
				<td class="text-center">${diemActor[status.index]}</td>
			</tr>
		</c:forEach>
		<tr style="font-weight: bold;">
			<td></td>
			<td>Cộng (1+2+3)</td>
			<td class="text-center">TAW</td>
			<td></td>
			<td></td>
			<td class="text-center">${tongDiemActor}</td>
		</tr>
		</tbody>
	</table>
</form>