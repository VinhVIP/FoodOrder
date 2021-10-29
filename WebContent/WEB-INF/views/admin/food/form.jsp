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
						<form:options itemLabel="name" itemValue="categoryId" items="${categories}"/>
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
				<div class="col-4">
					<form:input path="images" class="form-control form-control-sm"
						type="file" accept="image/*" id="formFile"
						onchange="loadFile(event)" />
					<img src="${Constants.getImageAt(food.images, 0)}" id="output1"
						width="100%" class="mv-10" />
				</div>
				<div class="col-4">
					<form:input path="images" class="form-control form-control-sm"
						type="file" accept="image/*" id="formFile"
						onchange="loadFile2(event)" />
					<img src="${Constants.getImageAt(food.images, 1)}" id="output2"
						width="100%" class="mv-10" />
				</div>
				<div class="col-4">
					<form:input path="images" class="form-control form-control-sm"
						type="file" accept="image/*" id="formFile"
						onchange="loadFile3(event)" />
					<img src="${Constants.getImageAt(food.images, 2)}" id="output3"
						width="100%" class="mv-10" />
				</div>
			</div>

			<hr style="border: 1px solid #f1f1f1">

			<div class="text-center mv-10">
				<button type="submit" class="btn btn-primary">${food == null ? "Thêm" : "Sửa" }</button>
			</div>
		</form:form>

	</div>

</div>

<script>
	var loadFile = function(event) {
		var output = document.getElementById('output1');
		output.src = URL.createObjectURL(event.target.files[0]);
		output.onload = function() {
			URL.revokeObjectURL(output.src) // free memory
		}
	};
	var loadFile2 = function(event) {
		var output = document.getElementById('output2');
		output.src = URL.createObjectURL(event.target.files[0]);
		output.onload = function() {
			URL.revokeObjectURL(output.src) // free memory
		}
	};
	var loadFile3 = function(event) {
		var output = document.getElementById('output3');
		output.src = URL.createObjectURL(event.target.files[0]);
		output.onload = function() {
			URL.revokeObjectURL(output.src) // free memory
		}
	};
</script>


<%@include file="/WEB-INF/views/include/footer.jsp"%>