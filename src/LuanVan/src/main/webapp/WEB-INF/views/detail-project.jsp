<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>
<html>
<head>
<title>Chi tiết dự án</title>
<link href="<c:url value="/resources/css/detail-project.css" />" rel="stylesheet">
</head>
<body style="padding-top: 70px;">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container">
<div class="row">
<div class="col-md-2 text-center">
	
    
    <span style="font-size:20px; color:#666;">${project.name}</span>
    <div class="tabs-left">
		<ul class="nav nav-tabs tab-left">
	        <li class="classLi active" id="UC"><a href="#tabUC" data-toggle="tab">Sơ đô Use Case</a></li>
	        <li class="classLi" id="thamKhao"><a href="#tabThamKhao" data-toggle="tab">Thông tin tham khảo</a></li>
	        <li class="classLi" id="thuThap"><a href="#tabThuThap" data-toggle="tab">Thu thập thông tin</a></li>
	        <li class="classLi" id="giaoDien"><a href="#tabGiaoDien" data-toggle="tab">Thiết kế giao diện</a></li>
	    </ul>
	</div>
</div>
<div class="col-md-10">
	 <div class="tab-content">
        <div class="tab-pane fade in active" id="tabUC">
            a
        </div>
        <div class="tab-pane fade" id="tabThamKhao">
            <%@ include file="/WEB-INF/views/thamkhao.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabThuThap">
        	<%@ include file="/WEB-INF/views/thuthap.jsp" %>             
        </div>
        <div class="tab-pane fade" id="tabGiaoDien">
           c
        </div>
    </div>
</div>

</div>  
<%@ include file="/WEB-INF/views/footer.jsp" %>  
</div>
</body>
</html>