<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>

<div class="row ml-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_admin.jsp"%>
	</div>
	<div class="col-lg-1"></div>
	<div class="col-lg-6">

		<form:form modelAttribute="categoryBean"
			action="${category == null ? 'admin/category/add.htm' : 'admin/category/edit.htm?id='}${category!=null?category.categoryId:''}"
			method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="txtNameControl" class="form-label"><b>Tên danh mục</b></label>
				<form:input path="name" type="text" class="form-control"
					id="txtNameControl" />
				<form:errors path="name" />
			</div>
			<div class="mb-3">
				<label for="formFile" class="form-label"><b>Logo</b></label>
				<form:input path="logo" class="form-control" type="file"
					accept="image/*" id="formFile" onchange="loadFile(event)" />
				<img src="${category.logo}" id="output" width="100px" height="100px" class="mv-10" />
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-primary shadow">${category == null ? "Thêm" : "Sửa"}</button>
			</div>
		</form:form>

	</div>

</div>

<script>
	var logo = "${category.logo}";
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