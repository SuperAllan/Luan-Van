$(document).ready(function(){
	$(".formatNameProject").each(function(){
		if($(this).text().length > 15){
		var formatTitle = $.trim($(this).text()).substring(0,15).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
	
	if ($("#updateTrangThaiSuccess").text() != "") {
		$(".classLi").each(function(){
			$(this).removeClass("active");
		});
		$(".tab-pane").each(function(){
			$(this).removeClass("in active");
		});
		$("#thietLap").addClass("active");
		$("#tabThietLap").addClass("in active")
		$("#suaDoi").addClass("active");
		$("#tabSuaDoi").addClass("in active")
	}
	
	if ($("#UpdateChucNangSuccess").text() != "") {
		$(".classLi").each(function(){
			$(this).removeClass("active");
		});
		$(".tab-pane").each(function(){
			$(this).removeClass("in active");
		});
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#chucNang").addClass("active");
		$("#tabChucNang").addClass("in active")
	}
	
	if ($("#updateKyThuatSuccess").text() != "") {
		$(".classLi").each(function(){
			$(this).removeClass("active");
		});
		$(".tab-pane").each(function(){
			$(this).removeClass("in active");
		});
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#KTCN").addClass("active");
		$("#tabKTCN").addClass("in active")
		
	}
	
	if ($("#updateMoiTruongSuccess").text() != "") {
		$(".classLi").each(function(){
			$(this).removeClass("active");
		});
		$(".tab-pane").each(function(){
			$(this).removeClass("in active");
		});
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#moiTruong").addClass("active");
		$("#tabMoiTruong").addClass("in active")
		
	}
	
	if ($("#updateBangLuongSuccess").text() != "") {
		$(".classLi").each(function(){
			$(this).removeClass("active");
		});
		$(".tab-pane").each(function(){
			$(this).removeClass("in active");
		});
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#bangLuong").addClass("active");
		$("#tabBangLuong").addClass("in active")
		
	}
	
	if ($("#updateGiaTriPhanMemSuccess").text() != "") {
		$(".classLi").each(function(){
			$(this).removeClass("active");
		});
		$(".tab-pane").each(function(){
			$(this).removeClass("in active");
		});
		$("#uocLuong").addClass("active");
		$("#tabUocLuong").addClass("in active")
		$("#giaTriPhanMem").addClass("active");
		$("#tabGiaTriPhanMem").addClass("in active")
		
	}
	
});

	function confirmDelete(){
		var confir = confirm('Bạn có chắc chắn muốn xóa không?');
		if(confir == true){
			return true;
		}else{
			return false
		}
	}

