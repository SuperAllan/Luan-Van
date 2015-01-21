
$(document).ready(function() {
    $('#form-doi-mat-khau').bootstrapValidator();
});

$(document).ready(function(){
	if ($("#errorOldPass").html() != "") {
		$("#litab1").removeClass("active");
		$("#litab3").removeClass("active");
		$("#litab2").addClass("active");
		$("#tab1").removeClass("in active");
		$("#tab3").removeClass("in active");
		$("#tab2").addClass("in active");	
	}
});

$(document).ready(function(){
	if ($("#successChangePassword").html() != "") {
		$("#litab1").removeClass("active");
		$("#litab3").removeClass("active");
		$("#litab2").addClass("active");
		$("#tab1").removeClass("in active");
		$("#tab3").removeClass("in active");
		$("#tab2").addClass("in active");	
	}
});

