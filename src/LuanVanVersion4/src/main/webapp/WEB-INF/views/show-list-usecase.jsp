<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default">
	<c:if test="${not empty resultNull}">
		<div class="text-danger list-group-item text-center">${resultNull}</div>
	</c:if>
	<c:forEach items="${resultNhomUC}" var="list">
	  <div class="panel-heading">
	    <h3 class="panel-title">${list.tennhom} </h3>
	    <span class="pull-right badge">${fn:length(list.usecases)}</span>
	  </div> 
	   <c:forEach items="${list.usecases}" var="list2">
				  	<a class="list-group-item" onclick="showDetailUsecase(${list2.usecaseid})" >
				  		<div class="text-danger">${list2.nameofuc}</div>
				  	</a>
		</c:forEach>
	</c:forEach> 
</div>