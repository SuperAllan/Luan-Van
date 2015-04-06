
$(document).ready(function(){
	
	window.setTimeout(function() { $(".alert-menu").alert('close'); }, 2000);
	
	$("#menu").click(function() {
		window.location.href = "/luanvan";
	});
});

function formSubmitMenu() {
	document.getElementById("logoutFormMenu").submit();
}

//Xu ly enter trong textarea
function textAreaAdjust(o) {
    o.style.height = "1px";
    o.style.height = o.scrollHeight + "px";
}