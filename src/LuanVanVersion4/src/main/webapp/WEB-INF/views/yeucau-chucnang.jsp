<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
.nav-tabs>li.active>a,.nav-tabs>li.active>a:hover,.nav-tabs>li.active>a:focus{
	color: #fff !important;
	opacity: 1;
}
.nav-tabs>li>a, .nav-tabs>li>a:hover, .nav-tabs>li>a:focus{
	opacity: 0.7;
}
#listChucNang tr td {
	border: none;
}
</style>
<h3 style="text-align: center;">BẢNG SẮP XẾP THỨ TỰ ƯU TIÊN CÁC YÊU CẦU 
CHỨC NĂNG CỦA PHẦN MỀM</h3>

<form action="/luanvan/updateFeatures" role="form"  method="GET" id="formChucNang">
	<input type="hidden" name="tenProject" value="${project.tenproject}">
	<div class="scrollBar" style="height: 400px; overflow: hidden; position: relative; width: 100%;">
		<table class="table " id="listChucNang">
			<thead>
				<tr>
					<th>STT</th>
					<th>Mô tả yêu cầu</th>
					<th>Phân loại</th>
					<th>Mức độ</th>
					<th>Ghi chú</th>
					<th><a class="btn btn-link btn-block text-success" style="padding: 0px; margin: 0px;" onclick="themNhom(); checkData()"><i class="glyphicon glyphicon-plus-sign" title="Thêm nhóm"></i> Thêm nhóm</a>
					</th>
				</tr>
			</thead>
			
			<tbody >
				<tr style="display: none;">
					<td>
						<select class="form-control" id="selectPhanLoai">
						<c:forEach var="list" items="${listPhanLoai}">
						<option value="${list.maloai}">${list.tenloai}</option>
						</c:forEach>
						</select>
					</td>
					<td>
						<select class="form-control" id="selectMucDo">
						<c:forEach var="list" items="${listMucDo}">
						<option value="${list.mucdoid}">${list.motamucdo}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
			</tbody>
			
			<c:forEach items="${listNhomChucNangFromData}" var="listData">
				<tbody>
					 <tr>
					 	<td colspan="5">
					 		<input type="text" name="nhomChucNang" class="form-control-bs nhomChucNang" style="width: 710px;" required value="${listData.tennhom}" onblur="checkNameNhomChucNang(this)" placeholder="Nhập tên nhóm">
					 		<input type="hidden" name="soLuongChucNang"  class="countNhomChucNangFromData">
					 	</td>
					 	<td>
						 	<a class="btn btn-link btn-block loadFromData text-success" title="Thêm chức năng" style="display: inline; padding-right: 5px;"><small><i class="glyphicon glyphicon-plus"></i> Thêm chức năng</small></a>
						 	<a class="btn btn-link btn-block xoaNhomFromData text-danger" title='Xóa nhóm' style="display: inline; padding-left: 0px;"><small ><i class="glyphicon glyphicon-remove"></i> Xóa nhóm</small></a>
					 	</td>
					 </tr>
					 	<c:forEach items="${listData.chucnangs}" var="listData2">
						 <tr>
						 	<td class="countSTT"></td>
						 	<td><textarea name="listChucNang" rows="1" class="form-control chucNang" style="resize: vertical;" required onkeyup="textAreaAdjust(this)" onblur="checkNameChucNang(this)">${listData2.motayeucau}</textarea></td>
						 	<td>
								<select class="form-control" name="listPhanLoai">
									<c:forEach var="list" items="${listPhanLoai}">
										<c:choose>
									      <c:when test="${listData2.phanloaichucnang.maloai == list.maloai}">
									      	<option value="${list.maloai}" selected>${list.tenloai}</option>
									      </c:when>
									      <c:otherwise>
									      	<option value="${list.maloai}">${list.tenloai}</option>
									      </c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
						 	</td>
						 	<td>
								<select class="form-control" name="listMucDo">
									<c:forEach var="list" items="${listMucDo}">
										<c:choose>
									      <c:when test="${listData2.mucdo.mucdoid == list.mucdoid}">
									      	<option value="${list.mucdoid}" selected>${list.motamucdo}</option>
									      </c:when>
									      <c:otherwise>
									      	<option value="${list.mucdoid}">${list.motamucdo}</option>
									      </c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
						 	<td>
						 		<textarea class="form-control listGhiChu" rows="1" name="listGhiChu" placeholder="Ghi chú">${listData2.ghichu }</textarea>
						 	</td>
						 	<td><a class='btn btn-link btn-block removeFunctionFromData text-danger'  title='Xóa chức năng'><small><i class="glyphicon glyphicon-remove"></i> Xóa chức năng</small></a></td>
						 </tr>
						 </c:forEach>
				</tbody>
			</c:forEach>
		</table>
	</div>
		<button type="submit" class="btn btn-primary" style="margin-top: 10px;" id="buttonChucNang" onclick="return checkData()">Cập nhật</button>
	
</form>
	
<script src="<c:url value="/resources/js/yeucau-chucnang.js" />" ></script>
