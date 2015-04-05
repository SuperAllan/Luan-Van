$(document).ready(function() {
    $('#form-dang-ky').bootstrapValidator();
    
    $('#password').change(function(){
    	$('#confirmPassword').val("");
    });
});
function formSubmit(){
	$('#form-send-mail-again').submit();
 }