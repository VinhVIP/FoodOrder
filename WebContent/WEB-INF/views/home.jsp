<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ page import="food.utils.Constants"%>

<div class="row m-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_categories.jsp"%>
	</div>
	<div class="col-lg-9">
	
		<h5>Các món ăn mới:</h5>
		<div class="row row-cols-2 row-cols-md-4 g-4">
			<c:forEach var="f" items="${newFoods}">
				<div class="col">
					<div class="card h-100">
						<a href="food/index.htm?id=${f.foodId}"><img src="${Constants.getBanner(f.images)}"
							class="card-img-top" height="200px"></a>
						<div class="card-body">
							<h5 class="card-title">${f.name}</h5>
							<p class="card-text">
								Giá: <i><b>${f.price}</b></i> đ
							</p>
							<p class="card-text">${f.detail}</p>
						</div>
						<div class="card-footer">
							<a href="#" class="btn btn-primary">Thêm vào giỏ</a>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>

		
		

	</div>
</div>


<%@include file="/WEB-INF/views/include/footer.jsp"%>