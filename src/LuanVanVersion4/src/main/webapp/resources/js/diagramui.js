var hasResizeUI = false;
var cellViewResizeUI = null;
var cellViewPointerDown = null;
var hasCopyUI = false;
var cellViewCopyUI = null;
var hasViewChangeBorder = null;
var hasBlankPointerDown = null;
var selectView = null;
var dragDropElementUI = false;
var choosedElement = false;
var graphUI = new joint.dia.Graph;

var backHistoryList = [];
var fowardHistoryList = [];
var widthPaperUI = $('#paperUI').css("width").split("px");

var paperUI = new joint.dia.Paper({
    el: $('#paperUI'),
    fill: '#ffffff',
    width: widthPaperUI[0],
    height: 1000,
    model: graphUI,
    gridSize: 1
});

var lineH1 = new joint.shapes.basic.Rect({
	size: { width: 1500, height: 1},
	attrs: { rect: { fill: '#d43f3a', stroke: 'none'} }
});

var lineH2 = new joint.shapes.basic.Rect({
	size: { width: 1500, height: 1},
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
	// Begin loading ui
	if ($("#path").html() && $("#path").html() != "") {
		graphUI.fromJSON(JSON.parse(decodeURIComponent(window.atob($("#path").html()))));
		var elements = graphUI.getElements();
		for (var i = 0; i < elements.length; i++) {
			var attrs = elements[i].attributes;
			if (attrs.type == "ui.Table") {
				createTableDynamic(attrs.row, attrs.col, attrs.size.width, attrs.size.height, attrs.attrs.text, attrs.attrs.rect);
				var table = new joint.shapes.ui.Table({
					position: { x: attrs.position.x, y: attrs.position.y },
				});
				elements[i].remove();
				graphUI.addCell(table);
			}
		}
	}
	if ($("#name-ui-show").html() != "") {
		$("#a-rename-ui").show("fade");
	} else {
		$("#a-rename-ui").hide("fade");
	}
	setIconSaveUI('saved');
	// End loading ui
});

$("#fullscreen-ui").on('click', function() {
	if ($(this).html() == '<i class="glyphicon glyphicon-fullscreen"></i>') {
		$(this).html('<i class="glyphicon glyphicon-resize-small"></i>');
		$("#header-diagram").hide();
		$("#properties-diagram").hide();
		$("#content-diagram").css("top", 0);
	} else {
		$(this).html('<i class="glyphicon glyphicon-fullscreen"></i>');
		$("#header-diagram").show();
		$("#properties-diagram").show();
		$("#content-diagram").css("top", "100px");
	}
	
})

paperUI.on('cell:pointerdblclick', function(cellView, x, y) {
	enableInput(cellView);
});

paperUI.on('blank:pointerdown', function(cellView, x, y) {
	$("#list-class-of-element").hide();
	hideAllToolUI();
	disableInput(cellView);
	cellViewPointerDown = null;
	if (!hasCopyUI && !hasResizeUI) {
		listSelectView = [];
		hasBlankPointerDown = true;
		selectView = new joint.shapes.basic.Rect({
			type: 'selectView',
			position: { x: x, y: y },
			size: { width: 1, height: 1 },
			attrs:{
				rect: { 'stroke' : '#333333', 'stroke-width': 1, 'stroke-dasharray': 3, fill: '#337ab7', 'fill-opacity': 0.5}
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
        if (cellViewBelow && cellViewBelow.model.get('parent') !== cell.id && (cellViewBelow.model.attributes.type == "ui.Div" || cellViewBelow.model.attributes.type == "ui.Form")) {
        	if (!cell.isEmbeddedIn(cellViewBelow) && !cell.get('parent')) {
        		cellViewBelow.model.embed(cell);
        	}
        }
    }
     if (hasViewChangeBorder) {
		hasViewChangeBorder.model.attr('rect/stroke', '#000000');
		hasViewChangeBorder = null;
	}
});

function setContentListClass(cell) {
	var strClass = "";
	var classArr = cell.attributes.nameClass.split(" ");
	var noBlank = true;
	for (var i = 0; i < classArr.length; i++) {
		if (classArr[i] != "") {
			noBlank = false;
			strClass +=  '<div>' + classArr[i] + '<button id="' + classArr[i] + '_' + cell.id 
				+ '" class="removeClassElement pull-right"><i class="glyphicon glyphicon-trash"></i></button></div>';
		}
	}
	$("#list-class-of-element").html(strClass);
	
	$(".removeClassElement").on('click', function() {
		removeClassElement(this.id);
	})
	if (noBlank) {
		$("#list-class-of-element").html('<div>Chưa gắn class</div>');
	}
}



function removeClassElement(id) {
	var idArray = id.split("_");
	var name = idArray[0];
	var cell = graphUI.getCell(idArray[1]);
	var strClass = "";
	var classArr = cell.attributes.nameClass.split(" ");
	for (var i = 0; i < classArr.length; i++) {
		if (classArr[i] != name) {
			strClass += classArr[i] + " ";
		}
	}
	cell.attributes.nameClass = strClass;
	setContentListClass(cell);
	setContentMessage("Thuộc tính đã thay đổi");
}

paperUI.on('cell:pointerdown', function(cellView, evt, px, py) {
	
	console.log(cellView.model.attributes);
	
	setContentListClass(cellView.model);
	$("#list-class-of-element").show();
	
	listSelectView = [];
	
	$("#properties-design-ui").show("fade");
	
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
	
	cellView.$box.find(".resize").on('mousedown', function() {
		hasResizeUI = true;
		cellViewResizeUI = cellView;
	});
	
	cellView.$box.find(".delete").on('mousedown', function() {
		backHistoryList.push(cellView.model);
		backHistoryList.push("delete");
	});
	cellView.$box.find(".copy").on('mousedown', function() {
		hasCopyUI = true;
		cellViewCopyUI = cellView;
	});

	cellView.$box.find(".link").on('mousedown', function() {
		$.ajax({
			url: "../diagramui/viewlistui",
			data: "nameProject=" + $("#nameProject").val(),
			success: function(result) {
				var object = $.parseJSON(result);
				var str = "";
				for (var i = 0; i < object.ui.length; i++) {
					if (object.ui[i].name != $("#name-ui-show").html()) {
						str += '<div class="form-group">';
						if (cellView.model.attributes.connect.indexOf(object.ui[i].name) != -1) {
							str+= '<button class="btn btn-success" id="' + object.ui[i].name + '"><i class="glyphicon glyphicon-link"></i></button>';
							str+= '<a href="../diagramui/viewdiagramui?nameProject=' + $("#nameProject").val() + '&nameUI=' + object.ui[i].name + '" target="_blank"> ' + object.ui[i].name + '</a>';
						} else {
							str+= '<button class="btn btn-danger" id="' + object.ui[i].name + '"><i class="glyphicon glyphicon-link"></i></button>';
							str+= '<span> ' + object.ui[i].name + '</span>';
						}
				        str+= '</div>';
					}
				}
				$("#list-link-ui").html(str);
				$("#list-link-ui div button").on('click', function() {
					if ($(this).hasClass("btn-success")) {
						cellView.model.attributes.connect.pop(this.id);
						$(this).removeClass("btn-success");
						$(this).addClass("btn-danger");
						$(this).parent().children('a').remove();
						$(this).parent().append('<span> ' + this.id + '</span>');
						setContentMessage("Link đã gở bỏ khỏi giao diện");
					} else {
						cellView.model.attributes.connect.push(this.id);
						$(this).parent().children('span').remove();
						$(this).parent().append('<a href="../diagramui/viewdiagramui?nameProject=' + $("#nameProject").val() + '&nameUI=' + this.id + '" target="_blank"> ' + this.id + '</a>');
						$(this).removeClass("btn-danger");
						$(this).addClass("btn-success");
						setContentMessage("Link đã được gắn vào giao diện");
					}
					$("#saveDiagramUI").trigger('click');
				});
			},
			error: function() {
				alert("Không thể lấy danh sách các sơ đồ");
			}
		});
		$("#modal-link-ui").modal('show');
	});
	
	var cell = cellView.model;

    if (!cell.get('embeds') || cell.get('embeds').length === 0) {
        cell.toFront();
    }
    
    if (cell.get('embeds') && cell.get('embeds').length > 0) {
    	var embeds = cell.get('embeds');
    	setContentMessage("Nhấn phím 'U' để UnGroup");
    	$(document).keydown(function(event) {
    		if (event.keyCode == 85) {
        		for (var i = 0; i < embeds.length; i++) {
        			cell.unembed(graphUI.getCell(embeds[i]));
        		}
        		setContentMessage("Đã UnGroup");
        	}
    	})
    }
    
//    if (cell.get('parent')) {
//        graphUI.getCell(cell.get('parent')).unembed(cell);
//    }
});

paperUI.on('cell:pointermove', function(cellView, evt, x, y) {
	
	var x = cellView.model.attributes.position.x;
	var y = cellView.model.attributes.position.y;

	$("#X-design-ui").val(x);
	$("#Y-design-ui").val(y);
	if (cellView.model.attributes.embeds == undefined) {
		alignBorderAuto(cellView);
	}

	var cell = cellView.model;
    var cellViewsBelow = paperUI.findViewsFromPoint(cell.getBBox().center());
    if (cellViewsBelow.length) {
        // Note that the findViewsFromPoint() returns the view for the `cell` itself.
        var cellViewBelow = _.find(cellViewsBelow, function(c) { return c.model.id !== cell.id });

        if (hasViewChangeBorder) {
    		hasViewChangeBorder.model.attr('rect/stroke', '#000000');
    	}

        // Prevent recursive embedding.
        if (cellViewBelow && cellViewBelow.model.get('parent') !== cell.id && (cellViewBelow.model.attributes.type == "ui.Div" || cellViewBelow.model.attributes.type == "ui.Form")) {
        	if (!cell.isEmbeddedIn(cellViewBelow) && !cell.get('parent')) {
        		cellViewBelow.model.attr('rect/stroke', 'blue');
                hasViewChangeBorder = cellViewBelow;
        	}
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
				listElements[i].$box.find('.delete').parent().css('outline', '#0769AD dashed 2px');
				listSelectView.push(listElements[i]);
				setContentMessage("Nhấn phím 'Delete' để xóa");
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
		hasCopyUI = false;
	}
	if (dragDropElementUI) {
		dragDropElementUI = false;
	}
});

$("#increase-layer-design-ui").on("click", function() {
	cellViewPointerDown.model.toFront();
})

$("#decrease-layer-design-ui").on("click", function() {
	cellViewPointerDown.model.toBack();
})

$("#element-design-ui button").on("mousedown", function(evt) {
	dragDropElementUI = true;
	choosedElement = this.id;
});

$("#div-bootstrap-design-ui").on('change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attributes.nameClass += " " + $(this).val();
		setContentListClass(cellViewPointerDown.model);
		setIconSaveUI('notsaved');
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#button-bootstrap-design-ui").on('change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attributes.nameClass += " " + $(this).val();
		setContentListClass(cellViewPointerDown.model);
		setIconSaveUI('notsaved');
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#form-bootstrap-design-ui").on('change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attributes.nameClass += " " + $(this).val();
		setContentListClass(cellViewPointerDown.model);
		setIconSaveUI('notsaved');
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#table-bootstrap-design-ui").on('change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attributes.nameClass += " " + $(this).val();
		setContentListClass(cellViewPointerDown.model);
		setIconSaveUI('notsaved');
		setContentMessage("Thuộc tính đã thay đổi");
	}
});


$("#width-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		resizeWidthHeightCell(cellViewPointerDown, $(this).val(), $("#height-design-ui").val());
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#height-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		resizeWidthHeightCell(cellViewPointerDown, $("#width-design-ui").val(), $(this).val());
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#font-color-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({text: {'fill': $(this).val()}});
		setContentMessage("Thuộc tính đã thay đổi");
	}
		
});

$("#font-family-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({text: {'font-family': $(this).val()}});
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#font-size-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({text: {'font-size': $(this).val()}});
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#background-color-design-ui").on('input', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({rect: {fill: $(this).val()}});
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#border-color-design-ui").on('input', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({rect: {stroke: $(this).val()}});
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#X-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		var x = cellViewPointerDown.model.attributes.position.x;
		var y = cellViewPointerDown.model.attributes.position.y;
		cellViewPointerDown.model.translate($(this).val() - x, $("#Y-design-ui").val() - y);
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#Y-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		var x = cellViewPointerDown.model.attributes.position.x;
		var y = cellViewPointerDown.model.attributes.position.y;
		cellViewPointerDown.model.translate($("#X-design-ui").val() - x, $(this).val() - y);
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#border-size-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({rect: {'stroke-width': $(this).val()}});
		setContentMessage("Thuộc tính đã thay đổi");
	}
});

$("#border-radius-design-ui").on('keyup change', function() {
	if (cellViewPointerDown) {
		cellViewPointerDown.model.attr({rect: {rx: $(this).val(), ry: $(this).val()}});
		setContentMessage("Thuộc tính đã thay đổi");
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
	console.log(allElements.length);
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

function resizeWidthHeightCell(cellView, width, height) {
	if (cellView.model.attributes.type == "ui.Button") {
		moveText(cellView, width, height);
	}
	if (cellView.model.attributes.type == "ui.SelectBox") {
		moveSelectBox(cellView, width, height);
	}
	if (cellView.model.attributes.type == "ui.Table") {
		moveTable(cellView, width, height);
	}
	moveRectSVG(cellView, width, height);
	cellView.model.resize(width, height);
}

// Di chuyen text button
function moveText(cellView, width, height) {
	cellView.model.attr({text: {x: width / 2, y: height / 2 + 5}});
}

// Di chuyen rect svg 
function moveRectSVG(cellView, width, height) {
	cellView.model.attr({rect: {width: width, height: height}});
}

// Di chuyen arrow select
function moveSelectBox(cellView, width, height) {
	cellView.model.attr({path: { d: "M " + (width - 20) + " " + (height/2 - 5) + " L " + (width - 10) + " " + (height/2 - 5) + " L " + (width - 15) + " " + (height/2 + 5) + " L " + (width - 20) + " " + (height/2 - 5) }});
	cellView.model.attr({text: {y: height/2 + 5}});
}

// Di chuyen cac duong ke trong table
function moveTable(cellView, width, height) {
	var column = cellView.model.attributes.col;
	var row = cellView.model.attributes.row;
	for (var i = 0; i < column - 1; i++) {
		var x = ((i + 1)/column) * width;
		cellView.model.attr(".column" + i, {x1: x, y1: 0, x2: x, y2: height});
	}
	for (var i = 0; i < row - 1; i++) {
		var y = ((i + 1)/row) * height;
		cellView.model.attr(".row" + i, {x1: 0, y1: y, x2: width, y2: y});
	}
	var dem = 0;
	for (var i = 0; i < row; i++) {
		for (var j = 0; j < column; j++) {
			cellView.$box.find(".inputCell" + dem).attr('style', 'width:' + width/column + 'px !important; height:' + height/row +'px !important;');
			cellView.model.attr(".text" + dem, {x: (((j/column) * width) + 5), y: ((((i*2 + 1)/(row * 2)) * height) + 5)});
			dem++;
		}
	}
}

// Tao mot button
function createButton(positionX, positionY) {
	var button = new joint.shapes.ui.Button({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(button);
	backHistoryList.push(button);
	backHistoryList.push("add");
}

// Tao mot div
function createDiv(positionX, positionY) {
	var div = new joint.shapes.ui.Div({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(div);
	backHistoryList.push(div);
	backHistoryList.push("add");
}

//Tao mot form
function createForm(positionX, positionY) {
	var form = new joint.shapes.ui.Form({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(form);
	backHistoryList.push(form);
	backHistoryList.push("add");
}

// Tao mot textbox
function createTextBox(positionX, positionY) {
	var textBox = new joint.shapes.ui.TextBox({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(textBox);
	backHistoryList.push(textBox);
	backHistoryList.push("add");
}

// Tao mot textbox
function createTextArea(positionX, positionY) {
	var textArea = new joint.shapes.ui.TextArea({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(textArea);
	backHistoryList.push(textArea);
	backHistoryList.push("add");
}

// Tao mot label
function createLabel(positionX, positionY) {
	var label = new joint.shapes.ui.Label({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(label);
	backHistoryList.push(label);
	backHistoryList.push("add");
}

// Tao mot radio checked
function createRadioChecked(positionX, positionY) {
	var radioChecked = new joint.shapes.ui.RadioChecked({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(radioChecked);
	backHistoryList.push(radioChecked);
	backHistoryList.push("add");
}

// Tao mot radio
function createRadio(positionX, positionY) {
	var radio = new joint.shapes.ui.Radio({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(radio);
	backHistoryList.push(radio);
	backHistoryList.push("add");
}

// Tao mot checkbox checked
function createCheckboxChecked(positionX, positionY) {
	var checkboxChecked = new joint.shapes.ui.CheckboxChecked({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(checkboxChecked);
	backHistoryList.push(checkboxChecked);
	backHistoryList.push("add");
}
	
// Tao mot checkbox
function createCheckbox(positionX, positionY) {
	var checkbox = new joint.shapes.ui.Checkbox({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(checkbox);
	backHistoryList.push(checkbox);
	backHistoryList.push("add");
}

//Tao mot selectbox
function createSelectbox(positionX, positionY) {
	var selectbox = new joint.shapes.ui.SelectBox({
		position: { x: positionX, y: positionY }
	});
	graphUI.addCell(selectbox);
	backHistoryList.push(selectbox);
	backHistoryList.push("add");
}

$("#modal-table-size-ui").on('shown.bs.modal', function() {
	$("#input-col-ui").focus();
})

//Tao mot table
function createTable(positionX, positionY) {
//	$("#modal-table-size-ui").modal('show');
	var col = $("#input-col-ui").val();
	var row = $("#input-row-ui").val();
	if (col == "" || row == "") {
		setContentMessage("Không được bỏ trống");
	} else if (col < 0 || row < 0) {
		setContentMessage("Phải lớn hơn 0");
	} if (col > 10 || row > 10) { 
		setContentMessage("Phải nhỏ hơn 10");
	}	
	else {
		createTableDynamic(row, col, 350, 100, "", "");
		var table = new joint.shapes.ui.Table({
			position: { x: positionX, y: positionY },
		});
		graphUI.addCell(table);
		backHistoryList.push(table);
		backHistoryList.push("add");
		$("#modal-table-size-ui").modal('hide');
		setContentMessage("Table đã được tạo");
	}
}
$("#btn-table-size-ui").on('click', function(){
	createTable(parseInt($("#input-hidden-x-position").val()), parseInt($("#input-hidden-y-position").val()));
});

// Su dung hien thi cac button xung quanh doi tuong dang chon
function displayToolUI(cellView) {

	// Lay tat ca cac doi tuong
	var allCells = graphUI.getElements();
	
	for (var i = 0; i < allCells.length; i++) {
		var element = allCells[i].findView(paperUI);
		if (element.model.attributes.type != "selectView") {
			if (cellView.model.id == allCells[i].id) {
				element.$box.find('.delete').show();
				element.$box.find('.copy').show();
				element.$box.find('.link').show();
				element.$box.find('.glyphicon-fullscreen').parent().show();
				element.$box.find('.delete').parent().css('outline', '#0769AD dashed 2px');
			} else {
				element.$box.find('.delete').hide();
				element.$box.find('.copy').hide();
				element.$box.find('.link').hide();
				element.$box.find('.glyphicon-fullscreen').parent().hide();
				element.$box.find('.delete').parent().css('outline', 'none');
			}
		}
	}
}

// An tat ca cac cong cu xung quanh phan tu dang chon
function hideAllToolUI() {
	// Lay tat ca cac doi tuong
	var allCells = graphUI.getElements();
	for (var i = 0; i < allCells.length; i++) {
		var element = allCells[i].findView(paperUI);
		if (element.model.attributes.type != "selectView") {
			element.$box.find('.delete').hide();
			element.$box.find('.copy').hide();
			element.$box.find('.link').hide();
			element.$box.find('.glyphicon-fullscreen').parent().hide();
			element.$box.find('.delete').parent().css('outline', 'none');
		}
	}
}

function enableInput(cellView) {
	cellView.$box.find("input").css('pointer-events', 'auto');
	cellView.$box.find("input:first").focus();
	cellView.$box.find("textarea").css('pointer-events', 'auto');
	cellView.$box.find("textarea:first").focus();
	cellView.$box.find('.delete').parent().css('outline', 'none');
}

function disableInput() {
	// Lay tat ca cac doi tuong
	var allCells = graphUI.getElements();
	for (var i = 0; i < allCells.length; i++) {
		var element = allCells[i].findView(paperUI);
		if (element.model.attributes.type != "selectView") {
			element.$box.find("input").css('pointer-events', 'none');
			element.$box.find("textarea").css('pointer-events', 'none');
			element.$box.find('.delete').parent().css('outline', 'none');
		}
	}
}

graphUI.on('change:position', function(cell) {

    var paperW = paperUI.options.width;
    var paperH = paperUI.options.height;
    var cellBbox = cell.getBBox();
	if (cellBbox.origin().x <= 0 || cellBbox.origin().y <= 0 || cellBbox.topRight().x >= paperW
    	|| cellBbox.origin().x >= paperW - cell.attributes.size.width 
    	|| cellBbox.origin().y >= paperW - cell.attributes.size.height) {
//			cell.set('position', cell.previous('position'));
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

	if ($("#nameFileSVGUI").val() != "") {
		$("#modal-exportSVGUI").modal('hide');
		
		var w = paperUI.options.width;
		var h = paperUI.options.height;
		paperUI.fitToContent({padding: 5});
		
		var svg = paperUI.svg;
		if ($('input[name="radioChooseTypeExportImage"]:checked').val() == "svg") {
			var serializer = new XMLSerializer();
		    var svgXML = serializer.serializeToString(svg);
			var pom = document.createElement('a');
		    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' 
		    	+ encodeURIComponent(svgXML));
		    pom.setAttribute('download', $("#nameFileSVGUI").val() + '.svg');
		    pom.click();
		} else {
			saveSvgAsPng(svg, $("#nameFileSVG").val() + '.png');
		}
	    
	    paperUI.setDimensions(w, h);
	    setContentMessage("Xuất ảnh thành công");
	} else {
		setContentMessage("Xin vui lòng nhập tên tập tin");
		$("#nameFileSVGUI").focus();
	}
});

graphUI.on('change:position change:attrs change:size change:embeds change:parent', function(cellView) {
	if (cellView.attributes.type != "selectView") {
		setIconSaveUI('notsaved');
		setContentMessage("Giao diện đã thay đổi");
	}
});

$("#back-history-ui").on('click', function() {
	if (backHistoryList.length) {
		var action = backHistoryList.pop();
		var cellView = backHistoryList.pop();
		if (action == "add") {
			fowardHistoryList.push(cellView);
			fowardHistoryList.push('delete');
			cellView.remove();
		} else if (action == "delete") {
			fowardHistoryList.push(cellView);
			fowardHistoryList.push('add');
			graphUI.addCell(cellView);
		}
	}
});

$("#foward-history-ui").on('click', function() {
	if (fowardHistoryList.length) {
		var action = fowardHistoryList.pop();
		var cellView = fowardHistoryList.pop();
		if (action == "add") {
			backHistoryList.push(cellView);
			backHistoryList.push('delete');
			cellView.remove();
		} else if (action == "delete") {
			backHistoryList.push(cellView);
			backHistoryList.push('add');
			graphUI.addCell(cellView);
		}
	}
});

graphUI.on('add', function(cellView) {
	if (cellView.attributes.type != "selectView") {
		setIconSaveUI('notsaved');
	}
});

graphUI.on('remove', function(cellView) {
	if (cellView.attributes.type != "selectView") {
		setIconSaveUI('notsaved');
	}
});

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
		$.ajax({
			url : "../diagramui/savediagramui",
			method: "post",
			data: "nameProject=" + $("#nameProject").val() + "&path=" + window.btoa(encodeURIComponent(JSON.stringify(graphUI)))
				+ "&nameui=" + $("#name-ui-show").html() + "&image=" + image,
			success: function() {
				setIconSaveUI('saved');
				setContentMessage("Giao diện đã lưu thành công");
			},
			error: function() {
				alert("error");
			}
		});
	} else {
		$("#modal-newUI").modal('show');
	}
});

function setContentMessage(content) {
	$("#message-alert-for-user").html(content);
	$("#message-alert-for-user").show();
	$('#message-alert-for-user').delay(2000).fadeOut(0);
}

$("#new-design-ui").on('click', function() {
	$("#modal-newUI").modal('show');
	$('#modal-newUI').on('shown.bs.modal', function () {
		$("#input-nameUI").val("");
		$("#input-nameUI").focus();
	})
});

$("#create-name-UI").on('click', function() {
	if ($("#input-nameUI").val() != "") {
		$.ajax({
			url: "../diagramui/checknameui",
			data: "nameProject=" + $("#nameProject").val() + "&nameui=" + $("#input-nameUI").val(),
			success: function(result) {
				if (result == "true") {
					setContentMessage("Tên đã có");
				} else {
					setContentMessage("Đã tạo giao diện mới");
					$("#name-ui-show").html($("#input-nameUI").val());
					$("#name-ui-show").show('fade');
					$("#modal-newUI").modal('hide');
					$("#a-rename-ui").show('fade');
					graphUI.clear();
					$("#saveDiagramUI").trigger('click');
				}
			},
			error: function() {
				alert("Error");
			}
		});
	} else {
		setContentMessage("Tên không được bỏ trống");
		$("#input-nameUI").focus();
	}
	
});

$("#viewListUI").on("click", function() {
	$.ajax({
		url: "../diagramui/viewlistui",
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
					url: "../diagramui/deleteui",
					data: "&nameProject=" + $("#nameProject").val() + "&nameui=" + nameui,
					success: function(result) {
						if ($("#name-ui-show").html() == nameui || $("#body-list-ui").html() == "") {
							$("#name-ui-show").html("");
							$("#a-rename-ui").hide('fade');
							graphUI.clear();
						}
						setIconSave("saved");
						setContentMessage("Đã xóa giao diện");
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
					url: "../diagramui/loadui",
					data: "&nameProject=" + $("#nameProject").val() + "&nameui=" + nameui,
					success: function(result) {
						graphUI.clear();
						graphUI.fromJSON(JSON.parse(decodeURIComponent(window.atob(result))));
						var elements = graphUI.getElements();
						for (var i = 0; i < elements.length; i++) {
							var attrs = elements[i].attributes;
							if (attrs.type == "ui.Table") {
								createTableDynamic(attrs.row, attrs.col, attrs.size.width, attrs.size.height, attrs.attrs.text, attrs.attrs.rect);
								var table = new joint.shapes.ui.Table({
									position: { x: attrs.position.x, y: attrs.position.y },
								});
								elements[i].remove();
								graphUI.addCell(table);
							}
						}
						$("#modal-listUI").modal('hide');
						$("#name-ui-show").html(nameui);
						$("#saveDiagramUI").prop("disabled", true);
						$("#properties-design-ui").hide("fade");
						setIconSaveUI("saved");
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
	$("#modal-exportSVGUI").modal('show');
	$('#modal-exportSVGUI').on('shown.bs.modal', function () {
		$("#nameFileSVGUI").val("");
		$("#nameFileSVGUI").focus();
	})
});

$("#a-rename-ui").on('click', function() {
	$("#input-rename-ui").focus();
});
$("#btn-rename-ui").on('click', function() {
	var nameuiOld = $("#name-ui-show").html();
	var nameuiNew = $("#input-rename-ui").val();
	if (nameuiNew != "") {
		$.ajax({
			url: "../diagramui/checknameui",
			data: "nameProject=" + $("#nameProject").val() + "&nameui=" + nameuiNew,
			success: function(result) {
				if (result == "true") {
					setContentMessage("Tên đã có");
				} else {
					$.ajax({
						url: "../diagramui/renameui",
						data: "&nameProject=" + $("#nameProject").val() + "&nameuiOld=" + nameuiOld + "&nameuiNew=" + nameuiNew,
						success: function(result) {
							$("#name-ui-show").html(nameuiNew);
							$("#saveDiagramUI").trigger('click');
							$("#modal-rename-ui").modal('hide');
							setContentMessage("Tên giao diện đã thay đổi");
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
	} else {
		setContentMessage("Tên thay đổi không được bỏ trống");
	}
});

$(document).keydown(function(event) {
	if (event.keyCode == 46) {
		for (var i = 0; i < listSelectView.length; i++) {
			listSelectView[i].model.remove();
			backHistoryList.push(listSelectView[i].model);
			backHistoryList.push("delete");
		}
		$("#list-class-of-element").hide();
		setContentMessage("Xóa thành công");
	}
	if (cellViewPointerDown) {
		if (event.keyCode == 46) {
			for (var i = 0; i < listSelectView.length; i++) {
				listSelectView[i].model.remove();
				backHistoryList.push(listSelectView[i].model);
				backHistoryList.push("delete");
			}
			cellViewPointerDown.model.remove();
			backHistoryList.push(cellViewPointerDown.model);
			backHistoryList.push("delete");
			$("#list-class-of-element").hide();
			setContentMessage("Xóa thành công");
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
	if (event.keyCode == 13) {
		if ($("#modal-newUI").hasClass("in")) {
			$("#create-name-UI").trigger('click');
		}
		if ($("#modal-table-size-ui").hasClass("in")) {
			$("#btn-table-size-ui").trigger('click');
		}
		if ($("#modal-rename-ui").hasClass("in")) {
			$("#btn-rename-ui").trigger('click');
		}
		if ($("#modal-assignUI").hasClass("in")) {
			$("#btn-assignUI").trigger('click');
		}
		if ($("#modal-exportSVGUI").hasClass("in")) {
			$("#exportSVGUI").trigger('click');
		}
	}
});

$("#assign-usecase-design-ui").on('click', function() {
	if ($("#name-ui-show").html() != "") { 
		$("#body-assignUI").html("Đang tải danh sách use-cases ...");
		$("#modal-assignUI").modal("show");
		$.ajax({
			url: "../diagramui/loaduiusecase",
			data: {
				nameProject : $("#nameProject").val(), nameui : $("#name-ui-show").html()
			},
			success: function(result){
				var object = $.parseJSON(result);
				var str = "";
				for (var j = 0; j < object.nhomucs.length; j++) {
					str += '<div><Label class="text-primary">Nhóm ' + object.nhomucs[j].name + '</Label>';
					for (var i = 0; i < object.nhomucs[j].usecases.length; i++) {
						var id = object.nhomucs[j].usecases[i].id;
						var name = object.nhomucs[j].usecases[i].name;
						if (object.nhomucs[j].usecases[i].checked == "1") {
							str += '<div class="form-group" style="word-break: break-word;">';
							str += '<label class="text-danger"><input type="checkbox" class="checkbox-assignUI" checked="checked" value="' + id + '" /> ' + name + '</label>';
							str += '</div>';
						} else {
							str += '<div class="form-group" style="word-break: break-word;">';
							str += '<label><input type="checkbox" class="checkbox-assignUI" value="' + id + '" /> ' + name + '</label>';
							str += '</div>';
						}	
					}
					str += "</div>";
				}
				$("#body-assignUI").html(str);
				$("#modal-assignUI").modal("show");
				$(".checkbox-assignUI").on('click', function() {
					if ($(this).parent().hasClass("text-danger")) {
						$(this).parent().attr("class", "");
					} else {
						$(this).parent().attr("class", "text-danger");
					}
				});
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
	if (idArray != "") {
		$.ajax({
			url: "../diagramui/assignuiusecase",
			type: "post",
			data: {
				usecaseIDs : idArray, nameProject : $("#nameProject").val(), nameui : $("#name-ui-show").html()
			},
			success: function(){
				$("#modal-assignUI").modal("hide");
				setContentMessage("Giao diện đã gán usecase");
			},
			error: function() {
				alert("Không thể gán usecase cho giao diện");
			}
		});
	} else {
		$("#modal-assignUI").modal("hide");
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
    if ($("#name-ui-show").html() != "") {
	    var pos = getClickPosition(ev);
	    var id = ev.dataTransfer.getData("id");
	    if (id == "form-design-ui") {
			createForm(pos.x, pos.y);
		}
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
		if (id == "selectbox-design-ui") {
			createSelectbox(pos.x, pos.y);
		}
		if (id == "table-design-ui") {
			$("#input-hidden-x-position").val(pos.x);
			$("#input-hidden-y-position").val(pos.y);
			$("#modal-table-size-ui").modal('show');
		}
    } else {
    	$("#modal-newUI").modal('show');
    }
}
// End drag drop element to paper

$("#screenflow-ui").on('click', function() {
	$(".name-ui-change-ajax").html($("#name-ui-show").html());
	$("#screenflow-in-ui").html("");
	$("#screenflow-out-ui").html("");
	$.ajax({
		url: "../diagramui/viewlistui",
		data: "nameProject=" + $("#nameProject").val(),
		success: function(result) {
			var object = $.parseJSON(result);
			var str = "";
			for (var i = 0; i < object.ui.length; i++) {
				var path = JSON.parse(decodeURIComponent(window.atob(object.ui[i].path)));
				for (var j = 0; j < path.cells.length; j++) {
					if (path.cells[j].type == "ui.Button") {
						if (object.ui[i].name != $("#name-ui-show").html()) {
							if (path.cells[j].connect.indexOf($("#name-ui-show").html()) != -1) {
								var aTag = '<div class="form-group text-primary"><a href="../diagramui/viewdiagramui?nameProject=' + $("#nameProject").val() + '&nameUI=' + object.ui[i].name + '" target="_blank"> ' + object.ui[i].name + '</a></div>';
								$("#screenflow-in-ui").append(aTag);
							}
						} else {
							if (path.cells[j].connect.indexOf($("#name-ui-show").html()) == -1) {
								for (var h = 0; h < path.cells[j].connect.length; h++) {
									var aTag = '<div class="form-group text-success"><a href="../diagramui/viewdiagramui?nameProject=' + $("#nameProject").val() + '&nameUI=' + path.cells[j].connect[h] + '" target="_blank"> ' + path.cells[j].connect[h] + '</a></div>';
									$("#screenflow-out-ui").append(aTag);
								}
							}
						}
					}
				}
			}
			$("#modal-screenflow-ui").modal('show');
		},
		error: function() {
			alert("error");
		}
	});
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

function createDivContainer(x, y) {
	var divContainer = new joint.shapes.ui.DivContainer({
		position: {x : x, y : y}
	});
	graphUI.addCell(divContainer);
}

$("#exportXML").on('click', function() {
	var x2js = new X2JS();
	var xmlString = '<?xml version="1.0" encoding="UTF-8"?>';
	xmlString += '<content>' + x2js.json2xml_str($.parseJSON(JSON.stringify(graphUI))) + '</content>';
	var pom = document.createElement('a');
    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' 
    	+ encodeURIComponent(xmlString));
    pom.setAttribute('download', 'ex.xml');
    pom.click();
});

$("#exportHTML").on('click', function() {
	var x2js = new X2JS();
	var xmlString = '<?xml version="1.0" encoding="UTF-8"?>';
	xmlString += '<content>' + x2js.json2xml_str($.parseJSON(JSON.stringify(graphUI))) + '</content>';
	$.ajax({
		url: "../ui/exportHTML",
		data: {xml: xmlString},
		success: function(result) {
			var pom = document.createElement('a');
		    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' 
		    	+ encodeURIComponent(result));
		    pom.setAttribute('download', 'ex.html');
		    pom.click();
		},
		error: function() {
			alert("Không thể sinh mã HTML");
		}
	})
})

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
