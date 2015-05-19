<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
.nav-tabs>li.active>a,.nav-tabs>li.active>a:hover,.nav-tabs>li.active>a:focus{
	color: #fff !important;
	opacity: 1;
}
.nav-tabs>li>a, .nav-tabs>li>a:hover, .nav-tabs>li>a:focus{
	opacity: 0.7;
}
tableChucNang tr td {
	border: none;
}
</style>
<h3 style="text-align: center;">CÁC YÊU CẦU 
CHỨC NĂNG CỦA PHẦN MỀM</h3>

<!-- 
	<select class="form-control" id="selectPhanLoai" style="display: none;">
	<c:forEach var="list" items="${listPhanLoai}">
	<option value="${list.maloai}">${list.tenloai}</option>
	</c:forEach>
	</select>
	
	<select class="form-control" id="selectMucDo"  style="display: none;">
	<c:forEach var="list" items="${listMucDo}">
	<option value="${list.mucdoid}">${list.motamucdo}</option>
	</c:forEach>
	</select>
 -->
	<div class="scrollBar" style="height: 500px; overflow: hidden; position: relative; width: 100%;">
		<table class="table table-hover" id="tableChucNang">
			<thead>
				<tr>
					<th class="text-center">STT</th>
					<th class="text-center">Mô tả yêu cầu</th>
					<th class="text-center">Phân loại</th>
					<th class="text-center">Mức độ</th>
					<th class="text-center">Ghi chú</th>
					<th style="width: 240px;" class="text-center"><a class="text-success" onclick="themNhom();"><i class="glyphicon glyphicon-plus-sign" title="Thêm nhóm"></i> Thêm nhóm</a>
					</th>
				</tr>
			</thead>
			<tbody id="listChucNang">
				
			</tbody>
		</table>
	</div>
	
	
<script src="<c:url value="/resources/js/yeucau-chucnang.js" />" ></script>
