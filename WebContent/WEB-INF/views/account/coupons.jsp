<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ page import="food.utils.Constants"%>

<div class="row m-10">

	<div class="col-12">
		<div class="text-center">
			<h3>Phiếu giảm giá</h3>
		</div>

		<div class="container my-5">
			<div class="row">
				<c:forEach items="${coupons}" var="cp">
					<div class="col-md-6 mb-10">
						<div
							class="coupon rounded mb-3 d-flex justify-content-between shadow "
							style="background: #e6e6ff">
							<div class="kiri p-3"></div>
							<div class="tengah py-3 d-flex w-100 justify-content-start">
								<div>
									<h4>
										<c:choose>
											<c:when test="${cp.type == 0}">
												<fmt:formatNumber value="${cp.value}" type="currency"
													currencySymbol="đ" minFractionDigits="0" />
											</c:when>
											<c:otherwise>
							${cp.value}%
							</c:otherwise>
										</c:choose>
									</h4>
									<h5 style="color: #4da6ff">${cp.detail}</h5>
									<p style="font-style: italic; font-size: 16px">
										Số lượng còn lại: ${cp.amount}<br /> Hạn sử dụng đến hết
										<fmt:formatDate pattern="dd/MM/yyyy" value="${cp.expiredTime}" />
									</p>

								</div>
							</div>
							<div class="kanan">
								<div class="info m-3 d-flex align-items-center">
									<div class="w-100">
										<div class="block">
											<span class="time font-weight-light"> <span>Code</span>
											</span>
										</div>
										<button class="btn btn-sm btn-outline-danger btn-block">
											${cp.couponsId}</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>


			</div>
		</div>

	</div>
</div>



<%@include file="/WEB-INF/views/include/footer.jsp"%>