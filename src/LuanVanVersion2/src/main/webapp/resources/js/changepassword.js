
$(document).ready(function() {
    $('#form-doi-mat-khau').bootstrapValidator();
    
    window.setTimeout(function() { $(".alert-update-success").alert('close'); }, 2000);
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

