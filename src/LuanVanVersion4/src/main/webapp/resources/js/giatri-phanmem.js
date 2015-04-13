$(document).ready(function(){
	$('#noLucE').text( $('#selectNoLuc').val() * parseFloat($('#AUCP').text()) );
	
	$('#chooseLuongFromBangLuong').text($("#CP1GioForBacLuong"+$('input[name=chooseBacLuong]:checked').val()).val());
	
	$('input[name=chooseBacLuong]').change(function(){
		$('#chooseLuongFromBangLuong').text($("#CP1GioForBacLuong"+$('input[name=chooseBacLuong]:checked').val()).val());
		var binhQuan = numeral().unformat($('#chooseLuongFromBangLuong').text());
		//binhQuan = binhQuan.replace(",","");
		$('#dinhGiaG').text(parseInt(parseFloat($('#noiSuyP').text()) * parseFloat($('#noLucE').text()) * parseInt(binhQuan) * 1.4));
		
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
			var string = numeral($(this).text()).format("0,0.[00000]");
			$(this).text(string);
		});
	});
	$('#dinhGiaG').text(parseInt(parseFloat($('#noiSuyP').text()) * parseFloat($('#noLucE').text()) * parseInt($('#chooseLuongFromBangLuong').text()) * 1.4));
	
	$('#selectNoLuc').change(function(){
		$('#noLucE').text( $(this).val() * parseFloat($('#AUCP').text()));
		var binhQuan = numeral().unformat($('#chooseLuongFromBangLuong').text());
		//binhQuan = binhQuan.replace(",","");
		$('#dinhGiaG').text(parseInt(parseFloat($('#noiSuyP').text()) * parseFloat($('#noLucE').text()) * parseInt(binhQuan) * 1.4));
		
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
			var string = numeral($(this).text()).format("0,0.[00000]");
			$(this).text(string);
		});
	});
	
	$('.formatKetQua').each(function(){
		var string = numeral($(this).text()).format("0,0.[00000]");
		$(this).text(string);
	});
});