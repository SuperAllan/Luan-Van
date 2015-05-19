<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="utf-8">
<title>Thiết kế giao diện</title>
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
<div id="message-alert-for-user"></div>
<div id="list-class-of-element"></div>
<input type="hidden" id="nameProject" value="${project.tenproject}" />
<div id="path" style="display:none;">${path}</div>
<div id="wrapper-diagram">
	<div id="header-diagram">
		<div>
			<img src="${contextPath}/resources/img/logo.gif" width="20" height="20">
			<a href="${contextPath}/">Ước lượng và thu thập yêu cầu </a>/<a href="${contextPath}/background"> Các dự án </a>/
			<a href="${contextPath}/detailProject?name=${project.tenproject}"> Dự án: <strong class="formatNameProject">${project.tenproject}</strong></a>
			<span> / Thiết kế giao diện</span>
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
		<div class="btn-group">
			<a class="btn-default" id="new-design-ui"><i class="glyphicon glyphicon-plus-sign"></i> Tạo mới giao diện</a>
			<a class="btn-default" id="btn-svgUI"><i class="glyphicon glyphicon-picture"></i> Xuất ảnh</a>
			<a class="btn-default" id="exportHTML"><i class="glyphicon glyphicon-retweet"></i> Sinh HTML</a>
			<a class="btn-default" id="viewListUI"><i class="glyphicon glyphicon-th-large"></i> Danh sách giao diện</a>
			<a class="btn-default" id="assign-usecase-design-ui"><i class="glyphicon glyphicon-list"></i> Gán giao diện vào use case</a>
			<a class="btn-default" id="screenflow-ui"><i class="glyphicon glyphicon-resize-horizontal"></i> Screenflow</a>
			<a class="btn-default" id="saveDiagramUI" style="background: #5cb85c; color: white;"><i class="glyphicon glyphicon-floppy-saved"></i> Lưu</a>
		</div>
	</div>
	<div id="content-diagram">
		<div id="list-element-diagram">
			<div>
				<div class="toggle-element-view">
					<strong>Bootstrap</strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
				</div>
				<div class="children-element-view">
					<div>Div 
						<select id="div-bootstrap-design-ui">
							<option value=""></option>
							<option value="container">container</option>
							<option value="container-fluid">container-fluid</option>
							<option value="col-md-12">col-md-12</option>
							<option value="col-md-11">col-md-11</option>
							<option value="col-md-10">col-md-10</option>
							<option value="col-md-9">col-md-9</option>
							<option value="col-md-8">col-md-8</option>
							<option value="col-md-7">col-md-7</option>
							<option value="col-md-6">col-md-6</option>
							<option value="col-md-5">col-md-5</option>
							<option value="col-md-4">col-md-4</option>
							<option value="col-md-3">col-md-3</option>
							<option value="col-md-2">col-md-2</option>
							<option value="col-md-1">col-md-1</option>
							<option value="col-md-offset-12">col-md-offset-12</option>
							<option value="col-md-offset-11">col-md-offset-11</option>
							<option value="col-md-offset-10">col-md-offset-10</option>
							<option value="col-md-offset-9">col-md-offset-9</option>
							<option value="col-md-offset-8">col-md-offset-8</option>
							<option value="col-md-offset-7">col-md-offset-7</option>
							<option value="col-md-offset-6">col-md-offset-6</option>
							<option value="col-md-offset-5">col-md-offset-5</option>
							<option value="col-md-offset-4">col-md-offset-4</option>
							<option value="col-md-offset-3">col-md-offset-3</option>
							<option value="col-md-offset-2">col-md-offset-2</option>
							<option value="col-md-offset-1">col-md-offset-1</option>
							<option value="row">row</option>
							<option value="row-fluid">row-fluid</option>
							<option value="table-responsive">table-responsive</option>
						</select>
					</div>
					<div>Button 
						<select id="button-bootstrap-design-ui">
							<option value=""></option>
							<option value="btn">btn-default</option>
							<option value="btn-default">btn-default</option>
							<option value="btn-primary">btn-primary</option>
							<option value="btn-success">btn-success</option>
							<option value="btn-info">btn-info</option>
							<option value="btn-warning">btn-warning</option>
							<option value="btn-danger">btn-danger</option>
							<option value="btn-link">btn-link</option>
							<option value="btn-lg">btn-lg</option>
							<option value="btn-sm">btn-sm</option>
							<option value="btn-xs">btn-xs</option>
							<option value="btn-block">btn-block</option>
						</select>
					</div>
					<div>Form 
						<select id="form-bootstrap-design-ui">
							<option value=""></option>
							<option value="form-control">form-control</option>
							<option value="form-group">form-group</option>
							<option value="form-inline">form-inline</option>
							<option value="form-horizontal">btn-info</option>
						</select>
					</div>
					<div>Table 
						<select id="table-bootstrap-design-ui">
							<option value=""></option>
							<option value="table">table</option>
							<option value="table-striped">table-striped</option>
							<option value="table-bordered">table-bordered</option>
							<option value="table-hover">table-hover</option>
							<option value="table-condensed">table-condensed</option>
						</select>
					</div>
				</div>
			</div>
			<div>
				<div class="toggle-element-view">
					<strong>Layer </strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
				</div>
				<div class="children-element-view">
					<div><button class="btn btn-default" id="increase-layer-design-ui">Tăng</button></div>
					<div><button class="btn btn-default" id="decrease-layer-design-ui">Giảm</button></div>
				</div>
			</div>
			<div>
				<div class="toggle-element-view">
					<strong>Background </strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
				</div>
				<div class="children-element-view">
					<div>Background color <input type="color" id="background-color-design-ui" style="width:30px; border: none;"/></div>
				</div>
			</div>
			<div>
				<div class="toggle-element-view">
					<strong>Position</strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
				</div>
				<div class="children-element-view">
					<div>X <input type="number" id="X-design-ui" min="0" style="width:50px;"/></div>
					<div>Y <input type="number" id="Y-design-ui" min="0" style="width:50px;"/></div>
				</div>
			</div>
			<div>
				<div class="toggle-element-view">
					<strong>Size</strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
				</div>
				<div class="children-element-view">
					<div>Width <input type="number" id="width-design-ui" min="0" style="width:50px;"/></div>
					<div>Height <input type="number" id="height-design-ui" min="0" style="width:50px;"/></div>
				</div>
			</div>
			<div>
				<div class="toggle-element-view">
					<strong>Font styles</strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
				</div>
				<div class="children-element-view">
					<div>Font family 
						<select id="font-family-design-ui">
							<option value="Arial">Arial</option>
							<option value="Arial Black">Arial Black</option>
							<option value="Comic Sans MS">Comic Sans MS</option>
							<option value="Times New Roman">Times New Roman</option>
						</select>
					</div>
					<div>Font size <input type="number" id="font-size-design-ui" min="0" style="width:50px;"/></div>
					<div>Font color <input type="color"  id="font-color-design-ui" style="width:30px; border: none;"/></div>
				</div>
			</div>
			<div>
				<div class="toggle-element-view">
					<strong>Border </strong> <i class="glyphicon glyphicon-chevron-up pull-right"></i>
				</div>
				<div class="children-element-view">
					<div>Border-width <input  type="number" id="border-size-design-ui" min="0" style="width:50px;"/></div>
					<div>Border-radius <input  type="number" id="border-radius-design-ui" min="0" style="width:50px;"/></div>
					<div>Border-color <input type="color" id="border-color-design-ui" style="width:30px; border: none;"/></div>
				</div>
			</div>
		</div>
		<div id="right-content-diagram">
			<div id="paper-diagram">
				<div id="name-diagram">
					<span id="name-ui-show">${nameUI}</span>
					<a href="#modal-rename-ui" data-toggle="modal" id="a-rename-ui" style="display: none;">
						<i class="glyphicon glyphicon-pencil"></i> Đổi tên
					</a>
					<div class="pull-right">
						<a id="back-history-ui"><i class="glyphicon glyphicon-arrow-left"></i></a>&nbsp;&nbsp;
						<a id="foward-history-ui"><i class="glyphicon glyphicon-arrow-right"></i></a>&nbsp;&nbsp;
						<a id="fullscreen-ui"><i class="glyphicon glyphicon-fullscreen"></i></a>
					</div>
				</div>
				<div id="paperUI" ondrop="drop(event);" ondragover="allowDrop(event)"></div>
			</div>
			<div id="tool-diagram">
				<div id="element-design-ui">
					<div>
						<img id="form-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/form.svg"/>" alt="form"/>
					</div>
					<div>
						<img id="div-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/div.svg"/>" alt="div"/>
					</div>
					<div>
						<img id="button-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/button.svg"/>" alt="button"/>
					</div>
					<div>
						<img id="label-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/label.svg"/>" alt="label"/>
					</div>
					<div>
						<img id="textarea-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/textarea.svg"/>" alt="textarea"/>
					</div>
					<div>
						<img id="textbox-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/textbox.svg"/>" alt="textbox"/>
					</div>
					<div>
						<img id="radio-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/radio.svg"/>" alt="radio"/>
					</div>
					<div>
						<img id="radio-checked-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/radiochecked.svg"/>" alt="radiochecked"/>
					</div>
					<div>
						<img id="checkbox-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/checkbox.svg"/>" alt="checkbox"/>
					</div>
					<div>
						<img id="checkbox-checked-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/checkboxchecked.svg"/>" alt="checkboxchecked"/>
					</div>
					<div>
						<img id="selectbox-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/selectbox.svg"/>" alt="selectbox"/>
					</div>
					<div>
						<img id="table-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/table.svg"/>" alt="table" width="120" height="60"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Begin modal export svg -->
<div class="modal fade" id="modal-exportSVGUI">
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
					<input type="text" id="nameFileSVGUI" placeholder="Nhập tên file cần lưu" class="form-control" autofocus/>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-block btn-info" id="exportSVGUI">Xuất ảnh</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal export svg -->

<!-- Begin modal new ui -->
<div class="modal fade" id="modal-newUI">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><i class="glyphicon glyphicon-plus-sign"></i> Tạo giao diện mới</h4>
			</div>
			<div class="modal-body">
				<input type="text" id="input-nameUI" placeholder="Nhập tên giao diện" class="form-control" autofocus/>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-block btn-info" id="create-name-UI">Tạo</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal new ui -->

<!-- Begin modal list ui -->
<div class="modal fade" id="modal-listUI">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><i class="glyphicon glyphicon-th-large"></i> Danh sách các giao diện</h4>
			</div>
			<div class="modal-body" style="height: 520px; overflow: auto;">
				<div class="row" id="body-listUI">
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End modal list ui -->

<!-- Begin modal assign ui -->
<div class="modal fade" id="modal-assignUI">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><i class="glyphicon glyphicon-list"></i> Lựa chọn Use-case gán vào giao diện: <label class="name-ui-change-ajax"></label></h4>
			</div>
			<div class="modal-body" style="height: 450px; overflow: auto;">
				<div class="row" id="body-assignUI" style="padding-left: 20px;">
				</div>
			</div>
			<div class="modal-footer" >
				<button type="submit" class="btn btn-info" id="btn-assignUI">Gán</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal assign ui -->

<!-- Begin modal rename ui -->
<div class="modal fade" id="modal-rename-ui">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><i class="glyphicon glyphicon-pencil"></i> Đổi tên giao diện</h4>
			</div>
			<div class="modal-body">
				<input type="text" id="input-rename-ui" placeholder="Nhập tên giao diện" class="form-control" autofocus/>
			</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
				<button type="submit" class="btn btn-block btn-info" id="btn-rename-ui">Đổi tên</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal rename ui -->

<!-- Begin modal link ui -->
<div class="modal fade" id="modal-link-ui">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><i class="glyphicon glyphicon-link"></i> Kết nối thành phần của giao diện: <label class="name-ui-change-ajax"></label> đến các giao diện khác</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<h4 class="text-primary"><i class="glyphicon glyphicon-th-large"></i> Danh sách giao diện</h4>
						<span class="help-block">Màu xanh là đang kết nối - màu đỏ là chưa kết nối</span>
						<div id="list-link-ui"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End modal link ui -->

<!-- Begin modal screenflow ui -->
<div class="modal fade" id="modal-screenflow-ui">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><i class="glyphicon glyphicon-resize-horizontal"></i> Screenflow của giao diện: <label class="name-ui-change-ajax"></label></h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-4" id="screenflow-in-ui">
					</div>
					<div class="col-md-4">
						<div class="col-md-2 text-center"><i class="glyphicon glyphicon-arrow-right"></i></div>
						<div class="col-md-8 text-center"><label class="name-ui-change-ajax"></label></div>
						<div class="col-md-2 text-center"><i class="glyphicon glyphicon-arrow-right"></i></div>
					</div>
					<div class="col-md-4" id="screenflow-out-ui">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End modal screenflow ui -->

<!-- Begin modal table size ui -->
<div class="modal fade" id="modal-table-size-ui">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><i class="glyphicon glyphicon-pencil"></i> Nhập thông tin</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					Số cột <input type="number" id="input-col-ui" min="0" placeholder="Nhập số cột" class="form-control" />
				</div>
				<div class="form-group">
					Số dòng <input type="number" id="input-row-ui" min="0" placeholder="Nhập số dòng" class="form-control"/>
				</div>
				<input type="hidden" id="input-hidden-x-position" />
				<input type="hidden" id="input-hidden-y-position" />
			</div>
			<div class="modal-footer">
				<button class="btn btn-block btn-info" id="btn-table-size-ui">Tạo bảng</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal table size ui -->

<script src="<c:url value="/resources/js/joint.nojquery.min.js" />" ></script>
<script src="<c:url value="/resources/js/global.js" />" ></script>
<script src="<c:url value="/resources/js/xml2json.js" />" ></script>
<script src="<c:url value="/resources/js/saveSvgAsPng.js" />" ></script>
<script src="<c:url value="/resources/js/joint.shapes.ui.custom.js"/>"></script>
<script src="<c:url value="/resources/js/diagramui.js" />"></script>
</body>
</html>