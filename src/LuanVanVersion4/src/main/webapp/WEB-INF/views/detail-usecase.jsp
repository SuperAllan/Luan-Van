<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/menu.jsp"%>

<html>
<head>
<style>
	.list-group .list-group-item:after {
  display: table;
  conent: "";
  clear: both;
}
.list-group .list-group-item.active {
  background: #40cc75;
  border-color: #2eab5d;
}

</style>
<title>Chi tiết Use-case</title>
</head>
<body style="height: 100%; width: 100%;" onload="searchUsecase('', ${project.projectid})">
<input type="hidden" id="usecaseFromDiagram" value="${usecaseid}"/>
<div class="container-fluid" style="background: #fff;">
	<div class="col-md-3">
		<div style="margin-left: -15px; margin-right: -15px; padding-top: 20px;">
			<div class="panel panel-default" style="margin-bottom: 0px;">
			    <div class="panel-heading">
			    <h3 class="panel-title text-center">Danh sách các Use-case</h3>
			    </div>
			</div>
			
			<div class="panel-side-search" style="background: #BDBDC4">
				<div  style="padding: 6px 12px; ">
					<input style="border-radius: 4px; height: 34px;" type="search" id="input" class="form-control text-center"  title="Search" onkeyup="searchUsecase(this.value, ${project.projectid})" placeholder="Nhập nội dung tìm kiếm">
				</div> 
			</div>
			<div id="danhSachUsecase" style="posibtion: relative; overflow: hidden; height: 500px; width: 100%;">
					<!-- Danh sach cac use case  -->
					
			</div>
		</div>
	</div>
	<div class="col-md-9">
		<div id="resultDetail" style="background: write !important; padding-top: 20px;">
					<h1>Chọn một Use-case để xem chi tiết</h1>		
		</div>
	</div>
</div>
<!-- Insert footer.jsp -->
<%@ include file="/WEB-INF/views/footer.jsp" %>
<script src="<c:url value="/resources/js/detail-usecase.js" />"></script>
</body>

</html>