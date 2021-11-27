<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" type="text/css" href="resources/css/signup.css">
<link href='https://fonts.googleapis.com/css?family=Ubuntu:500'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<form:form action="account/signup.htm" method="post"
		modelAttribute="user" enctype="multipart/form-data">
		<div class="signup">
			<div class="signup-header">
				<h1>SignUp</h1>
				<br>
				<h4>${message}</h4>
				<br>
			</div>
			<div class="signup-form">
				<h3>Tên:</h3>
				<form:input type="text" placeholder="Name" path="name" />
				<br>
				<h3>Email:</h3>
				<form:input type="email" placeholder="Email" path="email" />
				<br>
				<h3>Password:</h3>
				<form:input type="password" placeholder="Password" path="password" />
				<br>
				<h3>Avatar:</h3>
				<form:input type="file" path="avatar" />
				<br> <input type="submit" value="Signup" class="signup-button" />
				<br> <a class="sign-in" href="account/signin.htm">Đã có tài
					khoản, mời đăng nhập</a>
			</div>
		</div>
	</form:form>
	<script type="text/javascript" src="resources/js/signup.js"></script>
</body>
</html>