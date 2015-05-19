
var number = 0;
var count = 0;
var tempFocus = null;
$(document).ready(function() {
	countSTT();
});  

function setTempFocus(bienThis){
	tempFocus = $(bienThis).val();
}

function ajaxNhomChucNang(bienThis){
	var dem = 0;
	var check = true;
	var parentID = null;
	var value = $(bienThis).val();
	if(value == "" || value == " " || value == null){
		alert("Tên nhóm chức năng phải khác rỗng.");
		$(bienThis).val(tempFocus);
		$(bienThis).focus();
		check = false;
	}else{
	if( $(bienThis).parent().parent().attr('id') != null ){
		parentID = $(bienThis).parent().parent().attr('id');
		parentID = parentID.replace('Nhom','');
	}
	$('.nhomChucNang').each(function(){
		 if(value == $(this).val()){
			 dem++;
			 if(dem > 1){
				alert('Tên nhóm chức năng đã tồn tại.');
					$(bienThis).val(tempFocus);
	    			$(bienThis).focus();
	    			check = false;
				 }
			 }
		 });
	}
	if(check == true){
		$.ajax({
			type: 'get',
			url: 'themNhomChucNang',
			data: {
				parentID: parentID,
				nhomChucNang: value,
				tenProject: $('#nameProject').val()
			},
			success: function(data) {
				var obj = $.parseJSON(data);
				$(bienThis).parent().parent().replaceWith("<tr id='Nhom"+obj.nhomid+"' style='background: #e2e2e2;'>"+
														 	"<td colspan='5'>"+
														 		"<input type='text' name='nhomChucNang' class='form-control-bs nhomChucNang' style='width: 100%;' required value='"+obj.tennhom+"' onfocus='setTempFocus(this)' onchange='ajaxNhomChucNang(this)' placeholder='Nhập tên nhóm' maxlength='255'>"+
														 	"</td>"+
														 	"<td>"+ 
															 	"<a class='text-success' title='Thêm chức năng' style='padding-right: 5px;' onclick='themChucNang("+obj.nhomid+")'><small><i class='glyphicon glyphicon-plus'></i> Thêm chức năng</small></a>"+
															 	"<a class='text-danger' title='Xóa nhóm' style='padding-left: 0px;' onclick='xoaNhom("+obj.nhomid+")'><small ><i class='glyphicon glyphicon-remove'></i> Xóa nhóm</small></a>"+
														 	"</td>"+
														 "</tr>");
				countSTT();
			},
			error: function() {
				alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
			}
		});
	}
}

function ajaxShowAllChucNang(){
	$('#listChucNang').html("<h3>Đang tải...</h3>");
	$.ajax({
		type: 'get',
		url: 'showAllChucNang',
		data: {
			tenProject: $('#nameProject').val()
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
	if(value == "" || value == " " || value == null){
		alert("Tên chức năng phải khác rỗng.");
		$(bienThis).val(tempFocus);
		$(bienThis).focus();
		check = false;
	}else{
		$('.chucNang').each(function(){
			 if(value == $(this).val()){
				 dem++;
				 if(dem > 1){
					alert('Tên chức năng đã tồn tại.');
					check = false;
					$(bienThis).val(tempFocus);
	    			$(bienThis).focus();
				 }
			 }
		 });
	}
	if($(bienThis).parent().parent().attr('id') != null){
		parentID = $(bienThis).parent().parent().attr('id');
		parentID = parentID.replace('Chuc','');
	}
	if(check == true){
		$.ajax({
			type: 'get',
			url: 'themChucNang',
			data: {
				parentID: parentID,
				chucNang: value,
				nhomChucNangID: nhomID
			},
			success: function(data) {
				$(bienThis).parent().parent().replaceWith(data);
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
			"<td colspan='5'><input name='nhomChucNang' id='newNhom' type='text' class='form-control-bs nhomChucNang' onfocus='setTempFocus(this)' onchange='ajaxNhomChucNang(this)' required='required' placeholder='Nhập tên nhóm' maxlength='255'></td>" +
			"<td>" +
				"<a class='text-danger' title='Xóa nhóm' style='padding-left: 0px;' onclick='xoaNhomTemp(this)'><small ><i class='glyphicon glyphicon-remove'></i> Xóa nhóm</small></a>" +
			"</td>" +
			"</tr>";
	listChucNang.prepend(nhom);
	focusID('newNhom');
	//countSTT();
	
}


/* Xu ly them chuc nang */
function themChucNang(id) {
	var tr = "<tr class='newChuc"+id+" classOfNhomChucNang"+id+"'>"+
			"<td class='text-center countSTT'></td>"+
			"<td><textarea name='listChucNang' id='newChuc' rows='1'  class='form-control chucNang' onkeyup='textAreaAdjust(this)' onfocus='setTempFocus(this)' onchange='ajaxChucNang(this,"+id+")' required='required' placeholder='Mô tả chức năng' maxlength='255'></textarea></td>"+
			"<td>"+
				"<select class='form-control' name='listPhanLoai' disabled='disabled'></select>"+
			"</td>"+
			"<td>"+
				"<select class='form-control' name='listMucDo' disabled='disabled'></select>"+
			"</td>"+
			"<td>"+
				"<textarea class='form-control listGhiChu' rows='1' name='listGhiChu' placeholder='Ghi chú' disabled='disabled'></textarea>"+
			"</td>"+
			"<td><a class='text-danger' title='Xóa chức năng' onclick='xoaChucNangTemp(this)'><small><i class='glyphicon glyphicon-remove'></i> Xóa chức năng</small></a></td>"+
		"</tr>";
	$('#Nhom'+id).after(tr);
	focusID('newChuc');
	//countSTT();
}
function xoaNhomTemp(bienThis){
	$(bienThis).parent().parent().remove();
}

function xoaNhom(id){
	var count  = 0;
	var run = 0;
	$('.classOfNhomChucNang'+id).each(function(){
		count++;
	});
	if(count > 0){
		var isConfirm = confirm("Bạn có chắc muốn xóa nhóm này không? Các chức năng trong nhóm sẽ mất hết!");
		run = 1;
	}
	if(isConfirm == true || run == 0){
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
				$(".newChuc"+id).each(function(){
					$(this).remove();
				});
				$("#Nhom"+id).remove();
				countSTT();
			},
			error: function() {
				alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
			}
		});
	}
}

function xoaChucNangTemp(bienThis){
	$(bienThis).parent().parent().remove();
}

function xoaChucNang(id){
	var isConfirm = confirm("Bạn có chắc muốn xóa chức năng này không?");
	if(isConfirm == true){
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
