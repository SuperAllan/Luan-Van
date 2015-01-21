<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>
<link href="<c:url value="/resources/css/background.css" />" rel="stylesheet">
<html>
<head>
<title>Thông tin cá nhân</title>

</head>
<body style="padding-top: 70px;">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container">
<div class="row">
<div class="col-md-2 text-center">
	<a href="#" class="thumbnail" style="margin-bottom:0;">
      <img src="<c:url value="/resources/img/hinh.svg" />" style="height: 180px; width: 171px;">
    </a>
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
			  <div class="panel-heading">
			  	<span>Các dự án</span>
			 	 <a type="button" class="btn btn-default pull-right" style="margin-top: -7px;" href="${contextPath}/taoduan">
			 	 	<span class="glyphicon glyphicon-pencil"></span> Tạo dự án</a>
			  </div>
			  <div class="panel-body">
				  <div class="col-md-4">
				  	<div class="panel panel-default">
					  <div class="panel-heading">Đang tiến hành</div>
					  <div class="panel-body">
						  
					    
					  </div>
					</div>
				  </div>
				  
				  <div class="col-md-4">
				  	<div class="panel panel-default">
					  <div class="panel-heading">Đã hoàn thành</div>
					  <div class="panel-body">
						  sađấ
					    
					  </div>
					</div>
				  </div>
				  
				  <div class="col-md-4">
				  	<div class="panel panel-default">
					  <div class="panel-heading">Tạm dừng</div>
					  <div class="panel-body">
						  sađấ
					    
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

</body>
</html>