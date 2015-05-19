<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function searchListDiagram(value){
		if(value == '' || value == ' ' || value == null){
			$('.forSearch').each(function(){
				$(this).parent().css("display", "");
			});
		}else{
			value = $.trim(value.toUpperCase());
			
			$('.forSearch').each(function(){
				var temp = $.trim($(this).text().toUpperCase());
				if(temp.indexOf(value) > -1){
					$(this).parent().css("display", "");
				}else{
					$(this).parent().css("display", "none");
				}
			});
		}
	}
$(document).ready(function(){
	$('#scrollBarListDiagram').perfectScrollbar();
	
	$('.classLi').on('click',function(){
		$('#searchDiagram').val('');
		searchListDiagram('');
	});
});	
</script>
<!-- search -->
<div class="panel-side-search">
	<div>
		<input style="border: solid 1px #e2e2e2; border-radius: 4px; height: 34px;" type="search" id="searchDiagram" class="form-control text-center"  title="Search" onkeyup="searchListDiagram(this.value)" placeholder="Nhập nội dung tìm kiếm">
	</div> 
</div>
<!-- End search -->
<div class="form-group" style="padding-top: 10px; font-weight: bold;">
	<span>Tên sơ đồ</span>  
	<a href="${contextPath}/diagram/newdiagram?nameProject=${project.tenproject}" class="pull-right">
						<i class="glyphicon glyphicon-plus-sign"></i> Tạo mới
	</a>
</div>
<div id="scrollBarListDiagram" style="height: 400px; overflow: hidden;">
	<table class="table table-striped table-hover">
		<tbody>
			<c:forEach items="${diagrams}" var="diagram">
				<tr>
					<td><span class="forSearch">${diagram.namediagram}</span>
						<a class="label label-primary pull-right" href="${contextPath}/diagram/viewdiagram?nameProject=${project.tenproject}&nameDiagram=${diagram.namediagram}">
							<i class="glyphicon glyphicon-pencil"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
