<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.80.0">
<title>Signin Template Â· Bootstrap v5.0</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/sign-in/">



<!-- Bootstrap core CSS -->
<link
	href="<c:url value="/resources/assets/dist/css/bootstrap.min.css" />"
	rel="stylesheet" />

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>


<!-- Custom styles for this template -->
<link href="<c:url value="/resources/assets/dist/signin.css" />"
	rel="stylesheet" />
</head>
<body class="text-center">

	<main class="form-signin">
		<form action="index.htm" method="post" modelAttribute="user">
			<img class="mb-4"
				src="${pageContext.request.contextPath}/resources/assets/brand/bootstrap-logo.svg"
				alt="" width="72" height="57">
			<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
			<label for="inputId" class="visually-hidden">Username</label> 
			<input
				type="text" id="inputId" name="id" class="form-control" placeholder="Username"
				required autofocus> <label for="inputPassword"
				class="visually-hidden">Password</label> <input type="password"
				name="password" id="inputPassword" class="form-control" placeholder="Password"
				required>
			
			<button class="w-100 btn btn-lg btn-primary" type="submit">Sign
				in</button>
			
		</form>
	</main>



</body>
</html>
