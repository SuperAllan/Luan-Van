<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid">
	<div class="form-group tool">
		<div class="btn-group-vertical">
			<div class="btn-group">
				<button type="button" class="btn btn-success" id="saveDiagram" disabled="disabled">
					<span style="display: none;" id="icon-not-save"><i class="glyphicon glyphicon-floppy-remove"></i> Chưa lưu</span>
					<span id="icon-saved"><i class="glyphicon glyphicon-floppy-saved"></i> Đã lưu</span>
				</button>
			</div>
			<button type="button" class="btn btn-default tool-btn" id="arrow">
				<img src="<c:url value="/resources/img/arrow.png" />" alt="arrow"/>
			</button>
			<button type="button" class="btn btn-default tool-btn" id="actor">
				<img src="<c:url value="/resources/img/actor.svg" />" alt="actor" width="24" height="24"/>
			</button>
			<button type="button" class="btn btn-default tool-btn" id="usecase">
				<img src="<c:url value="/resources/img/usecase.png" />" alt="usecase"/>
			</button>
			<a class="btn btn-primary" data-toggle="modal" href='#modal-exportJSON'>
				Export JSON
			</a>
			<a class="btn btn-danger" data-toggle="modal" href='#modal-exportXML'>
				Export XML
			</a>
			<a class="btn btn-info" data-toggle="modal" href='#modal-exportSVG'>
				Export SVG
			</a>
			<button type="button" class="btn btn-success" id="loadFile">
				Import JSON
			</button>
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
					<label for="">Tác nhân sử dụng</label>
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

<div class="div-loading">
</div>
