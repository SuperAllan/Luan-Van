var temp = null;
$(document).ready(function(){
		$('#scrollPhiChucNang').perfectScrollbar();
		countSTTPhiChucNang();
		$('.listPhiChucNang').on('change', function(){
			$('#submitPhiChucNang').prop("disabled", false);
			$('#submitPhiChucNang').removeClass('btn-primary');
			$('#submitPhiChucNang').addClass('btn-danger');
			$('#submitPhiChucNang').text('Chưa cập nhật');
		});
	});

function addPhiChucNang(){
	$('#submitPhiChucNang').prop("disabled", false);
	$('#submitPhiChucNang').removeClass('btn-primary');
	$('#submitPhiChucNang').addClass('btn-danger');
	$('#submitPhiChucNang').text('Chưa cập nhật');
	
	$('#list-phi-chuc-nang').prepend("<tr>"+
			"<td class='sttPhiChucNang'></td>"+
			"<td><textarea name='listPhiChucNang' id='newPhi' class='form-control listPhiChucNang' rows='1' onkeyup='textAreaAdjust(this)' onfocus='updateTempFocus(this)' onchange='ajaxPhiChucNang(this)' required='required'></textarea></td>"+
			"<td><a class='text-danger' onclick='xoaPhiChucNangTemp(this)'><i class='glyphicon glyphicon-remove' title='Xóa yêu cầu'></i> Xóa yêu cầu</a></td>"+
		"</tr>");
	$('#newPhi').focus();
}

function updateTempFocus(bienThis){
	temp = $(bienThis).val();
}

function xoaPhiChucNangTemp(bienThis){
	$(bienThis).parent().parent().remove();
	countSTTPhiChucNang();
}

function ajaxShowAllPhiChucNang(){
	$.ajax({
		type: 'get',
		url: 'showPhiChucNang',
		data: {
			tenProject: $('#nameProject').val()
		},
		success: function(data) {
			$('#list-phi-chuc-nang').html(data);
			countSTTPhiChucNang();
		},
		error: function() {
			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
		}
	});
}

function ajaxPhiChucNang(bienThis){
	var check = true;
	var count = 0;
	var value = $(bienThis).val();
	var parentID = null;
	if(value == null || value == ' ' || value == ''){
		alert("Yêu cầu phi chức năng phải khác rỗng.");
		$(bienThis).val(temp);
		$(bienThis).focus();
		check = false;
	}else{
		$('.listPhiChucNang').each(function(){
			if($(this).val() == value){
				count++;
			}
		});
		if(count > 1){
			alert("Yêu cầu phi chức này đã tồn tại.");
			$(bienThis).val(temp);
			$(bienThis).focus();
			check = false;
		}
	}
	if($(bienThis).parent().parent().attr('id') != null ){
		parentID = $(bienThis).parent().parent().attr('id');
		parentID = parentID.replace('phiCN','');
	}
	if(check == true){
		$.ajax({
			type: 'get',
			url: 'themPhiChucNang',
			dataType: 'json',
			data: {
				parentID: parentID,
				phiChucNang: value,
				tenProject: $('#nameProject').val()
			},
			success: function(parsedJson) {
				$(bienThis).parent().parent().replaceWith("<tr id='phiCN"+parsedJson.mayeucau+"'>"+
						"<td class='sttPhiChucNang'></td>"+
						"<td><textarea name='listPhiChucNang' class='form-control listPhiChucNang' rows='1' onfocus='updateTempFocus(this)' onchange='ajaxPhiChucNang(this)'  required='required'>"+parsedJson.motayeucau+"</textarea></td>"+
						"<td><a class='text-danger' onclick='xoaPhiChucNang("+parsedJson.mayeucau+")'><i class='glyphicon glyphicon-remove' title='Xóa yêu cầu'></i> Xóa yêu cầu</a></td>"+
						"</tr>");
				countSTTPhiChucNang();
			},
			error: function() {
				alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
			}
		});
	}
}

function xoaPhiChucNang(id){
	var isConfirm = confirm("Bạn có chắc muốn xóa không?");
	if(isConfirm == true){
		$.ajax({
			type: 'get',
			url: 'xoaPhiChucNang',
			data: {
				phiChucNangID: id
			},
			success: function(data) {
				$('#phiCN'+id).remove();
				countSTTPhiChucNang();
			},
			error: function() {
				alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
			}
		});
	}
}

function countSTTPhiChucNang(){
	var count = 1;
	$('.sttPhiChucNang').each(function(){
		$(this).text(count);
		count++;
	});
}
	