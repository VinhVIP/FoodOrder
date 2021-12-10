<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="food.utils.Constants"%>
<h5>
	<div class="ml-10">Quản lý</div>
</h5>

<div class="list-group shadow">

	<a href="admin/category.htm"
		class="list-group-item list-group-item-action ${Constants.adminPanelIndex(requestScope['javax.servlet.forward.request_uri']) == 0 ? 'active' : '' }">
		Quản lý danh mục</a> <a href="admin/food.htm"
		class="list-group-item list-group-item-action ${Constants.adminPanelIndex(requestScope['javax.servlet.forward.request_uri']) == 1 ? 'active' : '' }">
		Quản lý món ăn</a> <a href="admin/mail.htm"
		class="list-group-item list-group-item-action ${Constants.adminPanelIndex(requestScope['javax.servlet.forward.request_uri']) == 2 ? 'active' : '' }">
		Gửi mail</a> <a href="admin/order/index.htm"
		class="list-group-item list-group-item-action ${Constants.adminPanelIndex(requestScope['javax.servlet.forward.request_uri']) == 3 ? 'active' : '' }">
		Quản lý đơn</a> <a href="admin/coupons.htm"
		class="list-group-item list-group-item-action ${Constants.adminPanelIndex(requestScope['javax.servlet.forward.request_uri']) == 4 ? 'active' : '' }">
		Quản lý phiếu giảm giá</a> 
		<a href="admin/account.htm"
		class="list-group-item list-group-item-action ${Constants.adminPanelIndex(requestScope['javax.servlet.forward.request_uri']) == 5 ? 'active' : '' }">
		Quản lý tài khoản</a>

</div>


<br />