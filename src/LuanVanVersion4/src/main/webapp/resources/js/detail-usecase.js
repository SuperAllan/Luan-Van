$(document).ready(function(){
	
	$("#danhSachUsecase").css("position", "relative");
	$('#danhSachUsecase').perfectScrollbar();
	if ($("#usecaseFromDiagram").val() != "") {
		showDetailUsecase($("#usecaseFromDiagram").val());
	}
	
	$(".formatNameDiagram").each(function(){
		if($(this).text().length > 40){
		var formatTitle = $.trim($(this).text()).substring(0,40).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
	
	$(".formatNameProject").each(function(){
		if($(this).text().length > 40){
		var formatTitle = $.trim($(this).text()).substring(0,40).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
});
function checkChangeValue(){
	$('#buttonSubmit').attr('disabled','');
}

function showDetailUsecase(usecaseid){
	$("#resultDetail").html("<div class='panel panel-info'><div class='panel-heading'><h3 class='panel-title'>Đang tải</h3></div></div>");
	$.ajax({
		type: 'get',
		url: 'showDetailUsecase',
		data: 'usecaseid=' + usecaseid,
		success: function(data) {
			$('#resultDetail').html(data);
		},
		error: function() {
			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
		}
	});
}

function searchUsecase(noidung, projectid) {
	$("#danhSachUsecase").html("<div class='panel panel-info'><div class='panel-heading'><h3 class='panel-title'>Đang tải</h3></div></div>");
	$.ajax({
		type: 'get',
		url: 'searchUsecase',
		data:{ 
			searchContent: noidung,
			projectID : projectid
		},
		success: function(data) {
			$('#danhSachUsecase').html(data);
		},
		error: function() {
			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
		}
	});	
}



