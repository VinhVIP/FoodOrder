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

<meta property="og:url" content="http://foodorder.com:9929/Food/" />
<meta property="og:type" content="website" />
<meta property="og:title" content="Food Order - Nhà Hàng" />
<meta property="og:description"
	content="Chỉ cần đặt móm - chúng tôi sẽ giao đến tận tay bạn" />
<meta property="og:image" content="resources/img/food.jpg" />

<link rel="icon" type="image/png" href="resources/img/icon.png" />
<link rel='stylesheet' type='text/css' media='screen'
	href='resources/css/bootstrap.min.css'>


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.1/font/bootstrap-icons.css">

<script src='resources/js/bootstrap.min.js'></script>
<script src='resources/js/jquery-3.6.0.min.js'></script>


<link rel='stylesheet' type='text/css' media='screen'
	href='resources/css/app.css?ver=1.0.1'>
</head>
<body class="d-flex flex-column min-vh-100 container-fluid">

	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.0";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

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
							<a href="login.htm" type="button"
								class="btn btn-primary btn-sm ml-10">Đăng nhập</a> <a
								href="signup.htm" type="button"
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

							<li class="nav-item"><a class="nav-link" href="#">Đơn
									hàng của ${sessionScope.account.name}</a></li>
						</ul>
						<form class="d-flex">
							<a href="cart.html" type="button"
								class="btn btn-primary shadow rounded-pill"><i
								class="bi bi-cart-check-fill"></i> Giỏ hàng <span
								class="badge bg-danger rounded-pill">${user.carts.size()}</span>
							</a>
							<div class="dropdown ml-10">
								<a class="dropdown-toggle" href="#" data-bs-toggle="dropdown"
									aria-expanded="false"> <img class="rounded-circle z-depth-2" src="${user.avatar}"
									alt="" width="40" height="40">
								</a>

								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="dropdownMenuLink">
									<li><a class="dropdown-item" href="profile.html">Trang
											cá nhân</a></li>
									<li><a class="dropdown-item" href="coupon.html">Phiếu
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