<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ page import="food.utils.Constants"%>

<div class="row m-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_categories.jsp"%>
	</div>
	<div class="col-lg-9">
		<div class="row mb-10">
			<div class="col-6 ">
				<div class="food_title2">${title}</div>
			</div>
			<div class="col-6">

				<form method="get"
					action="food/index.htm?keyword=${keyword}&id_category=${categoryId}&filter=${filter}&page=${page}">

					<div class="input-group">
						<input type="search" placeholder="Từ khóa" name="keyword"
							class="form-control border-success rounded-pill pr-5 form-control-sm mr-10"
							value="${keyword}" /> <span class="col-auto mr-10"> <select
							name="filter" id="filter"
							class="form-select form-select-sm mr-10 border-warning rounded-pill pr-5 ">

								<option value="1" <c:if test="${filter == 1}"> selected </c:if>>Mới
									nhất</option>
								<option value="2" <c:if test="${filter == 2}"> selected </c:if>>Cũ
									nhất</option>
								<option value="3" <c:if test="${filter == 3}"> selected </c:if>>Đánh
									giá cao nhất</option>
								<option value="4" <c:if test="${filter == 4}"> selected </c:if>>Mua
									nhiều nhất</option>
						</select>
						</span><input type="hidden" name="id_category" value="${id_category}" />

						<div class="input-group-append ml-10">
							<button class="btn btn-primary btn-sm">
								<i class="bi bi-search"></i>
							</button>
						</div>

					</div>
				</form>
			</div>
		</div>

		<div class="container">
			<c:forEach var="f" items="${foods}">
				<div class="${f.status == 0 ? 'fcard' : 'fcard_fade'}">
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
									<i
										class="fa fa-star ${index.count <= Constants.getAvgStar(f) ? 'rating-color' : ''}"></i>
								</c:forEach> <span class="ml-10">${Constants.getAvgStarString(f)}</span>
							</span>
						</p>
						<p>${Constants.getShortDetail(f.detail)}</p>

					</div>

					<div class="fbutton">
						<c:choose>
							<c:when test="${Constants.hasOrderedFood(user, f.foodId)}">
								<button disabled class="btn shadow rounded-pill btn-success"
									data-bs-toggle="tooltip" data-bs-placement="bottom"
									title="Đã có trong giỏ">
									<i class="bi bi-cart-check-fill"></i>
								</button>
							</c:when>
							<c:otherwise>
								<a href="food/cart.htm?id_food=${f.foodId}"
									class="btn shadow rounded-pill btn-primary"
									data-bs-toggle="tooltip" data-bs-placement="bottom"
									title="Thêm vào giỏ"><i class="bi bi-cart-plus-fill"></i></a>
							</c:otherwise>
						</c:choose>

						<a href="food/${f.foodId}.htm"
							class="btn btn-danger shadow rounded-pill float-end"
							data-bs-toggle="tooltip" data-bs-placement="bottom"
							title="Add to cart"><i class="bi bi-arrow-right"></i></a>
					</div>
				</div>
			</c:forEach>
		</div>
		<br />
		<nav aria-label="Page navigation">
			<ul class="pagination justify-content-end">
				<!-- First Page -->
				<li class="page-item ${page == 1 ? 'disabled' : '' }"><a
					class="page-link" href="${url}1" tabindex="-1" aria-disabled="true">&laquo;</a></li>

				<!-- Page Number -->
				<c:forEach begin="${page-2<1 ? 1 : page-2}"
					end="${page+2 > maxPage ? maxPage : page+2}" varStatus="loop">
					<li class="page-item ${loop.count == page ? 'active' : '' }"><a
						class="page-link" href="${url}${loop.count}">${loop.count}</a></li>
				</c:forEach>

				<!-- Last Page -->
				<li class="page-item ${page == maxPage ? 'disabled' : '' }"><a
					class="page-link" href="${url}${maxPage}" tabindex="-1"
					aria-disabled="true">&raquo;</a></li>
			</ul>

		</nav>

	</div>
</div>


<%@include file="/WEB-INF/views/include/footer.jsp"%>