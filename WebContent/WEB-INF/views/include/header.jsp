<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<base href="${pageContext.servletContext.contextPath}/">
<title>Food Order</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>

<meta name="description" content="Food order website">
<meta name="keywords" content="Food, order">
<meta name="author" content="Vinh">

<meta property="og:url" content="http://foodorder.com:9920/Food/" />
<meta property="og:type" content="website" />
<meta property="og:title" content="Food Order - Nhà Hàng" />
<meta property="og:description"
	content="Chỉ cần đặt móm - chúng tôi sẽ giao đến tận tay bạn" />
<meta property="og:image" content="resources/img/icon.png" />

<link rel="icon" type="image/png" href="resources/img/icon.jpg" />
<link rel='stylesheet' type='text/css' media='screen'
	href='resources/css/bootstrap.min.css'>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.1/font/bootstrap-icons.css">

<script src='resources/js/bootstrap.min.js'></script>
<script src='resources/js/jquery-3.6.0.min.js'></script>

<link rel="stylesheet prefetch"
	href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>


<link rel='stylesheet' type='text/css' media='screen'
	href='resources/css/app.css?ver=1.1.0'>
</head>
<body class="d-flex flex-column min-vh-100 container-fluid">

	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v12.0"
		nonce="FQmdELxV"></script>

	<!-- Navbar -->
	<nav class="navbar navbar-expand-md navbar-light bg-info fixed-top">

		<c:choose>
			<c:when test="${sessionScope.account == null}">
				<div class="container-fluid">
					<button class="navbar-toggler" type="button"
						data-mdb-toggle="collapse" data-mdb-target="#navbarExample01"
						aria-controls="navbarExample01" aria-expanded="false"
						aria-label="Toggle navigation">
						<i class="fas fa-bars"></i>
					</button>
					<div class="collapse navbar-collapse" id="navbarExample01">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li><img src="resources/img/icon.png" alt="" width="40"
								height="40" class="d-inline-block align-text-top"></li>
							<li class="nav-item active"><a class="nav-link"
								aria-current="page" href="home.htm">Trang chủ</a></li>

						</ul>
						<form class="d-flex">
							<!-- <a href="login.htm" type="button"
								class="btn btn-primary btn-sm ml-10">Fake login</a>  -->
							<a href="account/signin.htm" type="button"
								class="btn btn-primary btn-sm ml-10">Đăng nhập</a> <a
								href="account/signup.htm" type="button"
								class="btn btn-outline-success btn-sm ml-10">Đăng ký</a>
						</form>
					</div>
				</div>
			</c:when>

			<c:otherwise>
				<div class="container-fluid">
					<button class="navbar-toggler" type="button"
						data-mdb-toggle="collapse" data-mdb-target="#navbarExample01"
						aria-controls="navbarExample01" aria-expanded="false"
						aria-label="Toggle navigation">
						<i class="fas fa-bars"></i>
					</button>

					<div class="collapse navbar-collapse" id="navbarExample01">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li><img src="resources/img/icon.png" alt="" width="40"
								height="40" class="d-inline-block align-text-top"></li>
							<li class="nav-item active"><a class="nav-link"
								aria-current="page" href="home.htm">Trang chủ</a></li>

							<c:if test="${sessionScope.account.accountId == 1}">
								<li class="nav-item"><a class="nav-link"
									href="admin/category.htm">Admin Panel</a></li>
							</c:if>

							<li class="nav-item"><a class="nav-link"
								href="order/index.htm">Đơn hàng của tôi</a></li>
						</ul>
						<form class="d-flex">
							<a href="cart/index.htm" type="button"
								class="btn btn-primary shadow rounded-pill"><i
								class="bi bi-cart-check-fill"></i> Giỏ hàng <span
								class="badge bg-danger rounded-pill">${user.carts.size()}</span>
							</a>
							<div class="dropdown ml-10">
								<a class="dropdown-toggle" href="#" data-bs-toggle="dropdown"
									aria-expanded="false"> <img
									class="rounded-circle z-depth-2" src="${user.avatar}" alt=""
									width="40" height="40">
								</a>

								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="dropdownMenuLink">
									<li><a class="dropdown-item" href="account/profile.htm">Trang
											cá nhân</a></li>
									<li><a class="dropdown-item" href="account/change.htm">Đổi
											mật khẩu</a></li>
									<li><a class="dropdown-item" href="account/coupons.htm">Phiếu
											giảm giá</a></li>
									<li><a class="dropdown-item" href="logout.htm">Đăng
											xuất</a></li>
								</ul>
							</div>
						</form>
					</div>
				</div>
			</c:otherwise>
		</c:choose>


	</nav>
	<!-- Navbar -->

	<div style="margin-top: 80px">