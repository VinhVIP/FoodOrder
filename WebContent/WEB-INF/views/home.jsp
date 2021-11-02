<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ page import="food.utils.Constants"%>

<div class="row m-10">
	<div class="col-3">
		<%@include file="/WEB-INF/views/include/sidebar_categories.jsp"%>
	</div>
	<div class="col-auto">

		<div class="shadow rcorners1">
			<div class="food_title">
				<a href="food/index.htm?filter=1">Mới nhất</a>
			</div>
			<div class="container">

				<c:forEach var="f" items="${newFoods}">
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
							<c:choose>
								<c:when
									test="${Constants.hasOrderedFood(user, f.foodId)}">
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
								title="Xem chi tiết"><i class="bi bi-arrow-right"></i></a>
						</div>
					</div>
				</c:forEach>
				<div class="row-auto more ml-10">
					<a href="food/index.htm?filter=1" type="button"
						class="btn btn-warning rounded-pill shadow"> <i
						class="bi bi-arrow-right"></i>
					</a>
				</div>
			</div>
		</div>


		<div class="shadow rcorners2">
			<div class="food_title">
				<a href="food/index.htm?filter=4">Mua nhiều nhất</a>
			</div>
			<div class="container">
				<c:forEach var="f" items="${mostBuyFoods}">
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
							<c:choose>
								<c:when
									test="${Constants.hasOrderedFood(user, f.foodId)}">
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
								title="Xem chi tiết"><i class="bi bi-arrow-right"></i></a>
						</div>
					</div>
				</c:forEach>
				<div class="row-auto more ml-10">
					<a href="food/index.htm?filter=4" type="button"
						class="btn btn-primary rounded-pill shadow"> <i
						class="bi bi-arrow-right"></i>
					</a>
				</div>
			</div>
		</div>

		<div class="shadow rcorners3">
			<div class="food_title">
				<a href="food/index.htm?filter=3">Đánh giá cao nhất</a>
			</div>
			<div class="container">

				<c:forEach var="f" items="${highRatingFoods}">
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
							<c:choose>
								<c:when
									test="${Constants.hasOrderedFood(user, f.foodId)}">
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
				<div class="row-auto more ml-10">
					<a href="food/index.htm?filter=3" type="button"
						class="btn btn-danger rounded-pill shadow"> <i
						class="bi bi-arrow-right"></i>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>


<%@include file="/WEB-INF/views/include/footer.jsp"%>