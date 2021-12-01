<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" type="text/css" href="resources/css/forgot.css">
<link href='https://fonts.googleapis.com/css?family=Ubuntu:500'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<form action="account/forgot.htm" method="post">
		<div class="forgot">
			<div class="forgot-header">
				<h1>Quên mật khẩu</h1>
				<h6 style="color: red; font-style: italic; padding-top: 15px;">${message}</h6>
			</div>
			<div class="forgot-form">
				<h3>Email:</h3>
				<input type="email" placeholder="Nhập Email" name="email" /> <br>

				<div>
					<img src="${pageContext.request.contextPath}/captcha/"><br>
					<!-- <s:message code="login.Captcha.placeholder" var="placeHolderCaptcha"/> -->
					<input
						style="max-width: 400px; line-height: 3em; font-family: 'Ubuntu', sans-serif; border-radius: 5px; border: 2px solid #f2f2f2; outline: none; padding-left: 10px; text-align: center;"
						name="captcha" type="text" placeholder="${placeHolderCaptcha}" />
				</div>
				<br> <input type="submit" value="Xác nhận"
					class="forgot-button" />
			</div>
		</div>
	</form>
	<script type="text/javascript" src="resources/js/forgot.js"></script>
</body>
</html>