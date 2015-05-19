$(document).ready(function(){
	STTForChuyenDoi();
	$('#submitChuyenDoi').attr('disabled', 'disabled');
	
	$("select[name='listTinhTien']").on('change', function(){
		$('#submitChuyenDoi').removeAttr('disabled');
	});
	
	$("select[name='listBMT']").on('change', function(){
		$('#submitChuyenDoi').removeAttr('disabled');
	});
	
});
function checkNhomUCName(bienThis){
	var dem = 0;
	var value = $(bienThis).val();
	 $('.nhomUCName').each(function(){
		 if(value != null && value == $(this).val()){
			 dem++;
			 if(dem > 1){
				alert('Tên nhóm Use-case đã tồn tại.');
    			$(bienThis).focus();
			 }
		 }
	 }); 
}

function checkUCName(bienThis){
	var dem = 0;
	var value = $(bienThis).val();
	 $('.UCName').each(function(){
		 if(value != null && value == $(this).val()){
			 dem++;
			 if(dem > 1){
				alert('Tên Use-case đã tồn tại.');
    			$(bienThis).focus();
			 }
		 }
	 }); 
}

function STTForChuyenDoi(){
	var count = 0;
	$('.countSTTForChuyenDoi').each(function(){
		count++;
		$(this).text(count);
	});
}

