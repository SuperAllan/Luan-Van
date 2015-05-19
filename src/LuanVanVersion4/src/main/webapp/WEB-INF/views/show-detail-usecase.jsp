<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/ckeditor/ckeditor.js" />" ></script>
<script src="<c:url value="/resources/ckeditor/config.js" />" ></script>
<script src="<c:url value="/resources/ckeditor/vi.js" />" ></script>
<script src="<c:url value="/resources/ckeditor/styles.js" />" ></script>
<link href="<c:url value="/resources/ckeditor/skins/moono/editor.css" />" rel="stylesheet">
<link href="<c:url value="/resources/ckeditor/contents.css" />" rel="stylesheet">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#buttonSubmit").attr('disabled', 'disabled');
	
	$('#file').on('change', function(){
		submitForm();
	});
	
	$("#buttonSubmit").click(function() {
	    CKEDITOR.instances[$('.listCauHoi').attr('id')].updateElement();
	    $.ajax({
           type: 'get',
           url: 'updateCauHoi',
           data: {
        	   usecaseID: $('#usecaseID').val(),
        	   listCauHoi:  CKEDITOR.instances[$('.listCauHoi').attr('id')].getData()
           },
           success: function(result){
        	   $("#buttonSubmit").attr('disabled', 'disabled');
           },
           error: function() {
   			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
   		   }
         });
	});
});

</script>

<legend>${usecase.nameofuc}</legend>
	<input type="hidden" id="usecaseID" name="usecaseID" value="${usecase.usecaseid}">
	<div class="form-group">
		<textarea id="listCauHoi${usecase.usecaseid}" class="listCauHoi" rows="17" style="width: 100%; resize: vertical;" name="listCauHoi" placeholder="Thu thập câu hỏi, ghi chú, các vấn đề liên quan tới khách hàng.">${usecase.cauhoi}</textarea>
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
	<button onclick="showDialog()" class="btn btn-primary text-center" id="buttonUploadFile" style="width: 145px; height: 36px;"><span>Chọn dữ liệu</span></button>
	<input type="file" name="file" id="file" style="display: none;">
</div>

