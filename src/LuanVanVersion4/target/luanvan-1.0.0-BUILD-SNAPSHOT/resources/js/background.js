//show dialog upload file image
function showDialog() {
		$("#uploadImageId").click();
	}

$(document).ready(function(){
	

    $('#form-tao-du-an').bootstrapValidator();
	
	$(".projectName").each(function(){
		if($(this).text().length > 20){
		var formatTitle = $.trim($(this).text()).substring(0,20).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
	
	$(".description").each(function(){
		if($(this).text().length > 75){
		var formatDescription = $.trim($(this).text()).substring(0,75).split(" ").join(" ") + "...";
		$(this).text(formatDescription);
		}
	});
});

// convert image to base64
$("#uploadImageId").change(function(evt) {
	var file = evt.target.files[0];
	if (file) {
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function(e) {
			var content = e.target.result;
			$("#fileString").val(content);
			$("#formUploadFile").submit();
		}
	}
});

function showMyModal(){
	$('#myModal').modal('show');   
}







