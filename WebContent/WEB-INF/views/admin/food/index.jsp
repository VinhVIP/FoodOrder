<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ page import="food.utils.Constants"%>

<div class="row ml-10">
	<div class="col-3">
		<%@include file="/WEB-INF/views/include/sidebar_admin.jsp"%>
	</div>
	<div class="col-8 ml-20">

		<div>
			<span class="float-start msg-success">${message}</span> <span
				class="float-start msg-fail">${msgError}</span> <span
				class="float-end"><a href="admin/food/add.htm" type="button"
				class="btn btn-primary rounded-pill mb-10 shadow"><i class="bi bi-plus-circle-fill"></i> Thêm món ăn</a></span>

		</div>
		<table class="shadow table table-hover">
			<thead class="indigo">
				<tr>
					<th class="btlr" scope="col">ID</th>
					<th scope="col">Tên</th>
					<th scope="col">Giá</th>
					<th scope="col">Danh mục</th>
					<th scope="col">Tình trạng</th>
					<th scope="col">Hiện/Ẩn</th>
					<th scope="col">Sửa</th>
					<th class="btrr" scope="col">Xóa</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="f" items="${foods}">
					<tr class="${f.status != 0 ? 'table-active' : '' }">
						<td scope="row">${f.foodId}</td>
						<td>${f.name}</td>
						<td><fmt:formatNumber value="${f.price}" type="currency"
								currencySymbol="đ" minFractionDigits="0" /></td>
						<td>${f.category.name}</td>
						<td><c:choose>
								<c:when test="${f.type == 0}">
									<p class="msg-success">Còn món</p>
								</c:when>
								<c:otherwise>
									<p class="msg-fail">Hết món</p>
								</c:otherwise>
							</c:choose></td>
						<td>${f.status == 0 ? "Hiện" : "Ẩn"}</td>
						<td><a href="admin/food/edit.htm?id=${f.foodId}"
							type="button" class="btn btn-primary"><i
								class="bi bi-pencil-fill"></i></a></td>
						<td><a href="admin/food.htm?delete=${f.foodId}" type="button"
							class="btn btn-danger"><i class="bi bi-x-lg"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<br />
		<nav aria-label="Page navigation">
			<ul class="pagination justify-content-end">

				<!-- Previous -->
				<c:choose>
					<c:when test="${page <= 1}">
						<li class="page-item disabled"><a class="page-link" href="#"
							tabindex="-1" aria-disabled="true">Trước</a></li>
					</c:when>

					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="admin/food.htm?page=${page-1}" tabindex="-1"
							aria-disabled="true">Trước</a></li>
					</c:otherwise>
				</c:choose>

				<!-- Page Number -->
				<c:forEach begin="1" end="${maxPage}" varStatus="loop">

					<c:choose>
						<c:when test="${page == loop.count}">
							<li class="page-item active"><a class="page-link"
								href="admin/food.htm?page=${loop.count}">${loop.count}</a></li>
						</c:when>

						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="admin/food.htm?page=${loop.count}">${loop.count}</a></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>

				<!-- Next -->
				<c:choose>
					<c:when test="${page >= maxPage}">
						<li class="page-item disabled"><a class="page-link" href="#"
							tabindex="-1" aria-disabled="true">Sau</a></li>
					</c:when>

					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="admin/food.htm?page=${page+1}" tabindex="-1"
							aria-disabled="true">Sau</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</nav>
	</div>

</div>


<%@include file="/WEB-INF/views/include/footer.jsp"%>