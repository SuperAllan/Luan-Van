<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value="/resources/css/thongtin.css" />" rel="stylesheet">



<div class="tab-content">
			<form role="form" action="/luanvan/updateProject" method="GET">
				<div class="form-group">
					<span>Tên dự án:</span>
					<input type="text" class="form-control" name="tenProject" value="${project.tenproject}" maxlength="200" required="required">
					<div class="text-warning">${errorNameThietLap}</div>
					<input type="hidden" class="form-control" name="tenProjectOld" value="${project.tenproject}">
				</div>
				
				<div class="form-group">
					<span>Mô tả dự án:</span>
					<textarea class="form-control" name="motaProject" rows="4">${project.motaproject}</textarea>
				</div>
				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
           	<div id="radioList">
           		<ul>
           			<c:choose>
           			<c:when test="${project.trangthai == 0}">
	           			<li>
		           			<label><input type="radio" value="0" name="radioTrangThai" checked> Đang tiến hành</label>
		           		</li>
		           		<li>
		           			<label><input type="radio" value="1" name="radioTrangThai" > Đã hoàn thành</label>
		           		</li>
		           		<li>
		           			<label><input type="radio" value="2" name="radioTrangThai" > Tạm dừng</label>
		           		</li>
	           		</c:when>
	           		<c:when test="${project.trangthai == 1}">
		           		<li>
			           			<label><input type="radio" value="0" name="radioTrangThai" > Đang tiến hành</label>
			           		</li>
			           		<li>
			           			<label><input type="radio" value="1" name="radioTrangThai" checked> Đã hoàn thành</label>
			           		</li>
			           		<li>
			           			<label><input type="radio" value="2" name="radioTrangThai" > Tạm dừng</label>
		           		</li>
	           		</c:when>
	           		<c:otherwise>
		           		<li>
			           			<label><input type="radio" value="0" name="radioTrangThai" > Đang tiến hành</label>
			           		</li>
			           		<li>
			           			<label><input type="radio" value="1" name="radioTrangThai" > Đã hoàn thành</label>
			           		</li>
			           		<li>
			           			<label><input type="radio" value="2" name="radioTrangThai" checked> Tạm dừng</label>
		           		</li>
	           		</c:otherwise>
	           		</c:choose>
           		</ul>
        	</div>
        	
				<button type="submit" class="btn btn-primary">Cập nhật</button>
			</form>
</div>