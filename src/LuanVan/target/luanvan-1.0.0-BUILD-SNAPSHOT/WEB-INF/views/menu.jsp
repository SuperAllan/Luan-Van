<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />" ></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<!-- Include trang dang ky popup -->
<%@ include file="/WEB-INF/views/register-popup.jsp" %>
<!-- Include trang doi mat khau popup -->
<%@ include file="/WEB-INF/views/changepassword-popup.jsp" %>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
       
        	<sec:authorize access="isAnonymous()">
				<li class="dropdown">
					<a href="#" id='loginLink' class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-log-in"></span> Đăng nhập</a>
					<ul class="dropdown-menu" style="right: 15px; width: 200px;">
						<form  action="<c:url value='j_spring_security_check' />" method="POST" role="form" style="padding: 10px;">
							<div class="form-group">
								<input type="text" id="username" class="form-control" name='username' placeholder="Tên tài khoản">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" name='password' placeholder="Mật khẩu">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</div>
							<button type="submit" class="btn btn-primary btn-block">Đăng
								nhập</button>
							<a class="btn btn-link btn-block" data-toggle="modal" href="#regModal">Chưa có tài khoản ?</a>
						</form>
					</ul>
				</li>
			</sec:authorize>
      
        
        <li>
        	<!-- Button logout -->
        	<sec:authorize access="hasRole('ROLE_USER')">
				<!-- For login user -->
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="POST" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>
		 
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<li class="dropdown" style="padding-top: 10px; padding-right: 20px;">			  
						<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true" >
					    ${pageContext.request.userPrincipal.name}
					    <span class="caret"></span>
					  </button>
					  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
					    <li role="presentation" id="nutdmk"><a role="menuitem" tabindex="-1" data-toggle="modal" href="#changePasswordModal" >Đổi mật khẩu</a></li>
					    <li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:formSubmit()">Logout</a></li>
					  </ul>
					</li>
				</c:if>
			</sec:authorize>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>