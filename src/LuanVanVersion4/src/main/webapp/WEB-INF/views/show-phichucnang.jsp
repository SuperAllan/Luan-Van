<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach items="${listPhiChucNang}" var="list">
	<tr id="phiCN${list.mayeucau}">
		<td class="sttPhiChucNang"></td>
		<td><textarea name="listPhiChucNang" class="form-control listPhiChucNang" rows="1" onfocus="updateTempFocus(this)" onchange="ajaxPhiChucNang(this)"  required="required">${list.motayeucau}</textarea></td>
		<td><a class="text-danger" onclick="xoaPhiChucNang(${list.mayeucau})"><i class="glyphicon glyphicon-remove" title="Xóa yêu cầu"></i> Xóa yêu cầu</a></td>
	</tr>
</c:forEach>