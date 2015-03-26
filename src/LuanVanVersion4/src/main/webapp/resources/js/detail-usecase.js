$(document).ready(function(){
	$("#danhSachUsecase").css("position", "relative");
	$('#danhSachUsecase').perfectScrollbar();
});

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

//this is the id of the form
$("#form-update-question").submit(function() {
    $('#listCauHoi').val('Đang tải');
    $.ajax({
           type: 'get',
           url: 'updateCauHoi',
           data: $("#form-update-question").serialize(), // serializes the form's elements.
           success: function(result){
              $('#listCauHoi').text(result);
           },
           error: function() {
   			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
   		   }
         });
});