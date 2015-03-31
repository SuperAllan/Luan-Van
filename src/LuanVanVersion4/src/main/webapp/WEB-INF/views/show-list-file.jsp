<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<tr>
	<td class="text-left">${resultFile.name}</td>
	<td>${resultFile.size}</td>
	<td class="formatType" title="${resultFile.type}">${resultFile.type}</td>
	<td><a href="${contextPath}/downloadFile?fileid=${resultFile.fileid}">Download</a></td>
	<td><a id="${resultFile.fileid}" onclick="deleteFile(this)"><i class="glyphicon glyphicon-remove"></i></a></td>
</tr>
