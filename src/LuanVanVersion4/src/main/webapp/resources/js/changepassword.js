
$(document).ready(function() {
    $('#form-doi-mat-khau').bootstrapValidator();
    
    $('#newPassword').change(function(){
    	$('#confirmPassword').val("");
    });
});

$(document).ready(function(){
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
});

$(document).ready(function(){
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

