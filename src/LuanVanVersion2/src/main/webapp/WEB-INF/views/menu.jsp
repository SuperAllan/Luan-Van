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

<!-- menu -->
<script src="<c:url value="/resources/js/menu.js" />" ></script>
<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900,200italic,300italic,400italic,600italic,700italic,900italic&subset=latin,vietnamese,latin-ext' rel='stylesheet' type='text/css'>

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
					    <c:if test="${not empty user.image}">
					    	<img src="<c:url value="${user.image}" />" class="img-rounded" style="width: 20px; height: 20px;">
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
