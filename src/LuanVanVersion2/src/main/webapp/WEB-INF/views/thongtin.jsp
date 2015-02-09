<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value="/resources/css/thongtin.css" />" rel="stylesheet">

<ul class="nav nav-tabs">
  <li role="presentation" class="active"><a href="#tabSuaDoi" data-toggle="tab">Sửa đổi dự án</a></li>
  <li role="presentation"><a href="#tabTrangThai" data-toggle="tab">Trạng thái dự án</a></li>
</ul>

<div class="tab-content">
        <div class="tab-pane fade in active" id="tabSuaDoi">
			<form role="form" action="/luanvan/updateProject" method="GET">
				<div class="form-group">
					<span>Tên dự án:</span>
					<input type="text" class="form-control" name="tenProject" value="${project.tenproject}" maxlength="200" required="required">
					<div class="text-warning">${errorName}</div>
					<input type="hidden" class="form-control" name="tenProjectOld" value="${project.tenproject}">
				</div>
				
				<div class="form-group">
					<span>Mô tả dự án:</span>
					<input type="text" class="form-control" name="motaProject" value="${project.motaproject}">
				</div>
				
				<button type="submit" class="btn btn-primary">Cập nhật</button>
				<div class="text-success">${successTenProject}</div>
				<div class="text-success">${successMotaProject}</div>
			</form>
        </div>
        
        <div class="tab-pane fade" id="tabTrangThai">
           <form role="form" action="/luanvan/updateTrangThai" method="POST">
           <input type="hidden" name="tenProjectForTrangThai" value="${project.tenproject}" >
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
        	<button type="submit" class="btn btn-primary" style="margin-top: 10px;">Cập nhật</button>
        	<div>${updateTrangThaiSuccess}</div>
           </form>
        </div>
        
</div>