// Begin declare variable
var graph = new joint.dia.Graph;
var paper = new joint.dia.Paper({
    el: $('#paper'),
    width: 1500,
    fill: 'white',
    height: 1000,
    model: graph,
    gridSize: 1
});
var chooseElement = "";
var elementDown = null;
var hasDrawing = false;
var link = null;
var hasDragDropTool = false;
var hasBlankPointerDownDiagram = null;
var selectViewDiagram = null;
// End declare variable

$(document).ready(function(){
	$(".formatNameProject").each(function(){
		if($(this).text().length > 70){
		var formatTitle = $.trim($(this).text()).substring(0,70).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
	// Begin loading diagram
	if ($("#path").html() && $("#path").html() != "") {
		graph.fromJSON(JSON.parse(decodeURIComponent(window.atob($("#path").html()))));
	}
	if ($("#name-diagram-show").html() != "") {
		$("#a-rename-diagram").show("fade");
	} else {
		$("#a-rename-diagram").hide("fade");
	}
	// End loading diagram
});

//var dmp = new diff_match_patch();
function searchUsecaseActor(value){
	if(value == '' || value == ' ' || value == null){
		$('.forSearch').each(function(){
			$(this).css("display", "");
		});
	}else{
		value = $.trim(value.toUpperCase());
		
		$('.forSearch').each(function(){
			var temp = $.trim($(this).text().toUpperCase());
			//var match = dmp.match_main(temp, value, 1);
			if(temp.indexOf(value) > -1){
				$(this).css("display", "");
			}else{
				$(this).css("display", "none");
			}
		});
	}
}

// Begin set icon save
function setIconSave(value) {
	if (value == "saved") {
		$("#saveDiagram").attr("style", "background: #5cb85c; color: white;");
		$("#saveDiagram").html("<i class='glyphicon glyphicon-floppy-saved'></i> Lưu");
	} else {
		$("#saveDiagram").attr("style", "background: #d9534f; color: white;");
		$("#saveDiagram").html("<i class='glyphicon glyphicon-floppy-remove'></i> Lưu");
	}
}
// End set icon save

$("#a-new-diagram").on('click', function() {
	$("#input-new-diagram").val("");
	$("#modal-new-diagram").modal('show');
});

// Begin create new diagram
$("#btn-new-diagram").on('click', function() {
	if ($("#input-new-diagram").val() != "") {
		$.ajax({
			url: '/luanvan/diagram/creatediagram',
			data: "nameProject=" + $("#nameProject").val() + "&nameDiagram=" + $("#input-new-diagram").val(),
			success: function(result) {
				if (result != "") {
					$("#name-diagram-show").html($("#input-new-diagram").val());
					$("#modal-new-diagram").modal('hide');
					$("#a-rename-diagram").show('fade');
					setIconSave("saved");
					graph.clear();
				} else {
					alert("Tên nhóm đã tồn tại");
				}
			},
			error: function() {
				alert("Đã xảy ra lỗi. Không thể tạo được sơ đồ mới. Liên hệ admin để giải quyết, cảm ơn!");
			}
		});
	} else {
		alert("Không được bỏ trống");
	}
	
});
// End create new diagram

// Begin rename diagram
$("#btn-rename-diagram").on('click', function() {
	if ($("#input-rename-diagram").val() != "") {
		$.ajax({
			url: '/luanvan/diagram/renamediagram',
			data: "nameProject=" + $("#nameProject").val() + "&nameDiagramNew=" + $("#input-rename-diagram").val()
			+ "&nameDiagramOld=" + $("#name-diagram-show").html(),
			success: function(result) {
				if (result != "") {
					$("#name-diagram-show").html($("#input-rename-diagram").val());
					$("#modal-rename-diagram").modal('hide');
					$("#a-rename-diagram").show('fade');
				} else {
					alert("Tên nhóm đã tồn tại");
				}
			},
			error: function() {
				alert("Đã xảy ra lỗi. Không thể đổi tên nhóm. Liên hệ admin để giải quyết, cảm ơn!");
			}
		});
	} else {
		alert("Tên nhóm không được bỏ trống");
	}
});
// End rename diagram

// Begin function test duplicate name
function hasDuplicateName(array) {
	for (var i = 0; i < array.length - 1; i++) {
		for (var j = i + 1; j < array.length; j++) {
			var splitArray = array[i].split("=");
			var splitArrayTemp = array[j].split("=");
			if (splitArray[0] == splitArrayTemp[0]) {
				return true;
			}
		}
	}
	return false;
}
// End function test duplicate name

// Begin save diagram
$("#saveDiagram").on('click', function() {
	if ($("#name-diagram-show").html() != "") {
		var allElement = graph.getElements();
		var ucs = new Array();
		var actors = new Array();
		for (var i = 0; i < allElement.length; i++) {
			if (allElement[i].attributes.type == "uml.Usecase") {
				
				var uc = allElement[i].attributes.name + "=";
				uc += allElement[i].attributes.created + "=";
				ucs.push(uc);
			}
			if (allElement[i].attributes.type == "uml.Actor") {
				
				var actor = allElement[i].attributes.name + "=";
				actor += allElement[i].attributes.created + "="
				actors.push(actor);
			}
		}
		
		if (!hasDuplicateName(actors) && !hasDuplicateName(ucs)) {
			// Khong hien thi cac view khong can thiet khi xuat hinh
		    toggleElementLinkView('none');
			var w = paper.options.width;
			var h = paper.options.height;
			paper.fitToContent({padding: 5});
			var svg = paper.svg;
		    var serializer = new XMLSerializer();
		    var svgXML = serializer.serializeToString(svg);
			var image = window.btoa("data:image/svg+xml;utf8," + encodeURIComponent(svgXML));
			paper.setDimensions(w, h);
			// Khong hien thi cac view khong can thiet khi xuat hinh
		    toggleElementLinkView('block');
			$.ajax({
				url : "/luanvan/diagram/savediagram",
				type: "post",
				data: "nameProject=" + $("#nameProject").val() + "&nameDiagram=" + $("#name-diagram-show").html()
					+ "&actorArray=" + actors + "&ucArray=" + ucs + "&path=" + window.btoa(encodeURIComponent(JSON.stringify(graph)))
					+ "&image=" + image,
				success: function(result) {
					if (result != "") {
						setIconSave("saved");
						listActor();
						listUsecase();
					} else {
						alert("Tên đối tượng đã tồn tại đã tồn tại trong dự án");
					}
				},
				error: function() {
					alert("Không thể lưu sơ đồ, xin vui lòng liên hệ Admin để giải quyết, cảm ơn!");
				}
			});
		} else {
			alert("Trùng tên không thể lưu");
		}
	} else {
		$("#modal-new-diagram").modal('show');
	}
});
// End save diagram

// Begin list diagram
$("#view-list-diagram").on("click", function() {
	$.ajax({
		url: "/luanvan/diagram/viewlistdiagram",
		data: "nameProject=" + $("#nameProject").val(),
		success: function(result) {
			var object = $.parseJSON(result);
			var str = "";
			for (var i = 0; i < object.diagram.length; i++) {
				str += '<div class="col-md-4"><div class="thumbnail" style="background: #f2f2f2;"><div style="background: white;">';
				str += '<img width="260" height="200" src="' + window.atob(object.diagram[i].image) + '" /></div>';
				str += '<div class="caption">';
		        str += '<h3 style="color: #333;">' + object.diagram[i].name + '</h3>';
		        str += '<div class="btn-group" style="width: 100%;">'
		        str += '<button class="btn btn-primary edit-details-diagram" id="' + object.diagram[i].name + '" style="font-size: 20px; width: 50%;"><i class="glyphicon glyphicon-pencil"></i></button>';
		        str += '<button class="btn btn-danger delete-details-diagram" id="' + object.diagram[i].name + '" style="font-size: 20px; width: 50%;"><i class="glyphicon glyphicon-trash"></i></button>';
		        str += '</div></div></div></div>';
			}
			
			$("#body-list-diagram").html(str);
			
			// Delete element when click button delete
			$(".delete-details-diagram").on('click', function() {
				if (confirm("Bạn có chắc chắn xóa không?")) {
					$(this).parent().parent().parent().parent().hide("fade");
					var nameDiagram = $(this).attr("id");
					$.ajax({
						url: "/luanvan/diagram/deletediagram",
						data: "nameProject=" + $("#nameProject").val() + "&nameDiagram=" + nameDiagram,
						success: function(result) {
							if ($("#name-diagram-show").html() == nameDiagram || $("#body-list-diagram").html() == "") {
								$("#name-diagram-show").html("");
								$("#a-rename-diagram").hide('fade');
								setIconSave("saved");
								graph.clear();
							}
						},
						error: function() {
							alert("error");
						}
					});
				}
			});
			
			//Edit diagram when click button edit
			$(".edit-details-diagram").on('click', function() {
				var nameDiagram = $(this).attr("id");
				$.ajax({
					url: "/luanvan/diagram/editdiagram",
					data: "nameProject=" + $("#nameProject").val() + "&nameDiagram=" + nameDiagram,
					success: function(result) {
						graph.clear();
						if (result) {
							graph.fromJSON(JSON.parse(decodeURIComponent(window.atob(result))));
						}
						var allElement = graph.getElements();
						for (var i = 0; i < allElement.length; i++) {
							if (allElement[i].attributes.type == "uml.Actor") {
								if (!hasIDActor(allElement[i].attributes.created)) {
									allElement[i].remove();
								} else {
									allElement[i].attributes.name = setAgainIDActor(allElement[i].attributes.created);
									allElement[i].attr(".name/text", setAgainIDActor(allElement[i].attributes.created));
								}
							}
							if (allElement[i].attributes.type == "uml.Usecase") {
								if (!hasIDUsecase(allElement[i].attributes.created)) {
									allElement[i].remove();
								} else {
									allElement[i].attributes.name = setAgainIDUsecase(allElement[i].attributes.created);
									allElement[i].attr(".name/text", setAgainIDUsecase(allElement[i].attributes.created));
								}
							}
						}
						$("#modal-list-diagram").modal('hide');
						$("#name-diagram-show").html(nameDiagram);
						$("#a-rename-diagram").show('fade');
					},
					error: function() {
						alert("Không thể tải được sơ đồ để chỉnh sửa, vui lòng liên hệ ADMIN để giải quyết");
					}
				});
			});
		},
		error: function() {
			alert("Không thể tải được danh sách sơ đồ, vui lòng liên hệ ADMIN để giải quyết");
		}
	});
});
// End list diagram

// Begin test name actor
function hasIDActor(id) {
	var arrayTest = [];
	$("#children-actor-view div").each(function() {
		arrayTest.push(this.id);
	});
	for (var i = 0; i < arrayTest.length; i++) {
		if (id == arrayTest[i]) {
			return true;
		}
	}
	return false;
}
// End test name actor

//Begin set again actor
function setAgainIDActor(id) {
	var arrayTest = [];
	$("#children-actor-view div").each(function() {
		arrayTest.push(this.id);
	});
	for (var i = 0; i < arrayTest.length; i++) {
		if (id == arrayTest[i]) {
			return $("#" + arrayTest[i]).html();
		}
	}
}
// End set again actor

//Begin test name usecase
function hasIDUsecase(id) {
	var arrayTest = [];
	$("#children-usecase-view div").each(function() {
		arrayTest.push(this.id);
	});
	for (var i = 0; i < arrayTest.length; i++) {
		if (id == arrayTest[i]) {
			return true;
		}
	}
	return false;
}
// End test name usecase

//Begin set again name usecase
function setAgainIDUsecase(id) {
	var arrayTest = [];
	$("#children-usecase-view div").each(function() {
		arrayTest.push(this.id);
	});
	for (var i = 0; i < arrayTest.length; i++) {
		if (id == arrayTest[i]) {
			return $("#" + arrayTest[i]).html();
		}
	}
}
// End set again name usecase

/*
 * Toggle element left side bar
 */
$(".toggle-element-view").on('click', function() {
	$(this).parent().find('div.children-element-view').toggle('fade');
	if ($(this).find('i').hasClass("glyphicon-chevron-up")) {
		$(this).find('i').removeClass("glyphicon-chevron-up");
		$(this).find('i').addClass("glyphicon-chevron-down");
	} else {
		$(this).find('i').removeClass("glyphicon-chevron-down");
		$(this).find('i').addClass("glyphicon-chevron-up");
	}
});

function removeRoleActorNew(idActor, idUsecase) {
	$.ajax({
		type: "post",
		url: "/luanvan/diagram/removeroleactor",
		data: "idActor=" + idActor + "&idUsecase=" + idUsecase,
		success: function() {
		},
		error: function() {
			alert("Không thể xóa quyền của actor");
		}
	});
}

graph.on('remove', function(cellView) {
	if (cellView.attributes.type == "uml.Association") {
		var source = graph.getCell(cellView.attributes.source.id);
		var target = graph.getCell(cellView.attributes.target.id);
		
		if (source && source.attributes && source.attributes.type && source.attributes.type == "uml.Actor") {
			if (target && target.attributes && target.attributes.type) {
				removeRoleActorNew(source.attributes.created, target.attributes.created);
			}
		}
		
		if (target && target.attributes && target.attributes.type && target.attributes.type == "uml.Actor") {
			if (source && source.attributes && source.attributes.type) {
				removeRoleActorNew(target.attributes.created, source.attributes.created);
			}
		}
	}
//	setPathDiagram();
});

function removeRoleActor(actor, nameRemove) {
	var roles = actor.attributes.role;
	actor.attributes.role = "";
	if (roles) {
		var r = roles.split("/");
		for (var i = 0; i < r.length; i++) {
			if (r[i] != "") {
				var attrs = r[i].split("_");
				if (attrs[1] != nameRemove) {
					actor.attributes.role += r[i] + "/";
				}
			}
		}
	}
}

function testRoleActor(roles, nameTest) {
	if (roles) {
		var r = roles.split("/");
		for (var i = 0; i < r.length; i++) {
			var attrs = r[i].split("_");
			if (attrs[1] == nameTest) {
				return attrs[0];
			}
		}
	}
	return null;
}

paper.on('cell:pointerdblclick ', function(cellView, evt, x, y) {
	if (cellView.model.attributes.type == "uml.Actor") {
		$.ajax({
			url: "/luanvan/diagram/getinfoactor",
			data: "nameProject=" + $("#nameProject").val() + "&id=" + cellView.model.attributes.created,
			success: function(result) {
				var object = $.parseJSON(result);
				$("#name-modal-actor").html(cellView.model.attributes.name);
				$("#id-modal-actor").val(cellView.model.attributes.created);
				if (object.actor.mota != "null") {
					$("#description-modal-actor").val(object.actor.mota);
				} else {
					$("#description-modal-actor").val("");
				}
				$("#level-modal-actor").val(object.actor.mucdo);
				var phanLoai = "";
				for (var i = 0; i < object.actor.phanloai.length; i++) {
					phanLoai += '<div class="form-group role-actor">' + object.actor.phanloai[i].name;
					phanLoai += '<select class="pull-right select-role" id="' + object.actor.phanloai[i].id + '">';
					if (object.actor.phanloai[i].vaitro == "0") {
						phanLoai += '<option value="0" selected>Tác nhân phụ</option>';
						phanLoai += '<option value="1">Tác nhân chính</option>';
					} else {
						phanLoai += '<option value="0">Tác nhân phụ</option>';
						phanLoai += '<option value="1" selected>Tác nhân chính</option>';
					}
					phanLoai += '</select></div>';
				}
				$("#listUCOfActor").html(phanLoai);
				$("#modal-actor").modal('show');
			},
			error: function() {
				alert("Không thể lấy thông tin tác nhân.");
			}
		});
	}
	if (cellView.model.attributes.type == "uml.Usecase") {
		$.ajax({
			url: "/luanvan/diagram/getinfousecase",
			data: "nameProject=" + $("#nameProject").val() + "&id=" + cellView.model.attributes.created,
			success: function(result) {
				var object = $.parseJSON(result);
				$("#name-modal-usecase").html(cellView.model.attributes.name);
				$("#id-modal-usecase").val(cellView.model.attributes.created);
				if (object.usecase.mota != "null") {
					$("#description-modal-usecase").val(object.usecase.mota);
				} else {
					$("#description-modal-usecase").val("");
				}
				$("#level-modal-usecase").val(object.usecase.mucdo);
				$("#a-question-upload-file").attr("href", "/luanvan/detailUsecase?name=" + $("#nameProject").val() + "&usecaseid=" + cellView.model.attributes.created +"&nameDiagram="+ $("#name-diagram-show").html());
				if (object.usecase.tinhtien == "0") {
					$("#pay-money-modal-usecase").prop("checked", false);
				} else {
					$("#pay-money-modal-usecase").prop("checked", true);
				}
				var nhomuc = "";
				for (var i = 0; i < object.usecase.dsnhom.length; i++) {
					if (object.usecase.dsnhom[i].id == object.usecase.nhomid ) {
						$("#input-group-modal-usecase").val(object.usecase.dsnhom[i].name);
						nhomuc += '<option value="' + object.usecase.dsnhom[i].name  + '" selected>' + object.usecase.dsnhom[i].name + '</option>';
					} else {
						nhomuc += '<option value="' + object.usecase.dsnhom[i].name  + '">' + object.usecase.dsnhom[i].name + '</option>';
					}
				}
				$("#select-group-modal-usecase").html(nhomuc);
				getRelationshipUsecase(cellView.model);
				var dsui = "";
				for (var i = 0; i < object.usecase.dsui.length; i++) {
					dsui += '<a target="_blank" href="/luanvan/diagramui/viewdiagramui?nameProject=' + $("#nameProject").val() + '&nameUI=' + object.usecase.dsui[i].name + '">' + object.usecase.dsui[i].name + '</a><br>';
				}
				$("#listUIOfUC").html(dsui);
				$("#modal-usecase").modal('show');
			},
			error: function() {
				alert("Không thể lấy thông tin usecase.");
			}
		});
	}
});

function getRelationshipActor(model) {
	
	// Lay link ma phan tu ket noi toi
	var linkConnectOut = graph.getConnectedLinks(model, { outbound: true });
	var linkConnectIn = graph.getConnectedLinks(model, { inbound: true });

	$("#listUCOfActor").html("");
	var actorLists = "";
	var tempSelectRole = "";
	for (var i = 0; i < linkConnectOut.length; i++) {
		var target = graph.getCell(linkConnectOut[i].attributes.target.id);
		if (target.attributes.type == "uml.Usecase") {
			var role = testRoleActor(model.attributes.role, target.attributes.created);
			if (role) {
				if (role == "1") {
					tempSelectRole = "<select class='pull-right select-role' id='" + target.attributes.created
					+ "'><option value='0'>Tác nhân phụ</option><option value='1' selected>Tác nhân chính</option></select>";
				} else {
					tempSelectRole = "<select class='pull-right select-role' id='" + target.attributes.created
					+ "'><option value='0' selected>Tác nhân phụ</option><option value='1'>Tác nhân chính</option></select>";
				}
			} else {
				tempSelectRole = "<select class='pull-right select-role' id='" + target.attributes.created
				+ "'><option value='0'>Tác nhân phụ</option><option value='1'>Tác nhân chính</option></select>";
			}
			actorLists += "<div class='form-group role-actor'>" + target.attributes.name + tempSelectRole + "</div>";
		}
	}
	for (var i = 0; i < linkConnectIn.length; i++) {
		var target = graph.getCell(linkConnectIn[i].attributes.source.id);
		if (target.attributes.type == "uml.Usecase") {
			var role = testRoleActor(model.attributes.role, target.attributes.created);
			if (role) {
				if (role == 1) {
					tempSelectRole = "<select class='pull-right select-role' id='" + target.attributes.created
					+ "'><option value='0'>Tác nhân phụ</option><option value='1' selected>Tác nhân chính</option></select>";
				} else {
					tempSelectRole = "<select class='pull-right select-role' id='" + target.attributes.created
					+ "'><option value='0' selected>Tác nhân phụ</option><option value='1' >Tác nhân chính</option></select>";
				}
			} else {
				tempSelectRole = "<select class='pull-right select-role' id='" + target.attributes.created
				+ "'><option value='0'>Tác nhân phụ</option><option value='1'>Tác nhân chính</option></select>";
			}
			actorLists += "<div class='form-group role-actor'>" + target.attributes.name + tempSelectRole + "</div>";
		}
	}
	$("#listUCOfActor").append(actorLists);
}

$("#select-group-modal-usecase").change(function() {
	$("#input-group-modal-usecase").val($(this).val());
});

// Kiem tra 1 phan tu co thuoc mang khong
function hasObjInArr(arr, obj) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] == obj) return true;
    }
}

// Lay nhom uc ten khac nhau
function getNhomuc() {
	var allElement = graph.getElements();
	var ucArray = [], k = 0;
	for (var i = 1; i < allElement.length; i++) {
		if (allElement[i].attributes.type == "uml.Usecase") {
			ucArray[k] = allElement[i];
			k++;
		}
	}
	
	var kqArray = [], j = 0;
	var n = ucArray.length;
	if (n > 0) {
		kqArray[j] = ucArray[0].attributes.group;
		for (var i = 1; i < ucArray.length; i++) {
			if (ucArray[i].attributes.type == "uml.Usecase") {
				if (!hasObjInArr(kqArray, ucArray[i].attributes.group)) {
					kqArray[++j] = ucArray[i].attributes.group;
				}
			}
		}
	}
	
	return kqArray;
}


function getRelationshipUsecase(model) {
	// Lay link ma phan tu ket noi toi
	var linkConnectOut = graph.getConnectedLinks(model, { outbound: true });
	var linkConnectIn = graph.getConnectedLinks(model, { inbound: true });
	
	$("#listActorOfUC").html("");
	var actorLists = "";
	for (var i = 0; i < linkConnectOut.length; i++) {
		var target = graph.getCell(linkConnectOut[i].attributes.target.id);
		if (target.attributes.type == "uml.Actor") {
			actorLists += "<li class='list-group-item'>" + target.attributes.name + "</li>";
		}
	}
	for (var i = 0; i < linkConnectIn.length; i++) {
		var target = graph.getCell(linkConnectIn[i].attributes.source.id);
		if (target.attributes.type == "uml.Actor") {
			actorLists += "<li class='list-group-item'>" + target.attributes.name + "</li>";
		}
	}
	$("#listActorOfUC").append(actorLists);
}

// Save information of usecase
$("#saveInfoUC").on('click', function(){
	setIconSave("saved");
	var group = $("#name-diagram-show").html();
	if ($("#input-group-modal-usecase").val() != "") {
		group = $("#input-group-modal-usecase").val();
	}
	$.ajax({
		url: "/luanvan/diagram/saveinfousecase",
		type: "post",
		data: "nameProject=" + $("#nameProject").val() + "&id=" + $("#id-modal-usecase").val()
			+ "&description=" + $("#description-modal-usecase").val() + "&level=" + $("#level-modal-usecase").val()
			+ "&name=" + $("#name-modal-usecase").html() + "&payMoney=" + $("#pay-money-modal-usecase").prop("checked")
			+ "&group=" + $("#input-group-modal-usecase").val(),
		success: function(result) {
			$("#modal-usecase").modal('hide');
		},
		error: function() {
			alert("Lỗi, không thể lưu thông tin use case, liên hệ admin để giải quyết, cảm ơn.");
		}
	});
});

//Save information of usecase
$("#saveInfoActor").on('click', function(){
	setIconSave("saved");
	var role = "";
	$(".select-role").each(function() {
		role += $(this).val() + "_" + this.id + "/";
	});
	$.ajax({
		url: "/luanvan/diagram/saveinfoactor",
		type: "post",
		data: "nameProject=" + $("#nameProject").val() + "&id=" + $("#id-modal-actor").val() + "&role=" + role
			+ "&description=" + $("#description-modal-actor").val() + "&level=" + $("#level-modal-actor").val()
			+ "&name=" + $("#name-modal-actor").html(),
		success: function(result) {
			$("#modal-actor").modal('hide');
		},
		error: function() {
			alert("Lỗi, không thể lưu thông tin tác nhân, liên hệ admin để giải quyết, cảm ơn.");
		}
	});
});

function listActor() {
	$.ajax({
		url: "/luanvan/diagram/listactor",
		data: "nameProject=" + $("#nameProject").val(),
		success: function(result) {
			var obj = $.parseJSON(result);
			var str = "";
			for (var i = 0; i < obj.actor.length; i++) {
				str += "<div style='word-break: break-work;' class='forSearch' draggable='true' ondragstart='drag(event)' id='" + obj.actor[i].id + "'>" + obj.actor[i].name + "</div>";
			}
			$("#children-actor-view").html(str);
			
			$("#children-actor-view div").on('mousedown', function() {
				chooseElement = "actor";
			});
		},
		error: function() {
			alert("Lỗi, không thể xem danh sách tác nhân, liên hệ admin để giải quyết, cảm ơn.");
		}
	});
}

$("#children-actor-view div").on('mousedown', function() {
	chooseElement = "actor";
});

$("#children-usecase-view div").on('mousedown', function() {
	chooseElement = "usecase";
});

function listUsecase() {
	$.ajax({
		url: "/luanvan/diagram/listusecase",
		data: "nameProject=" + $("#nameProject").val(),
		success: function(result) {
			var obj = $.parseJSON(result);
			var str = "";
			for (var i = 0; i < obj.usecase.length; i++) {
				str += "<div style='word-break: break-all;' class='forSearch' draggable='true' ondragstart='drag(event)' id='" + obj.usecase[i].id + "'>" + obj.usecase[i].name + "</div>";
			}
			$("#children-usecase-view").html(str);
			
			$("#children-usecase-view div").on('mousedown', function() {
				chooseElement = "usecase";
			});
		},
		error: function() {
			alert("Lỗi, không thể xem danh sách usecase, liên hệ admin để giải quyết, cảm ơn.");
		}
	});
}

paper.on('blank:pointerclick', function() {
//	hideAllTool();
	elementDown = null;
});

paper.on('blank:pointerdown', function(cellView, x, y) {
	if (!hasDrawing) {
		hideAllTool();
		elementDown = null;
		listSelectViewDiagram = [];
		hasBlankPointerDownDiagram = true;
		selectViewDiagram = new joint.shapes.basic.Rect({
			type: 'selectViewDiagram',
			position: { x: x, y: y },
			size: { width: 1, height: 1 },
			attrs:{
				rect: { 'stroke' : '#333333', 'stroke-width': 1, 'stroke-dasharray': 5, fill: 'none'}
			}
		});
		graph.addCell(selectViewDiagram);
	}
});

// Khi mot doi tuong tren paper dang duoc chon
paper.on('cell:pointerdown', function(cellView, evt, px, py) {
	
	listSelectViewDiagram = [];
	
	elementDown = cellView;
	
	// Hien thi cac cong cu xung quanh phan tu dang duoc chon
	displayTool(cellView);

	if (cellView.model.attributes.type == "uml.Usecase" || cellView.model.attributes.type == "uml.Actor") {
		cellView.$box.find('.association').on('mousedown', function() {
			hasDrawing = true;
			link = new joint.shapes.uml.Association({
				source: { id: cellView.model.id },
				target: { id: cellView.model.id }
			});
			graph.addCell(link);
		});
		cellView.$box.find('.extend').on('mousedown', function() {
			hasDrawing = true;
			link = new joint.shapes.uml.Extend({
				source: { id: cellView.model.id },
				target: { id: cellView.model.id }
			});
			link.label(0, {
			    position: .5,
			    attrs: {
			        rect: { fill: 'white' },
			        text: { fill: 'gray', text: '" Extend "', 'font-size': 10, 'font-family': 'Arial' }
			    }
			});
			graph.addCell(link);
		});
		cellView.$box.find('.include').on('mousedown', function() {
			hasDrawing = true;
			link = new joint.shapes.uml.Include({
				source: { id: cellView.model.id },
				target: { id: cellView.model.id }
			});
			link.label(0, {
			    position: .5,
			    attrs: {
			        rect: { fill: 'white' },
			        text: { fill: 'gray', text: '" Include "', 'font-size': 10, 'font-family': 'Arial' }
			    }
			});
			graph.addCell(link);
		});
		cellView.$box.find('.generalization').on('mousedown', function() {
			hasDrawing = true;
			link = new joint.shapes.uml.Generalization({
				source: { id: cellView.model.id },
				target: { id: cellView.model.id }
			});
			graph.addCell(link);
		});
	}

});

graph.on('change:position', function(cell) {
	setIconSave('notsaved');
	var paperW = paper.options.width;
    var paperH = paper.options.height;
    var cellBbox = cell.getBBox();

    if (cellBbox.origin().x <= 0 || cellBbox.origin().y <= 0
    	|| cellBbox.origin().x >= paperW - cell.attributes.size.width 
    	|| cellBbox.origin().y >= paperW - cell.attributes.size.height) {
    	cell.set('position', cell.previous('position'));
    }
});

// Khi chuot di chuyen tren paper
$("#paper").on('mousemove', function(e) {
	var pos = getClickPosition(e);
	if (hasBlankPointerDownDiagram) {
		selectViewDiagram.resize(pos.x - selectViewDiagram.attributes.position.x, pos.y - selectViewDiagram.attributes.position.y);
	}
	if (hasDrawing) {
		link.set('target', pos);
	}
});

// Kiem tra trung ten
function hasNameActor(id) {
	var allElement = graph.getElements();
	for (var i = 0; i < allElement.length; i++) {
		if (allElement[i].attributes.type == 'uml.Actor') {
			if (allElement[i].attributes.created == id)
				return true;
		}
	}
	return false;
}

//Kiem tra trung ten
function hasNameUsecase(id) {
	var allElement = graph.getElements();
	for (var i = 0; i < allElement.length; i++) {
		if (allElement[i].attributes.type == 'uml.Usecase') {
			if (allElement[i].attributes.created == id)
				return true;
		}
	}
	return false;
}

// Khi chuot tha tren paper
$("#paper").on('mouseup', function(e) {
	
	if (hasBlankPointerDownDiagram) {
		hasBlankPointerDownDiagram = false;
		var listElements = paper.findViewsInArea({
			x: selectViewDiagram.attributes.position.x,
			y: selectViewDiagram.attributes.position.y,
			width: selectViewDiagram.attributes.size.width,
			height: selectViewDiagram.attributes.size.height
		});
		for (var i = 0; i < listElements.length; i++) {
			if (listElements[i].model.attributes.type != 'selectViewDiagram') {
				listElements[i].$box.find('.delete').show();
				listElements[i].$box.find('.relationship').show();
				listElements[i].$box.find('.delete').parent().css('border', '1px dashed #d9534f');
				listSelectViewDiagram.push(listElements[i]);
			}
		}
		selectViewDiagram.remove();
	}
	
	var posMouse = getClickPosition(e);
	
	removeLinkSameSourceTarget();
    
	hasDrawing = false;
	var canDrawingTarget = false;
    var viewTarget = paper.findViewsFromPoint(getClickPosition(e));
    if (viewTarget.length) {
    	if (link) {
    		var target = graph.getCell(link.attributes.source.id);
			if (link.attributes.type == "uml.Association") {
				if ((viewTarget[0].model.attributes.type == "uml.Actor" && target.attributes.type == "uml.Actor")
					|| (viewTarget[0].model.attributes.type == "uml.Usecase" && target.attributes.type == "uml.Usecase")) {
					canDrawingTarget = false;
				} else {
					canDrawingTarget = true;
				}
    			
    		}
    		if (link.attributes.type == "uml.Extend" || link.attributes.type == "uml.Include") {
    			if (viewTarget[0].model.attributes.type != "uml.Actor") {
    				canDrawingTarget = true;
    			} else {
    				canDrawingTarget = false;
    			}
    		}
    		if (link.attributes.type == "uml.Generalization") {
    			if (viewTarget[0].model.attributes.type == target.attributes.type) {
    				canDrawingTarget = true;
    			} else {
    				canDrawingTarget = false;
    			}
    		}
    	}
    }

    if (canDrawingTarget) {
	    if (link.attributes.source.id != viewTarget[0].model.id) {
			link.set('target', { id: viewTarget[0].model.id });
			if (!hasLinkST(link)) {
				graph.addCell(link.clone());
			}
		}
	}

	if (link) {
		link.remove();
		if (link.attributes.type == "uml.Association") {
			var object = graph.getCell(link.attributes.source.id);
			if (viewTarget[0].model.attributes.type == "uml.Actor" && object.attributes.type == "uml.Usecase") {
				$.ajax({
					type: "post",
					url: '/luanvan/diagram/setroleactor',
					data: "idActor=" + viewTarget[0].model.attributes.created + "&idUsecase=" + object.attributes.created,
					success: function(result) {
						setPathDiagram();
					},
					error: function() {
						alert("Không thể cập nhật thông tin quyền của tác nhân");
					}
				});
			} else if (viewTarget[0].model.attributes.type == "uml.Usecase" && object.attributes.type == "uml.Actor") {
				$.ajax({
					type: "post",
					url: '/luanvan/diagram/setroleactor',
					data: "idUsecase=" + viewTarget[0].model.attributes.created + "&idActor=" + object.attributes.created,
					success: function(result) {
						setPathDiagram();
					},
					error: function() {
						alert("Không thể cập nhật thông tin quyền của tác nhân");
					}
				});
			}
		}
		
		link = null;
	}
});

function hasLinkST(link) {
	var allLinks = graph.getLinks();
	for (var i = 0; i < allLinks.length - 1; i++) {
		if ((allLinks[i].attributes.source.id == link.attributes.source.id && allLinks[i].attributes.target.id == link.attributes.target.id) 
			|| (allLinks[i].attributes.source.id == link.attributes.target.id && allLinks[i].attributes.target.id == link.attributes.source.id)) {
			return true;
		}
	}
	return false;
}

// Neu link co source va target cung id se bi xoa bo khoi graph
function removeLinkSameSourceTarget() {
	var allLinks = graph.getLinks();
	for (var i = 0; i < allLinks.length; i++) {
		if (allLinks[i].attributes.source.id == allLinks[i].attributes.target.id) {
			allLinks[i].remove();
		}
	}
}

// An tat ca cac cong cu xung quanh phan tu dang chon
function hideAllTool() {
	// Lay tat ca cac doi tuong
	var allElement = graph.getElements();
	for (var i = 0; i < allElement.length; i++) {
		allElement[i].findView(paper).$box.find('.delete').hide();
		allElement[i].findView(paper).$box.find('.relationship').hide();
		allElement[i].findView(paper).$box.find('.delete').parent().css('border', 'none');
	}	
}

// Su dung hien thi cac button xung quanh doi tuong dang chon
function displayTool(cellView) {

	// Lay tat ca cac doi tuong
	var allElement = graph.getElements();
	
	for (var i = 0; i < allElement.length; i++) {
		if (cellView.model.id == allElement[i].id) {
			allElement[i].findView(paper).$box.find('.delete').show();
			allElement[i].findView(paper).$box.find('.relationship').show();
			allElement[i].findView(paper).$box.find('.delete').parent().css('border', '1px dashed #d9534f');
		} else {
			allElement[i].findView(paper).$box.find('.delete').hide();
			allElement[i].findView(paper).$box.find('.relationship').hide();
			allElement[i].findView(paper).$box.find('.delete').parent().css('border', 'none');
		}
	}
}

// Tao mot tac nhan
function createActor(positionX, positionY) {
	var actor = new joint.shapes.uml.Actor({
		position: { x: positionX, y: positionY }
	});
	graph.addCell(actor);
}

// Tao mot truong hop su dung
function createUsecase(positionX, positionY) {
	var usecase = new joint.shapes.uml.Usecase({
		position: { x: positionX, y: positionY }
	});
	graph.addCell(usecase);
}

// Xu ly enter trong textarea
function textAreaAdjust(o) {
    o.style.height = "1px";
    o.style.height = o.scrollHeight + "px";
}

$("#exportXML").on('click', function() {
	$("#modal-exportXML").modal('hide');
	var x2js = new X2JS();
	var xmlString = '<?xml version="1.0" encoding="UTF-8"?>';
	xmlString += x2js.json2xml_str($.parseJSON(JSON.stringify(graph)));
	var pom = document.createElement('a');
    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' 
    	+ encodeURIComponent(xmlString));
    pom.setAttribute('download', $("#nameFileXML").val() + '.xml');
    pom.click();

});

$("#exportJSON").on('click', function() {
	$("#modal-exportJSON").modal('hide');
	var pom = document.createElement('a');
    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' 
    	+ encodeURIComponent(JSON.stringify(graph)));
    pom.setAttribute('download', $("#nameFileJSON").val() + '.json');
    pom.click();

});

// Tat mo view tren doi tuong link
function toggleElementLinkView(value) {
	$(".link-tools").css('display', value);
    $(".marker-arrowheads").css('display', value);
    $(".marker-vertices").css('display', value);
    $(".connection-wrap").css('display', value);
}

$("#a-export-svg").on('click', function() {
	$("#nameFileSVG").val("");
	$("#modal-exportSVG").modal('show');
});
$("#exportSVG").on('click', function() {

	// Khong hien thi cac view khong can thiet khi xuat hinh
	toggleElementLinkView('none');

	$("#modal-exportSVG").modal('hide');
	
	var w = paper.options.width;
	var h = paper.options.height;
	paper.fitToContent({padding: 5});
	
	var svg = paper.svg;
    var serializer = new XMLSerializer();
    var svgXML = serializer.serializeToString(svg);
	
	var pom = document.createElement('a');
    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' 
    	+ encodeURIComponent(svgXML));
    pom.setAttribute('download', $("#nameFileSVG").val() + '.svg');
    pom.click();

    // Khong hien thi cac view khong can thiet khi xuat hinh
    toggleElementLinkView('block');
    
    paper.setDimensions(w, h);

});

$("#loadFile").click(function() {
	$("#readFile").click();
});

$("#readFile").change(function(evt) {
	var file = evt.target.files[0];
	if (file) {
		var reader = new FileReader();
		reader.readAsText(file);
		reader.onload = function(e) {
			var content = e.target.result;
			graph.clear();
			graph.fromJSON(JSON.parse(content));
		}
	}
});

//Xu ly khi nhan phim
$(document).keydown(function(event) {
	if (event.keyCode == 46) {
		for (var i = 0; i < listSelectViewDiagram.length; i++) {
			listSelectViewDiagram[i].model.remove();
		}
	}
	if (elementDown) {
		if (event.keyCode == 46) {
			$("#modal-delete-actor-usecase").modal('show');
		}
	}
});

$("#btn-delete-actor-usecase").on('click', function() {
	if ($('input[name="radio-delete-actor-usecase"]:checked').val() == "1") {
		$.ajax({
			url: "/luanvan/diagram/deleteobject",
			data: "type=" + elementDown.model.attributes.type + "&id=" + elementDown.model.attributes.created
			+ "&nameProject=" + $("#nameProject").val(),
			success: function(){
				elementDown.model.remove();
				listActor();
				listUsecase();
			},
			error: function() {
				alert("Không thể xóa đối tượng");
			}
		});
	} else {
		elementDown.model.remove();
	}
	setPathDiagram();
	$("#modal-delete-actor-usecase").modal('hide');
});

// Begin drag drop element to paper
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("id", ev.target.id);
    ev.dataTransfer.setData("name", ev.target.innerHTML);
}

function drop(ev) {
    ev.preventDefault();
    var posMouse = getClickPosition(ev);
    var id = ev.dataTransfer.getData("id");
    var name = ev.dataTransfer.getData("name");
	if ($("#name-diagram-show").html() != "") {
		if (chooseElement == "actor") {
			if (!hasNameActor(id)) {
				var actor = new joint.shapes.uml.Actor({
					position: { x: posMouse.x, y: posMouse.y },
					name: name,
					created: id,
				});
				actor.attr(".name/text", name);
				graph.addCell(actor);
				$.ajax({
					type: "post",
					url: "/luanvan/diagram/savediagramactor",
					data: "nameDiagram=" + $("#name-diagram-show").html() + "&idActor=" + id + "&nameProject=" + $("#nameProject").val(),
					success: function() {
					},
					error: function() {
						alert("Không thể lưu sơ đồ tác nhân.")
					}
				});
				setPathDiagram();
			} else {
				alert(name + " đã tồn tại");
			}
			
		}
		if (chooseElement == "usecase") {
			if (!hasNameUsecase(id)) {
				var usecase = new joint.shapes.uml.Usecase({
					position: { x: posMouse.x, y: posMouse.y },
					name: name,
					created: id,
				});
				usecase.attr(".name/text", name);
				graph.addCell(usecase);
				$.ajax({
					type: "post",
					url: "/luanvan/diagram/savediagramusecase",
					data: "nameDiagram=" + $("#name-diagram-show").html() + "&idUsecase=" + id + "&nameProject=" + $("#nameProject").val(),
					success: function() {
						
					},
					error: function() {
						alert("Không thể lưu sơ đồ usecase.")
					}
				});
				setPathDiagram();
			} else {
				alert(name + " đã tồn tại");
			}
		}
		chooseElement = "";
		if (id == "create-new-actor") {
			var actor = new joint.shapes.uml.Actor({position: { x: posMouse.x, y: posMouse.y }});
			var max = 0;
			var hasDefaultActor = false;
			$("#children-actor-view div").each(function() {
				var str = $(this).html().split(":");
				console.log(str[1]);
				if (str[0] == "Actor") {
					if (str[1] == "" || str[1] == null || str[1] == "undefined") {
						hasDefaultActor = true;
					} else if (parseInt(str[1]) > max) {
						max = parseInt(str[1]);
						hasDefaultActor = false;
					}
				}
			});
			var nameActor = "Actor";
			if (hasDefaultActor) {
				nameActor += ":1";
			} else if (max > 0) {
				nameActor += ":" + (max + 1);
			}
			actor.attributes.name = nameActor;
			actor.attr(".name/text", nameActor);
			graph.addCell(actor);
			$.ajax({
			url: "/luanvan/diagram/createactor",
			type: "post",
			data: "nameProject=" + $("#nameProject").val() + "&nameDiagram=" + $("#name-diagram-show").html()
				+ "&nameActor=" + nameActor,
			success: function(result) {
				actor.attributes.created = result;
				
				setIconSave("saved");
				listActor();
				setPathDiagram();
			},
			error: function() {
				alert("Không thể tạo tác nhân. Xin vui lòng liên hệ ADMIN để giải quyết.");
			}
		});
		}
		if (id == "create-new-usecase") {
			var usecase = new joint.shapes.uml.Usecase({position: { x: posMouse.x, y: posMouse.y }});
			var max = 0;
			var hasDefaultUsecase = false;
			$("#children-usecase-view div").each(function() {
				var str = $(this).html().split(":");
				if (str[0] == "Usecase") {
					if (str[1] == "" || str[1] == null || str[1] == "undefined") {
						hasDefaultUsecase = true;
					} else if (parseInt(str[1]) > max) {
						max = parseInt(str[1]);
						hasDefaultUsecase = false;
					}
				}
				
			});
			var nameUsecase = "Usecase";
			if (hasDefaultUsecase) {
				nameUsecase += ":1";
			} else if (max > 0) {
				nameUsecase += ":" + (max + 1);
			}
			usecase.attributes.name = nameUsecase;
			usecase.attr(".name/text", nameUsecase);
			graph.addCell(usecase);
			$.ajax({
				url: "/luanvan/diagram/createusecase",
				type: "post",
				data: "nameProject=" + $("#nameProject").val() + "&nameDiagram=" + $("#name-diagram-show").html()
					+ "&nameUsecase=" + nameUsecase,
				success: function(result) {
					var object = $.parseJSON(result);
					usecase.attributes.created = result;
					
					setIconSave("saved");
					listUsecase();
					setPathDiagram();
				},
				error: function() {
					alert("Không thể tao use case. Xin vui lòng liên hệ ADMIN để giải quyết.");
				}
			});
		}
	} else {
		$("#modal-new-diagram").modal('show');
	}
}
// End drag drop element to paper

// Begin function set path diagram
function setPathDiagram() {
	// Khong hien thi cac view khong can thiet khi xuat hinh
    toggleElementLinkView('none');
	var w = paper.options.width;
	var h = paper.options.height;
	paper.fitToContent({padding: 5});
	var svg = paper.svg;
    var serializer = new XMLSerializer();
    var svgXML = serializer.serializeToString(svg);
	var image = window.btoa("data:image/svg+xml;utf8," + encodeURIComponent(svgXML));
	paper.setDimensions(w, h);
	// Khong hien thi cac view khong can thiet khi xuat hinh
    toggleElementLinkView('block');
	$.ajax({
		url: '/luanvan/diagram/setpathdiagram',
		type: "post",
		data: "nameProject=" + $("#nameProject").val() + "&nameDiagram=" + $("#name-diagram-show").html() + "&image=" + image
			+ "&path=" + window.btoa(encodeURIComponent(JSON.stringify(graph))),
		success: function() {
			
		},
		error: function() {
			alert("Không thể cập nhật lại sơ đồ. Xin vui lòng liên hệ ADMIN để giải quyết.");
		}
	});
}
// End function set path diagram