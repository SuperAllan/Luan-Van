<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<ul class="nav nav-tabs">
  <li role="presentation" id ="chucNang" class="classCN text-center active" style="width: 50%"><a href="#tabChucNang" data-toggle="tab"> Yêu cầu chức năng</a></li>
  <li role="presentation" id ="phiChucNang" class="classCN text-center" style="width: 50%"><a href="#tabPhiChucNang" data-toggle="tab"> Yêu cầu phi chức năng</a></li>
</ul>

<div class="tab-content">
        <div class="tab-paneCN tab-pane fade in active" id="tabChucNang">
          	 <%@ include file="/WEB-INF/views/yeucau-chucnang.jsp" %>
        </div>
         <div class="tab-paneCN tab-pane fade" id="tabPhiChucNang">
            <%@ include file="/WEB-INF/views/yeucau-phichucnang.jsp" %>
        </div>
</div>