<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xác nhận</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" type="text/css" href="resources/css/change.css">
<link href='https://fonts.googleapis.com/css?family=Ubuntu:500'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<form action="account/code.htm" method="post">
		<div class="change">
			<div class="change-header">
				<h1>Xác nhận code</h1>
				<h5 style="color:red; font-style: italic;padding-top: 15px;">${message}</h5>
			</div>
			<div class="change-form">
				<h3>Email:</h3>
				<input type="email" placeholder="Nhập Email" name="email" value="${email}" readonly />
				<br>
				<h3>Code:</h3>
				<input type="text" placeholder="Nhập code" name="code" /> <br> <br> 
				
				<input type="submit" value="Xác nhận" class="change-button" />
			</div>
		</div>
	</form>
	<script type="text/javascript" src="resources/js/signin.js"></script>
</body>
</html>