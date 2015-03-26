$(document).ready(function(){
	$('#getGiaTri').text($('#dinhGiaG').text());
	
	var dinhGia = $('#dinhGiaG').text();
	dinhGia = dinhGia.replace(/,/g,"");
	$('#chiPhiChung').text(parseInt(parseInt(dinhGia) * 0.65));
	
	var chiPhiChung = $('#chiPhiChung').text();
	chiPhiChung = chiPhiChung.replace(/,/g,"");
	$('#thuThapChiuThue').text( parseInt( (parseInt(dinhGia) + parseInt(chiPhiChung))* 0.06) );
	var thuThap = $('#thuThapChiuThue').text();
	thuThap = thuThap.replace(/,/g,"");
	$('#chiPhiPhanMem').text( parseInt(parseInt(dinhGia) + parseInt(chiPhiChung) + parseInt(thuThap)) );
	
	$('#tongCong').text($('#chiPhiPhanMem').text());
	
	$('.formatKetQua').each(function(){
		var string = numeral($(this).text()).format();
		$(this).text(string);
	});
});