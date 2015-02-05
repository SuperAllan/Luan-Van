<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
 .scroll { position:relative; height: 400px; overflow: hidden; width: 100%;}
</style>
<h3 style="text-align: center;">BẢNG SẮP XẾP THỨ TỰ ƯU TIÊN CÁC YÊU CẦU 
CHỨC NĂNG CỦA PHẦN MỀM</h3>
<form action="/luanvan/updateFeatures" role="form"  method="GET" >
	<input type="hidden" name="tenProject" value="${project.tenproject}">
	<div class="scroll">
		<table class="table table-hover table-stripped" id="listChucNang">
			<thead>
				<tr>
					<th></th>
					<th>Mô tả yêu cầu</th>
					<th>Phân loại</th>
					<th>Mức độ</th>
					<th>Ghi chú</th>
					<th><a class="btn btn-link btn-block" onclick="themNhom()"><i class="glyphicon glyphicon-plus" title="Thêm nhóm"></i></a>
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
					 		<input name="nhomChucNang" class="form-control" required value="${listData.tennhom}">
					 		<input type="hidden" name="soLuongChucNang"  class="countNhomChucNangFromData">
					 	</td>
					 	<td><a class="btn btn-link btn-block loadFromData" title="Thêm chức năng">+</a></td>
					 	<td><a class='btn btn-link btn-block xoaNhomFromData' title='Xóa chức năng'>x</a></td>
					 </tr>
					 	<c:forEach items="${listData.chucnangs}" var="listData2">
						 <tr>
						 	<td></td>
						 	<td><input name="listChucNang" class="form-control" required value="${listData2.motayeucau}"></td>
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
						 		<textarea class="form-control listGhiChu" rows="1" name="listGhiChu">${listData2.ghichu }</textarea>
						 	</td>
						 	<td><a class='btn btn-link btn-block removeFunctionFromData '  title='Xóa chức năng'>x</a></td>
						 </tr>
						 </c:forEach>
				</tbody>
			</c:forEach>
		</table>
	</div>
		<button type="submit" class="btn btn-primary" style="margin-top: 10px;" >Cập nhật</button>
		<div>${UpdateChucNangSuccess}</div>
</form>
<script src="<c:url value="/resources/js/yeucau-chucnang.js" />" ></script>
