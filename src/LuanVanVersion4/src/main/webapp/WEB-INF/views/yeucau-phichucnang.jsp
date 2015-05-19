<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	#tablePhiChucNang tbody tr td{
		border: none;
	}
</style>

<div class="form-group">
	<h3 style="text-align: center;">CÁC YÊU CẦU PHI CHỨC NĂNG CỦA PHẦN MỀM</h3>
</div>
<div class="form-group">
	<input type="hidden" name="tenProject" value="${project.tenproject}">
	<div id="scrollPhiChucNang" class="scrollBar" style="height: 450px; overflow: hidden;">
		<table class="table" id="tablePhiChucNang" style="width: 100%;">
			<thead>
				<tr>
					<th style="width: 5%;">STT</th>
					<th class="text-center" style="width: 75%">Yêu cầu phi chức năng</th>
					<th style="width: 20%;"><a class="text-success" onclick="addPhiChucNang()"><i class="glyphicon glyphicon-plus-sign" title="Thêm yêu cầu"></i> Thêm yêu cầu</a></th>
				</tr>
			</thead>
			<tbody id="list-phi-chuc-nang">
			
			</tbody>
		</table>
	</div>
</div>
<script src="<c:url value="/resources/js/yeucau-phichucnang.js" />"></script>