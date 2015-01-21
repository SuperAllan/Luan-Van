<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>
<html>
<head>
<title>Tạo dự án</title>
</head>
<style>
.edit-container{
	width: 700px;
	margin: 0 auto;

}
p{
	margin-top: 10px;
	margin-bottom: 15px;
}
.index{
font-size: 15px;
font-weight: bold;
}
</style>
<body style="padding-top: 90px;" >
<div class="container">
	<div class="edit-container">
	<form action="/luanvan/taoduan" method="GET" role="form" id="form-tao-du-an"
		data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
		data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
		data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
		<div class="form-group">
			<span class="index">Tên dự án</span>
			<input type="text" class="form-control input-lg" placeholder="Tên dự án" style="width: 310px;">
			<p>Hãy tạo tên dự án một cách ngắn gọn và dễ nhớ</p>
		</div>
		
		<div class="form-group">
			<span class="index">Mô tả dự án (Tùy chọn)</span>
			<input type="text" class="form-control input-lg" placeholder="Mô tả dự án" >
			<p>Hãy giới thiệu hoặc mô tả ngắn gọn mục tiêu 	mà dự án bạn hướng tới. Bạn có thể để trống mục này.</p>
		</div>
		
		<button type="submit" class="btn btn-primary btn-lg	" style="width: 200px;">Đồng ý</button>
	</form>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>  
</div><!-- End container -->
</body>
</html>