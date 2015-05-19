var iconRight = '<i class="glyphicon glyphicon-chevron-right"></i>';
var iconLeft = '<i class="glyphicon glyphicon-chevron-left"></i>';
var nameProject = $('#nameProject').val();

$(document).ready(function(){
	window.history.pushState('page2', 'Title', "detailProject?name="+nameProject+"");
	
	$('#submitFormKyThuat').attr('disabled', 'disabled');
	
	$('#submitFormMoiTruong').attr('disabled', 'disabled');
	
	$('#submitCreateProject').attr('disabled', 'disabled');
	
	$('#downloadExcel').click(function(){
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
			var dinhGia = numeral().unformat($('#dinhGiaG').text());
			$('#downloadExcel > a').attr('href', 'downloadExcel?project='+$('#nameProject').val()+'&dinhgia='+dinhGia);
		}
	});
	
	$('#downloadWord').click(function(){
		if($('#luongNhaNuoc').val() < 1){
			$('#downloadWord > a').attr('href', "#");
			var isConfirm  = confirm('Bạn chưa tính bảng lương. Vui lòng tính bảng lương trước.');
			if(isConfirm == true){
				removeClassActive();
				$("#uocLuong").addClass("active");
				$("#tabUocLuong").addClass("in active")
				$("#bangLuong").addClass("active");
				$("#tabBangLuong").addClass("in active");
			}
		}else{
			var dinhGia = numeral().unformat($('#dinhGiaG').text());
			$('#downloadWord > a').attr('href', 'downloadDoc?project='+$('#nameProject').val()+'&dinhgia='+dinhGia);
		}
	});
	
	$(".formatNameProject").each(function(){
		if($(this).text().length > 70){
			var formatTitle = $.trim($(this).text()).substring(0,70).split(" ").join(" ") + "...";
			$(this).text(formatTitle);
		}
	});
	
	$('.scrollBar').perfectScrollbar();
	
	$('.tenProjectForThiepLap').on('change', function(){
		$('#submitCreateProject').removeAttr('disabled');
	});
	
	$('.motaProjectForThietLap').on('change', function(){
		$('#submitCreateProject').removeAttr('disabled');
	});
	
	$("input[name='radioTrangThai']").on('change', function(){
		$('#submitCreateProject').removeAttr('disabled');
	});
	
	$('.giaTriXepHangKT').on('change', function(){
		$('#submitFormKyThuat').removeAttr('disabled');
	});
	
	$('.ghiChuKyThuat').on('change', function(){
		$('#submitFormKyThuat').removeAttr('disabled');
	});
	
	$('.giaTriXepHangMT').on('change', function(){
		$('#submitFormMoiTruong').removeAttr('disabled');
	});
	
	$('#moTaChucNang').click(function(){
		var countInActive = 0;
		var countActive = 0;
		$('.tab-paneCN').each(function(){
			if($(this).hasClass('in active')){
				countInActive++;
			}
		});
		$('.classCN').each(function(){
			if($(this).hasClass('active')){
				countActive++;
			}
		});
		if(countInActive <= 0 && countActive <= 0){
			$('#chucNang').addClass('active');
			$('#tabChucNang').addClass('in active');
		}
	});
	
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
	
	if ($("#errorNameThietLap").text() != "") {
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
	
	if ($("#UpdatePhiChucNangSuccess").text() != "") {
		removeClassActive();
		$("#moTaChucNang").addClass("active");
		$("#tabMoTaChucNang").addClass("in active");
		$("#phiChucNang").addClass("active");
		$("#tabPhiChucNang").addClass("in active");
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

//Begin toggle menu left details project
$(".toggle-menu-detail-project").on('click', function() {
	$("#menu-detail-project").toggle("fade");
	if ($(this).html() == iconRight) {
		$(this).html(iconLeft);
		$(".wrapper-tab-content-detail-project").addClass("col-md-10");
		$(".wrapper-tab-content-detail-project").removeClass("col-md-12");
	} else {
		$(this).html(iconRight);
		$(".wrapper-tab-content-detail-project").addClass("col-md-12");
		$(".wrapper-tab-content-detail-project").removeClass("col-md-10");
	}
});

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
