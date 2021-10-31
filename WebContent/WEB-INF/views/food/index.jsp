<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ page import="food.utils.Constants"%>

<div class="row m-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_categories.jsp"%>
	</div>
	<div class="col-lg-9">
		<div class="text-center">
			<h5>Danh mục: ${category.name}</h5>
		</div>
		<div class="container">
			<c:forEach var="f" items="${foods}">
				<div class="fcard">
					<div class="fcard-header">
						<a href="food/${f.foodId}.htm"><img
							src="${Constants.getBanner(f.images)}" class="card-img-top"
							height="200px"></a>
					</div>
					<div class="fcard-body">
						<div class="fcard-body-header">

							<span class="ftag ftag-teal"><a
								href="food/index.htm?id_category=${f.category.categoryId}&page=1">${f.category.name}</a>
							</span> <span class="ftag ftag-price"><fmt:formatNumber
									value="${f.price}" type="currency" currencySymbol="đ"
									minFractionDigits="0" /></span>
						</div>

						<h5>${f.name}</h5>
						<p>
							<span class="small-ratings"> <c:forEach begin="1" end="5"
									varStatus="index">
									<c:choose>
										<c:when test="${index.count <= Constants.getAvgStar(f)}">
											<i class="fa fa-star rating-color"></i>
										</c:when>
										<c:otherwise>
											<i class="fa fa-star"></i>
										</c:otherwise>
									</c:choose>
								</c:forEach> <span class="ml-10">${Constants.getAvgStarString(f)}</span>
							</span>
						</p>
						<p>${Constants.getShortDetail(f.detail)}</p>

					</div>

					<div class="fbutton">
						<a href="#" class="btn btn-primary shadow rounded-pill"
							data-bs-toggle="tooltip" data-bs-placement="bottom"
							title="Add to cart"><i class="bi bi-cart-plus-fill"></i></a> <a
							href="food/${f.foodId}.htm"
							class="btn btn-danger shadow rounded-pill float-end"
							data-bs-toggle="tooltip" data-bs-placement="bottom"
							title="Xem chi tiết"><i class="bi bi-arrow-right"></i></a>
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