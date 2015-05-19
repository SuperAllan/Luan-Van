$(document).ready(function(){
	$('#submitThongTinCaNhan').attr('disabled', 'disabled');

    $('#form-tao-du-an').bootstrapValidator();
	
	$(".description").each(function(){
		if($(this).text().length > 75){
		var formatDescription = $.trim($(this).text()).substring(0,75).split(" ").join(" ") + "...";
		$(this).text(formatDescription);
		}
	});
	
	$('#buttonCreateProject').on('click', function(){
		$('#projectName').focus();
	});
	
	$('#myModal').on('shown.bs.modal', function () {
		$("#projectName").focus();
	})
	
	if($('#errorNameProject').text() != ''){
		showMyModal();
	}
});

function setSubmitThongTinCaNhan(){
	$('#submitThongTinCaNhan').removeAttr('disabled');
}

//show dialog upload file image
function showDialog() {
		$("#uploadImageId").click();
}

// convert image to base64
$("#uploadImageId").change(function(evt) {
	var file = evt.target.files[0];
	var name = file.name.split('.').pop().toLowerCase();
	if (file && $.inArray(name, ['gif','png','jpg','jpeg']) != -1) {
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function(e) {
			var content = e.target.result;
			$("#fileString").val(content);
			$("#formUploadFile").submit();
		}
	}else{
		alert('Dữ liệu chỉ chấp nhận các định dạng như: .gif, .png, .jpg, .jpeg');
	}
});

function showMyModal(){
	$('#myModal').modal('show');   
}

function checkSpecialCharacter(value){
	var dem = 0;
	var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
	for(var i = 0; i < value.length; i++){
		console.log(value.charAt(i));
		if(iChars.indexOf(value.charAt(i)) != -1){
			$('#showCheckSpecialCharacter').text('Tên dự án chỉ chấp nhận chữ và số.');
			$('#submitCreateProject').prop('disabled', true);
			dem++;
		}
	}
	if(dem == 0){
		$('#showCheckSpecialCharacter').text('');
		$('#submitCreateProject').prop('disabled', false);
	}
}
