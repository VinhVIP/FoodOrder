<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignIn</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" type="text/css" href="resources/css/signin.css">
<link href='https://fonts.googleapis.com/css?family=Ubuntu:500'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<form action="/account/signin.htm" method="post">
		<div class="signin">
			<div class="signin-header">
				<h1>SignIn</h1>
				<br>
				<h4>${message}</h4>
				<br>
			</div>
			<div class="signin-form">
				<h3>Email:</h3>
				<input type="email" placeholder="Email" name="email" value="${uid}" />
				<br>
				<h3>Password:</h3>
				<input type="password" placeholder="Password" name="pw"
					value="${pwd}" /> <br> <span style="color: #001100;"> <input
					type="checkbox" name="rm" /> Remember me
				</span> <br> <input type="button" value="signin" class="signin-button" />
				<br> <a class="sign-up" href="account/signup.htm">Sign Up!</a>
				<br>
				<h5 class="no-access">
					<a href="account/forgot.htm">Forgot Password?</a>
				</h5>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="resources/js/signin.js"></script>
</body>
</html>