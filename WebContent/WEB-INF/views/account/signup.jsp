<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<style type="text/css">
*[id$=errors] {
	color: red;
	font-style: italic;
}
</style>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" type="text/css" href="resources/css/signup.css">
<link href='https://fonts.googleapis.com/css?family=Ubuntu:500'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<form:form action="account/signup.htm" method="post" modelAttribute="user">
		<div class="signup">
			<div class="signup-header">
				<h1>Đăng Ký</h1>
				<h5 style="color: red; font-style: italic; padding-top: 15px;">${message}</h5>
			</div>
			<div class="signup-form">
				<h3>Tên:</h3>
				<form:input type="text" path="name" />
				<br>
				<form:errors path="name" />
				<br>
				<h3>Email:</h3>
				<form:input type="email" path="email" />
				<form:errors path="email" />
				<br>
				
				<h3>Số điện thoại:</h3>
				<form:input type="text" path="phone" />
				<form:errors path="phone" />
				<br>
				
				<h3>Địa chỉ:</h3>
				<form:input type="text" path="address" />
				<form:errors path="address" />
				<br>
				
				<h3>Password:</h3>
				<form:input type="password" path="password" />
				<form:errors path="password" />
				<br>
				<%-- <h3>Avatar:</h3>
				<form:input type="file" path="avatar"/> --%>
				<%-- <form:hidden path="avatar"/> --%>
				<br> <input type="submit" value="Đăng ký" class="signup-button" />
				<!-- <button>Cập nhật</button> -->
				<br> <a class="sign-in" href="account/signin.htm">Đã có tài
					khoản, mời đăng nhập</a>
			</div>
		</div>
	</form:form>
	<!-- 	<script type="text/javascript" src="resources/js/signup.js"></script> -->
</body>
</html>