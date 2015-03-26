<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="utf-8">
<title>Sơ đồ trường hợp sử dụng (Use-cases)</title>
<!-- Bootstrap vs iquery -->
<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />" ></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/joint.nojquery.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/diagram.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/jointshapesumlcustom.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/diagramui.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/joint.shapes.ui.custom.css" />" rel="stylesheet">
</head>
<body>
<input type="hidden" id="nameProject" value="${project.tenproject}" />
<div id="path" style="display:none;">${diagram.path}</div>
<div id="wrapper-diagram">
	<div id="properties-diagram">
		<div class="btn-group">
			<a class="btn btn-default" href="/luanvan"><i class="glyphicon glyphicon-home"></i> Trang chủ</a>
		</div>
		<div class="btn-group">
			<a class="btn btn-default" data-toggle="modal" href="#modal-new-diagram"><i class="glyphicon glyphicon-plus-sign"></i> Tạo mới</a>
			<a class="btn btn-default" data-toggle="modal" href="#modal-exportSVG"><i class="glyphicon glyphicon-picture"></i> Xuất ảnh</a>
			<button class="btn btn-default" id="view-list-diagram"><i class="glyphicon glyphicon-th-large"></i> Sơ đồ</button>
			<button class="btn btn-default" id="saveDiagram" disabled="disabled">
				<i class="glyphicon glyphicon-floppy-saved"></i> Đã lưu
			</button>
			<a class="btn btn-default" data-toggle="modal" href='#modal-scoreActor' id="scoreActor">
				TAW
			</a>
			<a class="btn btn-default" data-toggle="modal" href='#modal-scoreUsecase' id="scoreUsecase">
				TBF
			</a>
		</div>
	</div>
	<div id="content-diagram">
		<div id="list-element-diagram">
			<div id="toggle-actor-view">
				<strong>Actors</strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
			</div>
			<div id="children-actor-view">
				<c:forEach items="${actors}" var="actor">
					<div draggable="true" ondragstart="drag(event)" id="${actor.actorid}">${actor.nameofactor}</div>
				</c:forEach>
			</div>
			<div id="toggle-usecase-view">
				<strong>Use-cases</strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
			</div>
			<div id="children-usecase-view">
				<c:forEach items="${usecases}" var="usecase">
					<div draggable="true" ondragstart="drag(event)" id="${usecase.usecaseid}">${usecase.nameofuc}</div>
				</c:forEach>
			</div>
		</div>
		<div id="right-content-diagram">
			<div id="paper-diagram">
				<div id="name-diagram">
					<span id="name-diagram-show">${nameDiagram}</span>
					<a href="#modal-rename-diagram" data-toggle="modal" id="a-rename-diagram" style="display: none;">
						<i class="glyphicon glyphicon-pencil"></i>
					</a>
				</div>
				<div id="paperUI" ondrop="drop(event);" ondragover="allowDrop(event)"></div>
			</div>
			<div id="tool-diagram">
				<div id="create-new-actor" draggable="true" ondragstart="drag(event)">
					<img src="<c:url value="/resources/img/actor.svg" />" alt="actor"/>
				</div>
				<div id="create-new-usecase" draggable="true" ondragstart="drag(event)">
					<img src="<c:url value="/resources/img/usecase.png" />" alt="usecase"/>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Begin modal actor -->
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
					<textarea class="form-control" onkeyup="textAreaAdjust(this)" id="description-modal-actor" rows="4"
						placeholder="Nhập mô tả của tác nhân" autofocus ></textarea>
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
			<div class="modal-footer">
				<button id="saveInfoActor" type="button" class="btn btn-primary" >
					<i class="glyphicon glyphicon-save"></i> Lưu thay đổi</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal actor -->

<!-- Begin modal usecase -->
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
					<textarea class="form-control" onkeyup="textAreaAdjust(this)" id="description-modal-usecase" rows="4"
					placeholder="Nhập mô tả của trường hợp sử dụng"  autofocus></textarea>
				</div>
				<div class="form-group">
					<label for="">Mức độ</label>
					<span class="label label-primary">B - Chức năng bắt buộc</span>
					<span class="label label-primary">M - Chức năng mong muốn</span>
					<span class="label label-primary">T - Chức năng tùy chọn</span>
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
			<div class="modal-footer">
				<button id="saveInfoUC" type="button" class="btn btn-primary" >
					<i class="glyphicon glyphicon-save"></i> Lưu thay đổi</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal usecase -->

<!-- Begin modal new diagram -->
<div class="modal fade" id="modal-new-diagram">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Tạo nhóm</h4>
			</div>
			<div class="modal-body">
				<input type="text" id="input-new-diagram" placeholder="Nhập tên nhóm" class="form-control" autofocus/>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-info" id="btn-new-diagram">
					<i class="glyphicon glyphicon-save"></i> OK</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal new diagram -->

<!-- Begin modal rename diagram -->
<div class="modal fade" id="modal-rename-diagram">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Đổi tên nhóm</h4>
			</div>
			<div class="modal-body">
				<input type="text" id="input-rename-diagram" placeholder="Nhập tên nhóm" class="form-control" autofocus/>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-info" id="btn-rename-diagram">
					<i class="glyphicon glyphicon-save"></i> OK</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal rename diagram -->

<!-- Begin modal list diagram -->
<div class="modal fade" id="modal-list-diagram">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Danh sách các sơ đồ</h4>
			</div>
			<div class="modal-body" >
				<div class="row" id="body-list-diagram">
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End modal list diagram -->

<!-- Begin modal export image -->
<form>
	<div class="modal fade" id="modal-exportSVG">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Lưu file SVG</h4>
				</div>
				<div class="modal-body">
					<input type="text" id="nameFileSVG" placeholder="Nhập tên file cần lưu" class="form-control" autofocus/>
				</div>
				<div class="modal-footer">
					<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
					<button type="button" class="btn btn-info" id="exportSVG">
						<i class="glyphicon glyphicon-save"></i> OK</button>
				</div>
			</div>
		</div>
	</div>
</form>
<!-- End modal export image -->

<!-- Begin modal delete actor and usecase -->
<div class="modal fade" id="modal-delete-actor-usecase">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Lựa chọn cách xóa</h4>
			</div>
			<div class="modal-body">
				<select id="select-delete-actor-usecase" class="form-control">
					<option value="0">Chỉ xóa khỏi sơ đồ hiện tại</option>
					<option value="1">Xóa hoàn toàn khỏi dự án (Đối tượng tương ứng tồn tại trên sơ đồ khác cũng sẽ bị xóa)</option>
				</select>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-info" id="btn-delete-actor-usecase">
					<i class="glyphicon glyphicon-save"></i> OK</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal delete actor and usecase -->

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

<script src="<c:url value="/resources/js/joint.nojquery.min.js" />" ></script>
<script src="<c:url value="/resources/js/diagram.js" />" ></script>
<script src="<c:url value="/resources/js/xml2json.js" />" ></script>
<script src="<c:url value="/resources/js/jointshapesumlcustom.js"/>"></script>
<script src="<c:url value="/resources/js/detail-project.js" />"></script>
<script src="<c:url value="/resources/js/diagramui.js" />"></script>
<script src="<c:url value="/resources/js/joint.shapes.ui.custom.js" />"></script>
</body>
</html>