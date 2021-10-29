<%@ page language="java" pageEncoding="utf-8"%>
<h5>Quản lý</h5>

<div class="list-group">
	<a href="admin/category.htm" class="list-group-item list-group-item-action"> Quản lý danh mục</a>
	
	<a href="admin/food.htm" class="list-group-item list-group-item-action"> Quản lý món ăn</a>

	<a href="#" class="list-group-item list-group-item-action active"
		data-bs-toggle="collapse" data-bs-target="#don" aria-expanded="false"
		aria-controls="don"> Quản lý đơn</a>

	<div class="collapse" id="don">
		<a href="#" class="list-group-item list-group-item-action">● Đơn
			chờ xác nhận</a> <a href="#"
			class="list-group-item list-group-item-action">● Đơn đã giao</a> <a
			href="#" class="list-group-item list-group-item-action">● Đơn đã
			hủy</a>
	</div>

	<a href="#" class="list-group-item list-group-item-action active"
		data-bs-toggle="collapse" data-bs-target="#phieu"
		aria-expanded="false" aria-controls="phieu"> Quản lý phiếu giảm
		giá</a>

	<div class="collapse" id="phieu">
		<a href="#" class="list-group-item list-group-item-action">● Thêm
			phiếu</a> <a href="#" class="list-group-item list-group-item-action">●
			Sửa, xóa phiếu</a>
	</div>

	<a href="#" class="list-group-item list-group-item-action active"
		data-bs-toggle="collapse" data-bs-target="#khoa" aria-expanded="false"
		aria-controls="khoa"> Quản lý tài khoản</a>

	<div class="collapse" id="khoa">
		<a href="#" class="list-group-item list-group-item-action">● Khóa
			tài khoản</a> <a href="#" class="list-group-item list-group-item-action">●
			Mở khóa tài khoản</a>
	</div>

</div>


<br />