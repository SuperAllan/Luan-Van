<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="text-center"><h2>BẢNG TÍNH TOÁN HỆ SỐ PHỨC TẠP VỀ MÔI TRƯỜNG</h2></div>
<form action="/luanvan/updateHeSoMoiTruong" method="GET" role="form">
	<input type="hidden" name="projectNameForMoiTruong" value="${project.tenproject}">
	<table class="table table-bordered">
		<thead>
			<tr class="text-center" style="font-weight: bold;">
				<td>TT</td>
				<td>Các hệ số tác động môi trường</td>
				<td>Trọng số</td>
				<td>Giá trị xếp hạng</td>
				<td>Kết quả</td>
				<td>Độ ổn định</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="text-center" style="font-weight: bold;">I</td>
				<td style="font-weight: bold;">Hệ số tác động môi trường và nhóm làm việc (EFW)</td>
				<td></td>
				<td></td>
					<c:choose>
						<c:when test="${tongKetQuaFromMoiTruong != 0 }">
							<td class="text-center" style="font-weight: bold;">${tongKetQuaFromMoiTruong}</td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
				<td></td>
			</tr>
		<c:forEach items="${listHeSoMoiTruong}" var="list" varStatus="status">
			<tr class="text-center">
				<td>
					${list.hesomoitruongid}
					<input type="hidden" name="IDMoiTruong" value="${list.hesomoitruongid}">
				</td>
				<td class="text-left">${list.motahesomt}</td>
				<td>${list.trongso}</td>
				<td><input type="number" class="form-control" name="giaTriXepHangMT" value="${listXepHangMoiTruong[status.index].giatrixephang}" min="0" max="5" style="width: 50px; text-align: center;" required="required"></td>
				<td class="ketQuaFromMoiTruong">${ketQuaFromMoiTruong[status.index]}</td>
				<td><input type="number" class="form-control giaTriOnDinh" name="giaTriOnDinh" value="${listXepHangMoiTruong[status.index].ondinh}" min="0" step="0.1" style="width: 70px; text-align: center;" required="required"></td>
			</tr>
		</c:forEach>
			<tr>
				<td class="text-center" style="font-weight: bold;">II</td>
				<td style="font-weight: bold;">Độ ổn định về kinh nghiệm (ES)</td>
				<td></td>
				<td></td>
				<td></td>
				<c:choose>
					<c:when test="${not empty listXepHangMoiTruong}">
						<td class="text-center" style="font-weight: bold;">${tongOnDinh}</td>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td class="text-center" style="font-weight: bold;">III</td>
				<td style="font-weight: bold;">Nội suy thời gian lao động (P)</td>
				<td></td>
				<td></td>
				<td></td>
				<c:choose>
					<c:when test="${not empty listXepHangMoiTruong}">
						<td class="text-center" style="font-weight: bold;">${tinhNoiSuy}</td>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
			</tr>	
		</tbody>
	</table>
	<button type="submit" class="btn btn-primary text-center">Xác nhận</button>
	<div>${updateMoiTruongSuccess }</div>
</form>
<script src="<c:url value="/resources/js/moitruong.js" />" ></script>