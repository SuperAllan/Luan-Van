<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />" ></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<!-- BootstraoValidator -->
<script src="<c:url value="/resources/js/bootstrapValidator.min.js" />" ></script>
<link href="<c:url value="/resources/css/bootstrapValidator.min.css" />" rel="stylesheet">
<style>
 .navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:hover, .navbar-default .navbar-nav>.open>a:focus{
 background-color: transparent;
 color: #337ab7;
 }


</style>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <div class="media">
			  <a class="media-left media-middle" href="/luanvan/welcome">
			    <img src="<c:url value="/resources/img/logo.gif" />"  style="width: 30px; height: 30px">
			  </a>
			  <div class="media-body">
			    <h4 class="media-heading">Ước lượng chi phí phần mềm</h4>
		  	</div>
		 </div>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
   
      <ul class="nav navbar-nav navbar-right">
       
        	<sec:authorize access="isAnonymous()">
				<li class="dropdown">
					<a href="#" id='loginLink' class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-log-in"></span> Đăng nhập</a>
					<ul class="dropdown-menu" style=" width: 200px;">
						<form  action="<c:url value='j_spring_security_check' />" method="POST" role="form" style="padding: 10px;">
							<div class="form-group">
								<input type="text" id="username" class="form-control" name='username' placeholder="Tên tài khoản">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" name='password' placeholder="Mật khẩu">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</div>
							<button type="submit" class="btn btn-primary btn-block">Đăng nhập</button>
						</form>
					</ul>
				</li>
			</sec:authorize>
      
        
        <li>
        	<!-- Button logout -->
        	<sec:authorize access="isAuthenticated()">
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
					<li class="dropdown">			  
						<a type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true" style="cursor: pointer; ">
					    <c:if test="${not empty image}">
					    	<img src="<c:url value="${image}" />" class="img-rounded" style="width: 20px; height: 20px;">
					    </c:if>
					    ${pageContext.request.userPrincipal.name}
					    <span class="caret"></span>
					  </a>
					  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
					  
					    <li role="presentation"><a role="menuitem" tabindex="-1" href="/luanvan/background" >Thông tin cá nhân</a></li>
					    <li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:formSubmit()">Đăng xuất</a></li>
					  </ul>
					</li>
				</c:if>
			</sec:authorize>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
