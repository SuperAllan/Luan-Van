<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function addPhiChucNang(){
		$('#submitPhiChucNang').prop("disabled", false);
		$('#submitPhiChucNang').removeClass('btn-primary');
		$('#submitPhiChucNang').addClass('btn-danger');
		$('#submitPhiChucNang').text('Chưa cập nhật');
		
		$('#list-phi-chuc-nang').prepend("<tr>"+
				"<td class='sttPhiChucNang'></td>"+
				"<td><textarea name='listPhiChucNang' class='form-control listPhiChucNang' rows='1' onblur='checkPhiChucNang(this)' required='required'></textarea></td>"+
				"<td><a class='text-danger'  onclick='removePhiChucNang(this)'><i class='glyphicon glyphicon-remove' title='Xóa yêu cầu'></i> Xóa yêu cầu</a></td>"+
			"</tr>");
		countSTTPhiChucNang();
		
	}
	function removePhiChucNang(bienThis){
		$(bienThis).parent().parent().remove();
		countSTTPhiChucNang();
		$('#submitPhiChucNang').prop("disabled", false);
		$('#submitPhiChucNang').removeClass('btn-primary');
		$('#submitPhiChucNang').addClass('btn-danger');
		$('#submitPhiChucNang').text('Chưa cập nhật');
	}
	function checkPhiChucNang(bienThis){
		var count = 0;
		var value = $(bienThis).val();
		$('.listPhiChucNang').each(function(){
			if($(this).val() == value){
				count++;
			}
		});
		if(count > 1){
			alert("Yêu cầu phi chức này đã tồn tại.");
			$(bienThis).focus();
		}
	}
	
	function checkNull(){
		var count = 0;
		$('.listPhiChucNang').each(function(){
			count++;
		});
		if(count < 1){
			$('#submitPhiChucNang').prop("disabled", true);
			return false;
		}else{
			$('#submitPhiChucNang').prop("disabled", false);
			return true;
		}
		
	}
	function countSTTPhiChucNang(){
		var count = 1;
		$('.sttPhiChucNang').each(function(){
			$(this).text(count);
			count++;
		});
	}
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
</script>
<style>
	#tablePhiChucNang tbody tr td{
		border: none;
	}
</style>
<div class="form-group">
	<h3><span>Danh sách các yêu cầu phi chức năng:</span> </h3>
</div>
<div class="form-group">
	<form action="/luanvan/updatePhiChucNang" role="form" method="get">
		<input type="hidden" name="tenProject" value="${project.tenproject}">
		<div id="scrollPhiChucNang" style="height: 400px; overflow: hidden;">
			<table class="table" id="tablePhiChucNang" style="width: 100%;">
				<thead>
					<tr>
						<th style="width: 5%;">STT</th>
						<th class="text-center" style="width: 75%">Yêu cầu phi chức năng</th>
						<th style="width: 20%;"><a class="text-success" onclick="addPhiChucNang()"><i class="glyphicon glyphicon-plus-sign" title="Thêm yêu cầu"></i> Thêm yêu cầu</a></th>
					</tr>
				</thead>
				<tbody id="list-phi-chuc-nang">
					<c:forEach items="${listPhiChucNang}" var="list">
					<tr>
						<td class="sttPhiChucNang"></td>
						<td><textarea name="listPhiChucNang" class="form-control listPhiChucNang" rows="1" onblur="checkPhiChucNang(this)"  required="required">${list.motayeucau}</textarea></td>
						<td><a class="text-danger" onclick="removePhiChucNang(this)"><i class="glyphicon glyphicon-remove" title="Xóa yêu cầu"></i> Xóa yêu cầu</a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary" id="submitPhiChucNang" onclick="return checkNull()" disabled="disabled">Cập nhật</button>
		</div>
	</form>
</div>