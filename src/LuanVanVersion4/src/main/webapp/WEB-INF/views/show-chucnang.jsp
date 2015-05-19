<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${listNhomChucNang}" var="listData">
		 <tr id="Nhom${listData.nhomid}" style="background: #e2e2e2;">
		 	<td colspan="5">
		 		<input type="text" name="nhomChucNang"  class="form-control-bs nhomChucNang" style="width: 100%;" required value="${listData.tennhom}" onfocus="setTempFocus(this)" onchange="ajaxNhomChucNang(this)" placeholder="Nhập tên nhóm" maxlength="255">
		 	</td>
		 	<td> 
			 	<a class="text-success" title="Thêm chức năng" style=" padding-right: 5px;" onclick='themChucNang(${listData.nhomid})'><small><i class="glyphicon glyphicon-plus"></i> Thêm chức năng</small></a>
			 	<a class="text-danger" title='Xóa nhóm' style=" padding-left: 0px;" onclick='xoaNhom(${listData.nhomid})'><small ><i class="glyphicon glyphicon-remove"></i> Xóa nhóm</small></a>
		 	</td>
		 </tr>
		 	<c:forEach items="${listData.chucnangs}" var="listData2">
			 <tr id="Chuc${listData2.mayeucau}" class="classOfNhomChucNang${listData.nhomid}">
			 	<td class="text-center countSTT"></td>
			 	<td><textarea name="listChucNang" rows="1" class="form-control chucNang" required onkeyup="textAreaAdjust(this)" onfocus="setTempFocus(this)" onchange="ajaxChucNang(this, ${listData.nhomid})" maxlength="255">${listData2.motayeucau}</textarea></td>
			 	<td>
					<select class="form-control" name="listPhanLoai" onchange="updatePhanLoai(this.value, ${listData2.mayeucau})">
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
					<select class="form-control" name="listMucDo" onchange="updateMucDo(this.value, ${listData2.mayeucau})">
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
			 		<textarea class="form-control listGhiChu" rows="1" name="listGhiChu" placeholder="Ghi chú" onkeyup="textAreaAdjust(this)" onchange="updateGhiChu(this.value, ${listData2.mayeucau})">${listData2.ghichu }</textarea>
			 	</td>
			 	<td><a class='text-danger'  title='Xóa chức năng' onclick="xoaChucNang(${listData2.mayeucau})"><small><i class="glyphicon glyphicon-remove"></i> Xóa chức năng</small></a></td>
			 </tr>
			 </c:forEach>
</c:forEach>