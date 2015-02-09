<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<ul class="nav nav-tabs">
  <li role="presentation" class="active"><a href="#chucNang" data-toggle="tab">Yêu cầu chức năng</a></li>
  <li role="presentation"><a href="#tabKTCN" data-toggle="tab">Kỹ thuật công nghệ</a></li>
  <li role="presentation"><a href="#tabMoiTruong" data-toggle="tab">Môi trường</a></li>
  <li role="presentation"><a href="#tabLuong" data-toggle="tab">Bảng Lương</a></li>
</ul>

<div class="tab-content">
        <div class="tab-pane fade in active" id="chucNang">
          	 <%@ include file="/WEB-INF/views/yeucau-chucnang.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabKTCN">
            <%@ include file="/WEB-INF/views/kythuat-congnghe.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabMoiTruong">
            <%@ include file="/WEB-INF/views/moitruong.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabLuong">
            <%@ include file="/WEB-INF/views/bangluong.jsp" %>
        </div>
</div>
