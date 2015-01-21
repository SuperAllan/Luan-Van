function showDialog() {
		$("#uploadImageId").click();
	}

$(document).ready(function() {
	$(":file").change(function() {
		$("#formUploadFile").submit();
	});
});

