$(document).ready(function(){
	$('#getGiaTri').text($('#dinhGiaG').text());
	
	var dinhGia = numeral().unformat($('#dinhGiaG').text());
	//dinhGia = dinhGia.replace(/,/g,"");
	$('#chiPhiChung').text(parseInt(parseInt(dinhGia) * 0.65));
	
	var chiPhiChung = numeral().unformat($('#chiPhiChung').text());
	//chiPhiChung = chiPhiChung.replace(/,/g,"");
	$('#thuThapChiuThue').text( parseInt( (parseInt(dinhGia) + parseInt(chiPhiChung))* 0.06) );
	var thuThap = numeral().unformat($('#thuThapChiuThue').text());
	//thuThap = thuThap.replace(/,/g,"");
	$('#chiPhiPhanMem').text( parseInt(parseInt(dinhGia) + parseInt(chiPhiChung) + parseInt(thuThap)) );
	
	$('#tongCong').text($('#chiPhiPhanMem').text());
	
	$('.formatKetQua').each(function(){
		var string = numeral($(this).text()).format("0,0");
		$(this).text(string);
	});
	
	$('.formatKetQuaDecimal').each(function(){
		var string = numeral($(this).text()).format("0,0");
		$(this).text(string);
	});
});