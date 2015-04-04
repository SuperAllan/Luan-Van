<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Thông tin cá nhân</title>
	<link href="<c:url value="/resources/css/background.css" />" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="panel panel-info content-home">
			<div class="panel-heading" >
				<div id="header-background">
					<a href="${contextPath}/">Ước lượng và thu thập yêu cầu </a>/<span> Các dự án </span>
				</div>
			</div>
			<div class="panel-body">
				<div class="col-md-2 text-center">
		     		<form method="POST" action="/luanvan/uploadImage" role="form"  id="formUploadFile">
		      			<div onclick="showDialog()" id="anhDaiDien">
		      				<c:if test="${not empty user.image }">
		      					<img src="<c:url value="${user.image}" />" style="height: 150px; width: 150px; cursor: pointer;">
		      				</c:if>
		      				<c:if test="${empty user.image }">
		      					<img src="<c:url value="/resources/img/hinh.svg" />" style="height: 150px; width: 150px; cursor: pointer;">
		      				</c:if>
		      			<div class="hoverImg" style="cursor: pointer;">
		      				<i class="glyphicon glyphicon-edit"></i> Ảnh đại diện</div>
		      			</div>
				      	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				      	 <input type="hidden" name="file" id="fileString"/>
				      	 <input type="file"  style="display:none;" id="uploadImageId"/>
		      		</form>
			    
			    <span style="font-size:20px; color:#666; cursor: default;">${user.username}</span>
			    <div class="tabs-left">
					<ul class="nav nav-stacked tab-left">
				        <li class="classLi text-left active" id="duAn"><a href="#tabDuAn" data-toggle="tab"><i class="glyphicon glyphicon-list-alt"></i> Các dự án</a></li>
				        <li class="classLi text-left" id="matKhau"><a href="#tabMatKhau" data-toggle="tab"><i class="glyphicon glyphicon-pencil"></i> Đổi mật khẩu</a></li>
				        <li class="classLi text-left" id="thongTin"><a href="#tabThongTin" data-toggle="tab"><i class="glyphicon glyphicon-info-sign"></i> Thông tin cá nhân</a></li>
				    </ul>
				</div>
			</div>
			<div class="col-md-10">
				 <div class="tab-content">
			        <div class="tab-pane fade in active" id="tabDuAn">
			            <div class="panel panel-primary">
						  <div class="panel-heading" >
						 	 <a  style="color:white;" data-toggle="modal" data-target="#myModal">
						 	 	<i class="glyphicon glyphicon-plus-sign"></i> Tạo dự án</a>
						  </div>
						  <div class="panel-body">
							  <div class="col-md-4">
							  	<div class="panel panel-info" style="margin-top: 5px">
								  <div class="panel-heading" style="font-size: 16px;">Đang tiến hành <span class="pull-right badge">${fn:length(listMaking)}</span></div>
								  <div class="panel-body" style="padding: 0px; width: 100%;">
								  	<c:forEach var="list" items="${listMaking}">
								  		<div class="list-project">
											<a href="${contextPath}/detailProject?name=${list.tenproject}" >
												<span class="projectName" title="${list.tenproject}">${list.tenproject}</span>
											</a>
											<span class=" date-create pull-right"><fmt:formatDate value="${list.ngaytao}" pattern="dd-MM-yyyy" /></span>
											<div class="description">${list.motaproject}</div>
										</div>
									</c:forEach>
								  </div>
								</div>
							  </div>
							  
							  <div class="col-md-4">
							  	<div class="panel panel-success" style="margin-top: 5px">
								  <div class="panel-heading" style="font-size: 16px;">Đã hoàn thành <span class="pull-right badge">${fn:length(listFinish)}</span></div>
								  <div class="panel-body" style="padding: 0px;">
				  					  <c:forEach var="list" items="${listFinish}">
								  		<div class="list-project">
											<a href="${contextPath}/detailProject?name=${list.tenproject}" >
												<span class="projectName" title="${list.tenproject}">${list.tenproject}</span>
											</a>
											<span class=" date-create pull-right"><fmt:formatDate value="${list.ngaytao}" pattern="dd-MM-yyyy" /></span>
											<div class="description">${list.motaproject}</div>
										</div>
									</c:forEach>
								  </div>
								</div>
							  </div>
							  
							  <div class="col-md-4">
							  	<div class="panel panel-danger" style="margin-top: 5px">
								  <div class="panel-heading" style="font-size: 16px;">Tạm dừng <span class="pull-right badge">${fn:length(listStopping)}</span></div>
								  <div class="panel-body" style="padding: 0px;">
				  					<c:forEach var="list" items="${listStopping}">
								  		<div class="list-project">
											<a href="${contextPath}/detailProject?name=${list.tenproject}" >
												<span class="projectName" title="${list.tenproject}">${list.tenproject}</span>
											</a>
											<span class=" date-create pull-right"><fmt:formatDate value="${list.ngaytao}" pattern="dd-MM-yyyy" /></span>
											<div class="description">${list.motaproject}</div>
										</div>
									</c:forEach>
								  </div>
								</div>
							  </div>
						  </div>
						</div>
			        </div>
			        
			        <div class="tab-pane fade" id="tabMatKhau">
			        	<div class="panel panel-primary">
			        		<div class="panel-heading" style="font-size: 18px;">Đổi mật khẩu</div>
			       			<div class="panel-body">
			      				<%@ include file="/WEB-INF/views/changepassword.jsp" %> 
			      			</div>
			         	</div>             
			        </div>
			        
			        <div class="tab-pane fade" id="tabThongTin">
			            <div class="panel panel-primary">
						  <div class="panel-heading" style="font-size: 18px">Thông tin cá nhân</div>
						  <div class="panel-body">
							  <form action="/luanvan/updateInformation" role="form" method="POST">
							  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								  	<div class="form-group">
								  		<label>Họ và tên:</label>
								  		<input type="text" name="name" class="form-control" value="${user.fullname}"  placeholder="Nhập họ và tên vào đây">
								  	</div>
								  	<div class="form-group">
								  		<label>Email:</label>
								  		<input type="email" name="updateEmail" class="form-control" value="${user.email}">
								  	</div>
								  	<div class="form-group">
								  		<label>Số điện thoại:</label>
								  		<input type="text" name="phone" value="${user.phone}"  class="form-control" placeholder="Nhập số điện thoại vào đây" maxlength="11">
								  	</div>
								  	<div class="form-group">
								  		<label>Địa chỉ:</label>
								  		<textarea name="address"  class="form-control" rows="3" >${user.address}</textarea>
								  	</div>
								  	<button type="submit" class="btn btn-default btn-primary">Cập nhật</button>
							  </form>
						  </div>
						</div><!-- End panel -->
			        </div>
			    </div>
			    
				 
			</div>
	</div>
</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<form action="/luanvan/createProject" method="GET" role="form" id="form-tao-du-an">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Tạo dự án</h4>
      </div>
      <div class="modal-body">
			<div class="form-group">
				<label class="index">Tên dự án</label>
				<input type="text" class="form-control input-lg" placeholder="Tên dự án" name="projectName"  maxlength="200" autofocus="autofocus" required="required">
			</div>
			<c:if test="${empty errorName}">
				<p>Hãy tạo tên dự án một cách ngắn gọn và dễ nhớ</p>
			</c:if>
				<span class="text-danger">${errorName}</span>
			<div class="form-group">
				<label class="index">Mô tả dự án (Tùy chọn)</label>
				<textarea class="form-control" placeholder="Mô tả dự án" name="description" rows="3"></textarea>
				<p>Hãy giới thiệu hoặc mô tả ngắn gọn mục tiêu 	mà dự án bạn hướng tới. Bạn có thể để trống mục này.</p>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
        <button type="submit" class="btn btn-primary">Đồng ý</button>
      </div>
    </div>
  </div>
  </form>
</div>

<!-- Insert footer.jsp -->
<%@ include file="/WEB-INF/views/footer.jsp" %>

<script src="<c:url value="/resources/js/background.js" />" ></script>
<script src="<c:url value="/resources/js/changepassword.js" />" ></script>
<script src="<c:url value="/resources/js/updateInfor.js" />" ></script>
</body>
</html>