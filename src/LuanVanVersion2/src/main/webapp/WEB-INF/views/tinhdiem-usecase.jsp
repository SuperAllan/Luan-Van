<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="get" action="/luanvan/updateTinhDiemActor" role="form">
	<input type="hidden" name="projectNameForLuong" value="${project.tenproject}">
	
	<div class="text-center">
		<h3>BẢNG TÍNH TOÁN ĐIỂM CÁC TRƯỜNG HỢP SỬ DỤNG (USE-CASES)</h3>
	</div>
	<table class="table table-bordered">
		<thead>
			<tr class="text-center" style="font-weight: bold;">
				<td>TT</td>
				<td>Loại</td>
				<td>Số trường hợp sử dụng</td>
				<td>Trọng số</td>
				<td>Hệ số BMT</td>
				<td>Điểm của từng loại Use-case</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${bmts}" var="list" varStatus="status">
			<c:if test="${status.index == 0}">
				<tr>
					<td>1</td>
					<td style="font-weight: bold;">B</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${status.index == 3}">
				<tr>
					<td>2</td>
					<td style="font-weight: bold;">M</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${status.index == 6}">
				<tr>
					<td>3</td>
					<td style="font-weight: bold;">T</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:if>
			<tr>
				<td></td>
				<td>${list.ten}</td>
				<td class="text-center">${soTruongHopSudung[status.index]}</td>
				<td class="text-center">${list.trongso}</td>
				<td class="text-center">${list.hesobmt}</td>
				<td class="text-center">${diemTungUsecase[status.index]}</td>
			</tr>
		</c:forEach>
		
		<tr style="font-weight: bold;">
			<td></td>
			<td>Cộng (1+2+3)</td>
			<td class="text-center">${tongSoTruongHopSudung}</td>
			<td></td>
			<td></td>
			<td class="text-center">${tongDiemTungUsecase}</td>
		</tr>
		</tbody>
	</table>
</form>