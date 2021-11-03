<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>

<div class="row ml-10">
	<div class="col-3">
		<%@include file="/WEB-INF/views/include/sidebar_admin.jsp"%>
	</div>
	<div class="col-1"></div>
	<div class="col-7">

		<div>
			<span class="float-start msg-success">${message}</span> <span
				class="float-start msg-fail">${msgError}</span> <span
				class="float-end"><a href="admin/category/add.htm"
				type="button" class="btn btn-primary rounded-pill mb-10 shadow"><i
					class="bi bi-plus-circle-fill"></i> Thêm danh mục</a></span>

		</div>
		<table class="shadow table table-hover">
			<thead class="indigo">
				<tr>
					<th class="btlr" scope="col">ID</th>
					<th scope="col">Logo</th>
					<th scope="col">Tên</th>
					<th scope="col">Số lượng món</th>
					<th scope="col">Sửa</th>
					<th class="btrr" scope="col">Xóa</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${categories}">
					<tr>
						<td scope="row">${c.categoryId}</td>
						<td><img class="rounded-circle z-depth-2 mr-10 shadow"
							src="${c.logo}" width="50px" height="50px" /></td>
						<td>${c.name}</td>
						<td><span class="badge bg-warning ml-10 rounded-pill">${c.foods.size()}</span></td>
						<td><a href="admin/category/edit.htm?id=${c.categoryId}"
							type="button" class="btn btn-primary btn-sm rounded-pill"><i
								class="bi bi-pencil-fill"></i></a></td>
						<td>
							<button type="button" class="btn btn-danger btn-sm rounded-pill"
								onclick="confirm(${c.categoryId})"
								${c.foods.size() > 0 ? 'disabled' : ''}>
								<i class="bi bi-x-lg"></i>
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<!-- Modal -->
		<div id="confirmModel" class="modal fade hide" id="exampleModal"
			tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Xác nhận xóa
							danh mục ?</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div id="confirmBody" class="modal-body">
						Nếu món ăn đã được thêm vào giỏ của người dùng thì vẫn sẽ bị xóa.
						<br> Thao tác này không thể khôi phục. <br>Bạn chắc chắn
						chứ ?
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Hủy</button>
						<button id="btnConfirm" type="button" class="btn btn-primary">Xóa</button>
					</div>
				</div>
			</div>
		</div>


	</div>

</div>

<script>
	var id = -1;
	
	var confirm = function(cId){
		id = cId;
		console.log(cId);
		$('#confirmModel').modal('show');
		var str = "Thao tác này không thể khôi phục<br>Bạn chắc chắn muốn xóa chứ?";
		$('#confirmBody').html(str);	
	}
	$('#btnConfirm').on('click', function(event) {
		console.log("c: "+id);
		window.location.href = "admin/category.htm?delete="+id;
	});
</script>

<%@include file="/WEB-INF/views/include/footer.jsp"%>