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
<input type="hidden" id="nameProject" value="${project.tenproject}" />
<div id="path" style="display:none;">${path}</div>
<div id="wrapper-diagram">
	<div id="properties-diagram">
		<div class="btn-group">
			<a class="btn btn-default" href="${contextPath}/detailProject?name=${project.tenproject}"><i class="glyphicon glyphicon-home"></i> Thông tin chi tiết</a>
			<button class="btn btn-default" id="new-design-ui"><i class="glyphicon glyphicon-plus-sign"></i> Tạo mới</button>
			<button class="btn btn-default" id="btn-svgUI"><i class="glyphicon glyphicon-picture"></i> Xuất ảnh</button>
			<button class="btn btn-default" id="viewListUI"><i class="glyphicon glyphicon-th-large"></i> Danh sách giao diện</button>
			<button class="btn btn-default" id="assign-usecase-design-ui"><i class="glyphicon glyphicon-list"></i> Gán giao diện vào use case</button>
			<button class="btn btn-default" id="saveDiagramUI" disabled="disabled">
				<i class="glyphicon glyphicon-floppy-saved"></i> Đã lưu
			</button>
		</div>
		<div class="btn-group pull-right" >
			<sec:authorize access="isAuthenticated()">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
						<div class="dropdown pull-right">			  
							<a type="button" id="dropdownMenu1" data-toggle="dropdown" class="btn btn-default" aria-expanded="true" style="cursor: pointer; color: #333; margin-right: 100px;">
						    <c:if test="${not empty user.image}">
						    	<img src="<c:url value="${user.image}" />" class="img-rounded" style="width: 20px; height: 20px;">
						    </c:if>
						    ${pageContext.request.userPrincipal.name}
						    <span class="caret"></span>
						  </a>
						  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
						    <li role="presentation"><a role="menuitem" tabindex="-1" href="/luanvan/background" ><i class="mdi-action-assignment-ind"></i> Thông tin chung</a></li>
						    <li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:formSubmit()"><i class="mdi-action-settings-power"></i> Đăng xuất</a></li>
						  </ul>
						</div>
					</c:if>
			</sec:authorize>
		</div>
	</div>
	<div id="content-diagram">
		<div id="list-element-diagram">
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
						<i class="glyphicon glyphicon-pencil"></i>
					</a>
				</div>
				<div id="paperUI" ondrop="drop(event);" ondragover="allowDrop(event)"></div>
			</div>
			<div id="tool-diagram">
				<div id="element-design-ui">
					<div>
						<img id="div-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/div.svg"/>" alt="actor"/>
					</div>
					<div>
						<img id="button-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/button.svg"/>" alt="actor"/>
					</div>
					<div>
						<img id="label-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/label.svg"/>" alt="actor"/>
					</div>
					<div>
						<img id="textarea-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/textarea.svg"/>" alt="actor"/>
					</div>
					<div>
						<img id="textbox-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/textbox.svg"/>" alt="actor"/>
					</div>
					<div>
						<img id="radio-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/radio.svg"/>" alt="actor"/>
					</div>
					<div>
						<img id="radio-checked-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/radiochecked.svg"/>" alt="actor"/>
					</div>
					<div>
						<img id="checkbox-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/checkbox.svg"/>" alt="actor"/>
					</div>
					<div>
						<img id="checkbox-checked-design-ui" draggable="true" ondragstart="drag(event)" src="<c:url value="/resources/img/checkboxchecked.svg"/>" alt="actor"/>
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
				<h4 class="modal-title">Lưu file SVG</h4>
			</div>
			<div class="modal-body" style="padding: 0;">
				<input type="text" id="nameFileSVGUI" placeholder="Nhập tên file cần lưu" class="form-control" autofocus/>
			</div>
			<div class="modal-footer" style="padding: 0; border: none;">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
				<button type="submit" class="btn btn-info" id="exportSVGUI">
					<i class="glyphicon glyphicon-save"></i> OK</button>
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
				<h4 class="modal-title">Tạo giao diện mới</h4>
			</div>
			<div class="modal-body">
				<input type="text" id="input-nameUI" placeholder="Nhập tên giao diện" class="form-control" autofocus/>
			</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
				<button type="submit" class="btn btn-info" id="create-name-UI">
					<i class="glyphicon glyphicon-save"></i> OK</button>
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
				<h4 class="modal-title">Danh sách các giao diện</h4>
			</div>
			<div class="modal-body" >
				<div class="row" id="body-listUI">
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End modal list ui -->

<!-- Begin modal list ui -->
<div class="modal fade" id="modal-assignUI">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Lựa chọn usecase gán vào giao diện: <strong>${nameUI}</strong></h4>
			</div>
			<div class="modal-body" >
				<div class="row" id="body-assignUI" style="padding-left: 20px;">
					<c:forEach items="${usecases}" var="usecase">
						<div class="form-group">
							<label><input type="checkbox" class="checkbox-assignUI" value="${usecase.usecaseid}" /> ${usecase.nameofuc}</label>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="modal-footer" >
				<button type="submit" class="btn btn-info" id="btn-assignUI">
					<i class="glyphicon glyphicon-plus-sign"></i> Gán</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal list ui -->

<!-- Begin modal rename ui -->
<div class="modal fade" id="modal-rename-ui">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Đổi tên giao diện</h4>
			</div>
			<div class="modal-body">
				<input type="text" id="input-rename-ui" placeholder="Nhập tên giao diện" class="form-control" autofocus/>
			</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button> -->
				<button type="submit" class="btn btn-info" id="btn-rename-ui">
					<i class="glyphicon glyphicon-save"></i> OK</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal rename ui -->

<script src="<c:url value="/resources/js/joint.nojquery.min.js" />" ></script>
<script src="<c:url value="/resources/js/diagram.js" />" ></script>
<script src="<c:url value="/resources/js/xml2json.js" />" ></script>
<script src="<c:url value="/resources/js/jointshapesumlcustom.js"/>"></script>
<script src="<c:url value="/resources/js/detail-project.js" />"></script>
<script src="<c:url value="/resources/js/diagramui.js" />"></script>
<script src="<c:url value="/resources/js/joint.shapes.ui.custom.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		divProperties(true, "#aaaaaa");
		// Begin loading diagram
		if ($("#path").html() && $("#path").html() != "") {
			graphUI.fromJSON(JSON.parse(decodeURIComponent(window.atob($("#path").html()))));
		}
		if ($("#name-ui-show").html() != "") {
			$("#a-rename-ui").show("fade");
		} else {
			$("#a-rename-ui").hide("fade");
		}
		// End loading diagram
		});
</script>
</body>
</html>