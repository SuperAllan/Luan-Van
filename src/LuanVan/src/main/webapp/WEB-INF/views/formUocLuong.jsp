<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<ul class="nav nav-tabs">
  <li role="presentation" class="active"><a href="#chucNang" data-toggle="tab">Yêu cầu chức năng</a></li>
  <li role="presentation"><a href="#2" data-toggle="tab">Thông tin tham khảo</a></li>
  <li role="presentation"><a href="#3" data-toggle="tab">Kỹ thuật công nghệ</a></li>
  <li role="presentation"><a href="#4" data-toggle="tab">Yêu cầu đối với nhân công</a></li>
</ul>

<div class="tab-content">
        <div class="tab-pane fade in active" id="chucNang">
          	 <%@ include file="/WEB-INF/views/yeucau-chucnang.jsp" %>
        </div>
        <div class="tab-pane fade" id="2">
             2        
        </div>
        <div class="tab-pane fade" id="3">
           3
        </div>
         <div class="tab-pane fade" id="4">
           4
        </div>
</div>
