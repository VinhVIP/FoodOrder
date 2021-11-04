<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="vi-VN" scope="session" />
<%@include file="/WEB-INF/views/include/header.jsp"%>

<div class="row m-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_categories.jsp"%>
	</div>
	<div class="col-lg-1"></div>
	<div class="col-lg-7">

		<div class="row mb-10">
			<div class="col-8">
				<div class="food_title2">
					Đánh giá món: <a href="food/${food.foodId}.htm">${food.name}</a> <i>(${ratedSize}
						đánh giá)</i>
				</div>
			</div>
			<div class="col-4">
				<div class="float-end">
					<form method="get" action="food/${food.foodId}/rateds.htm?filter=${filter}&page=${page}">

						<div class="input-group">
							<span class="col-auto mr-10"> <select name="filter"
								id="filter"
								class="form-select form-select-sm mr-10 border-warning rounded-pill pr-5 ">

									<option value="1" <c:if test="${filter == 1}"> selected </c:if>>Mới
										nhất</option>
									<option value="2" <c:if test="${filter == 2}"> selected </c:if>>Đánh
										giá cao nhất</option>
									<option value="3" <c:if test="${filter == 3}"> selected </c:if>>5
										sao</option>
									<option value="3" <c:if test="${filter == 4}"> selected </c:if>>4
										sao</option>
									<option value="3" <c:if test="${filter == 5}"> selected </c:if>>3
										sao</option>
									<option value="3" <c:if test="${filter == 6}"> selected </c:if>>2
										sao</option>
									<option value="3" <c:if test="${filter == 7}"> selected </c:if>>1
										sao</option>
							</select>
							</span>

							<div class="input-group-append ml-10">
								<button class="btn btn-primary btn-sm">
									<i class="bi bi-search"></i>
								</button>
							</div>

						</div>
					</form>
				</div>

			</div>

			<br>
			<div class="col mt-10">
				<c:forEach items="${rateds}" var="rated">
					<div class="row">
						<div class="col-1 mr-10">
							<img class="rounded-circle z-depth-2" alt="50x50" width="60px"
								height="60px" src="${rated.account.avatar}"
								data-holder-rendered="true">
						</div>
						<div class="col-8">
							<b>${rated.account.name}</b><br> <span class="small-ratings">
								<c:forEach begin="1" end="5" varStatus="index">
									<c:choose>
										<c:when test="${index.count <= rated.star}">
											<i class="fa fa-star rating-color"></i>
										</c:when>
										<c:otherwise>
											<i class="fa fa-star"></i>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</span> <span class="mh-10">•</span> <span class="time"><fmt:formatDate
									pattern="dd/MM/yyyy - HH:mm" value="${rated.cmtTime}" /></span> <br>
							<p class="comment">${rated.comment}</p>
							<hr style="border: 1px solid #f1f1f1">
						</div>
					</div>
				</c:forEach>
			</div>

			<br />
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-end">
					<!-- First Page -->
					<li class="page-item ${page == 1 ? 'disabled' : '' }"><a
						class="page-link" href="food/${food.foodId}/rateds.htm?page=1&filter=${filter}"
						tabindex="-1" aria-disabled="true">&laquo;</a></li>

					<!-- Page Number -->
					<c:forEach begin="${page-2<1 ? 1 : page-2}"
						end="${page+2 > maxPage ? maxPage : page+2}" varStatus="loop">
						<li class="page-item ${loop.count == page ? 'active' : '' }"><a
							class="page-link"
							href="food/${food.foodId}/rateds.htm?page=${loop.count}&filter=${filter}">${loop.count}</a></li>
					</c:forEach>

					<!-- Last Page -->
					<li class="page-item ${page == maxPage ? 'disabled' : '' }"><a
						class="page-link"
						href="food/${food.foodId}/rateds.htm?page=${maxPage}&filter=${filter}"
						tabindex="-1" aria-disabled="true">&raquo;</a></li>
				</ul>

			</nav>

		</div>

		<%-- <h5>
			Đánh giá món: <a href="food/${food.foodId}.htm">${food.name}</a> <i>(${food.rateds.size()}
				đánh giá)</i>
		</h5> --%>


	</div>
</div>


<%@include file="/WEB-INF/views/include/footer.jsp"%>