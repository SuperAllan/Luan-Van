<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form action="/luanvan/updateCauHoi" method="get" role="form" id="form-update-question">
		<legend>${usecase.nameofuc}</legend>
			<input type="hidden" name="usecaseID" value="${usecase.usecaseid}">
			<div class="form-group">
				<textarea id="listCauHoi" rows="17" cols="135" name="listCauHoi" placeholder="Thu thập câu hỏi, ghi chú, các vấn đề liên quan tới khách hàng.">${usecase.cauhoi}</textarea>
			</div>
			<button type="submit" class="btn btn-primary btn-lg">Cập nhật</button>
</form>
			
<form action="/luanvan/uploadFile" method="get" role="form" enctype="multipart/form-data">
	<div class="form-group">
		<label class="col-md-4 control-label">Danh sách các File:</label>
	</div>
	<div class="form-group">
	    <div style="position:relative;">
			<a class='btn btn-primary' href='javascript:;'>
				Choose File...
				<input type="file" style='position:absolute;z-index:2;top:0;left:0;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' name="file_source" size="40"  onchange='$("#upload-file-info").html($(this).val());'>
			</a>
			<span class='label label-info' id="upload-file-info"></span>
		</div>
	</div>
</form>