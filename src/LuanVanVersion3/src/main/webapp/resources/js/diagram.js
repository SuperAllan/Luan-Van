var iconRight =  '<i class="glyphicon glyphicon-chevron-right"></i>';
var iconLeft =  '<i class="glyphicon glyphicon-chevron-left"></i>';

$(".toggle-menu-detail-project").on('click', function() {
	$("#menu-detail-project").toggle("fade");
	if ($(this).html() == iconRight) {
		$(this).html(iconLeft);
		$("#paper").addClass("paper-scroll");
		$("#paper").removeClass("paper-no-scroll");
		$(".wrapper-tab-content-detail-project").addClass("col-md-10");
		$(".wrapper-tab-content-detail-project").removeClass("col-md-12");
	} else {
		$(this).html(iconRight);
		$("#paper").addClass("paper-no-scroll");
		$("#paper").removeClass("paper-scroll");
		$(".wrapper-tab-content-detail-project").addClass("col-md-12");
		$(".wrapper-tab-content-detail-project").removeClass("col-md-10");
	}
});

var elementDown = null;

// Bien toan cuc su dung de xem cong cu nao dang duoc chon
var chooseTool = "";

// Su dung cho cach ve keo quan he
var hasDrawing = false;
var link = null;

// Khi click tren menu cong cu thay doi hieu ung va bien chooseTool
$(document).ready(function() {
	if ($("#sodo").html()) {
		graph.fromJSON(JSON.parse($("#sodo").html()));
	}
	$('.tool-btn').on('click', function() {
		chooseTool = this.id;
		$('.tool-btn').each(function() {
			if (this.id == chooseTool) {
				$(this).addClass('active');
			} else {
				$(this).removeClass('active');
			}
		});
	});
});

// Khoi tao so do - 1 graph la 1 so do
var graph = new joint.dia.Graph;

// Noi de ve so do cua graph da khoi tao
var paper = new joint.dia.Paper({
    el: $('#paper'),
    width: 1050,
    fill: 'white',
    height: 1000,
    model: graph,
    gridSize: 1
});

graph.on('change', function() {
	setIconSave('block', 'none');
});

graph.on('add', function(cellView) {
	setIconSave('block', 'none');
});

graph.on('remove', function(cellView) {
	setIconSave('block', 'none');
	if (cellView.attributes.type == "uml.Association") {
		var source = graph.getCell(cellView.attributes.source.id);
		var target = graph.getCell(cellView.attributes.target.id);
		
		if (source.attributes.type && source.attributes.type == "uml.Actor") {
			if (target.attributes.type) {
				removeRoleActor(source, target.id);
			}
		}
		
		if (target.attributes.type && target.attributes.type == "uml.Actor") {
			if (source.attributes.type) {
				removeRoleActor(target, source.id);
			}
		}
	}
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
		getRelationshipActor(cellView.model);
		$("#modal-actor").modal('show');
	}
	if (cellView.model.attributes.type == "uml.Usecase") {
		getRelationshipUsecase(cellView.model);
		$("#modal-usecase").modal('show');
	}
});

function getRelationshipActor(model) {
	
	// Lay link ma phan tu ket noi toi
	var linkConnectOut = graph.getConnectedLinks(model, { outbound: true });
	var linkConnectIn = graph.getConnectedLinks(model, { inbound: true });
	
	$("#name-modal-actor").html(model.attributes.name);
	$("#id-modal-actor").val(model.id);
	$("#description-modal-actor").val(model.attributes.description);
	$("#level-modal-actor").val(model.attributes.level);

	$("#listUCOfActor").html("");
	var actorLists = "";
	var tempSelectRole = "";
	for (var i = 0; i < linkConnectOut.length; i++) {
		var target = graph.getCell(linkConnectOut[i].attributes.target.id);
		if (target.attributes.type == "uml.Usecase") {
			var role = testRoleActor(model.attributes.role, target.id);
			if (role) {
				if (role == "1") {
					tempSelectRole = "<select class='pull-right select-role' id='" + target.id
					+ "'><option value='0'>Tác nhân phụ</option><option value='1' selected>Tác nhân chính</option></select>";
				} else {
					tempSelectRole = "<select class='pull-right select-role' id='" + target.id
					+ "'><option value='0' selected>Tác nhân phụ</option><option value='1'>Tác nhân chính</option></select>";
				}
			} else {
				tempSelectRole = "<select class='pull-right select-role' id='" + target.id
				+ "'><option value='0'>Tác nhân phụ</option><option value='1'>Tác nhân chính</option></select>";
			}
			actorLists += "<div class='form-group role-actor'>" + target.attributes.name + tempSelectRole + "</div>";
		}
	}
	for (var i = 0; i < linkConnectIn.length; i++) {
		var target = graph.getCell(linkConnectIn[i].attributes.source.id);
		if (target.attributes.type == "uml.Usecase") {
			var role = testRoleActor(model.attributes.role, target.id);
			if (role) {
				if (role == 1) {
					tempSelectRole = "<select class='pull-right select-role' id='" + target.id
					+ "'><option value='0'>Tác nhân phụ</option><option value='1' selected>Tác nhân chính</option></select>";
				} else {
					tempSelectRole = "<select class='pull-right select-role' id='" + target.id
					+ "'><option value='0' selected>Tác nhân phụ</option><option value='1' >Tác nhân chính</option></select>";
				}
			} else {
				tempSelectRole = "<select class='pull-right select-role' id='" + target.id
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
	
	$("#name-modal-usecase").html(model.attributes.name);
	$("#id-modal-usecase").val(model.id);
	$("#description-modal-usecase").val(model.attributes.description);
	$("#level-modal-usecase").val(model.attributes.level);
	$("#input-group-modal-usecase").val(model.attributes.group);
	if (model.attributes.payMoney) {
		$("#pay-money-modal-usecase").prop("checked", true);
	} else {
		$("#pay-money-modal-usecase").prop("checked", false);
	}
	
	var nhomucs = getNhomuc();
	$("#select-group-modal-usecase").html("");
	$("#select-group-modal-usecase").append("<option selected value=\"\">- - - - - - - - -</option>");
	for (var i = 0; i < nhomucs.length; i++) {
		if (nhomucs[i]) {
			if (model.attributes.group == nhomucs[i]) {
				$("#input-group-modal-usecase").val(model.attributes.group);
				$("#select-group-modal-usecase").append("<option selected value=\"" + nhomucs[i] + "\">" + nhomucs[i] + "</option>");
			} else {
				$("#select-group-modal-usecase").append("<option value=\"" + nhomucs[i] + "\">" + nhomucs[i] + "</option>");
			}
		}
	}
	
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

function setIconSave(notSave, saved) {
	$("#icon-not-save").css('display', notSave);
	$("#icon-saved").css('display', saved);
	if (saved == 'none') {
		$("#icon-saved").parent().prop('disabled', false);
	} else {
		$("#icon-saved").parent().prop('disabled', true);
	}
}

// Save information of usecase
$("#saveInfoUC").on('click', function(){
	setIconSave('block', 'none');
	var UC = graph.getCell($("#id-modal-usecase").val());
	UC.attributes.description = $("#description-modal-usecase").val();
	UC.attributes.level = $("#level-modal-usecase").val();
	UC.attributes.payMoney = $("#pay-money-modal-usecase").prop("checked");
	UC.attributes.group = $("#input-group-modal-usecase").val();
	$("#modal-usecase").modal('hide');
	//$("#saveDiagram").trigger('click');
});

//Save information of usecase
$("#saveInfoActor").on('click', function(){
	setIconSave('block', 'none');
	var actor = graph.getCell($("#id-modal-actor").val());
	actor.attributes.description = $("#description-modal-actor").val();
	actor.attributes.level = $("#level-modal-actor").val();
	actor.attributes.role = "";
	$(".select-role").each(function() {
		var role = $("#"+ this.id).val() + "_" + this.id + "/";
		actor.attributes.role += role;
	});
	$("#modal-actor").modal('hide');
	//$("#saveDiagram").trigger('click');
});

$("#saveDiagram").on('click', function() {
	var allElement = graph.getElements();
	var ucs = new Array();
	var actors = new Array();
	for (var i = 0; i < allElement.length; i++) {
		if (allElement[i].attributes.type == "uml.Usecase") {
			
			var uc = allElement[i].attributes.name + "=";
			uc += allElement[i].attributes.description + "=";
			uc += allElement[i].attributes.level + "=";
			uc += allElement[i].attributes.payMoney + "=";
			uc += allElement[i].id + "=";
			uc += allElement[i].attributes.group;
			
			ucs.push(uc);
		}
		if (allElement[i].attributes.type == "uml.Actor") {
			
			var actor = allElement[i].attributes.name + "=";
			actor += allElement[i].attributes.description + "=";
			actor += allElement[i].attributes.level + "=";
			actor += allElement[i].attributes.role;
			
			actors.push(actor);
		}
	}
	$("#saveDiagram").prop("disabled", true);
	$("#icon-not-save").html("<img src='/luanvan/resources/img/icon-loading.gif' />");
	if ($(".toggle-menu-detail-project").html() == iconLeft) {
		$(".div-loading").css('width', "870px");
		$(".div-loading").css('height', "800px");
	} else {
		$(".div-loading").css('width', "1050px");
		$(".div-loading").css('height', "1000px");
	}
	$(".div-loading").show();
	$.ajax({
		url : "/luanvan/diagram/savediagramusecase",
		method: "post",
		data: "ucArray=" + ucs + "&nameProject=" + $("#nameProject").val() 
			+ "&actorArray=" + actors + "&sodo=" + JSON.stringify(graph),
		success: function() {
			$("#icon-not-save").html("<i class=\"glyphicon glyphicon-floppy-remove\"></i>");
			setIconSave('none', 'block');
			$(".div-loading").hide();
		},
		error: function() {
			alert("error");
		}
	});
});


// Khi co chuot click tren cho trong cua paper
paper.on('blank:pointerdown', function(evt, x, y) {
	
	if (chooseTool == "actor") {
		createActor(x, y);
	}
	if (chooseTool == "usecase") {
		createUsecase(x, y);
	}
});

paper.on('blank:pointerclick', function() {
	hideAllTool();
});

// Khi mot doi tuong tren paper dang duoc chon
paper.on('cell:pointerdown', function(cellView, evt, px, py) {
	
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

// Khi chuot di chuyen tren paper
$("#paper").on('mousemove', function(e) {
	if (hasDrawing) {
		link.set('target', getClickPosition(e));
	}
});

// Khi chuot tha tren paper
$("#paper").on('mouseup', function(e) {
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
			if (viewTarget[0].model.attributes.type == "uml.Actor") {
				getRelationshipActor(viewTarget[0].model);
				getRelationshipUsecase(graph.getCell(link.attributes.source.id));
			} else {
				getRelationshipActor(graph.getCell(link.attributes.source.id));
				getRelationshipUsecase(viewTarget[0].model);
			}
			
			$("#saveInfoActor").trigger('click');
			$("#saveInfoUC").trigger('click');
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
	var allCells = graph.getElements();
	for (var i = 0; i < allCells.length; i++) {
		allCells[i].findView(paper).$box.find('.delete').hide();
		allCells[i].findView(paper).$box.find('.relationship').hide();
		allCells[i].findView(paper).$box.find('.delete').parent().css('border', 'none');
	}	
}

// Su dung hien thi cac button xung quanh doi tuong dang chon
function displayTool(cellView) {

	// Lay tat ca cac doi tuong
	var allCells = graph.getElements();
	
	for (var i = 0; i < allCells.length; i++) {
		if (cellView.model.id == allCells[i].id) {
			allCells[i].findView(paper).$box.find('.delete').show();
			allCells[i].findView(paper).$box.find('.relationship').show();
			allCells[i].findView(paper).$box.find('.delete').parent().css('border', '1px dashed #d9534f');
		} else {
			allCells[i].findView(paper).$box.find('.delete').hide();
			allCells[i].findView(paper).$box.find('.relationship').hide();
			allCells[i].findView(paper).$box.find('.delete').parent().css('border', 'none');
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

// Nguon: http://www.kirupa.com/html5/getting_mouse_click_position.htm
function getClickPosition(e) {
    var parentPosition = getPosition(e.currentTarget);
    var xPosition = e.clientX - parentPosition.x;
    var yPosition = e.clientY - parentPosition.y;
    return { x: xPosition, y: yPosition };
}

// Nguon: http://www.kirupa.com/html5/getting_mouse_click_position.htm
function getPosition(element) {
    var xPosition = 0;
    var yPosition = 0;
      
    while (element) {
        xPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);
        yPosition += (element.offsetTop - element.scrollTop + element.clientTop);
        element = element.offsetParent;
    }
 
    return { x: xPosition, y: yPosition };
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
function toogleElementLinkView(value) {
	$(".link-tools").css('display', value);
    $(".marker-arrowheads").css('display', value);
    $(".marker-vertices").css('display', value);
    $(".connection-wrap").css('display', value);
}

$("#exportSVG").on('click', function() {

	// Khong hien thi cac view khong can thiet khi xuat hinh
	toogleElementLinkView('none');

	$("#modal-exportSVG").modal('hide');
	var svg = paper.svg;
    var serializer = new XMLSerializer();
    var svgXML = serializer.serializeToString(svg);
	
	var pom = document.createElement('a');
    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' 
    	+ encodeURIComponent(svgXML));
    pom.setAttribute('download', $("#nameFileSVG").val() + '.svg');
    pom.click();

    // Khong hien thi cac view khong can thiet khi xuat hinh
    toogleElementLinkView('block');

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

$("#scoreActor").click(function() {
	$.ajax({
		url : "/luanvan/diagram/scoreactor",
		method: "post",
		data: "nameProject=" + $("#nameProject").val(),
		success: function(result) {
			var kq = result.split("_");
			$("#numberActorSimple").text(kq[0]);
			$("#numberActorAverage").text(kq[1]);
			$("#numberActorComplex").text(kq[2]);
			$("#scoreActorSimple").text(kq[0]);
			$("#scoreActorAverage").text(kq[1] * 2);
			$("#scoreActorComplex").text(kq[2] * 3);
			$("#TAW").text(parseInt(kq[0]) + parseInt(kq[1] * 2) + parseInt(kq[2] * 3));
		},
		error: function() {
			alert("error");
		}
	});
});

$("#scoreUsecase").click(function() {
	$.ajax({
		url : "/luanvan/diagram/scoreusecase",
		method: "post",
		data: "nameProject=" + $("#nameProject").val(),
		success: function(result) {
			var kq = result.split("_");
			$("#numberTypeB").text(kq[0]);
			$("#numberTypeM").text(kq[1]);
			$("#numberTypeT").text(kq[2]);
			$("#numberTypeBSimple").text(kq[3]);
			$("#numberTypeBAverage").text(kq[4]);
			$("#numberTypeBComplex").text(kq[5]);
			$("#numberTypeMSimple").text(kq[6]);
			$("#numberTypeMAverage").text(kq[7]);
			$("#numberTypeMComplex").text(kq[8]);
			$("#numberTypeTSimple").text(kq[9]);
			$("#numberTypeTAverage").text(kq[10]);
			$("#numberTypeTComplex").text(kq[11]);
			$("#scoreTypeBSimple").text(kq[12]);
			$("#scoreTypeBAverage").text(kq[13]);
			$("#scoreTypeBComplex").text(kq[14]);
			$("#scoreTypeMSimple").text(kq[15]);
			$("#scoreTypeMAverage").text(kq[16]);
			$("#scoreTypeMComplex").text(kq[17]);
			$("#scoreTypeTSimple").text(kq[18]);
			$("#scoreTypeTAverage").text(kq[19]);
			$("#scoreTypeTComplex").text(kq[20]);
			
			$("#sumNumberUC").text(parseInt($("#numberTypeB").text()) + parseInt($("#numberTypeM").text()) + parseInt($("#numberTypeT").text()));
			$("#sumScoreTypeB").text(parseFloat($("#scoreTypeBSimple").text()) + parseFloat($("#scoreTypeBAverage").text()) + parseFloat($("#scoreTypeBComplex").text()));
			$("#sumScoreTypeM").text(parseFloat($("#scoreTypeMSimple").text()) + parseFloat($("#scoreTypeMAverage").text()) + parseFloat($("#scoreTypeMComplex").text()));
			$("#sumScoreTypeT").text(parseFloat($("#scoreTypeTSimple").text()) + parseFloat($("#scoreTypeTAverage").text()) + parseFloat($("#scoreTypeTComplex").text()));
			
			$("#TBF").text(parseFloat($("#sumScoreTypeB").text()) + parseFloat($("#sumScoreTypeM").text()) + parseFloat($("#sumScoreTypeT").text()));
		},
		error: function() {
			alert("error");
		}
	});
});

//Xu ly khi nhan phim
$(document).keydown(function(event) {

	if (event.keyCode == 46) {
		if (elementDown) {
			elementDown.model.remove();
		}
	}
});
		