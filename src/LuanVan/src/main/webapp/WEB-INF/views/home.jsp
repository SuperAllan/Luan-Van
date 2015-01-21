<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>

<html>
<head>
<title>Trang chủ</title>
</head>
<body style="padding-top: 50px;" >
<div class="container-fluid" style="padding-top: 20px; background-color: #202021;height: 500px;">
<div class="container">
	<div class="row">
	<div class="col-md-8">
		<h1 style="font-size: 50px; color: white;">Giá trị phần mềm của bạn bao nhiêu?<br>
			Nó đã được tính chính xác hay chưa?<br>
			Tại sao không ước lượng thử?<br>
		</h1>
		<p style="color: white;">Hãy đăng ký tài khoản và trải nghiệm với chúng tôi
			<span class="glyphicon glyphicon-arrow-right"></span></p>
	</div>
	<div class="col-md-4">
			<form action="/luanvan/dangky" method="POST" role="form" id="form-dang-ky">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="form-group">
						<div class="input-group">
						  <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
						  <input type="text" name="username" id="username" class="form-control input-lg"  placeholder="Tài khoản" 
						  	data-bv-notempty="true"
                			data-bv-notempty-message="Tài khoản phải khác rỗng"
						  	data-bv-stringlength="true"
                			data-bv-stringlength-min="6"
                			data-bv-stringlength-max="20"
                			data-bv-stringlength-message="Tài khoản phải ít nhất là 6 ký tự và nhiều nhất là 20 ký tự">
						</div>
						<div style="color: #a94442;font-size: 85%;padding-top: 5px;">
						<c:if test="${not empty strUser}">
							<c:out value="${strUser}" />
						</c:if>
						</div>
					</div>
					
					<div class="form-group">
						<div class="input-group">
						 <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
						 <input type="email" name="email" id="email" class="form-control input-lg" required="required" placeholder="Email" 
						  data-bv-notempty="true"
                		  data-bv-notempty-message="Email phải khác rỗng"
						  data-bv-emailaddress="true"
                		  data-bv-emailaddress-message="Email nhập chưa chính xác">
						</div>
						<div style="color: #a94442;font-size: 85%;padding-top: 5px;">
						<c:if test="${not empty strEmail}">
							<c:out value="${strEmail}" />
						</c:if>
						</div>
					</div>
		        <button type="submit" class="btn btn-primary btn-lg	" style="width:360px;">Đăng kí</button><br>
		   		<span style="color: white;">Sau khi đăng ký thành công mật khẩu sẽ được gửi qua Email của bạn.</span>
	      </form>
	      
	</div>
 	</div>
</div>
</div>
<script src="<c:url value="/resources/js/home.js" />" ></script>
</body>

</html>