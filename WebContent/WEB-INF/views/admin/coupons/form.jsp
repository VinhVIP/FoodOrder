<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>

<!-- <style>
label {
	margin-left: 20px;
}

#datepicker {
	width: 180px;
	margin: 0 20px 20px 20px;
}

#datepicker>span:hover {
	cursor: pointer;
}
</style> -->

<div class="row ml-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_admin.jsp"%>
	</div>
	<div class="col-lg-1"></div>
	<div class="col-lg-6">

		<div class="mb-10">
			<span class="float-start msg-success">${message}</span> <span
				class="float-start msg-fail">${msgError}</span> <span
				class="float-end"></span>

		</div>
		<br />

		<form:form modelAttribute="couponsBean"
			action="${coupons == null ? 'admin/coupons/add.htm' : 'admin/coupons/edit.htm?id='}${coupons!=null?coupons.couponsId:''}"
			method="post">

			<div>
				<label class="form-label"><b>Mã phiếu</b></label>
				<form:input path="couponsId" type="text" class="form-control"
					id="txtNameControl" disabled="${coupons != null}" />


				<form:errors path="couponsId" />
			</div>

			<div>
				<label class="form-label"><b>Thông tin chi tiết</b></label>
				<form:input path="detail" type="text" class="form-control"
					id="txtNameControl" />
				<form:errors path="detail" />
			</div>

			<div class="row mt-10">
				<div class="col">
					<label class="form-label"><b>Loại phiếu</b></label><br>
					<div class="form-check form-check-inline">
						<form:radiobutton class="form-check-input" path="type"
							id="inlineRadio1" value="0" />
						<label class="form-check-label">Tiền</label>
					</div>
					<div class="form-check form-check-inline">
						<form:radiobutton class="form-check-input" path="type"
							id="inlineRadio2" value="1" />
						<label class="form-check-label">Phần trăm</label>
					</div>
				</div>

				<div class="col">
					<label class="form-label"><b>Trạng thái</b></label><br>
					<div class="form-check form-check-inline">
						<form:radiobutton class="form-check-input" path="status"
							id="raioStatus1" value="0" />
						<label class="form-check-label">Công khai</label>
					</div>
					<div class="form-check form-check-inline">
						<form:radiobutton class="form-check-input" path="status"
							id="raioStatus2" value="1" />
						<label class="form-check-label">Ẩn</label>
					</div>
				</div>
			</div>

			<div class="row mt-10">
				<div class="col-md-6">
					<label class="form-label"><b>Giá trị</b></label>
					<form:input path="value" type="number" class="form-control"
						id="txtNameControl" />
					<form:errors path="value" />
				</div>
				<div class="col-md-6">
					<label class="form-label"><b>Số lượng</b></label>
					<form:input path="amount" type="number" class="form-control"
						id="txtNameControl" />
					<form:errors path="amount" />
				</div>
			</div>

			<div>
				<label class="form-label">Hạn sử dụng</label>

				<form:input data-date-format="dd/mm/yyyy" path="expiredTime"
					id="datepicker" class="form-control" readonly="" type="text" />
				<form:errors path="expiredTime" />

			</div>


			<hr style="border: 1px solid #f1f1f1">

			<div class="text-center mv-10">
				<button type="submit" class="btn btn-primary shadow">${coupons == null ? "Thêm" : "Sửa" }</button>
			</div>
		</form:form>

	</div>

</div>

<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
			autoclose : true,
			todayHighlight : true
		}).datepicker('update', new Date());
	});
</script>


<%@include file="/WEB-INF/views/include/footer.jsp"%>