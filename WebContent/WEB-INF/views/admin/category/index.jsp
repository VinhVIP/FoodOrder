<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>

<div class="row ml-10">
	<div class="col-3">
		<%@include file="/WEB-INF/views/include/sidebar_admin.jsp"%>
	</div>
	<div class="col-1"></div>
	<div class="col-7">

		<div>
			<span class="float-start">${message}</span> <span class="float-end"><a
				href="admin/category/add.htm" type="button" class="btn btn-primary">Thêm
					danh mục</a></span>

		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Logo</th>
					<th scope="col">Tên</th>
					<th scope="col">Sửa</th>
					<th scope="col">Xóa</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${categories}">
					<tr>
						<td scope="row">${c.categoryId}</td>
						<td><img class="rounded-circle z-depth-2 mr-10"
							src="${c.logo}" width="50px" height="50px" /></td>
						<td>${c.name}</td>
						<td><a href="admin/category/edit.htm?id=${c.categoryId}"
							type="button" class="btn btn-primary">Sửa</a></td>
						<td><a href="admin/category.htm?delete=${c.categoryId}" type="button" class="btn btn-danger">Xóa</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>


<%@include file="/WEB-INF/views/include/footer.jsp"%>