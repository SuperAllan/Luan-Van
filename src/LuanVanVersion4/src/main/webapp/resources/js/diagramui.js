var hasResizeUI = false;
var cellViewResizeUI = null;
var cellViewPointerDown = null;
var hasCopyUI = false;
var cellViewCopyUI = null;
var hasViewChangeBorder = null;
var hasBlankPointerDown = null;
var selectView = null;

// Khoi tao so do - 1 graph la 1 so do
var graphUI = new joint.dia.Graph;

// Noi de ve so do cua graph da khoi tao
var paperUI = new joint.dia.Paper({
    el: $('#paperUI'),
    fill: 'white',
    width: 1500,
    height: 1000,
    model: graphUI,
    gridSize: 1
});

var dragDropElementUI = false;
var choosedElement = false;

var lineH1 = new joint.shapes.basic.Rect({
	size: { width: 960, height: 1},
	attrs: { rect: { fill: '#d43f3a', stroke: 'none'} }
});

var lineH2 = new joint.shapes.basic.Rect({
	size: { width: 960, height: 1},
	attrs: { rect: { fill: '#d43f3a', stroke: 'none'} }
});

var lineV1 = new joint.shapes.basic.Rect({
	size: { width: 1, height: 1000},
	attrs: { rect: { fill: '#d43f3a', stroke: 'none'} }
});

var lineV2 = new joint.shapes.basic.Rect({
	size: { width: 1, height: 1000},
	attrs: { rect: { fill: '#d43f3a', stroke: 'none'} }
});

$(document).ready(function() {
//	divProperties(true, "#aaaaaa");
//	// Begin loading diagram
//	if ($("#path").html() && $("#path").html() != "") {
//		graphUI.fromJSON(JSON.parse(decodeURIComponent(window.atob($("#path").html()))));
//	}
//	if ($("#name-ui-show").html() != "") {
//		$("#a-rename-ui").show("fade");
//	} else {
//		$("#a-rename-ui").hide("fade");
//	}
//	// End loading diagram
//});
//$(document).ready(function(){
//	$(document).ready(function(){
//		$(".formatNameProject").each(function(){
//			if($(this).text().length > 70){
//			var formatTitle = $.trim($(this).text()).substring(0,70).split(" ").join(" ") + "...";
//			$(this).text(formatTitle);
//			}
//		});
//	});
	$('#body-assignUI').perfectScrollbar();
});
paperUI.on('cell:pointerdblclick', function(cellView, x, y) {
	enableInput(cellView);
});

paperUI.on('blank:pointerdown', function(cellView, x, y) {
	hideAllToolUI();
	disableInput(cellView);
	divProperties(true, '#aaaaaa');
	cellViewPointerDown = null;
	$("#properties-design-ui").hide("fade");
	if (!hasCopyUI) {
		listSelectView = [];
		hasBlankPointerDown = true;
		selectView = new joint.shapes.basic.Rect({
			type: 'selectView',
			position: { x: x, y: y },
			size: { width: 1, height: 1 },
			attrs:{
				rect: { 'stroke' : '#333333', 'stroke-width': 1, 'stroke-dasharray': 5, fill: 'none'}
			}
		});
		graphUI.addCell(selectView);
	}
});

paperUI.on('cell:pointerup', function(cellView, evt, x, y) {
	var cell = cellView.model;
    var cellViewsBelow = paperUI.findViewsFromPoint(cell.getBBox().center());
    
    if (cellViewsBelow.length) {
        // Note that the findViewsFromPoint() returns the view for the `cell` itself.
        var cellViewBelow = _.find(cellViewsBelow, function(c) { return c.model.id !== cell.id });

        // Prevent recursive embedding.
        if (cellViewBelow && cellViewBelow.model.get('parent') !== cell.id && cellViewBelow.model.attributes.type == "ui.Div") {
            cellViewBelow.model.embed(cell);
        }
    }
     if (hasViewChangeBorder) {
    		hasViewChangeBorder.model.attr('rect/stroke', 'black');
    		hasViewChangeBorder = null;
    	}
});

paperUI.on('cell:pointerclick', function(cellView, evt, px, py) {

});

paperUI.on('cell:pointerdown', function(cellView, evt, px, py) {
	
	listSelectView = [];
	
	$("#properties-design-ui").show("fade");
	
	divProperties(false, 'black');

	displayToolUI(cellView);

	cellViewPointerDown = cellView;

	$("#width-design-ui").val(cellViewPointerDown.model.attributes.size.width);
	$("#height-design-ui").val(cellViewPointerDown.model.attributes.size.height);
	$("#background-color-design-ui").val(cellViewPointerDown.model.attr('rect/fill'));
	$("#border-color-design-ui").val(cellViewPointerDown.model.attr('rect/stroke'));
	$("#font-size-design-ui").val(cellViewPointerDown.model.attr('text/font-size'));
	$("#font-color-design-ui").val(cellViewPointerDown.model.attr('text/fill'));
	$("#font-family-design-ui").val(cellViewPointerDown.model.attr('text/font-family'));
	$("#X-design-ui").val(cellViewPointerDown.model.attributes.position.x);
	$("#Y-design-ui").val(cellViewPointerDown.model.attributes.position.y);
	$("#border-size-design-ui").val(cellViewPointerDown.model.attr('rect/stroke-width'));
	$("#border-radius-design-ui").val(cellViewPointerDown.model.attr('rect/rx'));

	cellView.$box.find(".glyphicon").on('mousedown', function() {
		hasResizeUI = true;
		cellViewResizeUI = cellView;
	});

	cellView.$box.find(".copy").on('mousedown', function() {
		hasCopyUI = true;
		cellViewCopyUI = cellView;
	});

	var cell = cellView.model;

    if (!cell.get('embeds') || cell.get('embeds').length === 0) {
        cell.toFront();
    }
    
    if (cell.get('parent')) {
        graphUI.getCell(cell.get('parent')).unembed(cell);
    }
});

paperUI.on('cell:pointermove', function(cellView, evt, x, y) {

	var x = cellView.model.attributes.position.x;
	var y = cellView.model.attributes.position.y;

	$("#X-design-ui").val(x);
	$("#Y-design-ui").val(y);
	
	alignBorderAuto(cellView);

	var cell = cellView.model;
    var cellViewsBelow = paperUI.findViewsFromPoint(cell.getBBox().center());
    if (cellViewsBelow.length) {
        // Note that the findViewsFromPoint() returns the view for the `cell` itself.
        var cellViewBelow = _.find(cellViewsBelow, function(c) { return c.model.id !== cell.id });

        if (hasViewChangeBorder) {
    		hasViewChangeBorder.model.attr('rect/stroke', 'black');
    	}

        // Prevent recursive embedding.
        if (cellViewBelow && cellViewBelow.model.get('parent') !== cell.id && cellViewBelow.model.attributes.type == "ui.Div") {
            cellViewBelow.model.attr('rect/stroke', 'blue');
            hasViewChangeBorder = cellViewBelow;
        }
    } 
});

$("#paperUI").on('mousemove', function(e) {
	var pos = getClickPosition(e);
	// Select view
	if (hasBlankPointerDown && !hasResizeUI) {
		selectView.resize(pos.x - selectView.attributes.position.x, pos.y - selectView.attributes.position.y);
	}
	if (hasResizeUI && cellViewResizeUI) {
		var x = cellViewResizeUI.model.attributes.position.x;
		var y = cellViewResizeUI.model.attributes.position.y;
		var width = pos.x - x;
		var height = pos.y - y;

		if (width < 0) {
			width = -width;
		}
		if (height < 0) {
			height = -height;
		}
		resizeWidthHeightCell(cellViewResizeUI, width, height);
	}
});

$("#paperUI").on('mouseup', function(e) {

	if (hasBlankPointerDown) {
		hasBlankPointerDown = false;
		var listElements = paperUI.findViewsInArea({
			x: selectView.attributes.position.x,
			y: selectView.attributes.position.y,
			width: selectView.attributes.size.width,
			height: selectView.attributes.size.height
		});
		for (var i = 0; i < listElements.length; i++) {
			if (listElements[i].model.attributes.type != 'selectView') {
				listElements[i].$box.find('.delete').show();
				listElements[i].$box.find('.copy').show();
				listElements[i].$box.find('.glyphicon-fullscreen').parent().show();
				listElements[i].$box.find('.delete').parent().css('border', '3px dashed #0769AD');
				listSelectView.push(listElements[i]);
			}
		}
		selectView.remove();
	}
	
	lineH1.remove();
	lineV1.remove();
	lineH2.remove();
	lineV2.remove();

	var pos = getClickPosition(e);
	hasResizeUI = false;
	if (hasCopyUI && cellViewCopyUI) {
		var cellNew = cellViewCopyUI.model.clone().set({position: {x: pos.x, y: pos.y}});
		graphUI.addCell(cellNew);
		moveIconResizeUI(cellNew.findView(paperUI), cellNew.attributes.size.width, cellNew.attributes.size.height);
		changeInputUI(cellNew.findView(paperUI), cellNew.attributes.size.width, cellNew.attributes.size.height);
		if (cellViewCopyUI.model.attributes.type == "ui.Button" 
			|| cellViewCopyUI.model.attributes.type == "ui.Textarea"
			|| cellViewCopyUI.model.attributes.type == "ui.Textbox") {
			moveTextButton(cellNew.findView(paperUI), cellNew.attributes.size.width, cellNew.attributes.size.height);
		}
		hasCopyUI = false;
	}
	if (dragDropElementUI) {
		dragDropElementUI = false;
//		if (choosedElement == "div-design-ui") {
//			createDiv(pos.x, pos.y);
//		}
//		if (choosedElement == "button-design-ui") {
//			createButton(pos.x, pos.y);
//		}
//		if (choosedElement == "label-design-ui") {
//			createLabel(pos.x, pos.y);
//		}
//		if (choosedElement == "radio-design-ui") {
//			createRadio(pos.x, pos.y);
//		}
//		if (choosedElement == "radio-checked-design-ui") {
//			createRadioChecked(pos.x, pos.y);
//		}
//		if (choosedElement == "checkbox-design-ui") {
//			createCheckbox(pos.x, pos.y);
//		}
//		if (choosedElement == "checkbox-checked-design-ui") {
//			createCheckboxChecked(pos.x, pos.y);
//		}
//		if (choosedElement == "textarea-design-ui") {
//			createTextArea(pos.x, pos.y);
//		}
//		if (choosedElement == "textbox-design-ui") {
//			createTextBox(pos.x, pos.y);
//		}
	}
});

$("#element-design-ui button").on("mousedown", function(evt) {
	dragDropElementUI = true;
	choosedElement = this.id;
});

$("#width-design-ui").on('keyup change', function() {
	if (cellViewPointerDown)
		resizeWidthHeightCell(cellViewPointerDown, $(this).val(), $("#height-design-ui").val());
});

$("#height-design-ui").on('keyup change', function() {
	if (cellViewPointerDown)
		resizeWidthHeightCell(cellViewPointerDown, $("#width-design-ui").val(), $(this).val());
});

$("#font-color-design-ui").on('keyup change', function() {
	if (cellViewPointerDown)
		cellViewPointerDown.model.attr({text: {'fill': $(this).val()}});
});

$("#font-family-design-ui").on('keyup change', function() {
	if (cellViewPointerDown)
		cellViewPointerDown.model.attr({text: {'font-family': $(this).val()}});
});

$("#font-size-design-ui").on('keyup change', function() {
	if (cellViewPointerDown)
		cellViewPointerDown.model.attr({text: {'font-size': $(this).val()}});
});

$("#background-color-design-ui").on('input', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({rect: {fill: $(this).val()}});
	}
});

$("#border-color-design-ui").on('input', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({rect: {stroke: $(this).val()}});
	}
});

$("#X-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		var x = cellViewPointerDown.model.attributes.position.x;
		var y = cellViewPointerDown.model.attributes.position.y;
		cellViewPointerDown.model.translate($(this).val() - x, $("#Y-design-ui").val() - y);
	}
});

$("#Y-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		var x = cellViewPointerDown.model.attributes.position.x;
		var y = cellViewPointerDown.model.attributes.position.y;
		cellViewPointerDown.model.translate($("#X-design-ui").val() - x, $(this).val() - y);
	}
});

$("#border-size-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({rect: {'stroke-width': $(this).val()}});
	}
});

$("#border-radius-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({rect: {rx: $(this).val(), ry: $(this).val()}});
	}
});

function alignBorderAuto(cellView) {
	lineH1.remove();
	lineV1.remove();
	lineH2.remove();
	lineV2.remove();
	var delta = 5;
	var cx = cellView.model.attributes.position.x;
	var cy = cellView.model.attributes.position.y;
	var cw = cellView.model.attributes.size.width;
	var ch = cellView.model.attributes.size.height;
	var allElements = graphUI.getElements();
	for (var i = 0; i < allElements.length; i++) {
		if (allElements[i].id != cellView.model.id) {
			var h = allElements[i].attributes.size.height;
			var w = allElements[i].attributes.size.width;
			var y = allElements[i].attributes.position.y;
			var x = allElements[i].attributes.position.x;
			if (cy >= y - delta && cy <= y + delta) {
				lineH1.set({position: { x: 0, y: y }});
				cellView.model.attributes.position.y = y;
				graphUI.addCell(lineH1);
			}

			if (cy + ch >= y + h - delta && cy + ch <= y + h + delta) {
				lineH2.set({position: { x: 0, y: y + h }});
				cellView.model.attributes.position.y = y + h - ch;
				graphUI.addCell(lineH2);
			}

			if (cx >= x - delta && cx <= x + delta) {
				lineV1.set({position: { x: x, y: 0 }});
				cellView.model.attributes.position.x = x;
				graphUI.addCell(lineV1);
			}

			if (cx + cw >= x + w - delta && cx + cw <= x + w + delta) {
				lineV2.set({position: { x: x + w, y: 0 }});
				cellView.model.attributes.position.x = x + w - cw;
				graphUI.addCell(lineV2);
			}
		}
	}
}

function divProperties(boolean, color) {
	$("#properties-design-ui input").each(function() {
		$(this).prop('disabled', boolean);
	});

	$("#properties-design-ui select").each(function() {
		$(this).prop('disabled', boolean);
	});

	$("#properties-design-ui span").each(function() {
		$(this).css('color', color);
	});
}

function resizeWidthHeightCell(cellView, width, height) {
	if (cellView.model.attributes.type == "ui.Button") {
		moveTextButton(cellView, width, height);
	}
	moveIconResizeUI(cellView, width, height);
	changeInputUI(cellView, width, height);
	cellView.model.resize(width, height);
}

// Di chuyen icon resize theo hinh 
function moveIconResizeUI(cellView, width, height) {
	cellView.$box.find(".glyphicon-fullscreen").parent().css("left", width + "px");
	cellView.$box.find(".glyphicon-fullscreen").parent().css("top", height + "px");
}

// Di chuyen o nhap text, textarea theo hinh
function changeInputUI(cellView, width, height) {
	cellView.$box.find(".name").css("width", width);
	cellView.$box.find(".name").css("height", height);
	cellView.$box.find(".name").css("top", 0);
}

// Di chuyen text button
function moveTextButton(cellView, width, height) {
	cellView.model.attr({ text: { x: width / 2, y: (height / 2) + 5 } });
}

// Tao mot button
function createButton(positionX, positionY) {
	var button = new joint.shapes.ui.Button({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(button);
}

// Tao mot div
function createDiv(positionX, positionY) {
	var div = new joint.shapes.ui.Div({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(div);
}

// Tao mot textbox
function createTextBox(positionX, positionY) {
	var textBox = new joint.shapes.ui.TextBox({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(textBox);
}

// Tao mot textbox
function createTextArea(positionX, positionY) {
	var textArea = new joint.shapes.ui.TextArea({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(textArea);
}

// Tao mot label
function createLabel(positionX, positionY) {
	var label = new joint.shapes.ui.Label({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(label);
}

// Tao mot radio checked
function createRadioChecked(positionX, positionY) {
	var radioChecked = new joint.shapes.ui.RadioChecked({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(radioChecked);
}

// Tao mot radio
function createRadio(positionX, positionY) {
	var radio = new joint.shapes.ui.Radio({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(radio);
}

// Tao mot checkbox checked
function createCheckboxChecked(positionX, positionY) {
	var checkboxChecked = new joint.shapes.ui.CheckboxChecked({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(checkboxChecked);
}

// Tao mot checkbox
function createCheckbox(positionX, positionY) {
	var checkbox = new joint.shapes.ui.Checkbox({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(checkbox);
}

// Su dung hien thi cac button xung quanh doi tuong dang chon
function displayToolUI(cellView) {

	// Lay tat ca cac doi tuong
	var allCells = graphUI.getElements();
	
	for (var i = 0; i < allCells.length; i++) {
		if (cellView.model.id == allCells[i].id) {
			allCells[i].findView(paperUI).$box.find('.delete').show();
			allCells[i].findView(paperUI).$box.find('.copy').show();
			allCells[i].findView(paperUI).$box.find('.glyphicon-fullscreen').parent().show();
			allCells[i].findView(paperUI).$box.find('.delete').parent().css('border', '3px dashed #0769AD');
		} else {
			allCells[i].findView(paperUI).$box.find('.delete').hide();
			allCells[i].findView(paperUI).$box.find('.copy').hide();
			allCells[i].findView(paperUI).$box.find('.glyphicon-fullscreen').parent().hide();
			allCells[i].findView(paperUI).$box.find('.delete').parent().css('border', 'none');
		}
	}
}

// An tat ca cac cong cu xung quanh phan tu dang chon
function hideAllToolUI() {
	// Lay tat ca cac doi tuong
	var allCells = graphUI.getElements();
	for (var i = 0; i < allCells.length; i++) {
		allCells[i].findView(paperUI).$box.find('.delete').hide();
		allCells[i].findView(paperUI).$box.find('.copy').hide();
		allCells[i].findView(paperUI).$box.find('.glyphicon-fullscreen').parent().hide();
		allCells[i].findView(paperUI).$box.find('.delete').parent().css('border', 'none');
	}
}

function enableInput(cellView) {
	cellView.$box.find("input").css('pointer-events', 'auto');
	cellView.$box.find("input").focus();
	cellView.$box.find("textarea").css('pointer-events', 'auto');
	cellView.$box.find("textarea").focus();
	cellView.$box.find('.delete').parent().css('border', 'none');
}

function disableInput() {
	// Lay tat ca cac doi tuong
	var allCells = graphUI.getElements();
	for (var i = 0; i < allCells.length; i++) {
		allCells[i].findView(paperUI).$box.find("input").css('pointer-events', 'none');
		allCells[i].findView(paperUI).$box.find("textarea").css('pointer-events', 'none');
		allCells[i].findView(paperUI).$box.find('.delete').parent().css('border', 'none');
	}
}

graphUI.on('change:position', function(cell) {

    var paperW = paperUI.options.width;
    var paperH = paperUI.options.height;
    var cellBbox = cell.getBBox();

    if (cellBbox.origin().x <= 0 || cellBbox.origin().y <= 0
    	|| cellBbox.origin().x >= paperW - cell.attributes.size.width 
    	|| cellBbox.origin().y >= paperW - cell.attributes.size.height) {
    	cell.set('position', cell.previous('position'));
    }
});

$("#exportXMLUI").on('click', function() {
	$("#modal-exportXMLUI").modal('hide');
	var x2js = new X2JS();
	var xmlString = '<?xml version="1.0" encoding="UTF-8"?>\n<?xml-stylesheet type="text/xsl" href="template.xsl"?>\n<all>\n';
	xmlString += x2js.json2xml_str($.parseJSON(JSON.stringify(graphUI))) + "\n</all>";
	var pom = document.createElement('a');
    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' 
    	+ encodeURIComponent(xmlString));
    pom.setAttribute('download', $("#nameFileXML").val() + '.xml');
    pom.click();
});

$("#exportSVGUI").on('click', function() {

	// Khong hien thi cac view khong can thiet khi xuat hinh
//	toogleElementLinkView('none');

	$("#modal-exportSVGUI").modal('hide');
	
	var w = paperUI.options.width;
	var h = paperUI.options.height;
	paperUI.fitToContent({padding: 5});
	
	var svg = paperUI.svg;
    var serializer = new XMLSerializer();
    var svgXML = serializer.serializeToString(svg);
	
	var pom = document.createElement('a');
    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' 
    	+ encodeURIComponent(svgXML));
    pom.setAttribute('download', $("#nameFileSVGUI").val() + '.svg');
    pom.click();
    
    paperUI.setDimensions(w, h);
});
graphUI.on('change', function() {
	setIconSaveUI('notsaved');
});

graphUI.on('add', function(cellView) {
	setIconSaveUI('notsaved');
});

graphUI.on('remove', function(cellView) {
	setIconSaveUI('notsaved');
});

function setIconSaveUI(value) {
	if (value == "saved") {
		$("#saveDiagramUI").attr("style", "background: #5cb85c; color: white;");
		$("#saveDiagramUI").html("<i class='glyphicon glyphicon-floppy-saved'></i> Lưu");
	} else {
		$("#saveDiagramUI").attr("style", "background: #d9534f; color: white;");
		$("#saveDiagramUI").html("<i class='glyphicon glyphicon-floppy-remove'></i> Lưu");
	}
}

$("#saveDiagramUI").on('click', function() {
	if ($("#name-ui-show").html() != "") {
		var w = paperUI.options.width;
		var h = paperUI.options.height;
		paperUI.fitToContent({padding: 5});
		var svg = paperUI.svg;
	    var serializer = new XMLSerializer();
	    var svgXML = serializer.serializeToString(svg);
		var image = window.btoa("data:image/svg+xml;utf8," + encodeURIComponent(svgXML));
		paperUI.setDimensions(w, h);
		$("#saveDiagramUI").prop("disabled", true);
		$("#icon-not-save-UI").html("<img src='/luanvan/resources/img/icon-loading.gif' />");
		if ($(".toggle-menu-detail-project").html() == iconLeft) {
			$(".div-loading").css('width', "870px");
			$(".div-loading").css('height', "800px");
		} else {
			$(".div-loading").css('width', "1050px");
			$(".div-loading").css('height', "1000px");
		}
		$(".div-loading").show();
		$.ajax({
			url : "/luanvan/diagramui/savediagramui",
			method: "post",
			data: "nameProject=" + $("#nameProject").val() + "&path=" + window.btoa(encodeURIComponent(JSON.stringify(graphUI)))
				+ "&nameui=" + $("#name-ui-show").html() + "&image=" + image,
			success: function() {
				$("#icon-not-save-UI").html("<i class=\"mdi-notification-event-busy\"></i>");
				setIconSaveUI('saved');
				$(".div-loading").hide();
			},
			error: function() {
				alert("error");
			}
		});
	} else {
		$("#modal-newUI").modal('show');
	}
});

$(function () {
  $('[data-toggle="tooltip"]').tooltip()
  $("").hide();
})

$("#new-design-ui").on('click', function() {
	$("#input-nameUI").val("");
	$("#modal-newUI").modal('show');
	graphUI.clear();
});

$("#create-name-UI").on('click', function() {
	$.ajax({
		url: "/luanvan/diagramui/checknameui",
		data: "nameProject=" + $("#nameProject").val() + "&nameui=" + $("#input-nameUI").val(),
		success: function(result) {
			if (result == "true") {
				alert("Tên đã có");
			} else {
				$("#name-ui-show").html($("#input-nameUI").val());
				$("#name-ui-show").show('fade');
				$("#modal-newUI").modal('hide');
				$("#a-rename-ui").show('fade');
				$("#saveDiagramUI").trigger('click');
			}
		},
		error: function() {
			alert("Error");
		}
	});
});

$("#viewListUI").on("click", function() {
	$.ajax({
		url: "/luanvan/diagramui/viewlistui",
		data: "nameProject=" + $("#nameProject").val(),
		success: function(result) {
			var object = $.parseJSON(result);
			var str = "";
			for (var i = 0; i < object.ui.length; i++) {
				str += '<div class="col-md-4"><div class="thumbnail" style="background: #f2f2f2;"><div style="border: 1px solid #ddd; background: white;">';
				str += '<img width="254" height="200" src="' + window.atob(object.ui[i].image) + '" /></div>';
				str += '<div class="caption">';
		        str+= '<h3>' + object.ui[i].name + '</h3><div class="btn-group" style="width: 100%;">';
		        str+= '<button class="btn btn-primary edit-details-ui" id="' + object.ui[i].name + '" style="font-size: 20px; width: 50%;"><i class="glyphicon glyphicon-pencil"></i> Sửa</button>';
		        str+= ' <button class="btn btn-danger delete-details-ui" id="' + object.ui[i].name + '" style="font-size: 20px; width: 50%;"><i class="glyphicon glyphicon-trash"></i> Xóa</button>';
		        str+= '</div></div></div></div>';
			}
			$("#body-listUI").html(str);
			
			// Delete element when click button delete
			$(".delete-details-ui").on('click', function() {
				$(this).parent().parent().parent().hide("fade");
				var nameui = $(this).attr("id");
				$.ajax({
					url: "/luanvan/diagramui/deleteui",
					data: "&nameProject=" + $("#nameProject").val() + "&nameui=" + nameui,
					success: function(result) {
						if ($("#name-ui-show").html() == nameDiagram || $("#body-list-ui").html() == "") {
							$("#name-ui-show").html("");
							$("#a-rename-ui").hide('fade');
							setIconSave("saved");
							graphUI.clear();
						}
					},
					error: function() {
						alert("error");
					}
				});
			});
			
			// Edit ui when click button edit
			$(".edit-details-ui").on('click', function() {
				if ($("#name-ui-show").html() != "") {
					$("#saveDiagramUI").trigger('click');
				}
				
				$("#a-rename-ui").show('fade');
				var nameui = $(this).attr("id");
				$.ajax({
					url: "/luanvan/diagramui/loadui",
					data: "&nameProject=" + $("#nameProject").val() + "&nameui=" + nameui,
					success: function(result) {
						graphUI.clear();
						graphUI.fromJSON(JSON.parse(decodeURIComponent(window.atob(result))));
						$("#modal-listUI").modal('hide');
						$("#name-ui-show").html(nameui);
						divProperties(true, "#aaaaaa");
						$("#saveDiagramUI").prop("disabled", true);
						$("#properties-design-ui").hide("fade");
					},
					error: function() {
						alert("error");
					}
				});
			});

		},
		error: function() {
			alert("error");
		}
	});
	$("#modal-listUI").modal('show');
});

$("#btn-svgUI").on('click', function() {
	$("#input-rename-ui").val("");
	$("#modal-exportSVGUI").modal('show');
});

$("#btn-rename-ui").on('click', function() {
	var nameuiOld = $("#name-ui-show").html();
	console.log(nameuiOld);
	var nameuiNew = $("#input-rename-ui").val();
	console.log(nameuiNew);
	$.ajax({
		url: "/luanvan/diagramui/checknameui",
		data: "nameProject=" + $("#nameProject").val() + "&nameui=" + nameuiNew,
		success: function(result) {
			if (result == "true") {
				alert("Tên đã có");
			} else {
				$.ajax({
					url: "/luanvan/diagramui/renameui",
					data: "&nameProject=" + $("#nameProject").val() + "&nameuiOld=" + nameuiOld + "&nameuiNew=" + nameuiNew,
					success: function(result) {
						$("#name-ui-show").html(nameuiNew);
						$("#modal-rename-ui").modal('hide');
					},
					error: function() {
						alert("error");
					}
				});
			}
		},
		error: function() {
			alert("Error");
		}
	});
});

$(document).keydown(function(event) {
	if (event.keyCode == 46) {
		for (var i = 0; i < listSelectView.length; i++) {
			listSelectView[i].model.remove();
		}
	}
	if (cellViewPointerDown) {
		if (event.keyCode == 46) {
			for (var i = 0; i < listSelectView.length; i++) {
				listSelectView[i].model.remove();
			}
			cellViewPointerDown.model.remove();
		}
		if (event.keyCode == 37) {
			event.preventDefault();
			cellViewPointerDown.model.translate(-1, 0);
		}
		if (event.keyCode == 38) {
			event.preventDefault();
			cellViewPointerDown.model.translate(0, -1);
		}
		if (event.keyCode == 39) {
			event.preventDefault();
			cellViewPointerDown.model.translate(1, 0);
		}
		if (event.keyCode == 40) {
			event.preventDefault();
			cellViewPointerDown.model.translate(0, 1);
		}
		if (event.ctrlKey) {
			hasCtrlKey = true;
		}
	}
});

$("#assign-usecase-design-ui").on('click', function() {
	if ($("#name-ui-show").html() != "") { 
		$.ajax({
			url: "/luanvan/diagramui/loaduiusecase",
			data: {
				nameProject : $("#nameProject").val(), nameui : $("#name-ui-show").html()
			},
			success: function(result){
				var object = $.parseJSON(result);
				var str = "";
				for (var i = 0; i < object.usecase.length; i++) {
					if (object.usecase[i].checked == "1") {
						str += '<div class="form-group" style="word-break: break-all;">';
						str += '<label><input type="checkbox" class="checkbox-assignUI" checked="checked" value="' + object.usecase[i].id + '" /> ' 
							+ object.usecase[i].name + '</label>';
						str += '</div>';
					} else {
						str += '<div class="form-group" style="word-break: break-all;">';
						str += '<label><input type="checkbox" class="checkbox-assignUI" value="' + object.usecase[i].id + '" /> ' 
							+ object.usecase[i].name + '</label>';
						str += '</div>';
					}	
				}
				$("#body-assignUI").html(str);
				$("#modal-assignUI").modal("show");
			},
			error: function() {
				alert("Không thể tải usecase để gán vào giao diện");
			}
		});
	} else {
		alert("Chưa lựa chọn giao diện để gán");
	}
});

$("#btn-assignUI").on('click', function() {
	var idArray = "";
	$(".checkbox-assignUI").each(function() {
		if ($(this).prop("checked") == true) {
			idArray += $(this).val() + "_";
		}
	});
	$.ajax({
		url: "/luanvan/diagramui/assignuiusecase",
		type: "post",
		data: {
			usecaseIDs : idArray, nameProject : $("#nameProject").val(), nameui : $("#name-ui-show").html()
		},
		success: function(){
			$("#modal-assignUI").modal("hide");
		},
		error: function() {
			alert("Không thể gán usecase cho giao diện");
		}
	});
});

$("#open-design-ui").on('click', function() {
	$("#openFileUI").trigger('click');
});

$("#openFileUI").on('click', function(evt) {
	var file = evt.target.files[0];
	if (file) {
		var reader = new FileReader();
		reader.readAsText(file);
		reader.onload = function(e) {
			var content = e.target.result;
			
		}
	}
});


//Begin drag drop element to paper
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("id", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var pos = getClickPosition(ev);
    var id = ev.dataTransfer.getData("id");
    if (id == "div-design-ui") {
		createDiv(pos.x, pos.y);
	}
	if (id == "button-design-ui") {
		createButton(pos.x, pos.y);
	}
	if (id == "label-design-ui") {
		createLabel(pos.x, pos.y);
	}
	if (id == "radio-design-ui") {
		createRadio(pos.x, pos.y);
	}
	if (id == "radio-checked-design-ui") {
		createRadioChecked(pos.x, pos.y);
	}
	if (id == "checkbox-design-ui") {
		createCheckbox(pos.x, pos.y);
	}
	if (id == "checkbox-checked-design-ui") {
		createCheckboxChecked(pos.x, pos.y);
	}
	if (id == "textarea-design-ui") {
		createTextArea(pos.x, pos.y);
	}
	if (id == "textbox-design-ui") {
		createTextBox(pos.x, pos.y);
	}
}
// End drag drop element to paper
