<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="${contextPath}/doimatkhau" method="POST" role="form" id="form-doi-mat-khau"> 
		  <input type="hidden" name="username" id="username" class="form-control" value="${pageContext.request.userPrincipal.name}">
		  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class="form-group">
		<div class="input-group">
		  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
		  <input type="password" name="oldPassword" class="form-control" placeholder="Mật khẩu cũ"
		  	data-bv-notempty="true"
          	data-bv-notempty-message="Mật khẩu phải khác rỗng.">
		</div>
		<span id="errorOldPass" class="text-danger small">${errorOldPass}</span>
	</div>
	<div class="form-group">
		<div class="input-group">
		  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
		  <input type="password" name="newPassword" id="newPassword" class="form-control" placeholder="Mật khẩu mới"
		  	data-bv-notempty="true" 
          	data-bv-notempty-message="Mật khẩu phải khác rỗng."
          	data-bv-stringlength="true"
       		data-bv-stringlength-min="6"
          	data-bv-stringlength-max="30"
         	data-bv-stringlength-message="Mật khẩu phải ít nhất là 6 ký tự và nhiều nhất là 30 ký tự">
		</div>
	</div>
	
	<div class="form-group">
		<div class="input-group">
		  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
		  <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" placeholder="Nhập lại mật khẩu mới"
		 	 data-bv-notempty="true" 
             data-bv-notempty-message="Mật khẩu phải khác rỗng." 
		 	 data-bv-identical="true" 
             data-bv-identical-field="newPassword" 
             data-bv-identical-message="Nhập lại mật khẩu mới chưa chính xác.">
		</div>
	</div>
	
   	 <button type="reset" class="btn btn-default" >Nhập lại</button>
     <button type="submit" class="btn btn-primary">Chấp nhận</button>
  	 
</form>


