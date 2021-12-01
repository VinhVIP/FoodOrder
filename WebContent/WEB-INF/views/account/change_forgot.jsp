<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thay đổi mật khẩu</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" type="text/css" href="resources/css/change.css">
<link href='https://fonts.googleapis.com/css?family=Ubuntu:500'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<form action="account/change_forgot.htm" method="post">
		<div class="change">
			<div class="change-header">
				<h1>Thay đổi mật khẩu</h1>
				<h5 style="color:red; font-style: italic;padding-top: 15px;">${message}</h5>
			</div>
			<div class="change-form">
				<h3>Email:</h3>
				<input type="email" placeholder="Nhập Email" name="email" value="${email}" readonly />
				<br>
				<h3>Mật khẩu mới:</h3>
				<input type="password" placeholder="New Password" name="pw1" /> <br> 
				<h3>Nhập lại mật khẩu mới:</h3>
				<input type="password" placeholder="Confirm New Password" name="pw2" /> <br> 
				
				<input type="submit" value="Đổi mật khẩu" class="change-button" />
			</div>
		</div>
	</form>
	<script type="text/javascript" src="resources/js/signin.js"></script>
</body>
</html>