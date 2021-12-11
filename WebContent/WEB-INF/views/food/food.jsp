<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="food.utils.Constants"%>
<fmt:setLocale value="vi-VN" scope="session" />
<%@include file="/WEB-INF/views/include/header.jsp"%>


<!-- Button Scroll To Top -->
<button onclick="topFunction()" id="btnScrollTop" title="Go to top">
	<i class="bi bi-arrow-up"></i>
</button>


<div class="row m-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_categories.jsp"%>
	</div>
	<div class="col-lg-9">

		<!-- Bread crumb - Đường dẫn -->
		<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="home.htm">Trang chủ</a></li>
				<li class="breadcrumb-item"><a
					href="food/index.htm?id_category=${category.categoryId}&page=1">${category.name}</a></li>
				<li class="breadcrumb-item active" aria-current="page">${food.name}</li>
			</ol>
		</nav>

		<div class="row">
			<div class="col-lg-5">

				<!-- Hình ảnh món ăn -->
				<div id="carouselFoodImages" class="shadow carousel slide"
					data-bs-ride="carousel">
					<div class="carousel-indicators">

						<c:forEach varStatus="index" begin="1" end="${images.size()}">
							<c:choose>
								<c:when test="${index.count == 1}">
									<button type="button"
										data-bs-target="#carouselExampleIndicators"
										data-bs-slide-to="0" class="active" aria-current="true"></button>
								</c:when>
								<c:otherwise>
									<button type="button"
										data-bs-target="#carouselExampleIndicators"
										data-bs-slide-to="${index.count-1}"></button>
								</c:otherwise>
							</c:choose>

						</c:forEach>

					</div>
					<div class="carousel-inner">
						<c:forEach items="${images}" var="img" varStatus="index">

							<div class="carousel-item ${index.count == 1 ? 'active' : ''}">
								<img src="${img}" id="myImg${index.count}"
									class="rounded d-block w-100" alt="${food.name}">
							</div>

							<%-- <c:choose>
								<c:when test="${index.count == 1}">
									<div class="carousel-item active">
										<img src="${img}" id = "myImg${index.count}" class="rounded d-block w-100"
											alt="${food.name}">
									</div>
								</c:when>
								<c:otherwise>
									<div class="carousel-item">
										<img src="${img}" class="rounded d-block w-100"
											alt="${food.name}">
									</div>
								</c:otherwise>
							</c:choose> --%>

						</c:forEach>

					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="prev"
						onclick="$('#carouselFoodImages').carousel('prev')">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="next"
						onclick="$('#carouselFoodImages').carousel('next')">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>

				<!-- Thêm vào giỏ & Đặt ngay -->
				<a
					class="btn btn-primary mv-10 shadow ${food.type == 0 && addedToCart == false?'':'disabled'}"
					href="food/cart.htm?id_food=${food.foodId}"><i
					class="bi bi-cart-plus-fill"></i> ${addedToCart ? 'Đã thêm' : 'Thêm vào giỏ'}</a>

				<c:if test="${user.accountId == 1}">
					<a class="btn btn-success ml-10 shadow"
						href="admin/food/edit.htm?id=${food.foodId}"><i
						class="bi bi-pencil-fill"></i> Chỉnh sửa</a>
				</c:if>

				<%-- <a href="#"
					type="button"
					class="btn btn-danger ml-10 shadow ${food.type == 0 && addedToCart == false?'':'disabled'}">
					<i class="bi bi-coin"></i> Đặt ngay
				</a> --%>

				<!-- <div class="fb-share-button"
					data-href="https://developers.facebook.com/docs/plugins/"
					data-layout="button" data-size="small">
					<a target="_blank"
						href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse"
						class="fb-xfbml-parse-ignore">Chia sẻ</a>
				</div> -->

				<!-- Nút share lên facebook -->
				<div class="fb-share-button shadow float-end mt-10"
					data-href="${requestScope['javax.servlet.forward.request_uri']}"
					data-layout="button" data-size="large">
					<a target="_blank"
						href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse"
						class="fb-xfbml-parse-ignore">Chia sẻ</a>
				</div>

			</div>

			<!-- Chi tiết món ăn -->
			<div class="col-lg-7">

				<h4>
					<b>${food.name}</b>
				</h4>
				<p class="mgt">
					<b>Giá: </b>
					<fmt:formatNumber value="${food.price}" type="currency"
						currencySymbol="đ" minFractionDigits="0" />
				</p>
				<p class="mgt">
					<b>Tình trạng: </b>
					<c:choose>
						<c:when test="${food.type == 0}">Còn món</c:when>
						<c:otherwise>
							<span class="msg-fail">Hết món</span>
						</c:otherwise>
					</c:choose>
				</p>


				<p class="mgt">
					<b>Thông tin: </b>${food.detail}
				</p>


				<!-- Rating -->
				<p>
					<b>Đánh giá: </b><span class="ml-10">${avgStarString}</span> <span
						class="small-ratings mh-10"> <c:forEach begin="1" end="5"
							varStatus="index">
							<c:choose>
								<c:when test="${index.count <= avgStar}">
									<i class="fa fa-star rating-color"></i>
								</c:when>
								<c:otherwise>
									<i class="fa fa-star"></i>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</span> <i>(${rateds.size()} lượt)</i>
				</p>

				<hr style="border: 1px solid #f1f1f1">

				<!-- Hiển thị số lượng sao mỗi loại 1-5 sao -->
				<div class="row mh-10">
					<c:forEach begin="1" end="5" varStatus="index">
						<div class="side">
							<div>${5-index.count+1}
								<i class="fa fa-star rating-color"></i>
							</div>
						</div>
						<div class="middle">
							<div class="bar-container">
								<div class="bar-${5-index.count+1}"
									style="width: ${food.rateds.size() > 0 ? (100*countStar[5-index.count+1]/food.rateds.size()) : 0}%"></div>
							</div>
						</div>
						<div class="side right">
							<div>${countStar[5-index.count+1]}</div>
						</div>
					</c:forEach>
				</div>



				<br /> <br />
				<c:if test="${hasOrdered}">
					<form:form action="food/rating.htm?id=${food.foodId}" method="POST"
						modelAttribute="userRating">
						<div>
							<p>
								<i><b>Đánh giá của bạn về món ăn này</b></i>
							</p>

							<section class='rating-widget'>

								<!-- Rating Stars Box -->
								<div class='rating-stars text-center'>
									<ul id='stars'>

										<c:forEach begin="1" end="5" varStatus="index">
											<c:choose>
												<c:when test="${index.count <= userRating.star}">
													<li class='star selected' data-value="${index.count}"><i
														class='fa fa-star fa-fw'></i></li>
												</c:when>
												<c:otherwise>
													<li class='star' data-value="${index.count}"><i
														class='fa fa-star fa-fw'></i></li>
												</c:otherwise>
											</c:choose>

										</c:forEach>

									</ul>
								</div>

							</section>

							<div class="mb-3">
								<form:input type="hidden" class="form-control" path="star"></form:input>
							</div>

							<div class="mb-3">
								<form:textarea class="form-control" path="comment" rows="3"></form:textarea>
							</div>

							<form:button type="submit" class="btn btn-primary">Gửi đánh
								giá</form:button>

							<c:if test="${userRating.star != 0}">
								<a href="food/rating.htm?deleteId=${food.foodId}" type="button"
									class="btn btn-warning float-end">Xóa đánh giá</a>
							</c:if>

						</div>
					</form:form>

				</c:if>

			</div>
		</div>

		<!--  Bình luận -->
		<c:choose>
			<c:when test="${food.rateds.size() == 0}">
				<p style="font-size: 22px">
					<i>Món ăn này chưa có đánh giá nào!</i>
				</p>
			</c:when>
			<c:otherwise>
				<div>
					<p class="heading">
						<b>Các đánh giá</b> <a class="ml-10"
							href="food/${food.foodId}/rateds.htm" style="font-size: 18px"><i>Xem
								thêm </i><i class="bi bi-arrow-right"></i></a>
					</p>
					<div class="col">
						<c:forEach items="${rateds}" var="rated" begin="0" end="4">
							<div class="row">
								<div class="col-1 mr-10">
									<img class="rounded-circle z-depth-2" alt="50x50" width="60px"
										height="60px" src="${rated.account.avatar}"
										data-holder-rendered="true">
								</div>
								<div class="col-8">
									<b>${rated.account.name}</b><br> <span
										class="small-ratings"> <c:forEach begin="1" end="5"
											varStatus="index">
											<c:choose>
												<c:when test="${index.count <= rated.star}">
													<i class="fa fa-star rating-color"></i>
												</c:when>
												<c:otherwise>
													<i class="fa fa-star"></i>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</span> <span class="mh-10">•</span> <span class="time"><fmt:formatDate
											pattern="dd/MM/yyyy - HH:mm" value="${rated.cmtTime}" /></span>
									<%-- <c:if test="${user.accountId == 1}">
										<span class="ml-10" style="font-size: 18px">• <a
											class="ml-10" href="#">Ẩn nội dung</a></span>
									</c:if> --%>
									<br>
									<p class="comment">${rated.comment}</p>
									<hr style="border: 1px solid #f1f1f1">
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
			</c:otherwise>

		</c:choose>

		<hr style="border: 2px solid #f1f1f1">

		<!-- Các món cùng danh mục -->
		<p class="heading">
			Các món cùng danh mục <a class="ml-10"
				href="food/index.htm?id_category=${food.category.categoryId}"
				style="font-size: 18px"><i>Xem thêm </i><i
				class="bi bi-arrow-right"></i></a>
		</p>


		<div class="container">
			<c:forEach var="f" items="${listFoodSameCategory}">
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
							title="Add to cart"><i class="bi bi-arrow-right"></i></a>
					</div>
				</div>
			</c:forEach>
		</div>


	</div>
</div>



<!-- <div id="myModal" class="modal">
	<span class="close">&times;</span> <img class="modal-content"
		id="img01">
	<div id="caption"></div>
</div>
 -->



<script>
	$(document)
			.ready(
					function() {

						/* 1. Visualizing things on Hover - See next part for action on click */
						$('#stars li').on(
								'mouseover',
								function() {
									var onStar = parseInt(
											$(this).data('value'), 10); // The star currently mouse on

									// Now highlight all the stars that's not after the current hovered star
									$(this).parent().children('li.star').each(
											function(e) {
												if (e < onStar) {
													$(this).addClass('hover');
												} else {
													$(this)
															.removeClass(
																	'hover');
												}
											});

								}).on(
								'mouseout',
								function() {
									$(this).parent().children('li.star').each(
											function(e) {
												$(this).removeClass('hover');
											});
								});

						/* 2. Action to perform on click */
						$('#stars li')
								.on(
										'click',
										function() {
											var onStar = parseInt($(this).data(
													'value'), 10); // The star currently selected
											var stars = $(this).parent()
													.children('li.star');

											for (i = 0; i < stars.length; i++) {
												$(stars[i]).removeClass(
														'selected');
											}

											for (i = 0; i < onStar; i++) {
												$(stars[i])
														.addClass('selected');
											}

											// JUST RESPONSE (Not needed)
											var ratingValue = parseInt($(
													'#stars li.selected')
													.last().data('value'), 10);
											var msg = "";
											if (ratingValue > 1) {
												msg = "Thanks! You rated this "
														+ ratingValue
														+ " stars.";
											} else {
												msg = "We will improve ourselves. You rated this "
														+ ratingValue
														+ " stars.";
											}
											responseMessage(ratingValue, msg);

										});

					});

	function responseMessage(ratingValue, msg) {
		$('.success-box').fadeIn(200);
		$('.success-box div.text-message').html("<span>" + msg + "</span>");
		$('#star').val(ratingValue);
	}
</script>

<script>
	//Get the button
	var mybutton = document.getElementById("btnScrollTop");

	// When the user scrolls down 20px from the top of the document, show the button
	window.onscroll = function() {
		scrollFunction()
	};

	function scrollFunction() {
		if (document.body.scrollTop > 20
				|| document.documentElement.scrollTop > 20) {
			mybutton.style.display = "block";
		} else {
			mybutton.style.display = "none";
		}
	}

	// When the user clicks on the button, scroll to the top of the document
	function topFunction() {
		document.body.scrollTop = 0;
		document.documentElement.scrollTop = 0;
	}
</script>

<script>
	// Get the modal
	var modal = document.getElementById("myModal");

	// Get the image and insert it inside the modal - use its "alt" text as a caption
	var img1 = document.getElementById("myImg1");
	var img2 = document.getElementById("myImg2");
	var img3 = document.getElementById("myImg3");

	var modalImg = document.getElementById("img01");
	var captionText = document.getElementById("caption");
	img1.onclick = function() {
		modal.style.display = "block";
		modalImg.src = this.src;
		captionText.innerHTML = this.alt;
	}

	img2.onclick = function() {
		modal.style.display = "block";
		modalImg.src = this.src;
		captionText.innerHTML = this.alt;
	}

	img3.onclick = function() {
		modal.style.display = "block";
		modalImg.src = this.src;
		captionText.innerHTML = this.alt;
	}

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}
</script>

<%@include file="/WEB-INF/views/include/footer.jsp"%>