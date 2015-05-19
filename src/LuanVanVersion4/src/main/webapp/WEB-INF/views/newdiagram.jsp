<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
</head>
<body>
<div id="message-alert-for-user"></div>
<input type="hidden" id="nameProject" value="${project.tenproject}" />
<div id="path" style="display:none;">${path}</div>
<div id="wrapper-diagram">
	<div id="header-diagram">
		<div>
			<img src="${contextPath}/resources/img/logo.gif" width="20" height="20">
			<a href="${contextPath}/">Ước lượng và thu thập yêu cầu </a>/<a href="${contextPath}/background"> Các dự án </a>/
			<a href="${contextPath}/detailProject?name=${project.tenproject}"> Dự án: <strong class="formatNameProject">${project.tenproject}</strong></a>
			<span> / Vẽ sơ đồ use-cases</span>
		</div>
		<div class="pull-right" >
			<sec:authorize access="isAuthenticated()">
				<!-- For login user -->
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="POST" id="logoutForm" style="display: none;">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
						<div class="dropdown pull-right">			  
							<a id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true" style="cursor: pointer; color: white; text-decoration: none;">
						    <c:if test="${not empty user.image}">
						    	<img src="<c:url value="${user.image}" />" class="img-rounded" style="width: 20px; height: 20px;">
						    </c:if>
						    ${pageContext.request.userPrincipal.name}
						    <span class="caret"></span>
						  </a>
						  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
						    <li role="presentation"><a role="menuitem" tabindex="-1" href="../background" style="color: #333 !important;" ><i class="mdi-action-assignment-ind"></i> Thông tin chung</a></li>
						    <li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:formSubmit()" style="color: #333 !important;"><i class="mdi-action-settings-power"></i> Đăng xuất</a></li>
						  </ul>
						</div>
					</c:if>
			</sec:authorize>
		</div>
	</div>
	<div id="properties-diagram">
		<a class="btn-default" id="a-new-diagram"><i class="glyphicon glyphicon-plus-sign"></i> Tạo mới sơ đồ</a>
		<a class="btn-default" id="a-export-svg"><i class="glyphicon glyphicon-picture"></i> Xuất ảnh</a>
		<a class="btn-default" id="view-list-diagram" data-toggle="modal" href="#modal-list-diagram"><i class="glyphicon glyphicon-th-large"></i> Xem danh sách sơ đồ</a>
		<a class="btn-default" id="saveDiagram" style="background: #5cb85c; color: white;"><i class="glyphicon glyphicon-floppy-saved"></i> Lưu</a>
	</div>
	<div id="content-diagram">
		<div id="list-element-diagram">
			<!-- search -->
			<input style="border: none; border-radius: 0; height: 40px; margin-top: -1px;" type="search" id="input" class="form-control text-center"  title="Search" onkeyup="searchUsecaseActor(this.value)" placeholder="Nhập thông tin tìm kiếm">
			<!-- End search -->
			<div>
				<div class="toggle-element-view">
					<strong>Actors đã có</strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
				</div>
				<div class="children-element-view" id="children-actor-view">
					<c:forEach items="${actors}" var="actor">
						<div style="word-break: break-word;" class="forSearch" draggable="true" ondragstart="drag(event)" id="${actor.actorid}">${actor.nameofactor}</div>
					</c:forEach>
				</div>
			</div>
			<div>
				<div class="toggle-element-view">
					<strong>Use-cases đã có</strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
				</div>
				<div class="children-element-view" id="children-usecase-view">
					<c:forEach items="${usecases}" var="usecase">
						<div style="word-break: break-word;" class="forSearch" draggable="true" ondragstart="drag(event)" id="${usecase.usecaseid}">${usecase.nameofuc}</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="right-content-diagram">
			<div id="paper-diagram">
				<div id="name-diagram">
					<span id="name-diagram-show">${nameDiagram}</span>
					<a href="#modal-rename-diagram" data-toggle="modal" id="a-rename-diagram" style="display: none;">
						<i class="glyphicon glyphicon-pencil"></i> Đổi tên
					</a>
					<a class="pull-right" id="fullscreen-diagram"><i class="glyphicon glyphicon-fullscreen"></i></a>
				</div>
				<div id="paper" ondrop="drop(event);" ondragover="allowDrop(event)"></div>
			</div>
			<div id="tool-diagram">
				<div id="tool-element-diagram">
					<div style="background: url('../resources/img/gauze.png'); padding: 0 20px 0 20px; margin-bottom: 10px;"><strong>Tạo mới đối tượng</strong></div>
					<div class="text-center">
						<img id="create-new-actor" draggable="true" ondragstart="drag(event)" width="40" height="40"
							src="<c:url value="/resources/img/actor.svg" />" alt="actor"/>
					</div>
					<div class="text-center">
						<img id="create-new-usecase" draggable="true" ondragstart="drag(event)" width="40" height="40"
							src="<c:url value="/resources/img/usecase.svg" />" alt="usecase"/>
					</div>
				</div>
				<div id="hint-usecase-diagram">
					<div style="background: url('../resources/img/gauze.png'); padding: 0 20px 0 20px; margin-bottom: 10px;">
						<strong>Gợi ý chức năng</strong>
					</div>
					<c:forEach items="${nhomChucNangs}" var="nhomChucNang">
						<c:forEach items="${nhomChucNang.chucnangs}" var="chucnang">
							<div style="word-break: break-word;" draggable="true" ondragstart="drag(event)" id="create-hint-usecase">${chucnang.motayeucau}</div>
						</c:forEach>
					</c:forEach>
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
					<textarea class="form-control" onkeyup="textAreaAdjust(this)" style="resize: vertical;" id="description-modal-actor" rows="4"
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
				<button id="saveInfoActor" type="button" class="btn btn-block btn-primary" >
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
					<textarea class="form-control" onkeyup="textAreaAdjust(this)"  style="resize: vertical;" id="description-modal-usecase" rows="4"
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
					<a id="a-question-upload-file" href="" target="_blank">Đặt câu hỏi và upload các tập tin liên quan</a>
				</div>
				<div class="form-group">
					<label for="input-group-modal-usecase">Thuộc nhóm</label>
					<div class="input-group">
						<input type="text" id="input-group-modal-usecase" class="form-control"><br />
						<!-- <label for="select-group-modal-usecase">Nhóm đã có</label> -->
						<div class="input-group-addon" style="background: none;">
							<select id="select-group-modal-usecase" style="border: none; outline: none;"></select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="listUIOfUC">Gắn trên các giao diện</label>
					<div id="listUIOfUC">
					</div>
				</div>
				<div class="form-group">
					<label for="listActorOfUC">Tác nhân sử dụng</label>
					<div id="listActorOfUC">
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button id="saveInfoUC" type="button" class="btn btn-block btn-primary" >
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
				<h4 class="modal-title"><i class="glyphicon glyphicon-plus-sign"></i> Tạo sơ đồ mới</h4>
			</div>
			<div class="modal-body">
				<input type="text" id="input-new-diagram" placeholder="Nhập tên nhóm" class="form-control" autofocus value=""/>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-block btn-info" id="btn-new-diagram">Tạo</button>
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
				<h4 class="modal-title"><i class="glyphicon glyphicon-pencil"></i> Đổi tên sơ đồ</h4>
			</div>
			<div class="modal-body">
				<input type="text" id="input-rename-diagram" placeholder="Nhập tên sơ đồ" class="form-control" autofocus/>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-block btn-info" id="btn-rename-diagram">Đổi tên</button>
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
				<h4 class="modal-title"><i class="glyphicon glyphicon-th-large"></i> Danh sách các sơ đồ</h4>
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
<div class="modal fade" id="modal-exportSVG">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><i class="glyphicon glyphicon-picture"></i> Xuất ảnh</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label class="checkbox-inline">
						<input type="radio" name="radioChooseTypeExportImage" value="svg" checked> SVG
					</label>
					<label class="checkbox-inline">
						<input type="radio" name="radioChooseTypeExportImage" value="png"> PNG
					</label>
				</div>
				<div class="form-group">
					<input type="text" id="nameFileSVG" placeholder="Nhập tên tập tin" class="form-control" autofocus/>
				</div>
			</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
				<button type="button" class="btn btn-block btn-info" id="exportSVG">Xuất ảnh</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal export image -->

<!-- Begin modal delete actor and usecase -->
<div class="modal fade" id="modal-delete-actor-usecase">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Lựa chọn cách xóa</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label><input type="radio" checked="checked" name="radio-delete-actor-usecase" value="0" /> Chỉ xóa khỏi sơ đồ hiện tại</label>
				</div>
				<div class="form-group">
					<label><input type="radio" name="radio-delete-actor-usecase" value="1"/><span class="alert-danger">Xóa hoàn toàn khỏi dự án, mọi dữ liệu liên quan sẽ mất</span></label>
				</div>			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-danger" id="btn-delete-actor-usecase">
					<i class="glyphicon glyphicon-trash"></i> Xóa</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal delete actor and usecase -->


<script src="<c:url value="/resources/js/joint.nojquery.min.js" />" ></script>
<script src="<c:url value="/resources/js/jointshapesumlcustom.js"/>"></script>
<script src="<c:url value="/resources/js/xml2json.js" />" ></script>
<script src="<c:url value="/resources/js/saveSvgAsPng.js" />" ></script>
<script src="<c:url value="/resources/js/global.js" />" ></script>
<script src="<c:url value="/resources/js/diagram.js" />" ></script>

</body>
</html>