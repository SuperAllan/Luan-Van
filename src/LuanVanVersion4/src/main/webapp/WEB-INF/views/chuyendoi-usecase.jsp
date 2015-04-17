<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
 .scroll { position:relative; height: 400px; overflow: hidden; width: 100%;}
 .tableChuyenDoi tbody tr td{
 	border: none;
 }
</style>
<h3 style="text-align: center;">BẢNG CHUYỂN ĐỔI YÊU CẦU CHỨC NĂNG SANG TRƯỜNG HỢP SỬ DỤNG (USE-CASE)</h3>
<form action="/luanvan/updateChuyenDoiUsecase" role="form"  method="GET">
	<input type="hidden" name="tenProject" value="${project.tenproject}">
	<div class="scrollBar" style="height: 400px; overflow: hidden; position: relative; width: 100%;">
		<table class="table table-hover tableChuyenDoi">
			<thead>
				<tr>
					<th class="text-center">TT</th>
					<th class="text-center">Tên Use-case</th>
					<th class="text-center">Tác nhân chính</th>
					<th class="text-center">Tác nhân phụ</th>
					<th class="text-center">Mô tả Use-case</th>
					<th class="text-center">Tính tiền</th>
					<th class="text-center">Mức độ</th>
				</tr>
			</thead>
			<c:forEach items="${listNhomUC}" var="listData">
				<tbody>
					 <tr>
					 	<td colspan="7">
					 		<input type="hidden" name="listNhomUCId" value="${listData.nhomucid}" required="required">
					 		<input type="text" name="listNhomUCName" class="form-control-bs nhomUCName" onblur="checkNhomUCName(this)" value="${listData.tennhom}" required="required">
					 	</td>
					 </tr>
					 	<c:forEach items="${listData.usecases}" var="listData2">
						 <tr>
						 	<td class="countSTTForChuyenDoi text-center">
						 		
						 	</td>
						 	<td>
							 	<input type="hidden" name="listUCId" value="${listData2.usecaseid}">
							 	<textarea name="listUCName" rows="1" class="form-control" required >${listData2.nameofuc}</textarea>
							</td>
						 	<td>
								<c:forEach items="${listData2.phanloais}" var="listData3">
									<c:if test="${listData3.vaitro == 1}">
										<span>${listData3.actor.nameofactor}</span><br>
									</c:if>
								</c:forEach>
						 	</td>
						 	<td>
								<c:forEach items="${listData2.phanloais}" var="listData3">
									<c:if test="${listData3.vaitro == 0}">
										<span>${listData3.actor.nameofactor}</span><br>
									</c:if>
								</c:forEach>
							</td>
						 	<td>
						 		<textarea class="form-control" rows="1" name="listMoTaUC">${listData2.motauc}</textarea>
						 	</td>
						 	<td class="text-center">
						 		<select class="form-control text-center" name="listTinhTien" style="text-align: center;">
							 		<c:choose>
							 			<c:when test="${listData2.tinhtien == true}">
							 				<option value="true" selected>Có</option>
							 				<option value="false">Không</option>
							 			</c:when>
							 			<c:otherwise>
							 				<option value="true">Có</option>
							 				<option value="false" selected>Không</option>
							 			</c:otherwise>
							 		</c:choose>
						 		</select>
						 	</td>
						 	<td>
						 		<select class="form-control text-center" name="listBMT" style="text-align: center;">
									<c:forEach var="list" items="${bmts}">
										<c:choose>
									      <c:when test="${listData2.bmt.bmtid == list.bmtid}">
									      	<option value="${list.bmtid}" selected>${list.ten}</option>
									      </c:when>
									      <c:otherwise>
									      	<option value="${list.bmtid}">${list.ten}</option>
									      </c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
						 	</td>
						 </tr>
						 </c:forEach>
				</tbody>
			</c:forEach>
		</table>
	</div>
		<button type="submit" class="btn btn-primary" style="margin-top: 10px;" id="buttonChucNang" onclick="return checkData()">Cập nhật</button>
		
</form>
<script src="<c:url value="/resources/js/chuyendoi-usecase.js" />" ></script>
