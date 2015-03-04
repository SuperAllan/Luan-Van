<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- Bootstrap vs iquery -->
<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />" ></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

<!-- BootstraoValidator -->
<script src="<c:url value="/resources/js/bootstrapValidator.min.js" />" ></script>
<link href="<c:url value="/resources/css/bootstrapValidator.min.css" />" rel="stylesheet">

<!-- Perfect scroll -->
<script src="<c:url value="/resources/js/perfect-scrollbar.min.js" />" ></script>
<link href="<c:url value="/resources/css/perfect-scrollbar.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/ripples.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/material-wfont.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/ripples.min.js" />" ></script>
<script src="<c:url value="/resources/js/material.min.js" />" ></script>

<!-- menu -->
<script src="<c:url value="/resources/js/menu.js" />" ></script>
<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet">
<script>
        $(document).ready(function() {
            $.material.init();
        });
    </script> 
<nav class="navbar navbar-default" style="margin-bottom: 0;">
  <div class="container">
    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <div id="menu">
	      	<img src="<c:url value="/resources/img/logo.gif" />" width="50" height="50">
			<ul id="uocluong">
				<li>Ư</li>
				<li>Ớ</li>
				<li>C</li>
				<li>L</li>
				<li>Ư</li>
				<li>Ợ</li>
				<li>N</li>
				<li>G</li>
			</ul>
			<span id="dauCong">+</span>
			<ul id="thuthap">
				<li>T</li>
				<li>H</li>
				<li>U</li>
				<li>T</li>
				<li>H</li>
				<li>Ậ</li>
				<li>P</li>
				<li>Y</li>
				<li>Ê</li>
				<li>U</li>
				<li>C</li>
				<li>Ầ</li>
				<li>U</li>
			</ul>
		</div>
    </div>
	
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <!-- alert tao du an -->
    <c:if test="${not empty success}">
	  	<div class="alert alert-success text-center alert-menu" >
			<button type="button" class="close pull-right" data-dismiss="alert" aria-hidden="true">&times;</button>
			${success}
		</div>
	 </c:if>
	 
	 <c:if test="${not empty errorName}">
	  	<div class="alert alert-danger text-center alert-menu">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			${errorName}
		</div>
	 </c:if>
   	<!-- alert change password -->
	  <c:if test="${not empty successChangePassword}">	
		<div id="successChangePassword" class="alert alert-success text-center alert-menu">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			${successChangePassword}
		</div>
	  </c:if>
	  <!-- alert update information -->
	  <c:if test="${not empty updateInforSuccess}">
		  	<div id="updateInforSuccess" class="alert alert-success text-center alert-menu">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${updateInforSuccess}
			</div>
	  	</c:if>
	  	<!-- alert thanh cong thiet lap du an -->
	  	<c:if test="${not empty updateTrangThaiSuccess}">
		  	<div id="updateTrangThaiSuccess" class="alert alert-success text-center alert-menu">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${updateTrangThaiSuccess}
			</div>
	  	</c:if>
	  	<!-- alert error thiet lap du an -->
	  	<c:if test="${not empty errorNameThietLap}">
		  	<div id="errorNameThietLap" class="alert alert-danger text-center alert-menu">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${errorNameThietLap}
			</div>
	  	</c:if>
	  	<!-- alert yeu cau chuc nang -->
	  	<c:if test="${not empty UpdateChucNangSuccess}">
		  	<div id="UpdateChucNangSuccess" class="alert alert-success alert-menu">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${UpdateChucNangSuccess}
			</div>
	  	</c:if>
	  	<!-- alert ky thuat cong nghe -->
	  	<c:if test="${not empty updateKyThuatSuccess}">
		  	<div id="updateKyThuatSuccess" class="alert alert-success alert-menu">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${updateKyThuatSuccess}
			</div>
	  	</c:if>
  		<!-- alert moi truong -->
	  	<c:if test="${not empty updateMoiTruongSuccess}">
		  	<div id="updateMoiTruongSuccess" class="alert alert-success alert-menu">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${updateMoiTruongSuccess}
			</div>
	  	</c:if>
	  	
	  	<!-- alert bang luong -->
	  	<c:if test="${not empty updateBangLuongSuccess}">
		  	<div id="updateBangLuongSuccess" class="alert alert-success alert-menu">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${updateBangLuongSuccess}
			</div>
	  	</c:if>
	  	
	  	<!-- alert gia tri phan mem -->
	  	<c:if test="${not empty updateGiaTriPhanMemSuccess}">
		  	<div id="updateGiaTriPhanMemSuccess" class="alert alert-success alert-menu">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${updateGiaTriPhanMemSuccess}
			</div>
	  	</c:if>
	  	
	  	<!-- alert gia tri phan mem -->
	  	<c:if test="${not empty deleteProjectSuccess}">
		  	<div id="deleteProjectSuccess" class="alert alert-success alert-menu">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${deleteProjectSuccess}
			</div>
	  	</c:if>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
			<sec:authorize access="isAnonymous()">
			<a href="#" id='loginLink' class="dropdown-toggle" data-toggle="dropdown">
				Đăng nhập <span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li>
					<form  action="<c:url value='j_spring_security_check' />" method="POST" role="form" style="margin-bottom: 0; padding: 10px; width: 200px;">
						<div class="form-group">
							<input type="text" id="username" class="form-control" name='username' placeholder="Tên tài khoản">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name='password' placeholder="Mật khẩu">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</div>
						<button type="submit" class="btn btn-primary btn-block btn-sm"><i class="glyphicon glyphicon-log-in"></i> Đăng nhập</button>
					</form>
				</li>
			</ul>
			</sec:authorize>
		</li>
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
					    <c:if test="${not empty user.image}">
					    	<img src="<c:url value="${user.image}" />" class="img-rounded" style="width: 20px; height: 20px;">
					    </c:if>
					    ${pageContext.request.userPrincipal.name}
					    <span class="caret"></span>
					  </a>
					  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
					  
					    <li role="presentation"><a role="menuitem" tabindex="-1" href="/luanvan/background" ><i class="mdi-action-assignment-ind"></i> Thông tin cá nhân</a></li>
					    <li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:formSubmit()"><i class="glyphicon glyphicon-log-out"></i> Đăng xuất</a></li>
					  </ul>
					</li>
				</c:if>
			</sec:authorize>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
