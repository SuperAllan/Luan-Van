
$(document).ready(function(){
	$(".projectName").each(function(){
		if($(this).text().length > 20){
		var formatTitle = $.trim($(this).text()).substring(0,20).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
	
	$(".username").each(function(){
		if($(this).text().length > 11){
		var formatTitle = $.trim($(this).text()).substring(0,11).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
	
	window.setTimeout(function() { $(".message-custom").alert('close'); }, 2000);
	window.setTimeout(function() { $(".message-custom-error").alert('close'); }, 2000);
	
	$("#menu").click(function() {
		window.location.href = "/luanvan";
	});
	$('textarea').keyup(function(){
		textAreaAdjust(this);
	});
	
	//$('#formLogIn').bootstrapValidator();
	$('#formLogIn').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        }
    })
});

function formSubmitMenu() {
	document.getElementById("logoutFormMenu").submit();
}

//Xu ly enter trong textarea
function textAreaAdjust(o) {
    o.style.height = "1px";
    o.style.height = o.scrollHeight + "px";
}