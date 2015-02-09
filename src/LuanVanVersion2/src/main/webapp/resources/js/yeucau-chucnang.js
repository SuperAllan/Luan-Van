//function loadData(){
////	document.location.href = "/luanvan/loadData";
//}
var number = 0;
var count = 0;
$(document).ready(function() {
	//$('.scrollBar').perfectScrollbar();
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
    
    $('.loadFromData').each(function(){
    	$(this).click(function(){
        	themChucNang($(this).parent().parent().parent().attr("id"));
        });
    });
    
    $('.removeFunctionFromData').each(function(){
    	$(this).click(function(){
    		$("#soLuong"+$(this).parent().parent().parent().attr("id")).val(parseInt($("#soLuong"+$(this).parent().parent().parent().attr("id")).val())-1);
			$(this).parent().parent().remove();
    	});
    });
    
    $('.xoaNhomFromData').each(function(){
    	$(this).click(function(){
        	xoaNhom($(this).parent().parent().parent().attr("id"));
        });
    });
    
    $('.listGhiChu').each(function(){
    	if($(this).val() == null || $(this).val() == "" || $(this).length < 1){
    		$(this).val("Ghi chú");
    	}
   	});
   
});  


function themNhom(){
	var listChucNang = $('#listChucNang');
	if(count > number){
		number = count + 1;
	}
	var nhom = "<tbody id='Nhom"+number+"'><tr><td colspan='5'><input name='nhomChucNang' type='text' class='form-control nhomChucNang' required='required' placeholder='Nhập tên nhóm' data-bv-notempty='true' data-bv-notempty-message='Tên nhóm khác rỗng' >"+
	"<input type='hidden' name='soLuongChucNang'  id='soLuongNhom"+number+"'></td><td><a class='btn btn-link btn-block' onclick='themChucNang(\"Nhom"+number+"\")' title='Thêm chức năng'>+</a></td><td><a class='btn btn-link btn-block' onclick='xoaNhom(\"Nhom"+number+"\")' title='Xóa chức năng'>x</a></td></tr></tbody>";
	
	listChucNang.append(nhom);
	$("#soLuongNhom"+number).val(0);
	number++;
	
}

function xoaNhom(id){
	var isConfirm = confirm("Bạn có chắc muốn xóa nhóm này không? Các chức năng trong nhóm sẽ mất hết!");
	if(isConfirm == true){
		$("#"+id).remove();
		return true;
	}else{
		return false;
	}
}

/* Xu ly them phong */
function themChucNang(id) {
	var tr = "<tr>"+
			"<td></td>"+
			"<td><input type='text' name='listChucNang' class='form-control' required='required' placeholder='Mô tả chức năng' data-bv-notempty='true' data-bv-notempty-message='Mô tả khác rỗng'></td>"+
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
				"<textarea class='form-control listGhiChu' rows='1' name='listGhiChu'>Ghi chú</textarea>"+
			"</td>"+
			"<td><a class='btn btn-link btn-block removeFunction' onclick='xoaChucNang(\""+id+"\",this)' title='Xóa chức năng'>x</a></td>"+
		"</tr>";
	$("#"+id).append(tr);
	$("#soLuong"+id).val(parseInt($("#soLuong"+id).val())+1);
}

function xoaChucNang(id,bienThis){
	$("#soLuong"+id).val(parseInt($("#soLuong"+id).val())-1);
	$(bienThis).parent().parent().remove();
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
