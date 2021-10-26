<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ page import="food.utils.Constants"%>

<div class="row m-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_categories.jsp"%>
	</div>
	<div class="col-lg-9">
		<h5>Danh mục: ${category.name}</h5>
		<div class="row row-cols-2 row-cols-md-4 g-4">
			<c:forEach var="f" items="${foods}">
				<div class="col">
					<div class="card h-100">
						<a href="food/index.htm?id=${f.foodId}"><img src="resources/img/suon.jpg"
							class="card-img-top" alt="..."></a>
						<div class="card-body">
							<h5 class="card-title">${f.name}</h5>
							<p class="card-text">
								Giá: <i><b>${f.price}</b></i> đ
							</p>
							<p class="card-text">${f.detail}</p>
						</div>
						<div class="card-footer">
							<a href="#" class="btn btn-primary">Thêm vào giỏ</a>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>

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
							href="food/index.htm?id_category=1&page=${page-1}" tabindex="-1"
							aria-disabled="true">Trước</a></li>
					</c:otherwise>
				</c:choose>

				<!-- Page Number -->
				<c:forEach begin="1"
					end="${Constants.getMaxPage(category.foods.size(), Constants.FPP)}"
					varStatus="loop">

					<c:choose>
						<c:when test="${page == loop.count}">
							<li class="page-item active"><a class="page-link"
								href="food/index.htm?id_category=1&page=${loop.count}">${loop.count}</a></li>
						</c:when>

						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="food/index.htm?id_category=1&page=${loop.count}">${loop.count}</a></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>

				<!-- Next -->
				<c:choose>
					<c:when test="${page >= category.foods.size()/Constants.FPP}">
						<li class="page-item disabled"><a class="page-link" href="#"
							tabindex="-1" aria-disabled="true">Sau</a></li>
					</c:when>

					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="food/index.htm?id_category=1&page=${page+1}" tabindex="-1"
							aria-disabled="true">Sau</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</nav>

	</div>
</div>


<%@include file="/WEB-INF/views/include/footer.jsp"%>