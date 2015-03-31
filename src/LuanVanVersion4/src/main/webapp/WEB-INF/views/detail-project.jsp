
<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>
<html>
<head>
<title>Chi tiết dự án</title>
<link href="<c:url value="/resources/css/detail-project.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/joint.nojquery.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/diagram.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/jointshapesumlcustom.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/diagramui.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/joint.shapes.ui.custom.css" />" rel="stylesheet">
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container-fluid">
	<div class="panel panel-info content-home">
		<div class="panel-heading">Thông tin chi tiết</div>
		 <a href="${contextPath}/detailUsecase?name=${project.tenproject}">Chi tiet</a>
		<div class="panel-body">
			<div class="col-md-12">
				<div class="toggle-menu-detail-project"><i class="glyphicon glyphicon-chevron-left"></i></div>
				<div class="col-md-2 text-center" id="menu-detail-project">
				    <span style="font-size:20px; color:#666; cursor: default;" class="formatNameProject" title="${project.tenproject}">${project.tenproject}</span>
				    <input type="hidden" id="nameProject" name="nameProject" value="${ project.tenproject }" />
				    <div class="tabs-left">
						<ul class="nav nav-stacked tab-left">
					        <li class="classLi active text-left" id="UC"><a href="#tabUC" data-toggle="tab"><i class="mdi-action-assessment"></i> Sơ đô Use Case</a></li>
					        <li class="classLi text-left" id="uocLuong"><a href="#tabUocLuong" data-toggle="tab"><i class="mdi-editor-attach-money"></i>Ước lượng chi phí</a></li>
					        <li class="classLi text-left" id="giaoDien"><a href="#tabGiaoDien" data-toggle="tab"><i class="mdi-hardware-desktop-windows"></i> Thiết kế giao diện</a></li>
					        <li class="classLi text-left" id="xuatFile"><a href="#tabXuatFile" data-toggle="tab"><i class="glyphicon glyphicon-export"></i> Hỗ trợ xuất dữ liệu</a></li>
					        <li class="classLi text-left" id="thietLap"><a href="#tabThietLap" data-toggle="tab"><i class="mdi-action-perm-data-setting"></i> Thiết lập dự án</a></li>
					        <li class="classLi text-left" id="xoaDuAn"><a href="#tabXoaDuAn" data-toggle="tab"><i class="mdi-content-clear"></i> Xóa dự án</a></li>
					    </ul>
					</div>
				</div>
				<div class="col-md-10 wrapper-tab-content-detail-project">
					 <div class="tab-content">
				        <div class="tab-Li tab-pane fade in active" id="tabUC">
				             <%@ include file="/WEB-INF/views/listdiagram.jsp" %>
				        </div>
				        <div class="tab-Li tab-pane fade" id="tabUocLuong">
				            <%@ include file="/WEB-INF/views/formUocLuong.jsp" %>
				        </div>
				        <div class="tab-Li tab-pane fade" id="tabXuatFile">
					        <div class="form-group" style="margin-top: 100px;">
					        	<div class="form-group" style="display: inline; margin-left: 100px;">
						        	<a href="${contextPath}/downloadDoc/project=${project.tenproject}">
						        		<img alt="Xuất File .docx" style="width: 30px; height: 40px;" src="${contextPath}/resources/img/word.png">
						        		<span>Xuất File Docx</span>
						        	</a>
					        	</div>
					        	<div class="form-group" style="display: inline; margin-left: 100px;">
					        		<a href="${contextPath}/downloadPDF/project=${project.tenproject}">
						        		<img alt="Xuất File .pdf" style="width: 40px; height: 40px;" src="${contextPath}/resources/img/pdf.jpg">
						        		<span>Xuất File PDF</span>
					        		</a>
					        	</div>
					        	<div class="form-group" style="display: inline; margin-left: 100px;">
					        		<a href="${contextPath}/downloadExcel/project=${project.tenproject}">
						        		<img alt="Xuất File .xls" style="width: 40px; height: 40px;" src="${contextPath}/resources/img/excel.png">
						        		<span>Xuất File Excel</span>
					        		</a>
					        	</div>
					        </div>
				        </div>
				        <div class="tab-Li tab-pane fade" id="tabGiaoDien">
				        	<span id="name-ui-show"></span>
							<a href="#modal-rename-ui" data-toggle="modal" id="a-rename-ui" style="display:none;"><i class="mdi-editor-mode-edit"></i></a>
				           <%@ include file="/WEB-INF/views/listui.jsp" %>
				        </div>
				        <div class="tab-Li tab-pane fade" id="tabThietLap">
				        	<%@ include file="/WEB-INF/views/thongtin.jsp" %>             
				        </div>
				        <div class="tab-Li tab-pane fade" id="tabXoaDuAn">
				           <form action="/luanvan/xoaDuAn" method="get" role="form">
				           		<input type="hidden" name="nameProjectForDelete" value="${project.tenproject}">
				           		<button class="btn btn-lg btn-danger text-center" style="margin-left: 300px; margin-top: 70px;" onclick="if (!confirm('Bạn có chắc chắn muốn xóa không?')) return false;">
				           			<i class="glyphicon glyphicon-trash"></i> Xóa dự án</button>
				           		<p class="help-block" style="margin-left: 300px;">Lưu ý: Mọi dữ liệu liên quan sẽ mất hết!</p>
				           </form>
				        </div>
				        
				    </div>
				</div>
				
				</div> 
		</div>
	</div>  
</div>

<!-- Insert footer.jsp -->
<%@ include file="/WEB-INF/views/footer.jsp" %> 

<script src="<c:url value="/resources/js/joint.nojquery.min.js" />" ></script>
<script src="<c:url value="/resources/js/diagram.js" />" ></script>
<script src="<c:url value="/resources/js/xml2json.js" />" ></script>
<script src="<c:url value="/resources/js/jointshapesumlcustom.js"/>"></script>
<script src="<c:url value="/resources/js/detail-project.js" />"></script>
<script src="<c:url value="/resources/js/diagramui.js" />"></script>
<script src="<c:url value="/resources/js/joint.shapes.ui.custom.js" />"></script>


</body>

</html>