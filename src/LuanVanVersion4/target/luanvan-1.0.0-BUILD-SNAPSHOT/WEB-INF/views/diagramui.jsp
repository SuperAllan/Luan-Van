<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="properties-design-ui">
	<div class="properties-child">
  		<span>X:</span>
		<input type="number" id="X-design-ui" min="0" style="width:50px;"/>
	</div>
	<div class="properties-child">
  		<span>Y:</span>
		<input type="number" id="Y-design-ui" min="0" style="width:50px;"/>
	</div>
	<div class="properties-child">
  		<span>W:</span>
		<input type="number" id="width-design-ui" min="0" style="width:50px;"/>
	</div>
	<div class="properties-child">
  		<span>H:</span>
		<input type="number" id="height-design-ui" min="0" style="width:50px;"/>
	</div>
	<div class="properties-child">
  		<span>Font-family:</span>
		<select id="font-family-design-ui">
			<option value="Arial">Arial</option>
			<option value="Arial Black">Arial Black</option>
			<option value="Comic Sans MS">Comic Sans MS</option>
			<option value="Times New Roman">Times New Roman</option>
		</select>
	</div>
	<div class="properties-child">
  		<span>Font-size:</span>
		<input type="number" id="font-size-design-ui" min="0" style="width:50px;"/>
	</div>
	<div class="properties-child">
  		<span>Border-width:</span>
		<input  type="number" id="border-size-design-ui" min="0" style="width:50px;"/>
	</div>
	<div class="properties-child">
  		<span>Border-radius:</span>
		<input  type="number" id="border-radius-design-ui" min="0" style="width:50px;"/>
	</div>
	<div class="properties-child">
  		<span>Background-color:</span>
		<input type="color" id="background-color-design-ui" style="width:30px; border: none;"/>
	</div>
	<div class="properties-child">
		<span>Border-color:</span>
		<input type="color" id="border-color-design-ui" style="width:30px; border: none;"/>
	</div>
	<div class="properties-child">
		<span>Font-color:</span>
		<input type="color"  id="font-color-design-ui" style="width:30px; border: none;"/>
	</div>
</div>
<div class="form-group">
	<div id="paperUI">
	</div>
</div>

<div id="tool-UI">
	<div class="btn-group-vertical" id="element-design-ui">
		<button type="button" class="btn btn-default" id="saveDiagramUI" disabled="disabled" data-toggle="tooltip" data-placement="left" title="Lưu file">
			<span style="display: none;" id="icon-not-save-UI"><i class="mdi-notification-event-busy"></i></span>
			<span id="icon-saved-UI"><i class="mdi-notification-event-available"></i></span>
		</button>
		<button type="button" class="btn btn-default" id="new-design-ui" data-toggle="tooltip" data-placement="left" title="Tạo mới">
			<i class="mdi-content-add-box"></i>
		</button>
		<button type="button" class="btn btn-default" id="open-design-ui" data-toggle="tooltip" data-placement="left" title="Mở file" style="border-bottom: 1px solid #0769AD;">
			<i class="mdi-file-folder-open"></i>
		</button>
		<button type="button" class="btn btn-default" id="div-design-ui" data-toggle="tooltip" data-placement="left" title="div">
			<i class="mdi-editor-border-style"></i>
		</button>
		<button type="button" class="btn btn-default" id="button-design-ui" data-toggle="tooltip" data-placement="left" title="Button">
			<i class="mdi-communication-stay-primary-landscape"></i>
		</button>
		<button type="button" class="btn btn-default" id="label-design-ui" data-toggle="tooltip" data-placement="left" title="Label">
			<i class="mdi-editor-format-bold"></i>
		</button>
		<button type="button" class="btn btn-default" id="textarea-design-ui" data-toggle="tooltip" data-placement="left" title="Textarea">
			<i class="mdi-image-crop-16-9"></i>
		</button>
		<button type="button" class="btn btn-default" id="textbox-design-ui" data-toggle="tooltip" data-placement="left" title="Textbox">
			<i class="mdi-image-crop-7-5"></i>
		</button>
		<button type="button" class="btn btn-default" id="radio-checked-design-ui" data-toggle="tooltip" data-placement="left" title="Radio">
			<i class="mdi-toggle-radio-button-on"></i>
		</button>
		<button type="button" class="btn btn-default" id="radio-design-ui" data-toggle="tooltip" data-placement="left" title="Radio">
			<i class="mdi-toggle-radio-button-off"></i>
		</button>
		<button type="button" class="btn btn-default" id="checkbox-checked-design-ui" data-toggle="tooltip" data-placement="left" title="Checkbox">
			<i class="mdi-toggle-check-box"></i></button>
		<button type="button" class="btn btn-default" id="checkbox-design-ui" data-toggle="tooltip" data-placement="left" title="Checkbox" style="border-bottom: 1px solid #0769AD;">
			<i class="mdi-toggle-check-box-outline-blank"></i>
		</button>
		<button class="btn btn-default" data-toggle="tooltip" data-placement="left" title="UI" id="viewListUI">
			<i class="mdi-action-view-list"></i>
		</button>
		<button class="btn btn-default" data-toggle="tooltip" data-placement="left" title="SVG" id="btn-svgUI">
			<i class="mdi-content-inbox"></i>
		</button>
	</div>
</div>

<!-- Begin modal export XML -->
<div class="modal fade" id="modal-exportXMLUI">
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
				<button type="button" class="btn btn-info" id="exportXMLUI">
					<i class="glyphicon glyphicon-save"></i> OK</button>
			</div>
		</div>
	</div>
</div>
<!-- End modal export XML -->

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
				<button type="button" class="btn btn-info" id="exportSVGUI">
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
					<%-- <c:forEach items="${listui}" var="ui">
					  <div class="col-md-4">
					    <div class="thumbnail">
					    	<div style="border: 1px solid #ddd;">
					    		<img class="image-details-ui" width="254" height="200" id='${ui.image }' />
					    	</div>
					      <div class="caption">
					        <h3>${ui.nameui}</h3>
					        <button class="btn btn-primary edit-details-ui" id="${ui.nameui}" style="font-size: 20px;"><i class="mdi-content-create"></i></button>
					        <button class="btn btn-danger delete-details-ui" id="${ui.nameui}" style="font-size: 20px;"><i class="mdi-action-delete"></i></button>
					      </div>
					    </div>
					  </div>
					</c:forEach> --%>
				</div>
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

<!-- Upload file ui -->
<input type="file"  style="display:none;" id="openFileUI"/>
<!-- End upload file ui -->