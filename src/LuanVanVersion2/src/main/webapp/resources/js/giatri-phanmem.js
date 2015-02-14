$(document).ready(function(){
	$('#noLucE').text( $('#selectNoLuc').val() * parseFloat($('#AUCP').text()) );
	
	$('#chooseLuongFromBangLuong').text($('input[name=chooseLuong]:checked').val());
	$('input[name=chooseLuong]').change(function(){
		$('#chooseLuongFromBangLuong').text($('input[name=chooseLuong]:checked').val());
		var binhQuan = $('#chooseLuongFromBangLuong').text();
		binhQuan = binhQuan.replace(",","");
		$('#dinhGiaG').text(parseInt(parseFloat($('#noiSuyP').text()) * parseFloat($('#noLucE').text()) * parseInt(binhQuan) * 1.4));
		
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
	$('#dinhGiaG').text(parseInt(parseFloat($('#noiSuyP').text()) * parseFloat($('#noLucE').text()) * parseInt($('#chooseLuongFromBangLuong').text()) * 1.4));
	
	$('#selectNoLuc').change(function(){
		$('#noLucE').text( $(this).val() * parseFloat($('#AUCP').text()));
		var binhQuan = $('#chooseLuongFromBangLuong').text();
		binhQuan = binhQuan.replace(",","");
		$('#dinhGiaG').text(parseInt(parseFloat($('#noiSuyP').text()) * parseFloat($('#noLucE').text()) * parseInt(binhQuan) * 1.4));
		
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
	var string = $('#dinhGiaG').text();
	var res  = string.split(",");
	
	$('.formatKetQua').each(function(){
		var string = numeral($(this).text()).format();
		$(this).text(string);
	});
});