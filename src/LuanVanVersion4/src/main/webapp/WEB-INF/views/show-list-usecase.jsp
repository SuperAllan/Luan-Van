<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default">
	<c:if test="${not empty resultNull}">
		<div class="text-danger list-group-item text-center">${resultNull}</div>
	</c:if>
	<c:forEach items="${resultNhomUC}" var="list">
	<div style="background: #ececec; padding: 6px;" class="col-md-12">
	    <div class="col-md-11">${list.tennhom} </div>
	    <div class="col-md-1 badge" style="margin-top: auto;">${fn:length(list.usecases)}</div>
	</div>
	   <c:forEach items="${list.usecases}" var="list2">
		  	<a class="list-group-item" onclick="showDetailUsecase(${list2.usecaseid})" style="clear: both;">
		  		<label class="text-danger">${list2.nameofuc}</label>
		  	</a>
		</c:forEach>
	</c:forEach> 
</div>