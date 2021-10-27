<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="text-center">
	<h5>Danh mục món</h5>
</div>


<div class="list-group">
	<c:forEach var="c" items="${categories}">
		<c:choose>
			<c:when test="${category.categoryId == c.categoryId}">
				<a href="./food/index.htm?id_category=${c.categoryId}"
					class="list-group-item list-group-item-action active"
					aria-current="true"><img class="rounded-circle z-depth-2 mr-10"
					src="${c.logo}" width="50px" height="50px" /> ${c.name}<span
					class="badge bg-warning ml-10">${c.foods.size()}</span></a>
			</c:when>
			<c:otherwise>
				<a href="./food/index.htm?id_category=${c.categoryId}"
					class="list-group-item list-group-item-action" aria-current="true"><img
					class="rounded-circle z-depth-2 mr-10" src="${c.logo}" width="50px"
					height="50px" /> ${c.name}<span class="badge bg-warning ml-10">${c.foods.size()}</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

</div>
<br />