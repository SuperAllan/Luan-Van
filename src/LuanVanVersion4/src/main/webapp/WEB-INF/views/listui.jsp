<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Tên các giao diện 
				<a href="/luanvan/diagram/newdiagramui?nameProject=${project.tenproject}" class="pull-right">
					<i class="glyphicon glyphicon-plus-sign"></i> Tạo mới
				</a>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${uis}" var="ui">
			<tr>
				<td>${ui.nameui}
					<a class="label label-primary pull-right" href="/luanvan/diagramui/viewdiagramui?nameProject=${project.tenproject}&nameUI=${ui.nameui}">
						<i class="glyphicon glyphicon-pencil"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>