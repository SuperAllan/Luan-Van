<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>

<html>
<head>
<title>Tạo dự án</title>


</head>

<body style="padding-top: 90px; font-family: 'Source Sans Pro', sans-serif;" >
<div class="container">
	<div class="edit-container">
	<form action="/luanvan/createProject" method="GET" role="form" id="form-tao-du-an">
		<div class="form-group">
			<span class="index">Tên dự án</span>
			<input type="text" class="form-control input-lg" placeholder="Tên dự án" name="projectName"  maxlength="200"
			data-bv-notempty="true"
          	data-bv-notempty-message="Tên dự án phải khác rỗng."
          	data-bv-stringlength="true">
		</div>
		<c:if test="${empty errorName}">
			<p>Hãy tạo tên dự án một cách ngắn gọn và dễ nhớ</p>
		</c:if>
			<p class="text-danger">${errorName}<p>
		
		<div class="form-group">
			<span class="index">Mô tả dự án (Tùy chọn)</span>
			<input type="text" class="form-control input-lg" placeholder="Mô tả dự án" name="description">
			<p>Hãy giới thiệu hoặc mô tả ngắn gọn mục tiêu 	mà dự án bạn hướng tới. Bạn có thể để trống mục này.</p>
		</div>
		
		<button type="submit" class="btn btn-primary btn-lg	" style="width: 200px;">Đồng ý</button>
		<c:if test="${not empty success}">
		  	<div class="alert alert-success" style="margin-top: 10px;">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${success}
			</div>
	  	</c:if>
	</form>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>  
</div><!-- End container -->
</body>
<!-- inlcude css vs js -->
<script src="<c:url value="/resources/js/create-project.js" />" ></script>
<link href="<c:url value="/resources/css/create-project.css" />" rel="stylesheet">
	
</html>