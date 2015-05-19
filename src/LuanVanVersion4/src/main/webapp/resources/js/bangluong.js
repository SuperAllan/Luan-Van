$(document).ready(function(){
	
	$('#submitBangLuong').attr('disabled', 'disabled');
	
	$('#selectLuongNhaNuoc').change(function(){
		if($(this).val() != "---"){
			$('#luongNhaNuoc').val($(this).val());
		}
	});
	
	$('#selectLuongNhaNuoc').on('change', function(){
		$('#submitBangLuong').removeAttr('disabled');
	});
	
	$('#luongNhaNuoc').on('change', function(){
		$('#submitBangLuong').removeAttr('disabled');
	});
	
	$("input[name='chooseBacLuong']").on('change', function(){
		$('#submitBangLuong').removeAttr('disabled');
	});
	
	$('.luongTangThem').on('change', function(){
		$('#submitBangLuong').removeAttr('disabled');
	}); 
	
	$('.pcKhuVuc').on('change', function(){
		$('#submitBangLuong').removeAttr('disabled');
	});
	
	$('.pcLuuDong').on('change', function(){
		$('#submitBangLuong').removeAttr('disabled');
	});
	
	//function format number
	$('.formatKetQua').each(function(){
		var string = numeral($(this).text()).format('0,0');
		$(this).text(string);
	});
	
	
});
	// kiểm tra lương tăng thêm, lưu động và khu vực 
	// nếu rỗng thì ráng bằng 0
	function checkBangLuong(){
		$('.luongTangThem').each(function(){
			if($(this).val() == "" || $(this).val() == null){
				$(this).val(0);
			}
		});
		$('.pcLuuDong').each(function(){
			if($(this).val() == "" || $(this).val() == null){
				$(this).val(0);
			}
		});
		$('.pcKhuVuc').each(function(){
			if($(this).val() == "" || $(this).val() == null){
				$(this).val(0);
			}
		});
	}