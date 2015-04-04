<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(function(){
	$(".formatType"	).each(function(){
		if($(this).text().length > 18){
		var formatDescription = $.trim($(this).text()).substring(0,18).split(" ").join(" ") + "...";
		$(this).text(formatDescription);
		}
	});
	$('#buttonSubmit').attr('disabled','disabled');
	$('#listCauHoi').keyup(function(){
		$('#buttonSubmit').removeAttr('disabled');
	});
	$('#listCauHoi').change(function(){
		$('#buttonSubmit').removeAttr('disabled');
	});
	//this is the id of the form
	$("#buttonSubmit").click(function() {
		$("#buttonSubmit").attr('disabled','disabled');
	    $.ajax({
           type: 'get',
           url: 'updateCauHoi',
           data: {
        	   usecaseID: $('#usecaseID').val(),
        	   listCauHoi: $('#listCauHoi').val()
           },
           success: function(result){
        	   
           },
           error: function() {
   			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
   		   }
         });
	});

$('#file').change(function(){
	submitForm();
});


});
function showDialog(){
	$('#file').click();
}

function submitForm(){
	$('#buttonUploadFile').children().replaceWith("<img title='Please wait...' src='/luanvan/resources/img/ajax-loader.gif'>");
 	$('#buttonUploadFile').attr('disabled', 'disabled');
	var oMyForm = new FormData();
	oMyForm.append("file", file.files[0]);
	oMyForm.append("usecaseID", $('#usecaseID').val());
	$.ajax({
 	url: 'uploadFile',
 	type: 'post',
    data: oMyForm,
    dataType: 'text',
    processData: false,
    contentType: false,
    success: function(data){
    	$('#buttonUploadFile').children().replaceWith("<span>Choose File</span>");
    	$('#buttonUploadFile').prop("disabled", false);
    	$('#listFile').append(data);
    	$(".formatType"	).each(function(){
    		if($(this).text().length > 18){
    		var formatDescription = $.trim($(this).text()).substring(0,18).split(" ").join(" ") + "...";
    		$(this).text(formatDescription);
    		}
    	});
    },
    error: function(){
    }
  });
}

function deleteFile(bienThis){
	var isConfirm = confirm('Bạn có chắc muốn xóa file này?');
	if(isConfirm == true){
	 $.ajax({
		    url: 'deleteFile',
		    data: {
		    	fileid: $(bienThis).attr('id')
		    },
		    type: 'get',
		    success: function(){
		    	$(bienThis).parent().parent().remove();
		    },
		    error: function(){
		    }
		  });
	}
}


</script>




<legend>${usecase.nameofuc}</legend>
	<input type="hidden" id="usecaseID" name="usecaseID" value="${usecase.usecaseid}">
	<div class="form-group">
		<textarea id="listCauHoi" rows="17" cols="135" onchange="checkChangeValue()" name="listCauHoi" placeholder="Thu thập câu hỏi, ghi chú, các vấn đề liên quan tới khách hàng.">${usecase.cauhoi}</textarea>
	</div>
	<button type="submit" class="btn btn-primary btn-lg" id="buttonSubmit">Cập nhật</button>
			

<table class="table table-hover">
	<thead>
		<tr>
		<th>File Name</th>
		<th>File Size</th>
		<th>File Type</th>
		<th>Download</th>
		<th>Delete</th>
		</tr>
	</thead>
	<tbody id="listFile">
		<c:forEach items="${usecase.files}" var="list">
		<tr>
			<td class="text-left">${list.name}</td>
			<td>${list.size}</td>
			<td title="${list.type}">${list.type}</td>
			<td><a href="${contextPath}/downloadFile?fileid=${list.fileid}">Download</a></td>
			<td><a id="${list.fileid}" onclick="deleteFile(this)"><i class="glyphicon glyphicon-remove"></i></a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<div class="form-group">
	<input type="hidden" id="usecaseID" name="usecaseID" value="${usecase.usecaseid}">
	<button onclick="showDialog()" class="btn btn-primary text-center" id="buttonUploadFile" style="width: 145px; height: 36px;"><span>Choose File</span></button>
	<input type="file" name="file" id="file" style="display: none;">
</div>
