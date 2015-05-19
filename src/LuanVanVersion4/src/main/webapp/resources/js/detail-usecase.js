var idd = 1;

$(document).ready(function(){
	//$("#danhSachUsecase").css("position", "relative");
	$('#danhSachUsecase').perfectScrollbar();
	if ($("#usecaseFromDiagram").val() != "") {
		showDetailUsecase($("#usecaseFromDiagram").val());
	}
	
	$(".formatNameDiagram").each(function(){
		if($(this).text().length > 40){
		var formatTitle = $.trim($(this).text()).substring(0,40).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
	
	$(".formatNameProject").each(function(){
		if($(this).text().length > 40){
		var formatTitle = $.trim($(this).text()).substring(0,40).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
	
	$(".formatType"	).each(function(){
		if($(this).text().length > 18){
		var formatDescription = $.trim($(this).text()).substring(0,18).split(" ").join(" ") + "...";
		$(this).text(formatDescription);
		}
	});
	
	
	
	

});

function showDetailUsecase(usecaseid){
	$("#resultDetail").html("<div class='panel panel-info'><div class='panel-heading'><h3 class='panel-title'>Đang tải</h3></div></div>");
	$.ajax({
		type: 'get',
		url: 'showDetailUsecase',
		data: 'usecaseid=' + usecaseid,
		success: function(data) {
			$('#resultDetail').html(data);
			CKEDITOR.stylesSet.add('default'+idd++, [
				{ name: 'Italic Title',		element: 'h2', styles: { 'font-style': 'italic' } },
				{ name: 'Subtitle',			element: 'h3', styles: { 'color': '#aaa', 'font-style': 'italic' } },
				{
					name: 'Special Container',
					element: 'div',
					styles: {
						padding: '5px 10px',
						background: '#eee',
						border: '1px solid #ccc'
					}
				},
				{ name: 'Marker',			element: 'span', attributes: { 'class': 'marker' } },
				{ name: 'Big',				element: 'big' },
				{ name: 'Small',			element: 'small' },
				{ name: 'Typewriter',		element: 'tt' },
				{ name: 'Computer Code',	element: 'code' },
				{ name: 'Keyboard Phrase',	element: 'kbd' },
				{ name: 'Sample Text',		element: 'samp' },
				{ name: 'Variable',			element: 'var' },
				{ name: 'Deleted Text',		element: 'del' },
				{ name: 'Inserted Text',	element: 'ins' },
				{ name: 'Cited Work',		element: 'cite' },
				{ name: 'Inline Quotation',	element: 'q' },
				{ name: 'Language: RTL',	element: 'span', attributes: { 'dir': 'rtl' } },
				{ name: 'Language: LTR',	element: 'span', attributes: { 'dir': 'ltr' } },
				{
					name: 'Styled image (left)',
					element: 'img',
					attributes: { 'class': 'left' }
				},
				{
					name: 'Styled image (right)',
					element: 'img',
					attributes: { 'class': 'right' }
				},
				{
					name: 'Compact table',
					element: 'table',
					attributes: {
						cellpadding: '5',
						cellspacing: '0',
						border: '1',
						bordercolor: '#ccc'
					},
					styles: {
						'border-collapse': 'collapse'
					}
				},
				
				{ name: 'Borderless Table',		element: 'table',	styles: { 'border-style': 'hidden', 'background-color': '#E6E6FA' } },
				{ name: 'Square Bulleted List',	element: 'ul',		styles: { 'list-style-type': 'square' } }
			]);
			CKEDITOR.replace('listCauHoi'+usecaseid,{
				stylesSet: 'default'+idd,
			});
			$("#buttonSubmit").attr('disabled', 'disabled');
			CKEDITOR.instances['listCauHoi'+usecaseid].on('change', function(){
				 $("#buttonSubmit").removeAttr('disabled');
			});
		},
		error: function() {	
			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
		}
	});
	
}

function searchUsecase(noidung, projectid) {
	$("#danhSachUsecase").html("<div class='panel panel-info'><div class='panel-heading'><h3 class='panel-title'>Đang tải</h3></div></div>");
	$.ajax({
		type: 'get',
		url: 'searchUsecase',
		data:{ 
			searchContent: noidung,
			projectID : projectid
		},
		success: function(data) {
			$('#danhSachUsecase').html(data);
		},
		error: function() {
			alert("Lỗi xảy ra do mất kết nối với cơ sở dữ liệu! Vui lòng nhấn F5 để thử lại!");
		}
	});	
}

function showDialog(){
	$('#file').click();
}

function submitForm(){
	$('#buttonUploadFile').children().replaceWith("<img title='Please wait...' src='/luanvan/resources/img/ajax-loader.gif'>");
 	$('#buttonUploadFile').attr('disabled', 'disabled');
	var oMyForm = new FormData();
	oMyForm.append("file", file.files[0]);
	oMyForm.append("usecaseID", $('#usecaseID').val());
	$.ajax({
 	url: 'uploadFile',
 	type: 'post',
    data: oMyForm,
    dataType: 'text',
    processData: false,
    contentType: false,
    success: function(data){
    	$('#buttonUploadFile').children().replaceWith("<span>Choose File</span>");
    	$('#buttonUploadFile').prop("disabled", false);
    	$('#listFile').append(data);
    	$(".formatType"	).each(function(){
    		if($(this).text().length > 18){
    		var formatDescription = $.trim($(this).text()).substring(0,18).split(" ").join(" ") + "...";
    		$(this).text(formatDescription);
    		}
    	});
    	$('#file').val('');
    },
    error: function(){
    }
  });
}

function deleteFile(bienThis){
	var isConfirm = confirm('Bạn có chắc muốn xóa dữ liệu này?');
	if(isConfirm == true){
	 $.ajax({
		    url: 'deleteFile',
		    data: {
		    	fileid: $(bienThis).attr('id')
		    },
		    type: 'get',
		    success: function(data){
		    	$(bienThis).parent().parent().remove();
		    },
		    error: function(){
		    }
		  });
	}
}


