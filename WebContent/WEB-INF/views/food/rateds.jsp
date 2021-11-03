<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="vi-VN" scope="session" />
<%@include file="/WEB-INF/views/include/header.jsp"%>

<div class="row m-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_categories.jsp"%>
	</div>
	<div class="col-lg-9">
		<h5>
			Đánh giá món: <a href="food/${food.foodId}.htm">${food.name}</a> <i>(${food.rateds.size()}
				đánh giá)</i>
		</h5>
		<br>
		<div class="col">
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
					class="page-link" href="food/${food.foodId}/rateds.htm?page=1"
					tabindex="-1" aria-disabled="true">&laquo;</a></li>

				<!-- Page Number -->
				<c:forEach begin="${page-2<1 ? 1 : page-2}"
					end="${page+2 > maxPage ? maxPage : page+2}" varStatus="loop">
					<li class="page-item ${loop.count == page ? 'active' : '' }"><a
						class="page-link"
						href="food/${food.foodId}/rateds.htm?page=${loop.count}">${loop.count}</a></li>
				</c:forEach>

				<!-- Last Page -->
				<li class="page-item ${page == maxPage ? 'disabled' : '' }"><a
					class="page-link" href="food/${food.foodId}/rateds.htm?page=${maxPage}"
					tabindex="-1" aria-disabled="true">&raquo;</a></li>
			</ul>

		</nav>

	</div>
</div>


<%@include file="/WEB-INF/views/include/footer.jsp"%>