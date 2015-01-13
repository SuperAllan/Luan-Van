<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<!-- Reg Modal -->
<script type="text/javascript">
	function loadagain(){
		$("#changePasswordModal").modal();
	}
</script>
		
<div class="modal fade bs-example-modal-sm" id="changePasswordModal" tabindex="-1" role="dialog" aria-labelledby="regModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog modal-sm">
    <div class="modal-content" style="width: 100%;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" >Đổi mật khẩu</h4>
      </div>
      <form action="/luanvan/doimatkhau" method="POST" role="form" >
	      <div class="modal-body">
				<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
				
				<div class="form-group">
					<div class="input-group">
					  <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
					  <input type="hidden" name="username" id="username" class="form-control" value="${pageContext.request.userPrincipal.name}">
					</div>
				</div>
 				<div class="form-group">
					<div class="input-group">
					  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
					  <input type="password" name="oldPassword" class="form-control" required="required" placeholder="Mật khẩu cũ" data-bv-message="Mật khẩu không được rỗng!">
					</div>
				</div>
				<span>${errorOldPass}</span>
				<div class="form-group">
					<div class="input-group">
					  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
					  <input type="password" name="newPassword" class="form-control" required="required" placeholder="Mật khẩu mới" data-bv-message="Mật khẩu không được rỗng!">
					</div>
				</div>
				
				<div class="form-group">
					<div class="input-group">
					  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
					  <input type="password" name="confirmPassword" class="form-control" required="required" placeholder="Nhập lại mật khẩu" data-bv-message="Mật khẩu không được rỗng!">
					</div>
				</div>

				<div class="clearfix"></div>
		
	      </div><!-- End Modal body -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
	        <button type="submit" id="chapNhan" class="btn btn-primary" onclick="loadagain()">Chấp nhận</button>
	      </div>
      </form>
    </div>
  </div>
</div>