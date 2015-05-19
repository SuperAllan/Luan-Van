
$(document).ready(function() {
    $('#form-doi-mat-khau').bootstrapValidator();
    
    $('#newPassword').change(function(){
    	$('#confirmPassword').val("");
    });

	if ($("#errorOldPass").text() != "") {
		$(".classLi").each(function(){
			$(this).removeClass("active");
		});
		$(".tab-pane").each(function(){
			$(this).removeClass("in active");
		});
		$("#matKhau").addClass("active");
		$("#tabMatKhau").addClass("in active")
	}
	
	if ($("#successChangePassword").text() != "") {
		$(".classLi").each(function(){
			$(this).removeClass("active");
		});
		$(".tab-pane").each(function(){
			$(this).removeClass("in active");
		});
		$("#matKhau").addClass("active");
		$("#tabMatKhau").addClass("in active")
	}
});

