<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value="/resources/css/thongtin.css" />" rel="stylesheet">

<div class="tab-content">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<span style="font-size: 20px"><i class="mdi-action-perm-data-setting"></i> Thiết lập dự án</span>
		</div>
		<div class="panel-body">
		<form role="form" action="${contextPath}/updateProject" method="GET">
			<div class="form-group">
				<label>Tên dự án:</label>
				<input type="text" class="form-control tenProjectForThiepLap" onkeyup="checkSpecialCharacter(this.value)" name="tenProject" value="${project.tenproject}" maxlength="200" required="required">
				<small id="showCheckSpecialCharacter" style="color: #a94442;">${errorName}</small>
				<div class="text-warning">${errorNameThietLap}</div>
				<input type="hidden" class="form-control" name="tenProjectOld" value="${project.tenproject}">
			</div>
			
			<div class="form-group">
				<label>Mô tả dự án:</label>
				<textarea class="form-control motaProjectForThietLap" name="motaProject" rows="4" style="resize: vertical;">${project.motaproject}</textarea>
			</div>
       			<c:choose>
	       			<c:when test="${project.trangthai == 0}">
	       				<div class="col-md-4 text-center">
		        			<div class="radio radio-primary">
		         				<label><input type="radio" value="0" name="radioTrangThai" checked> Đang tiến hành</label>
		         			</div>
		         		</div>
		         		<div class="col-md-4 text-center">
		         			<div class="radio radio-primary">
		         				<label><input type="radio" value="1" name="radioTrangThai" > Đã hoàn thành</label>
		         			</div>
		         		</div>
		         		<div class="col-md-4 text-center">
		         			<div class="radio radio-primary">
		         				<label><input type="radio" value="2" name="radioTrangThai" > Đang trì hoãn</label>
		         			</div>
	         			</div>
	        		</c:when>
	          		<c:when test="${project.trangthai == 1}">
	          			<div class="col-md-4 text-center">
		           			<div class="radio radio-primary">
			           			<label><input type="radio" value="0" name="radioTrangThai" > Đang tiến hành</label>
			           		</div>
			           	</div>
			           	<div class="col-md-4 text-center">
		           			<div class="radio radio-primary">
		           				<label><input type="radio" value="1" name="radioTrangThai" checked> Đã hoàn thành</label>
		           			</div>
		           		</div>
		           		<div class="col-md-4 text-center">
			           		<div class="radio radio-primary">
			           			<label><input type="radio" value="2" name="radioTrangThai" > Đang trì hoãn</label>
			           		</div>
			           	</div>
	          		</c:when>
	          		<c:otherwise>
	          			<div class="col-md-4 text-center">
		           			<label><input type="radio" value="0" name="radioTrangThai" > Đang tiến hành</label>
		           		</div>
		           		<div class="col-md-4 text-center">
		           			<label><input type="radio" value="1" name="radioTrangThai" > Đã hoàn thành</label>
		           		</div>
		           		<div class="col-md-4 text-center">
		           			<label><input type="radio" value="2" name="radioTrangThai" checked> Đang trì hoãn</label>
		           		</div>
	          		</c:otherwise>
          		</c:choose>
				<button type="submit" id="submitCreateProject" class="btn btn-primary">Cập nhật</button>
			</form>
		</div>
	</div>
</div>