//function loadData(){
////	document.location.href = "/luanvan/loadData";
//}

var number = 0;
var count = 0;

$(document).ready(function() {
	countSTT();
});  

function ajaxNhomChucNang(bienThis){
	var dem = 0;
	var check = true;
	var value = $(bienThis).val();
	var parentID = null;
	if( $(bienThis).parent().parent().attr('id') != null ){
		parentID = $(bienThis).parent().parent().attr('id');
		parentID = parentID.replace('Nhom','');
	}
	if(value == "" || value == " " || value == null || value == 'null'){
		alert("Tên nhóm chức năng phải khác rỗng.");
		check = false;
		$(bienThis).focus();
	}else{
		$('.nhomChucNang').each(function(){
			 if(value == $(this).val()){
				 dem++;
				 if(dem > 1){
					alert('Tên nhóm chức năng đã tồn tại.');
					check = false;
	    			$(bienThis).focus();
				 }
			 }
		 });
	}
	if(check == true){
		onloadChucNang(parentID, value);
	}
	
}
function onloadChucNang(parentID, nhom){
	$('#listChucNang').html("<h3>Đang tải...</h3>");
	$.ajax({
		type: 'get',
		url: 'themNhomChucNang',
		data: {
			parentID: parentID,
			nhomChucNang: nhom,
			tenProject: $('#tenProjectForChucNang').val()
		},
		success: function(data) {
			$('#listChucNang').html(data);
			countSTT();
		},
		error: function() {
			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
		}
	});
}

function ajaxChucNang(bienThis, nhomID){
	var dem = 0;
	var check = true;
	var parentID = null;
	var value = $(bienThis).val();
	if($(bienThis).parent().parent().attr('id') != null){
		parentID = $(bienThis).parent().parent().attr('id');
		parentID = parentID.replace('Chuc','');
	}
	if(value == "" || value == " " || value == null){
		alert("Tên chức năng phải khác rỗng.");
		check = false;
		$(bienThis).focus();
	}else{
		$('.chucNang').each(function(){
			 if(value == $(this).val()){
				 dem++;
				 if(dem > 1){
					alert('Tên chức năng đã tồn tại.');
					check = false;
	    			$(bienThis).focus();
				 }
			 }
		 });
	}
	if(check == true){
		$('#listChucNang').html("<h3>Đang tải...</h3>");
		$.ajax({
			type: 'get',
			url: 'themChucNang',
			data: {
				parentID: parentID,
				chucNang: value,
				nhomChucNangID: nhomID,
				tenProject: $('#tenProjectForChucNang').val()
			},
			success: function(data) {
				$('#listChucNang').html(data);
				countSTT();
			},
			error: function() {
				alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
			}
		});
		
	}
	
}

function updatePhanLoai(value, chucNangID){
	$.ajax({
		type: 'get',
		url: 'updatePhanLoai',
		data: {
			phanLoai: value,
			chucNangID: chucNangID
		},
		success: function(data) {
			countSTT();
		},
		error: function() {
			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
		}
	});
}

function updateMucDo(value, chucNangID){
	$.ajax({
		type: 'get',
		url: 'updateMucDo',
		data: {
			mucDo: value,
			chucNangID: chucNangID
		},
		success: function(data) {
		},
		error: function() {
			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
		}
	});
}

function updateGhiChu(value, chucNangID){
	$.ajax({
		type: 'get',
		url: 'updateGhiChu',
		data: {
			ghiChu: value,
			chucNangID: chucNangID
		},
		success: function(data) {
		},
		error: function() {
			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
		}
	});
}
 
function themNhom(){
	var listChucNang = $('#listChucNang');
	var nhom = "<tr>" +
			"<td colspan='5'><input name='nhomChucNang' id='newNhom' type='text' class='form-control-bs nhomChucNang' onblur='ajaxNhomChucNang(this)' required='required' placeholder='Nhập tên nhóm'></td>" +
			"<td>" +
				"<a class='text-success' title='Thêm chức năng' style='padding-right: 5px; pointer-events: none;'><small><i class='glyphicon glyphicon-plus'></i> Thêm chức năng</small></a>" +
				"<a class='text-danger' title='Xóa nhóm' style='padding-left: 0px; pointer-events: none;'><small ><i class='glyphicon glyphicon-remove'></i> Xóa nhóm</small></a>" +
			"</td>" +
			"</tr>";
	listChucNang.prepend(nhom);
	countSTT();
	focusID('newNhom');
}


/* Xu ly them chuc nang */
function themChucNang(id) {
	var tr = "<tr>"+
			"<td class='text-center countSTT'></td>"+
			"<td><textarea name='listChucNang' id='newChuc' rows='1'  class='form-control chucNang' onkeyup='textAreaAdjust(this)'  onblur='ajaxChucNang(this,"+id+")' required='required' placeholder='Mô tả chức năng' data-bv-notempty='true' data-bv-notempty-message='Mô tả khác rỗng'></textarea></td>"+
			"<td>"+
				"<select class='form-control' name='listPhanLoai' disabled='disabled'></select>"+
			"</td>"+
			"<td>"+
				"<select class='form-control' name='listMucDo' disabled='disabled'></select>"+
			"</td>"+
			"<td>"+
				"<textarea class='form-control listGhiChu' rows='1' name='listGhiChu' placeholder='Ghi chú' disabled='disabled'></textarea>"+
			"</td>"+
			"<td><a class='text-danger' title='Xóa chức năng' style='pointer-events: none;'><small><i class='glyphicon glyphicon-remove'></i> Xóa chức năng</small></a></td>"+
		"</tr>";
	$('#Nhom'+id).after(tr);
	countSTT();
	focusID('newChuc');
}

function xoaNhom(id){
	var isConfirm = confirm("Bạn có chắc muốn xóa nhóm này không? Các chức năng trong nhóm sẽ mất hết!");
	if(isConfirm == true){
		$("#"+id).remove();
		$.ajax({
			type: 'get',
			url: 'xoaNhomChucNang',
			data: {
				nhomChucNangID: id
			},
			success: function(data) {
				var object = $.parseJSON(data);
				for (var i = 0; i < object.chucnangs.length; i++) {
					$("#Chuc"+ object.chucnangs[i].value).remove();
				}
				$("#Nhom"+id).remove();
				countSTT();
			},
			error: function() {
				alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
			}
		});
	}
}


function xoaChucNang(id){
	
	//$('#listChucNang').html("<h3>Đang tải...</h3>");
	$.ajax({
		type: 'get',
		url: 'xoaChucNang',
		data: {
			chucNangID: id
		},
		success: function(data) {
			if(data == "OKOKOK"){
				$("#Chuc"+id).remove();
				countSTT();
			}
		},
		error: function() {
			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
		}
	});
	countSTT();
}


function countSTT(){
	var countSTT = 0;
	$('.countSTT').each(function(){
		countSTT++;
		$(this).text(countSTT);
	});
}
function focusID(id){
	$('#'+id).focus();
}
