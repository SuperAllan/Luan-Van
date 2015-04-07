<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>
<html>
<head>
<title>Trang chủ</title>
</head>
<body>
<div class="container">
<div class="panel panel-info content-home">
	<div class="panel-heading">Giới thiệu</div>
	<div class="panel-body">
		<div class="col-md-8">
			<div class="header-paragraph-home">Công văn 2589/BTTTT-ƯDCNTT là gì?</div>
			<div class="content-paragraph-home">
				Bộ Thông tin và Truyền thông công bố Hướng dẫn xác định chi phí phát triển,
				nâng cấp phần mềm nội bộ kèm theo văn bản này để cơ quan, tổ chức, 
				cá nhân có liên quan sử dụng vào việc xác định chi phí phát triển, nâng cấp phần mềm nội bộ,
				phục vụ công tác ứng dụng công nghệ thông tin trong hoạt động của cơ quan nhà nước.
				Công văn này thay thế Công văn số 3364/BTTTT-ƯDCNTT ngày 17/10/2008 về hướng dẫn xác định giá trị phần mềm 
				và Công văn số 2496/BTTTT-ƯDCNTT ngày 04/08/2010 về việc sửa đổi giá trị trọng số BMT tại Công văn số 3364/BTTTT-ƯDCNTT 
				ngày 17/10/2008.
			</div>
			<div class="header-paragraph-home">Ước lượng chi phí phần mềm như thế nào?</div>
			<div class="content-paragraph-home">
				Dựa trên công văn 2589, website sẽ tự động tính toán theo công thức trong công văn
				 dựa trên các thông tin thu thập, thông tin người dùng đã điền vào. Các tính toán sẽ được hiển thị dưới dạng các bảng 
				 dữ liệu, người dùng có thể tải xuống các bảng tính toán dưới dạng tập tin excel.
			</div>
			<div class="header-paragraph-home">Các thông tin có thể thu thập lưu trữ là gì?</div>
			<div class="content-paragraph-home">
				Các thông tin thu thập như: tài liệu phân tích, tài liệu phỏng vấn có thể được tải lên website và tải xuống. 
				Các loại tập tin có thể tải lên hoặc tải xuống: pdf, docx, xls, tập tin ảnh (svg) ...
			</div>
		</div>
	
		<div class="form-register-home col-md-4">
				<form action="/luanvan/dangky" method="POST" role="form" id="form-dang-ky">
					<div class="form-group">
						<div class="input-group">
						  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
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
						  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						  <input type="password" name="password" id="password" class="form-control input-lg"  placeholder="Mật khẩu" 
						  		data-bv-notempty="true"
		              			data-bv-notempty-message="Mật khẩu phải khác rỗng"
						  		data-bv-stringlength="true"
		              			data-bv-stringlength-min="6"
		              			data-bv-stringlength-max="30"
		              			data-bv-stringlength-message="Mật khẩu phải ít nhất là 6 ký tự và nhiều nhất là 30 ký tự">
						</div>
					</div>
					
					<div class="form-group">
						<div class="input-group">
						  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						  <input type="password" name="confirmPassword" id="confirmPassword" class="form-control input-lg"  placeholder="Nhập lại mật khẩu" 
					  		data-bv-notempty="true"
	              			data-bv-notempty-message="Mật khẩu phải khác rỗng"
					  		data-bv-identical="true" 
	            			data-bv-identical-field="password" 
	            			data-bv-identical-message="Nhập lại mật khẩu chưa chính xác.">
						</div>
					</div>
					
					
					<div class="form-group">
						<div class="input-group">
						 <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
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
		        <button type="submit" class="btn btn-info btn-lg btn-block"><i class="glyphicon glyphicon-hand-o-right"></i> Đăng kí ngay</button>
		        <small class="text-danger"></small>
		     </form>
		     <span style="color: black;">Sau khi đăng ký thành công. Xin vào Email để kích hoạt tài khoản. 
			     <c:if test="${not empty userInActive}">
			      	 Nếu không nhận được Email 
				     <form action="/luanvan/sendMailAgain" method="POST" role="form" id="form-send-mail-again">
				     <input type="hidden" name="userInActiveForSendMail" value="${userInActive.username}">
				     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				   	 <a type="submit" onclick="formSubmit()">nhấn vào đây.</a>
				   	 </form>
			   	 </c:if>
			 </span>
	</div>
	
		<div class="col-md-12">
			<div class="header-in-content-home">Các tính năng</div>
			<div class="col-md-12">
				<div class="col-md-4 list-feature-home">
					<div class="icon-feature-home"><i class="glyphicon glyphicon-usd"></i></div> Ước lượng chi phí
				</div>
				<div class="col-md-4 list-feature-home">
					<div class="icon-feature-home"><i class="glyphicon glyphicon-book"></i></div> Thu thập yêu cầu phần mềm
				</div>
				<div class="col-md-4 list-feature-home">
					<div class="icon-feature-home"><i class="mdi-image-brush"></i></div> Vẽ sơ đồ use-case
				</div>
			</div>
			<div class="col-md-12" style="margin-top: 10px;">
				<div class="col-md-4 list-feature-home">
					<div class="icon-feature-home"><i class="mdi-hardware-desktop-windows"></i></div> Thiết kế giao diện
				</div>
				<div class="col-md-4 list-feature-home">
					<div class="icon-feature-home"><i class="mdi-action-wallet-travel"></i></div> Quản lý các dự án
				</div>
				<div class="col-md-4 list-feature-home">
					<div class="icon-feature-home"><i class="mdi-action-swap-vert"></i></div> Hỗ trợ xuất file dữ liệu
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %> 
<script src="<c:url value="/resources/js/home.js" />" ></script>
</body>

</html>