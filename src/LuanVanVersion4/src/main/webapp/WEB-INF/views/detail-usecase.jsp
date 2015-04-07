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
<script type="text/javascript">
$(document).ready(function(){
	$(".formatNameDiagram").each(function(){
		if($(this).text().length > 40){
		var formatTitle = $.trim($(this).text()).substring(0,40).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
	
	$(".formatNameProject").each(function(){
		if($(this).text().length > 40){
		var formatTitle = $.trim($(this).text()).substring(0,40).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
});
</script>
<title>Chi tiết Use-case</title>
</head>
<body style="height: 100%; width: 100%;" onload="searchUsecase('', ${project.projectid})">
<input type="hidden" id="usecaseFromDiagram" value="${usecaseid}"/>
<div class="container-fluid">
	<div class="panel panel-info content-home">
			<div class="panel-heading" >
				<div id="header-background" style="font-size: 16px;">
					<a href="${contextPath}/">Ước lượng và thu thập yêu cầu </a>/<a href="${contextPath}/background"> Các dự án </a>/
					<a href="${contextPath}/detailProject?name=${project.tenproject}"> Dự án: <strong class="formatNameProject">${project.tenproject}</strong></a>
					<a  href="${contextPath}/diagram/viewdiagram?nameProject=${project.tenproject}&nameDiagram=${nameDiagram}"> / Sơ đồ: <strong class="formatNameDiagram">${nameDiagram}</strong></a><span>/ Đặt câu hỏi và upload tập tin</span>
				</div>
			</div>
			<div class="panel-body">
				<div class="col-md-3">
				<div style="margin-left: -15px; margin-right: -15px; padding-top: 20px;">
					<div class="panel panel-default" style="margin-bottom: 0px;">
					    <div class="panel-heading" style="background: #ececec; border-radius: 0px;  box-shadow: none;">
					    <h3 class="panel-title text-center">Danh sách các Use-case</h3>
					    </div>
					</div>
					
					<div class="panel-side-search" style="background: #BDBDC4">
						<div  style="padding: 6px 12px; ">
							<input style="border-radius: 4px; height: 34px;" type="search" id="input" class="form-control text-center"  title="Search" onkeyup="searchUsecase(this.value, ${project.projectid})" placeholder="Nhập nội dung tìm kiếm">
						</div> 
					</div>
					<div id="danhSachUsecase" style="overflow: hidden; height: 500px; width: 100%;">
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
	</div>
</div>
<!-- Insert footer.jsp -->
<%@ include file="/WEB-INF/views/footer.jsp" %>
<script src="<c:url value="/resources/js/detail-usecase.js" />"></script>
</body>

</html>