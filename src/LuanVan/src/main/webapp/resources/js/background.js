function showDialog() {
		$("#uploadImageId").click();
	}


$(document).ready(function(){
	if ($("#updateSuccess").html() != "") {
		$(".classLi").each(function(){
			$(this).removeClass("active");
		});
		$(".tab-pane").each(function(){
			$(this).removeClass("in active");
		});
		$("#thongTin").addClass("active");
		$("#tabThongTin").addClass("in active")
	}
});

$(document).ready(function(){
	$(".logoImg").hover(function(){
		$(".hoverImg").css('background', 'rgba(34, 85, 85, 0.8)');
	},function(){
		$(".hoverImg").css('background', 'rgba(34, 85, 85, 0.3)')
	});
});


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