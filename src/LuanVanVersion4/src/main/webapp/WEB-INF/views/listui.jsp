<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function searchListUI(value){
		if(value == '' || value == ' ' || value == null){
			$('.UIforSearch').each(function(){
				$(this).css("display", "");
			});
		}else{
			value = $.trim(value.toUpperCase());
			
			$('.UIforSearch').each(function(){
				var temp = $.trim($(this).text().toUpperCase());
				if(temp.indexOf(value) > -1){
					$(this).css("display", "");
				}else{
					$(this).css("display", "none");
				}
			});
		}
	}
$(document).ready(function(){
	$('#scrollBarListUI').perfectScrollbar();
	
	$('.classLi').on('click',function(){
		$('#searchUI').val('');
		searchListUI('');
	});
});	
</script>
<!-- search -->
<div class="panel-side-search">
	<div>
		<input style="border: solid 1px #e2e2e2; border-radius: 4px; height: 34px;" type="search" id="searchUI" class="form-control text-center"  title="Search" onkeyup="searchListUI(this.value)" placeholder="Nhập nội dung tìm kiếm">
	</div> 
</div>
<!-- End search -->
<div class="form-group" style="padding-top: 10px; font-weight: bold;">
	<span>Tên các giao diện</span>  
	<a href="${contextPath}/diagram/newdiagramui?nameProject=${project.tenproject}" class="pull-right">
		<i class="glyphicon glyphicon-plus-sign"></i> Tạo mới
	</a>
</div>
<div id="scrollBarListUI" style="height: 400px; overflow: hidden;">
	<table class="table table-striped table-hover">
		
		<tbody>
			<c:forEach items="${uis}" var="ui">
				<tr>
					<td class="UIforSearch">${ui.nameui}
						<a class="label label-primary pull-right" href="${contextPath}/diagramui/viewdiagramui?nameProject=${project.tenproject}&nameUI=${ui.nameui}">
							<i class="glyphicon glyphicon-pencil"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>