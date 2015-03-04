$(document).ready(function() {
    $('#form-dang-ky').bootstrapValidator();
    
    $('#password').change(function(){
    	$('#confirmPassword').val("");
    });
});
