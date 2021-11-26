<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ page import="food.utils.Constants"%>

<div class="row m-10">

	<div class="col-lg-2"></div>
	<div class="col-lg-8">
		<div class="text-center"><h3>Trang cá nhân</h3></div>
		
		<div class="msg-success">${message}</div>

		<form:form method="post" modelAttribute="accountBean"
			action="account/profile.htm" enctype="multipart/form-data">

			<div class="mb-3">
				<label for="formFile" class="form-label"><b>Avatar</b></label>
				<form:input path="avatar" class="form-control" type="file"
					accept="image/*" id="formFile" onchange="loadFile(event)" />
				<img src="${user.avatar}" id="output" width="100px" height="100px"
					class="mv-10" />
			</div>

			<div class="mb-3">
				<label class="form-label"><b>Họ và tên</b></label>
				<form:input path="name" type="text" class="form-control" id="name" />
				<form:errors path="name" />
			</div>

			<div class="mb-3">
				<label class="form-label"><b>Email</b></label>
				<form:input path="email" type="email" class="form-control" id="email" />
				
			</div>

			<div class="mb-3">
				<label class="form-label"><b>Số điện thoại</b></label>
				<form:input path="phone" type="text" class="form-control" id="phone" />
				<form:errors path="phone" />
			</div>

			<div class="mb-3">
				<label class="form-label"><b>Địa chỉ</b></label>
				<form:input path="address" type="text" class="form-control" id="address" />
				<form:errors path="address" />
			</div>

			<div class="text-center mb-10">
				<button type="submit" class="btn btn-primary">Cập nhật</button>
			</div>

		</form:form>
	</div>
	<div class="col-lg-2"></div>
</div>

<script>
	var logo = "${user.avatar}";
	console.log(logo);
	if(logo == null || logo == '') $("#output").hide();
	
	var loadFile = function(event) {
		var output = document.getElementById('output');
		$("#output").show();
		output.src = URL.createObjectURL(event.target.files[0]);
		output.onload = function() {
			URL.revokeObjectURL(output.src) // free memory
		}
	};
</script>

<%@include file="/WEB-INF/views/include/footer.jsp"%>