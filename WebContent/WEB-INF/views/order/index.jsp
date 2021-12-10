<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="food.utils.Constants"%> 
<fmt:setLocale value="vi-VN" scope="session" />
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
 

<div>
	<jsp:useBean id="pagedListHolder" scope="request"
		type="org.springframework.beans.support.PagedListHolder" />
	<c:url value="order/" var="pagedLink">
		<c:param name="p" value="~" />
	</c:url>
	<c:choose>
		<c:when test="${pagedListHolder.pageList.isEmpty() == true }"> 
			<h1>Đơn hàng trống.</h1>
		</c:when>
		<c:when test="${pagedListHolder.pageList.isEmpty() == false }">
			<div>
				<tg:paging pagedListHolder="${pagedListHolder}"
					pagedLink="${pagedLink}" />
			</div>
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">Mã đơn hàng</th>

			      <th scope="col">Địa chỉ</th>
			      <th scope="col">Thời gian đặt</th>
			      <th scope="col">Trạng thái</th>
			      <th scope="col">Thao tác</th>				
			      <th scope="col">Thông tin</th>
			    </tr>
			  </thead>
			  <c:forEach var="o" items="${pagedListHolder.pageList}" >
				  <tbody>
				    <tr>
				      <th scope="row">${o.orderId}</th>

				      <td>${o.account.address}</td>
				      <td>${o.orderTime}</td>
				      <td>
				      	<c:if test="${o.status == 2}"><p style="color:blue;">Đã hoàn thành</p></c:if>
				      	<c:if test="${o.status == 1}"><p style="color: green;">Đang giao</p></c:if>
				      	<c:if test="${o.status == 0}"><p style="color: yellow;">Chờ xác nhận</p></c:if>
				   		<c:if test="${o.status == 3}"><p style="color: red;">Đã hủy</p></c:if>
				      </td>
				      <td>
					      <c:if test="${o.status == 0}">
					      	<a class="btn btn-danger" href="order/status.htm?orderId=${o.orderId}&status=3">Hủy đơn</a>
					      </c:if>
				      </td>
				      <td><a class="btn" href="order/detail/${o.orderId}.htm" style="color: blue;">Chi tiết</a> </td>
				    </tr>
				   </tbody>
			  </c:forEach>
			  
			</table>
			<div>
				<tg:paging pagedListHolder="${pagedListHolder}"
					pagedLink="${pagedLink}" />
			</div>
		</c:when>
	</c:choose>
</div>




<%@include file="/WEB-INF/views/include/footer.jsp"%>