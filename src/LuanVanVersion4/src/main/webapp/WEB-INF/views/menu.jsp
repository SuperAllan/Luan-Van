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
<!-- material -->
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
<nav class="navbar navbar-default navbar-fixed-top" style="margin-bottom: 0px; background: url('resources/img/bg-footer.jpg');">
  <div class="container">
    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand icon-menu" href="${contextPath}/"><i class="glyphicon glyphicon-home glyphicon-lg"></i></a>
    </div>
	
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <p class="navbar-text"><i class="mdi-navigation-more-vert"></i></p>
    <p class="navbar-text">
    	<a href="http://www.ctu.edu.vn" class="navbar-link">Đại Học Cần Thơ</a>
    </p>
    <p class="navbar-text"><i class="mdi-navigation-more-vert"></i></p>
    <p class="navbar-text">
    	<a href="http://www.cit.ctu.edu.vn" class="navbar-link">Khoa CNTT</a>
    </p>
		
    <!-- alert dang ky thanh cong-->
    <c:if test="${not empty successRegister}">
	  	<div class="alert alert-success text-center message-custom" >
			${successRegister}
		</div>
	 </c:if>
	 
	 <!-- alert dang ky bi loi-->
    <c:if test="${not empty errorRegister}">
	  	<div class="alert alert-danger text-center message-custom" >
			${errorRegister}
		</div>
	 </c:if>
	 
	  <!-- alert dang ky email khong ton tai-->
    <c:if test="${not empty existEmail}">
	  	<div class="alert alert-danger text-center message-custom" >
			${existEmail}
		</div>
	 </c:if>
	 
	  <!-- alert dang ky tai khoan trung-->
    <c:if test="${not empty strUser}">
	  	<div class="alert alert-danger text-center message-custom" >
			${strUser}
		</div>
	 </c:if>
	 
	  <!-- alert dang ky email da duoc su dung-->
    <c:if test="${not empty strEmail}">
	  	<div class="alert alert-danger text-center message-custom" >
			${strEmail}
		</div>
	 </c:if>
	 
	  <!-- alert gui email thanh cong-->
    <c:if test="${not empty successSendMail}">
	  	<div class="alert alert-success text-center message-custom" >
			${successSendMail}
		</div>
	 </c:if>
	 
    <!-- alert tao du an -->
    <c:if test="${not empty successCreateProject}">
	  	<div class="alert alert-success text-center message-custom" >
			${successCreateProject}
		</div>
	 </c:if>
	 
	 <c:if test="${not empty errorNameProject}">
	  	<div id="errorNameProject" class="alert alert-danger text-center message-custom">
			${errorNameProject}
		</div>
	 </c:if>
	 
   	<!-- alert change password -->
	  <c:if test="${not empty successChangePassword}">	
		<div id="successChangePassword" class="alert alert-success text-center message-custom">
			${successChangePassword}
		</div>
	  </c:if>
	  <!-- alert update information -->
	  <c:if test="${not empty updateInforSuccess}">
		  	<div id="updateInforSuccess" class="alert alert-success text-center message-custom">
				${updateInforSuccess}
			</div>
	  	</c:if>
	  	
	  	<!-- alert thanh cong thiet lap du an -->
	  	<c:if test="${not empty updateTrangThaiSuccess}">
		  	<div id="updateTrangThaiSuccess" class="alert alert-success text-center message-custom">
				${updateTrangThaiSuccess}
			</div>
	  	</c:if>
	  	
	  	<!-- alert error thiet lap du an -->
	  	<c:if test="${not empty errorNameThietLap}">
		  	<div id="errorNameThietLap" class="alert alert-danger text-center message-custom-error">
				${errorNameThietLap}
			</div>
	  	</c:if>
	  	
	  	<!-- alert error doi mat khau -->
	  	<c:if test="${not empty errorOldPass}">
		  	<div id="errorOldPass" class="alert alert-danger text-center message-custom-error">
				${errorOldPass}
			</div>
	  	</c:if>
	  	
	  	<!-- alert yeu cau phi chuc nang -->
	  	<c:if test="${not empty UpdatePhiChucNangSuccess}">
		  	<div id="UpdatePhiChucNangSuccess" class="alert alert-success message-custom">
				${UpdatePhiChucNangSuccess}
			</div>
	  	</c:if>
	  	
	  	<!-- alert chuyen doi usecase-->
	    <c:if test="${not empty UpdateChuyenDoiSuccess}">
		  	<div id="UpdateChuyenDoiSuccess" class="alert alert-success text-center message-custom" >
				${UpdateChuyenDoiSuccess}
			</div>
		 </c:if>
	 
	  	<!-- alert ky thuat cong nghe -->
	  	<c:if test="${not empty updateKyThuatSuccess}">
		  	<div id="updateKyThuatSuccess" class="alert alert-success message-custom">
				${updateKyThuatSuccess}
			</div>
	  	</c:if>
  		<!-- alert moi truong -->
	  	<c:if test="${not empty updateMoiTruongSuccess}">
		  	<div id="updateMoiTruongSuccess" class="alert alert-success message-custom">
				${updateMoiTruongSuccess}
			</div>
	  	</c:if>
	  	
	  	<!-- alert bang luong -->
	  	<c:if test="${not empty updateBangLuongSuccess}">
		  	<div id="updateBangLuongSuccess" class="alert alert-success message-custom">
				${updateBangLuongSuccess}
			</div>
	  	</c:if>
	  	
	  	<!-- alert gia tri phan mem -->
	  	<c:if test="${not empty updateGiaTriPhanMemSuccess}">
		  	<div id="updateGiaTriPhanMemSuccess" class="alert alert-success message-custom">
				${updateGiaTriPhanMemSuccess}
			</div>
	  	</c:if>
	  	
	  	<!-- alert gia tri phan mem -->
	  	<c:if test="${not empty deleteProjectSuccess}">
		  	<div id="deleteProjectSuccess" class="alert alert-success message-custom">
				${deleteProjectSuccess}
			</div>
	  	</c:if>
	  	<%-- <sec:authorize access="isAnonymous()">
	  	<form class="navbar-form pull-right" action="<c:url value='j_spring_security_check' />" method="POST" role="form">
			<input type="text" placeholder="Tài khoản" name="username">
			<input type="password" placeholder="Mật khẩu" name='password'>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input class='loginInput' type="submit" value="Đăng nhập"/>
			<i class="glyphicon glyphicon-log-in"></i>
		</form>
		</sec:authorize> --%>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
			<sec:authorize access="isAnonymous()">
			<a href="#" id='loginLink' class="dropdown-toggle" data-toggle="dropdown">
				Đăng nhập <span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li>
					<form  action="<c:url value='j_spring_security_check' />" method="POST" role="form" style="margin-bottom: 0; padding: 10px; width: 200px;" id="formLogIn">
						<div class="form-group">
							<input type="text" id="username" class="form-control-bs" name='username' placeholder="Tên tài khoản" required="required" autofocus="autofocus"
								data-bv-notempty="true"
								data-bv-notempty-message=" "
						  		data-bv-stringlength="true"
		              			data-bv-stringlength-min="6"
		              			data-bv-stringlength-max="30"
		              			data-bv-stringlength-message=" ">
						</div>
						<div class="form-group">
							<input type="password" class="form-control-bs" name='password' placeholder="Mật khẩu" required="required"
								data-bv-notempty="true"
								data-bv-notempty-message=" "
						  		data-bv-stringlength="true"
		              			data-bv-stringlength-min="6"
		              			data-bv-stringlength-max="30"
		              			data-bv-stringlength-message=" ">
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<button type="submit" class="btn btn-info btn-block"><i class="glyphicon glyphicon-log-in"></i> Đăng nhập</button>
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
				<form action="${logoutUrl}" method="POST" id="logoutFormMenu">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				
		 
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<li class="dropdown">			  
						<a type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true" style="cursor: pointer; ">
						    <c:if test="${not empty user.image}">
						    	<img src="<c:url value="${user.image}" />" class="img-rounded" style="width: 20px; height: 20px;">
						    </c:if>
						    <span id="userNameInMenu" class="username">${pageContext.request.userPrincipal.name}</span>
						    <span class="caret"></span>
					    </a>
					  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
					  
					    <li role="presentation"><a role="menuitem" tabindex="-1" href="${contextPath}/background" ><i class="mdi-action-assignment-ind"></i> Thông tin chung</a></li>
					    <li role="presentation"><a role="menuitem" tabindex="-1" onclick="formSubmitMenu()"><i class="mdi-action-settings-power"></i> Đăng xuất</a></li>
					  </ul>
					</li>
				</c:if>
			</sec:authorize>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<!-- Logo and title -->
<div class="container">
	<div class="logo-home" style="margin-top: 20px;">
		<div class="img-logo-home">
			<img src="<c:url value="/resources/img/logo.gif" />" width="150" height="150"/>
		</div>
		<div class="text-logo-home">
			<span>ƯỚC LƯỢNG CHI PHÍ</span><br>
			<span>THU THẬP YÊU CẦU PHẦN MỀM</span>
		</div>
		<div class="btn-download-home">
			<a class="btn btn-info" href="${contextPath}/resources/congvan/congvan.odt"><i class="mdi-file-cloud-download"></i> Công văn 2589/BTTTT-ƯDCNTT</a>
		</div>
	</div>
</div>
