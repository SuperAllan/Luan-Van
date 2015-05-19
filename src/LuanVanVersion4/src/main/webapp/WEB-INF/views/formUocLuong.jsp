<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<ul class="nav nav-tabs">
  <li role="presentation" id ="chuyenDoi" class="active"><a href="#tabChuyenDoi" data-toggle="tab">I. Chuyển đổi Use-case</a></li>
  <li role="presentation"><a href="#tabTacNhan" data-toggle="tab">II. Tính điểm tác nhân</a></li>
  <li role="presentation"><a href="#tabUsecase" data-toggle="tab">III. Tính điểm Use-Case</a></li>
  <li role="presentation" id="KTCN"><a href="#tabKTCN" data-toggle="tab">IV. Kỹ thuật công nghệ</a></li>
  <li role="presentation" id="moiTruong"><a href="#tabMoiTruong" data-toggle="tab">V. Tác động môi trường</a></li>
  <li role="presentation" id="bangLuong"><a href="#tabBangLuong" data-toggle="tab">VI.Bảng Lương</a></li>
  <li role="presentation" id="giaTriPhanMem"><a href="#tabGiaTriPhanMem" data-toggle="tab">VII. Giá trị phần mềm</a></li>
  <li role="presentation"><a href="#tabTongHop" data-toggle="tab">VIII. Tổng hợp chi phí</a></li>
</ul>

<div class="tab-content">
       
         <div class="tab-pane fade in active" id="tabChuyenDoi">
            <%@ include file="/WEB-INF/views/chuyendoi-usecase.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabTacNhan">
            <%@ include file="/WEB-INF/views/tinhdiem-tacnhan.jsp" %>
        </div>
         <div class="tab-pane fade" id="tabUsecase">
            <%@ include file="/WEB-INF/views/tinhdiem-usecase.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabKTCN">
            <%@ include file="/WEB-INF/views/kythuat-congnghe.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabMoiTruong">
            <%@ include file="/WEB-INF/views/moitruong.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabBangLuong">
            <%@ include file="/WEB-INF/views/bangluong.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabGiaTriPhanMem">
            <%@ include file="/WEB-INF/views/giatri-phanmem.jsp" %>
        </div>
        <div class="tab-pane fade" id="tabTongHop">
            <%@ include file="/WEB-INF/views/tonghop-chiphi.jsp" %>
        </div>
</div>
