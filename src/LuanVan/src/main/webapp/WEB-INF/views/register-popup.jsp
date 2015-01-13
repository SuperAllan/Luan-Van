<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<!-- Reg Modal -->
<div class="modal fade bs-example-modal-sm" id="regModal" tabindex="-1" role="dialog" aria-labelledby="regModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog modal-sm">
    <div class="modal-content" style="width: 100%;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="regModalLabel">Đăng kí</h4>
      </div>
      <form action="/luanvan/dangky" method="POST" role="form" id="form-dang-ki">
	      <div class="modal-body">
				<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
				
				<div class="form-group">
					<div class="input-group">
					  <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
					  <input type="text" name="username" id="username" class="form-control" required="required" data-bv-message="Tài khoản không được rỗng!" placeholder="Tài khoản">
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
					  <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
					  <input type="email" name="email" id="email" class="form-control" required="required" placeholder="Email" data-bv-message="Hãy nhập một email có thực!">
					</div>
				</div>
	<!-- 		<div class="form-group">
					<div class="input-group">
					  <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
					  <input type="password" name="password" class="form-control" required="required" placeholder="Mật khẩu" data-bv-message="Mật khẩu không được rỗng!">
					</div>
				</div>
	-->
				<div class="clearfix"></div>
	      </div><!-- End Modal body -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
	        <button type="submit" class="btn btn-primary">Đăng kí</button>
	      </div>
      </form>
    </div>
  </div>
</div>