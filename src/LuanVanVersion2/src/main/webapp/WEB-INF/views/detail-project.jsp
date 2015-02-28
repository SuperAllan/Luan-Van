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
<link href="<c:url value="/resources/css/joint.nojquery.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/diagram.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/jointshapesumlcustom.css" />" rel="stylesheet">
</head>
<body style="padding-top: 70px;" >
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container">
<div class="row">
<div class="col-md-2 text-center">
	
    
    <span style="font-size:20px; color:#666;" class="formatNameProject">${project.tenproject}</span>
    <input type="hidden" id="nameProject" name="nameProject" value="${ project.tenproject }" />
    <div id="sodo" style="display:none;">${ project.sodo }</div>
    <div class="tabs-left">
		<ul class="nav nav-stacked tab-left">
	        <li class="classLi active" id="UC"><a href="#tabUC" data-toggle="tab">Sơ đô Use Case</a></li>
	        <li class="classLi" id="uocLuong"><a href="#tabUocLuong" data-toggle="tab">Ước lượng chi phí</a></li>
	        <li class="classLi" id="giaoDien"><a href="#tabGiaoDien" data-toggle="tab">Thiết kế giao diện</a></li>
	        <li class="classLi" id="thuThap"><a href="#tabThongTin" data-toggle="tab">Thông tin dự án</a></li>
	    </ul>
	</div>
</div>
<div class="col-md-10">
	 <div class="tab-content">
        <div class="tab-pane fade in active" id="tabUC">
             <%@ include file="/WEB-INF/views/diagram.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabUocLuong">
            <%@ include file="/WEB-INF/views/formUocLuong.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabThongTin">
        	<%@ include file="/WEB-INF/views/thongtin.jsp" %>             
        </div>
        <div class="tab-pane fade" id="tabGiaoDien">
           c
        </div>
    </div>
</div>

</div>  
<%@ include file="/WEB-INF/views/footer.jsp" %>  
</div>
<script src="<c:url value="/resources/js/joint.nojquery.min.js" />" ></script>
<script src="<c:url value="/resources/js/diagram.js" />" ></script>
<script src="<c:url value="/resources/js/xml2json.js" />" ></script>
<script src="<c:url value="/resources/js/jointshapesumlcustom.js"/>"></script>
<script src="<c:url value="/resources/js/detail-project.js" />"></script>

</body>

</html>