<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ page import="food.utils.Constants"%>

<div class="row ml-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_admin.jsp"%>
	</div>
	<div class="col-lg-1"></div>
	<div class="col-lg-7">

		<form:form modelAttribute="foodBean"
			action="${food == null ? 'admin/food/add.htm' : 'admin/food/edit.htm?id='}${food!=null?food.foodId:''}"
			method="post" enctype="multipart/form-data">
			<div class="row">
				<div class="col-md-6">
					<label for="txtNameControl" class="form-label"><b>Tên
							món ăn</b></label>
					<form:input path="name" type="text" class="form-control"
						id="txtNameControl" />
					<form:errors path="name" />
				</div>
				<div class="col-md-3">
					<label class="form-label"><b>Danh mục</b></label>
					<form:select path="category" class="form-select">
						<form:options itemLabel="name" itemValue="categoryId"
							items="${categories}" />
					</form:select>
				</div>
				<div class="col-md-3">
					<label for="txtPrice" class="form-label"><b>Giá</b></label>
					<form:input path="price" type="number" class="form-control"
						id="txtPrice" />
					<form:errors path="price" />
				</div>
			</div>

			<div class="mb-3 mt-10">
				<label for="txtDetail" class="form-label"><b>Thông tin
						chi tiết</b></label>
				<form:textarea class="form-control" path="detail" rows="3"></form:textarea>
				<form:errors path="detail" />
			</div>

			<div class="row mt-10">
				<div class="col">
					<label class="form-label"><b>Tình trạng</b></label><br>
					<div class="form-check form-check-inline">
						<form:radiobutton class="form-check-input" path="type"
							id="inlineRadio1" value="0" />
						<label class="form-check-label">Còn món</label>
					</div>
					<div class="form-check form-check-inline">
						<form:radiobutton class="form-check-input" path="type"
							id="inlineRadio2" value="1" />
						<label class="form-check-label">Hết món</label>
					</div>
				</div>

				<div class="col">
					<label class="form-label"><b>Trạng thái</b></label><br>
					<div class="form-check form-check-inline">
						<form:radiobutton class="form-check-input" path="status"
							id="raioStatus1" value="0" />
						<label class="form-check-label">Hiển thị</label>
					</div>
					<div class="form-check form-check-inline">
						<form:radiobutton class="form-check-input" path="status"
							id="raioStatus2" value="1" />
						<label class="form-check-label">Ẩn</label>
					</div>
				</div>
			</div>


			<label for="formFile" class="form-label mt-10"><b>Các
					hình ảnh</b></label>
			<div class="row">

				<c:forEach begin="1" end="3" varStatus="index">
					<div class="col-4">
						<form:input path="images" class="form-control form-control-sm"
							type="file" accept="image/*" id="formFile${index.count}"
							onchange="loadFile(${index.count})" />
						<div class="text-center mv-10">
							<button id="btn-${index.count}" type="button"
								class="btn btn-warning btn-sm"
								onclick="deleteImage(${index.count})">
								<i class="bi bi-x-lg"></i>
							</button>
						</div>
						<img src="${Constants.getImageAt(food.images, index.count-1)}"
							id="output${index.count}" width="100%" class="mv-10" />

						<form:input type="hidden" class="form-control" path="imagePath"
							id="imagePath${index.count}" />

					</div>
				</c:forEach>

			</div>

			<hr style="border: 1px solid #f1f1f1">

			<div class="text-center mv-10">
				<button type="submit" class="btn btn-primary shadow">${food == null ? "Thêm" : "Sửa" }</button>
			</div>
		</form:form>

	</div>

</div>

<script>
	var images = "${food.images}";
	console.log(images);
	var arr = images.split(" ");
	console.log(arr.length);

	for (var i = 1; i <= 3; i++) {
		$("#btn-" + i).hide();
		$("#imagePath"+i).val('');
	}

	for (var i = 1; i <= arr.length; i++) {
		if(arr[i-1].length > 0){
			$("#btn-" + i).show();
			$("#imagePath"+i).val(arr[i-1]);
		}	
	}

	var deleteImage = function(i) {
		var output = document.getElementById("output" + i);
		output.src = '';
		$("#formFile" + i).val('');
		$("#btn-" + i).hide();
		$("#imagePath"+i).val('');
	};

	var loadFile = function(i) {
		var output = document.getElementById("output" + i);
		$("#btn-" + i).show();
		$("#imagePath"+i).val(event.target.files[0].name);
		output.src = URL.createObjectURL(event.target.files[0]);
		output.onload = function() {
			URL.revokeObjectURL(output.src); // free memory
		}
	};
</script>


<%@include file="/WEB-INF/views/include/footer.jsp"%>