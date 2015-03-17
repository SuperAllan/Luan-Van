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
		<button type="button" class="btn btn-default" id="div-design-ui">
			<i class="mdi-editor-border-style"></i>
		</button>
		<button type="button" class="btn btn-default" id="button-design-ui">
			<i class="mdi-communication-stay-primary-landscape"></i>
		</button>
		<button type="button" class="btn btn-default" id="label-design-ui">
			<i class="mdi-editor-format-bold"></i>
		</button>
		<button type="button" class="btn btn-default" id="textarea-design-ui">
			<i class="mdi-image-crop-16-9"></i>
		</button>
		<button type="button" class="btn btn-default" id="textbox-design-ui">
			<i class="mdi-image-crop-7-5"></i>
		</button>
		<button type="button" class="btn btn-default" id="radio-checked-design-ui">
			<i class="mdi-toggle-radio-button-on"></i>
		</button>
		<button type="button" class="btn btn-default" id="radio-design-ui">
			<i class="mdi-toggle-radio-button-off"></i>
		</button>
		<button type="button" class="btn btn-default" id="checkbox-checked-design-ui">
			<i class="mdi-toggle-check-box"></i></button>
		<button type="button" class="btn btn-default" id="checkbox-design-ui">
			<i class="mdi-toggle-check-box-outline-blank"></i>
		</button>
		<a class="btn btn-success" data-toggle="modal" href='#modal-exportXMLUI'>
			XML
		</a>
		<a class="btn btn-success" data-toggle="modal" href='#modal-exportSVGUI'>
			SVG
		</a>
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
<form>
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
</form>
<!-- End modal export svg -->