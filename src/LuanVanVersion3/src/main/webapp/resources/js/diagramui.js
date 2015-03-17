$(document).ready(function() {
	divProperties(true, "#aaaaaa");	
});

// Khoi tao so do - 1 graph la 1 so do
var graphUI = new joint.dia.Graph;

// Noi de ve so do cua graph da khoi tao
var paperUI = new joint.dia.Paper({
    el: $('#paperUI'),
    fill: 'white',
    width: 870,
    height: 870,
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

// createDiv(20, 20);
// createButton(260, 20);
// createTextBox(260, 70);
// createTextArea(260, 140);
// createRadioChecked(460, 20);
// createRadio(460, 70);
// createCheckboxChecked(460, 120);
// createCheckbox(460, 170);
// createLabel(660, 20);

paperUI.on('cell:pointerdblclick', function(cellView, x, y) {
	enableInput(cellView);
});

paperUI.on('blank:pointerclick', function(cellView, x, y) {
	hideAllToolUI();
	disableInput(cellView);
	divProperties(true, '#aaaaaa');
	$("#properties-design-ui").hide("fade");
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

var hasResizeUI = false;
var cellViewResizeUI = null;
var cellViewPointerDown = null;
var hasCopyUI = false;
var cellViewCopyUI = null;

paperUI.on('cell:pointerdown', function(cellView, evt, px, py) {

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

var hasViewChangeBorder = null;

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
		moveTextButton(cellNew.findView(paperUI), cellNew.attributes.size.width, cellNew.attributes.size.height);
		hasCopyUI = false;
	}
	if (dragDropElementUI) {
		dragDropElementUI = false;
		if (choosedElement == "div-design-ui") {
			createDiv(pos.x, pos.y);
		}
		if (choosedElement == "button-design-ui") {
			createButton(pos.x, pos.y);
		}
		if (choosedElement == "label-design-ui") {
			createLabel(pos.x, pos.y);
		}
		if (choosedElement == "radio-design-ui") {
			createRadio(pos.x, pos.y);
		}
		if (choosedElement == "radio-checked-design-ui") {
			createRadioChecked(pos.x, pos.y);
		}
		if (choosedElement == "checkbox-design-ui") {
			createCheckbox(pos.x, pos.y);
		}
		if (choosedElement == "checkbox-checked-design-ui") {
			createCheckboxChecked(pos.x, pos.y);
		}
		if (choosedElement == "textarea-design-ui") {
			createTextArea(pos.x, pos.y);
		}
		if (choosedElement == "textbox-design-ui") {
			createTextBox(pos.x, pos.y);
		}
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
	toogleElementLinkView('none');

	$("#modal-exportSVGUI").modal('hide');
	var svg = paperUI.svg;
    var serializer = new XMLSerializer();
    var svgXML = serializer.serializeToString(svg);
	
	var pom = document.createElement('a');
    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' 
    	+ encodeURIComponent(svgXML));
    pom.setAttribute('download', $("#nameFileSVGUI").val() + '.svg');
    pom.click();
});

//Xu ly khi nhan phim
$(document).keydown(function(event) {

	if (event.keyCode == 46) {
		if (cellViewPointerDown) {
			cellViewPointerDown.model.remove();
		}
	}
});
