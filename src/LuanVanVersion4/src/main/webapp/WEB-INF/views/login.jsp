<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="utf-8">
<title>Login Page</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}
 
.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
 
#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
#title-login-again {
	font-size: 24px;
	padding: 20px;
	color: white;
}
#form-login-again {
	border: 1px solid #ddd;
	border-radius: 6px;
	padding: 20px;
	background: white;
}
</style>
</head>
<body onload='document.loginForm.username.focus();' style="background: #357ebd;">

	<div id="title-login-again" class="col-md-12 text-center">
		<span>Tài khoản hoặc mật khẩu của bạn không đúng, xin vui lòng đăng nhập lại phía dưới.</span>
	</div>
	<div id="form-login-again" class="col-md-4 col-md-offset-4">
		<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
			<legend>Đăng nhập</legend>
			<div class="form-group">
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
			</div>
			<div class="form-group">
				<label for="">Tài khoản</label>
				<input type="text" class="form-control" name="username" placeholder="Nhập tài khoản">
			</div>
		
			<div class="form-group">
				<label for="">Mật khẩu</label>
				<input type='password' class="form-control" name='password' placeholder="Nhập mật khẩu">
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
			<a class="btn btn-primary pull-left" href="${contextPath}">Về trang chủ</a>
			<button type="submit" class="btn btn-primary pull-right">Đăng nhập</button>
		</form>
	</div>
 
</body>
<!-- Bootstrap vs iquery -->
<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />" ></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
</html>