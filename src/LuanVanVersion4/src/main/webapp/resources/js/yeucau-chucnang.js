//function loadData(){
////	document.location.href = "/luanvan/loadData";
//}

var number = 0;
var count = 0;

$(document).ready(function() {
	
    $('.countNhomChucNangFromData').each(function(){
    	count++;
    	$(this).attr("id","soLuongNhom"+count+"");
    	var bien = 0;
    	$(this).parent().parent().parent().attr("id","Nhom"+count+"");
    	$("#Nhom"+count+" tr").each(function(){
    		bien++;
    	});
    	$('#soLuongNhom'+count).attr("value", bien-1);
    });
    
	$('.loadFromData').on('click', function(){
    	themChucNang($(this).parent().parent().parent().attr("id"));
    });

	$('.removeFunctionFromData').on('click', function(){
		$("#soLuong"+$(this).parent().parent().parent().attr("id")).val(parseInt($("#soLuong"+$(this).parent().parent().parent().attr("id")).val())-1);
		$(this).parent().parent().remove();
	});
    
	$('.xoaNhomFromData').on('click',function(){
    	xoaNhom($(this).parent().parent().parent().attr("id"));
    });
    
	countSTT();
//    $('.listGhiChu').each(function(){
//    	if($(this).val() == null || $(this).val() == "" || $(this).length < 1){
//    		$(this).val("Ghi chú");
//    	}
//   	});
});  



function checkNameNhomChucNang(bienThis){
	var dem = 0;
	var value = $(bienThis).val();
	 $('.nhomChucNang').each(function(){
		 if(value != "" && value == $(this).val()){
			 dem++;
			 if(dem > 1){
				alert('Tên nhóm chức năng đã tồn tại.');
    			$(bienThis).focus();
			 }
		 }
	 });
}

function checkNameChucNang(bienThis){
	var dem = 0;
	var value = $(bienThis).val();
	 $('.chucNang').each(function(){
		 if(value != "" && value == $(this).val()){
			 dem++;
			 if(dem > 1){
				alert('Tên chức năng đã tồn tại.');
    			$(bienThis).focus();
			 }
		 }
	 });
}


function themNhom(){
	var listChucNang = $('#listChucNang');
	if(count > number){
		number = count + 1;
	}
	var nhom = "<tbody id='Nhom"+number+"'><tr class='well-material-green-100'><td colspan='5'><input name='nhomChucNang' type='text' class='form-control nhomChucNang well-material-green-100' onblur='checkNameNhomChucNang(this)' required='required' placeholder='Nhập tên nhóm' data-bv-notempty='true' data-bv-notempty-message='Tên nhóm khác rỗng' >"+
	"<input type='hidden' name='soLuongChucNang'  id='soLuongNhom"+number+"'></td><td><a class='btn btn-link btn-block' onclick='themChucNang(\"Nhom"+number+"\")' title='Thêm chức năng'>+</a></td><td><a class='btn btn-link btn-block' onclick='xoaNhom(\"Nhom"+number+"\")' title='Xóa nhóm'>x</a></td></tr></tbody>";
	
	listChucNang.prepend(nhom);
	$("#soLuongNhom"+number).val(0);
	number++;
	countSTT();
}

function xoaNhom(id){
	var isConfirm = confirm("Bạn có chắc muốn xóa nhóm này không? Các chức năng trong nhóm sẽ mất hết!");
	if(isConfirm == true){
		$("#"+id).remove();
	}
	countSTT();
}

/* Xu ly them phong */
function themChucNang(id) {
	var tr = "<tr>"+
			"<td class='countSTT'></td>"+
			"<td><textarea name='listChucNang' rows='1' class='form-control chucNang' onblur='checkNameChucNang(this)' required='required' placeholder='Mô tả chức năng' data-bv-notempty='true' data-bv-notempty-message='Mô tả khác rỗng'></textarea></td>"+
			"<td>"+
				
				"<select class='form-control' name='listPhanLoai'>";
	
				$("#selectPhanLoai > option").each(function() {
				tr += "<option value="+this.value+">"+this.text+"</option>";
				});
			tr+="</select>"+
			"</td>"+
			"<td>"+
				"<select class='form-control' name='listMucDo'>";
				$("#selectMucDo > option").each(function() {
				tr += "<option value="+this.value+">"+this.text+"</option>";
				});
			tr+="</select>"+
			"</td>"+
			"<td>"+
				"<textarea class='form-control listGhiChu' rows='1' name='listGhiChu' placeholder='Ghi chú'></textarea>"+
			"</td>"+
			"<td><a class='btn btn-link btn-block removeFunction' onclick='xoaChucNang(\""+id+"\",this)' title='Xóa chức năng'>x</a></td>"+
		"</tr>";
	//$("#"+id).after(tr);
	if($("#"+id).children('tr').length > 1){
		$("#"+id+" tr:first").after(tr);
	}else{
		$("#"+id).append(tr);
	}
	$("#soLuong"+id).val(parseInt($("#soLuong"+id).val())+1);
	countSTT();
}

function xoaChucNang(id, bienThis){
	$("#soLuong"+id).val(parseInt($("#soLuong"+id).val())-1);
	$(bienThis).parent().parent().remove();
	countSTT();
}

function checkData(){
 	temp = 0;
    $('.nhomChucNang').each(function(){
    	temp++;
    });
    if(temp < 1){
    	$('#buttonChucNang').prop("disabled", true);
    	return false;
    }else{
    	$('#buttonChucNang').prop("disabled", false);
    	return true;
    }
}

function countSTT(){
	var countSTT = 0;
	$('.countSTT').each(function(){
		countSTT++;
		$(this).text(countSTT);
	});
}
