<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ page import="food.utils.Constants"%>

<div class="row ml-10">
	<div class="col-3">
		<%@include file="/WEB-INF/views/include/sidebar_admin.jsp"%>
	</div>
	<div class="col-8 ml-20">

		<div>
			<span class="float-start msg-success">${message}</span> <span
				class="float-start msg-fail">${msgError}</span> <span
				class="float-end"><a href="admin/account.htm" type="button"
				class="btn btn-primary rounded-pill mb-10 shadow">Xem danh sách tất cả tài khoản</a></span>

		</div>

		<table class="shadow table table-hover">
			<thead class="indigo">
				<tr>
					<th class="btlr" scope="col">ID</th>
					<th scope="col">Avatar</th>
					<th scope="col">Tên</th>
					<th scope="col">Email</th>
					<th scope="col">SĐT</th>
					<th scope="col">Trạng thái</th>
					<th class="btrr" scope="col">Hành động</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="a" items="${accounts}">
					<tr>
						<td scope="row"><b>${a.accountId}</b></td>
						<td><img class="rounded-circle z-depth-2 mr-10 shadow"
							src="${a.avatar}" width="50px" height="50px" /></td>
						<td><b>${a.name}</b></td>
						<td><i>${a.email}</i></td>
						<td>${a.phone}</td>
						<td><c:choose>
								<c:when test="${a.status == 0}">
									<span class="msg-success">Hoạt động</span>
								</c:when>
								<c:otherwise>
									<span class="msg-fail">Khóa</span>
								</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${a.status==0}">
									<button type="button"
										class="btn btn-danger btn-sm rounded-pill"
										onclick="confirm(${a.accountId})">
										<i class="bi bi-lock-fill"></i>
									</button>
								</c:when>
								<c:otherwise>
									<a href="admin/account/unlock2.htm?id=${a.accountId}"
										class="btn btn-success btn-sm rounded-pill"> <i
										class="bi bi-unlock-fill"></i>
									</a>
								</c:otherwise>
							</c:choose></td>
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
						<h5 class="modal-title" id="exampleModalLabel">Xác nhận khóa
							tài khoản</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div id="confirmBody" class="modal-body">Người dùng khi bị
						khóa sẽ không thể sử dụng website của bạn. Bạn chắc chắn chứ?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Hủy</button>
						<button id="btnConfirm" type="button" class="btn btn-danger">Khóa</button>
					</div>
				</div>
			</div>
		</div>




		<br />
		<nav aria-label="Page navigation">
			<ul class="pagination justify-content-end">
				<!-- First Page -->
				<li class="page-item ${page == 1 ? 'disabled' : '' }"><a
					class="page-link" href="admin/account/locked.htm?page=1" tabindex="-1"
					aria-disabled="true">&laquo;</a></li>

				<!-- Page Number -->
				<c:forEach begin="${page-2<1 ? 1 : page-2}"
					end="${page+2 > maxPage ? maxPage : page+2}" varStatus="loop">
					<li class="page-item ${loop.count == page ? 'active' : '' }"><a
						class="page-link" href="admin/account/locked.htm?page=${loop.count}">${loop.count}</a></li>
				</c:forEach>

				<!-- Last Page -->
				<li class="page-item ${page == maxPage ? 'disabled' : '' }"><a
					class="page-link" href="admin/account/locked.htm?page=${maxPage}"
					tabindex="-1" aria-disabled="true">&raquo;</a></li>
			</ul>
		</nav>
	</div>

</div>

<script>
	var id = -1;
	
	var confirm = function(accountId){
		id = accountId;
		console.log(accountId);
		$('#confirmModel').modal('show');
		var str = "Người dùng khi bị khóa sẽ không thể sử dụng website của bạn. Bạn chắc chắn chứ?";
		$('#confirmBody').html(str);	
	}
	$('#btnConfirm').on('click', function(event) {
		window.location.href = "admin/account/lock.htm?id="+id;
	});
</script>


<%@include file="/WEB-INF/views/include/footer.jsp"%>