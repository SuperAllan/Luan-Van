$(document).ready(function(){
	$('#selectLuongNhaNuoc').change(function(){
		if($(this).val() != "---"){
			$('#luongNhaNuoc').val($(this).val());
		}
	});
	
	
//	$('.ketQuaBaoHiem').each(function(index){
//		var index = index +1;
//		var as = ":nth-child("+index+")";
//		var luongCoBan = $(this).parent().parent().find('tr'+as).find('.ketQuaLuongCoBan').text();
//		var luongPhu = $(this).parent().parent().find('tr'+as).find('.ketQuaLuongPhu').text();
//		var pcKhuVuc = $(this).parent().parent().find('tr'+as).find('.pcKhuVuc').val();
//		$(this).text((parseInt(luongCoBan)+parseInt(luongPhu)+parseInt(pcKhuVuc))*0.23);
//	});
//	
//	$('.ketQuaTong').each(function(index){
//		var index = index +1;
//		var as = ":nth-child("+index+")";
//		var luongCoBan = $(this).parent().parent().find('tr'+as).find('.ketQuaLuongCoBan').text();
//		var luongTangThem = $(this).parent().parent().find('tr'+as).find('.luongTangThem').val();
//		var luongPhu = $(this).parent().parent().find('tr'+as).find('.ketQuaLuongPhu').text();
//		var pcKhuVuc = $(this).parent().parent().find('tr'+as).find('.pcKhuVuc').val();
//		var pcLuuDong = $(this).parent().parent().find('tr'+as).find('.pcLuuDong').val();
//		var CPKG = $(this).parent().parent().find('tr'+as).find('.ketQuaCPKG').text();
//		var baoHiem = $(this).parent().parent().find('tr'+as).find('.ketQuaBaoHiem').text();
//		$(this).text((parseInt(luongCoBan)+parseInt(luongTangThem)+parseInt(luongPhu)+parseInt(pcKhuVuc)+parseInt(pcLuuDong)+parseInt(CPKG)+parseInt(baoHiem))*1);
//	});
	
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