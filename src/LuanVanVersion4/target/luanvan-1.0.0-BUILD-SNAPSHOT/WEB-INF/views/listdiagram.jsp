<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Tên sơ đồ 
				<a href="/luanvan/diagram/newdiagram?nameProject=${project.tenproject}" class="pull-right">
					<i class="glyphicon glyphicon-plus-sign"></i> Tạo mới
				</a>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${diagrams}" var="diagram">
			<tr>
				<td>${diagram.namediagram}
					<a class="label label-primary pull-right" href="/luanvan/diagram/viewdiagram?nameProject=${project.tenproject}&nameDiagram=${diagram.namediagram}">
						<i class="glyphicon glyphicon-pencil"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>