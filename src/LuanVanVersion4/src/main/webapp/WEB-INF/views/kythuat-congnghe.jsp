<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="text-center"><h3>BẢNG TÍNH TOÁN HỆ SỐ PHỨC TẠP KỸ THUẬT-CÔNG NGHỆ</h3></div>
<form action="${contextPath}/updateHeSoKyThuat" method="GET" role="form">
	<input type="hidden" name="projectNameForKyThuat" value="${project.tenproject}">
	<table class="table table-hover" >
		<thead>
			<tr class="text-center" style="font-weight: bold;">
				<td>TT</td>
				<td>Các hệ số</td>
				<td>Trọng số</td>
				<td>Giá trị xếp hạng</td>
				<td>Kết quả</td>
				<td>Ghi chú</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="font-weight: bold;" class="text-center">I</td>
				<td>Hệ số KT-CN (TFW)</td>
				<td></td>
				<td></td>
				<c:choose>
					<c:when test="${tongKetQuaFromKyThuat != 0 }">
						<td class="text-center" style="font-weight: bold;">${tongKetQuaFromKyThuat}</td>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
				<td></td>
			</tr>
		<c:forEach items="${listHeSoKyThuat}" var="list" varStatus="status">
			<tr class="text-center">
				<td style="border: none;">
					${list.hesokythuatid}
					<input type="hidden" name="IDHeSo" value="${list.hesokythuatid}">
				</td>
				<td class="text-left" style="border: none;">${list.tenheso}</td>
				<td style="border: none;">${list.trongso}</td>
				<td style="border: none;"><input type="number" class="form-control giaTriXepHangKT" name="giaTriXepHang" value="${listXepHangKyThuat[status.index].giatrixephang}" min="0" max="5" style="text-align: center;" required="required"></td>
				<td class="ketQuaFromKyThuat" style="border: none;">${ketQuaFromKyThuat[status.index]}</td>
				<td style="border: none;"><textarea name="ghiChuKyThuat" class="form-control ghiChuKyThuat" rows="1" placeholder="Ghi chú">${listXepHangKyThuat[status.index].ghichu}</textarea></td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
	<button type="submit" class="btn btn-primary text-center" id="submitFormKyThuat">Cập nhật</button>
</form>
