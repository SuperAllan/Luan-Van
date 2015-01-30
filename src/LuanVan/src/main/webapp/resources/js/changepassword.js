
$(document).ready(function() {
    $('#form-doi-mat-khau').bootstrapValidator();
});

$(document).ready(function(){
	if ($("#errorOldPass").html() != "") {
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
	if ($("#successChangePassword").html() != "") {
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

