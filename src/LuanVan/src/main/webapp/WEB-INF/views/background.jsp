<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>

<html>
<head>
<title>Thông tin cá nhân</title>
<link href="<c:url value="/resources/css/background.css" />" rel="stylesheet">
</head>
<body style="padding-top: 70px;">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container">
<div class="row">
<div class="col-md-2 text-center">
	<div class="thumbnail" style="margin-bottom:0; height: 180px; width: 171px; padding: 0px;">
      <form:form method="POST" action="/luanvan/uploadFile" role="form" modelAttribute="formUpload" enctype="multipart/form-data" id="formUploadFile">
     
      	<div onclick="showDialog()" style="height: 180px; width: 171px; padding: 0px; margin: 0px;">
      		<c:if test="${not empty image }">
      			<img src="<c:url value="${image}" />" style="height: 180px; width: 171px;">
      		</c:if>
      		<c:if test="${empty image }">
      			<img src="<c:url value="/resources/img/hinh.svg" />" style="height: 180px; width: 171px;">
      		</c:if>
      	</div>
      	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      	 <input type="file" name="file" style="opacity:0;" id="uploadImageId"/>
      </form:form>
    </div>
    
    <span style="font-size:20px; color:#666;">${username}</span>
    <div class="tabs-left">
		<ul class="nav nav-tabs tab-left">
	        <li class="active" id="litab1"><a href="#tab1" data-toggle="tab">Các dự án</a></li>
	        <li id="litab2"><a href="#tab2" data-toggle="tab">Đổi mật khẩu</a></li>
	        <li id="litab3"><a href="#tab3" data-toggle="tab">Tab 3</a></li>
	    </ul>
	</div>
</div>
<div class="col-md-10">
	 <div class="tab-content">
        <div class="tab-pane fade in active" id="tab1">
            <div class="panel panel-default">
			  <div class="panel-heading" >
			  		<span style="font-size: 20px">Các dự án</span>
			 	 <a type="button" class="btn btn-default pull-right"  href="${contextPath}/formCreateProject">
			 	 	<span class="glyphicon glyphicon-pencil"></span> Tạo dự án</a>
			  </div>
			  <div class="panel-body">
				  <div class="col-md-4">
				  	<div class="panel panel-default" style="margin-top: 5px">
					  <div class="panel-heading">Đang tiến hành <span class="pull-right badge">${fn:length(listMaking)}</span></div>
					  <div class="panel-body" style="padding: 0px;">
					  	<table class="table">
							<c:forEach var="list" items="${listMaking}">
								<tr>
									<td>
										<a href="" class="projectName">${list.name}</a><br>
										<span class="description">${list.description}</span>
									</td>
								</tr>
							</c:forEach>
  						</table>
					  </div>
					</div>
				  </div>
				  
				  <div class="col-md-4">
				  	<div class="panel panel-default" style="margin-top: 5px">
					  <div class="panel-heading">Đã hoàn thành <span class="pull-right badge">${fn:length(listFinish)}</span></div>
					  <div class="panel-body" style="padding: 0px;">
						  <table class="table">
								<c:forEach var="list" items="${listFinish}">
									<tr>
										<td>
											<a href="" class="projectName">${list.name}</a><br>
											<span class="description">${list.description}</span>
										</td>
									</tr>
								</c:forEach>
	  					  </table>
					  </div>
					</div>
				  </div>
				  
				  <div class="col-md-4">
				  	<div class="panel panel-default" style="margin-top: 5px">
					  <div class="panel-heading">Tạm dừng <span class="pull-right badge">${fn:length(listStopping)}</span></div>
					  <div class="panel-body" style="padding: 0px;">
						<table class="table">
							<c:forEach var="list" items="${listStopping}">
								<tr>
									<td>
										<a href="" class="projectName">${list.name}</a><br>
										<span class="description">${list.description}</span>
									</td>
								</tr>
							</c:forEach>
	  					</table>
					  </div>
					</div>
				  </div>
			    
			  </div>
			</div>
        </div>
        <div class="tab-pane fade" id="tab2">
            <%@ include file="/WEB-INF/views/changepassword.jsp" %>               
        </div>
        <div class="tab-pane fade" id="tab3">
            Tab 3 content
        </div>
    </div>
</div>

</div>  
<%@ include file="/WEB-INF/views/footer.jsp" %>  
</div>
<script src="<c:url value="/resources/js/background.js" />" ></script>
</body>
</html>