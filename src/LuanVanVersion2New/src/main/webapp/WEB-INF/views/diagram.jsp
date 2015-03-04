<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid">
	<div class="form-group tool">
		<div class="btn-group-vertical">
			<div class="btn-group">
				<button type="button" class="btn btn-success" id="saveDiagram" disabled="disabled">
					<span style="display: none;" id="icon-not-save"><i class="glyphicon glyphicon-floppy-remove"></i></span>
					<span id="icon-saved"><i class="glyphicon glyphicon-floppy-saved"></i></span>
				</button>
			</div>
			<button type="button" class="btn btn-danger tool-btn" id="arrow">
				<img src="<c:url value="/resources/img/arrow.png" />" alt="arrow"/>
			</button>
			<button type="button" class="btn btn-default tool-btn" id="actor">
				<img src="<c:url value="/resources/img/actor.svg" />" alt="actor" width="24" height="24"/>
			</button>
			<button type="button" class="btn btn-default tool-btn" id="usecase">
				<img src="<c:url value="/resources/img/usecase.png" />" alt="usecase"/>
			</button>
			<a class="btn btn-primary" data-toggle="modal" href='#modal-exportJSON'>
				JSON
			</a>
			<a class="btn btn-danger" data-toggle="modal" href='#modal-exportXML'>
				XML
			</a>
			<a class="btn btn-info" data-toggle="modal" href='#modal-exportSVG'>
				SVG
			</a>
			<button type="button" class="btn btn-success" id="loadFile">
				Import
			</button>
			<a class="btn btn-warning" data-toggle="modal" href='#modal-scoreActor' id="scoreActor">
				TAW
			</a>
			<a class="btn btn-warning" data-toggle="modal" href='#modal-scoreUsecase' id="scoreUsecase">
				TBF
			</a>
		</div>
		<input type="file" id="readFile" style="opacity:0; display:none;"/>
	</div>
	<div class="form-group">
		<div id="paper"></div>
	</div>
</div>

<div class="modal fade" id="modal-actor">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Tác nhân: <span id="name-modal-actor"></span></h4>
			</div>
			<div class="modal-body">
				<input type="hidden" id="id-modal-actor" />
				<div class="form-group">
					<label for="">Mô tả</label>
					<textarea class="form-control" onkeyup="textAreaAdjust(this)" id="description-modal-actor" 
						placeholder="Input description" style="height: 34px;" autofocus></textarea>
				</div>
				<div class="form-group">
					<label for="">Loại tác nhân</label>
					<select id="level-modal-actor" class="form-control">
					<c:forEach var="loaiactor" items="${loaiactors}" >
						<option value="${loaiactor.loaiactorid}">${loaiactor.motaloaiactor}</option>
					</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label>Vai trò với chức năng</label>
					<div id="listUCOfActor">
					</div>
				</div>
			</div>
			<div class="modal-footer" style="padding: 0; border: none;">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
				<button id="saveInfoActor" type="button" class="btn btn-success" >
					<i class="glyphicon glyphicon-save"></i> Lưu thay đổi</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="modal-usecase">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Usecase: <span id="name-modal-usecase"></span></h4>
			</div>
			<div class="modal-body">
				<input type="hidden" id="id-modal-usecase" />
				<div class="form-group">
					<label for="">Mô tả</label>
					<textarea class="form-control" onkeyup="textAreaAdjust(this)" id="description-modal-usecase" 
					placeholder="Input description" style="height: 34px;" autofocus></textarea>
				</div>
				<div class="form-group">
					<label for="">Mức độ</label>
					<span class="label label-success">B - Chức năng bắt buộc</span>
					<span class="label label-success">M - Chức năng mong muốn</span>
					<span class="label label-success">T - Chức năng tùy chọn</span>
					<select id="level-modal-usecase" class="form-control">
						<c:forEach var="bmt" items="${bmts}" >
							<option value="${bmt.bmtid}">${bmt.ten}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label><input type="checkbox" id="pay-money-modal-usecase"/> Tính tiền</label>
				</div>
				<div class="form-group">
					<label for="input-group-modal-usecase">Thuộc nhóm</label>
					<input type="text" id="input-group-modal-usecase">
					<label for="select-group-modal-usecase">Nhóm đã có</label>
					<select id="select-group-modal-usecase"></select>
				</div>
				<div class="form-group">
					<label for="listActorOfUC">Tác nhân sử dụng</label>
					<div id="listActorOfUC">
					</div>
				</div>
			</div>
			<div class="modal-footer" style="padding: 0; border: none;">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
				<button id="saveInfoUC" type="button" class="btn btn-success" >
					<i class="glyphicon glyphicon-save"></i> Lưu thay đổi</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="modal-exportJSON">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Lưu file JSON</h4>
			</div>
			<div class="modal-body" style="padding: 0;">
				<input type="text" id="nameFileJSON" placeholder="Nhập tên file cần lưu" class="form-control" autofocus/>
			</div>
			<div class="modal-footer" style="padding: 0; border: none;">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
				<button type="button" class="btn btn-primary" id="exportJSON">
					<i class="glyphicon glyphicon-save"></i> OK</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="modal-exportXML">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Lưu file XML</h4>
			</div>
			<div class="modal-body" style="padding: 0;">
				<input type="text" id="nameFileXML" placeholder="Nhập tên file cần lưu" class="form-control" autofocus/>
			</div>
			<div class="modal-footer" style="padding: 0; border: none;">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
				<button type="button" class="btn btn-danger" id="exportXML">
					<i class="glyphicon glyphicon-save"></i> OK</button>
			</div>
		</div>
	</div>
</div>

<!-- Bắt đầu form lưu file svg -->
<form>
	<div class="modal fade" id="modal-exportSVG">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Lưu file SVG</h4>
				</div>
				<div class="modal-body" style="padding: 0;">
					<input type="text" id="nameFileSVG" placeholder="Nhập tên file cần lưu" class="form-control" autofocus/>
				</div>
				<div class="modal-footer" style="padding: 0; border: none;">
					<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
					<button type="button" class="btn btn-info" id="exportSVG">
						<i class="glyphicon glyphicon-save"></i> OK</button>
				</div>
			</div>
		</div>
	</div>
</form>
<!-- Kết thúc form lưu file svg -->

<!-- Bat dau bang tinh toan diem tac nhan -->
<div class="modal fade" id="modal-scoreActor">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">BẢNG TÍNH TOÁN ĐIỂM TÁC NHÂN (ACTORS) TƯƠNG TÁC TRAO ĐỔI THÔNG TIN VỚI PHẦN MỀM</h4>
			</div>
			<div class="modal-body">
				<table class="table table-bordered">
					<tr>
						<th class="text-center">TT</th>
						<th class="text-center">Loại actor</th>
						<th class="text-center">Mô tả</th>
						<th class="text-center">Số tác nhân</th>
						<th class="text-center">Trọng số</th>
						<th class="text-center">Điểm của từng loại tác nhân</th>
						<th class="text-center">Ghi chú</th>
					</tr>
					<tr class="text-warning">
						<td class="text-center">1</td>
						<td class="text-center">Đơn giản (Simple)</td>
						<td class="text-center">Thuộc loại giao diện của chương trình</td>
						<td id="numberActorSimple" class="text-center"></td>
						<td class="text-center">1</td>
						<td id="scoreActorSimple" class="text-center"></td>
						<td id="noteActorSimple" class="text-center"></td>
					</tr>
					<tr class="text-info">
						<td class="text-center">2</td>
						<td class="text-center">Trung bình (Average)</td>
						<td class="text-center">Giao diện tương tác hoặc phục vụ một giao thức hoạt động</td>
						<td id="numberActorAverage" class="text-center"></td>
						<td class="text-center">2</td>
						<td id="scoreActorAverage" class="text-center"></td>
						<td id="noteActorAverage" class="text-center"></td>
					</tr>
					<tr class="text-success">
						<td class="text-center">3</td>
						<td class="text-center">Phức tạp (Complex)</td>
						<td class="text-center">Giao diện đồ họa</td>
						<td id="numberActorComplex" class="text-center"></td>
						<td class="text-center">3</td>
						<td id="scoreActorComplex" class="text-center"></td>
						<td id="noteActorComplex" class="text-center"></td>
					</tr>
					<tr class="text-danger">
						<td colspan="2" class="text-center">Cộng (1 + 2 + 3)</td>
						<th class="text-center">TAW</th>
						<td></td>
						<td></td>
						<td id="TAW" class="text-center"></td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- Ket thuc bang tinh toan diem use case -->

<!-- Bat dau bang tinh toan diem tac nhan -->
<div class="modal fade" id="modal-scoreUsecase">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">BẢNG TÍNH TOÁN ĐIỂM CÁC TRƯỜNG HỢP SỬ DỤNG (USE-CASES)</h4>
			</div>
			<div class="modal-body">
				<table class="table table-bordered">
					<tr>
						<th class="text-center">TT</th>
						<th class="text-center">Loại</th>
						<th class="text-center">Số trường hợp sử dụng</th>
						<th class="text-center">Trọng số</th>
						<th class="text-center">Hệ số BMT</th>
						<th class="text-center">Điểm của từng loại use-case</th>
						<th class="text-center">Mô tả</th>
					</tr>
					<tr class="text-warning">
						<td class="text-center">1</td>
						<td class="text-center">B</td>
						<td class="text-center" id="numberTypeB"></td>
						<td class="text-center"></td>
						<td class="text-center">1</td>
						<td id="sumScoreTypeB" class="text-center"></td>
						<td class="text-center" rowspan="4" style="width: 150px; text-align: justify;">Các yêu cầu phải thỏa mãn thì phần mềm mới được chấp nhận.</td>
					</tr>
					<tr class="text-warning">
						<td class="text-center"></td>
						<td class="text-center">Đơn giản</td>
						<td class="text-center" id="numberTypeBSimple"></td>
						<td class="text-center">5</td>
						<td class="text-center"></td>
						<td class="text-center" id="scoreTypeBSimple"></td>
					</tr>
					<tr class="text-warning">
						<td class="text-center"></td>
						<td class="text-center">Trung bình</td>
						<td class="text-center" id="numberTypeBAverage"></td>
						<td class="text-center">10</td>
						<td class="text-center"></td>
						<td class="text-center" id="scoreTypeBAverage"></td>
					</tr>
					<tr class="text-warning">
						<td class="text-center"></td>
						<td class="text-center">Phức tạp</td>
						<td class="text-center" id="numberTypeBComplex"></td>
						<td class="text-center">15</td>
						<td class="text-center"></td>
						<td class="text-center" id="scoreTypeBComplex"></td>
					</tr>
					<tr class="text-info">
						<td class="text-center">2</td>
						<td class="text-center">M</td>
						<td class="text-center" id="numberTypeM"></td>
						<td class="text-center"></td>
						<td class="text-center">1.2</td>
						<td id="sumScoreTypeM" class="text-center"></td>
						<td class="text-center" rowspan="4" style="width: 150px; text-align: justify;">Các chức năng không phải là cốt lõi hay các chức năng phụ trợ hoặc theo yêu cầu của bên đặt hàng.</td>
					</tr>
					<tr class="text-info">
						<td class="text-center"></td>
						<td class="text-center">Đơn giản</td>
						<td class="text-center" id="numberTypeMSimple"></td>
						<td class="text-center">5</td>
						<td class="text-center"></td>
						<td class="text-center" id="scoreTypeMSimple"></td>
					</tr>
					<tr class="text-info">
						<td class="text-center"></td>
						<td class="text-center">Trung bình</td>
						<td class="text-center" id="numberTypeMAverage"></td>
						<td class="text-center">10</td>
						<td class="text-center"></td>
						<td class="text-center" id="scoreTypeMAverage"></td>
					</tr>
					<tr class="text-info">
						<td class="text-center"></td>
						<td class="text-center">Phức tạp</td>
						<td class="text-center" id="numberTypeMComplex"></td>
						<td class="text-center">15</td>
						<td class="text-center"></td>
						<td class="text-center" id="scoreTypeMComplex"></td>
					</tr>
					<tr class="text-success">
						<td class="text-center">3</td>
						<td class="text-center">T</td>
						<td class="text-center" id="numberTypeT"></td>
						<td class="text-center"></td>
						<td class="text-center">1.5</td>
						<td id="sumScoreTypeT" class="text-center"></td>
						<td class="text-center" rowspan="4" style="width: 150px; text-align: justify;">Các yêu cầu được tư vấn thêm hoặc đưa ra để bên đặt hàng lựa chọn thêm nếu muôn.</td>
					</tr>
					<tr class="text-success">
						<td class="text-center"></td>
						<td class="text-center">Đơn giản</td>
						<td class="text-center" id="numberTypeTSimple"></td>
						<td class="text-center">5</td>
						<td class="text-center"></td>
						<td class="text-center" id="scoreTypeTSimple"></td>
					</tr>
					<tr class="text-success">
						<td class="text-center"></td>
						<td class="text-center">Trung bình</td>
						<td class="text-center" id="numberTypeTAverage"></td>
						<td class="text-center">10</td>
						<td class="text-center"></td>
						<td class="text-center" id="scoreTypeTAverage"></td>
					</tr>
					<tr class="text-success">
						<td class="text-center"></td>
						<td class="text-center">Phức tạp</td>
						<td class="text-center" id="numberTypeTComplex"></td>
						<td class="text-center">15</td>
						<td class="text-center"></td>
						<td class="text-center" id="scoreTypeTComplex"></td>
					</tr>
					<tr class="text-danger">
						<td colspan="2" class="text-center" style="width: 150px;">Cộng (1 + 2 + 3)</td>
						<th class="text-center" id="sumNumberUC"></th>
						<td></td>
						<td></td>
						<th id="TBF" class="text-center"></th>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- Ket thuc bang tinh toan diem use case -->

<!-- Bat dau bang thong tin use case -->
<div class="modal fade" id="modal-infoUsecase">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">BẢNG CHUYỂN ĐỔI YÊU CẦU CHỨC NĂNG SANG TRƯỜNG HỢP SỬ DỤNG</h4>
			</div>
			<div class="modal-body">
				<table class="table table-bordered">
					<tr>
						<th class="text-center">TT</th>
						<th class="text-center">Tên Use-case</th>
						<th class="text-center">Tác nhân chính</th>
						<th class="text-center">Tác nhân phụ</th>
						<th class="text-center">Mô tả Use-case</th>
						<th class="text-center">Mức độ cần thiết</th>
						<th class="text-center">Mức độ</th>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- Ket thuc bang tinh toan diem use case -->

<!-- div loading ngan khong cho tuong tac voi so do khi dang luu -->
<div class="div-loading">
</div>