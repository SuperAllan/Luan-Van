$(document).ready(function(){
	$('#selectLuongNhaNuoc').change(function(){
		if($(this).val() != "---"){
			$('#luongNhaNuoc').val($(this).val());
		}
	});
	
	$('.formatKetQua').each(function(){
		var string = numeral($(this).text()).format('0,0');
		$(this).text(string);
	});
});