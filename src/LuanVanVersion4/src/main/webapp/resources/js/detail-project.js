$(document).ready(function(){
	$('#downloadExcel').click(function(){
		var href = $('#downloadExcel > a').attr('href');
		if($('#luongNhaNuoc').val() < 1){
			$('#downloadExcel > a').attr('href', "#");
			var isConfirm  = confirm('Bạn chưa tính bảng lương. Vui lòng tính bảng lương trước.');
			if(isConfirm == true){
				removeClassActive();
				$("#uocLuong").addClass("active");
				$("#tabUocLuong").addClass("in active")
				$("#bangLuong").addClass("active");
				$("#tabBangLuong").addClass("in active");
			}
		}else{
			$('#downloadExcel > a').attr('href', href);
		}
	});
	
	
	$(".formatNameProject").each(function(){
		if($(this).text().length > 70){
		var formatTitle = $.trim($(this).text()).substring(0,70).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
	
	$('.scrollBar').perfectScrollbar();
	
	if ($("#updateTrangThaiSuccess").text() != "") {
		$(".classLi").each(function(){
			$(this).removeClass("active");
		});
		$(".tab-Li").each(function(){
			$(this).removeClass("in active");
		});
		$("#thietLap").addClass("active");
		$("#tabThietLap").addClass("in active")
	}
	
	if ($("#UpdateChuyenDoiSuccess").text() != "") {
		removeClassActive();
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#chuyenDoi").addClass("active");
		$("#tabChuyenDoi").addClass("in active")
	}
	
	if ($("#UpdateChucNangSuccess").text() != "") {
		removeClassActive();
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#chucNang").addClass("active");
		$("#tabChucNang").addClass("in active")
	}
	
	if ($("#updateKyThuatSuccess").text() != "") {
		removeClassActive();
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#KTCN").addClass("active");
		$("#tabKTCN").addClass("in active")
		
	}
	
	if ($("#updateMoiTruongSuccess").text() != "") {
		removeClassActive();
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#moiTruong").addClass("active");
		$("#tabMoiTruong").addClass("in active")
		
	}
	
	if ($("#updateBangLuongSuccess").text() != "") {
		removeClassActive();
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#bangLuong").addClass("active");
		$("#tabBangLuong").addClass("in active");
	}
	
	if ($("#updateGiaTriPhanMemSuccess").text() != "") {
		removeClassActive();
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#giaTriPhanMem").addClass("active");
		$("#tabGiaTriPhanMem").addClass("in active")
	}
});


function removeClassActive() {
	$(".classLi").each(function(){
		$(this).removeClass("active");
	});
	
	$(".tab-pane").each(function(){
		$(this).removeClass("in active");
	});
	$(".tab-pane li").each(function(){
		$(this).removeClass("active");
	});
}

