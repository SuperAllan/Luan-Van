<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	 <tr id="Chuc${chucNang.mayeucau}" class="classOfNhomChucNang${chucNang.nhomchucnang.nhomid}">
	 	<td class="text-center countSTT"></td>
	 	<td><textarea name="listChucNang" rows="1" class="form-control chucNang" required onkeyup="textAreaAdjust(this)" onfocus="setTempFocus(this)" onchange="ajaxChucNang(this, ${chucNang.nhomchucnang.nhomid})" maxlength="255">${chucNang.motayeucau}</textarea></td>
	 	<td>
			<select class="form-control" name="listPhanLoai" onchange="updatePhanLoai(this.value, ${chucNang.mayeucau})">
				<c:forEach var="list" items="${listPhanLoai}">
					<c:choose>
				      <c:when test="${chucNang.phanloaichucnang.maloai == list.maloai}">
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
			<select class="form-control" name="listMucDo" onchange="updateMucDo(this.value, ${chucNang.mayeucau})">
				<c:forEach var="list" items="${listMucDo}">
					<c:choose>
				      <c:when test="${chucNang.mucdo.mucdoid == list.mucdoid}">
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
	 		<textarea class="form-control listGhiChu" rows="1" name="listGhiChu" placeholder="Ghi chú" onkeyup="textAreaAdjust(this)" onchange="updateGhiChu(this.value, ${chucNang.mayeucau})">${chucNang.ghichu}</textarea>
	 	</td>
	 	<td><a class='text-danger'  title='Xóa chức năng' onclick="xoaChucNang(${chucNang.mayeucau})"><small><i class="glyphicon glyphicon-remove"></i> Xóa chức năng</small></a></td>
	 </tr>

