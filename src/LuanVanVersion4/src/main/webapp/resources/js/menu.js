
$(document).ready(function(){
	
	window.setTimeout(function() { $(".alert-menu").alert('close'); }, 2000);
	
	$("#menu").click(function() {
		window.location.href = "/luanvan";
	});
});