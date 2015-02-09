$(document).ready(function(){
	$(".formatNameProject").each(function(){
		if($(this).text().length > 15){
		var formatTitle = $.trim($(this).text()).substring(0,15).split(" ").join(" ") + "...";
		$(this).text(formatTitle);
		}
	});
});
