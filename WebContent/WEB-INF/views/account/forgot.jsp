<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" type="text/css" href="resources/css/forgot.css">
<link href='https://fonts.googleapis.com/css?family=Ubuntu:500'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<form action="account/forgot.htm" method="post">
		<div class="forgot">
			<div class="forgot-header">
				<h1>Forgot Password</h1>
				<br>
				<h4>${message}</h4>
				<br>
			</div>
			<div class="forgot-form">
				<h3>Email:</h3>
				<input type="email" placeholder="Email" name="email" /> <br> <input
					type="button" value="Retrieve Password" class="forgot-button" />
			</div>
		</div>
	</form>
	<script type="text/javascript" src="resources/js/forgot.js"></script>
</body>
</html>