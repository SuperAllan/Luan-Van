<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>	
<link href="<c:url value="/resources/css/tonghop-chiphi.css" />" rel="stylesheet">
	<div class="text-center">
		<h3>BẢNG TỔNG HỢP CHI PHÍ PHẦN MỀM</h3>
	</div>
	<table class="table table-bordered">
		<thead>
			<tr class="text-center" style="font-weight: bold;">
				<td>TT</td>
				<td>Khoản mục chi phí</td>
				<td>Cách tính</td>
				<td>Giá trị</td>
				<td>Ký hiệu</td>
				<td>Ghi chú</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="text-center">1</td>
				<td>Giá trị phần mềm</td>
				<td class="text-center">1.4*E*P*H</td>
				<td id="getGiaTri" class="formatKetQua text-right"></td>
				<td class="text-center">G</td>
				<td></td>
			</tr>
			<tr>
				<td class="text-center">2</td>
				<td>Chi phí chung</td>
				<td class="text-center">G*65%</td>
				<td id="chiPhiChung" class="formatKetQua text-right"></td>
				<td class="text-center">C</td>
				<td>Công văn 2589-BTTTT-UDCNTT quy định chi phí chung (65%)</td>
			</tr>
			<tr>
				<td class="text-center">3</td>
				<td>Thu thập chịu thuế tính trước</td>
				<td class="text-center">(G+C)*6%</td>
				<td id="thuThapChiuThue" class="formatKetQua text-right"></td>
				<td class="text-center">TL</td>
				<td>Công văn 2589-BTTTT-UDCNTT quy định thu thập chịu thuế tính trước (6%)</td>
			</tr>
			<tr>
				<td class="text-center">4</td>
				<td>Chi phí phần mềm</td>
				<td class="text-center">G+C+TL</td>
				<td id="chiPhiPhanMem" class="formatKetQua text-right"></td>
				<td class="text-center">GPM</td>
				<td></td>
			</tr>
			<tr class="text-bold">
				<td></td>
				<td>Tổng cộng</td>
				<td></td>
				<td id="tongCong" class="formatKetQua text-right"></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
<script src="<c:url value="/resources/js/tonghop-chiphi.js" />" ></script>