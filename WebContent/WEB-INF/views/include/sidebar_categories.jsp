<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="mb-10">
	<br>

	<div class="shadow list-group">
		<c:forEach var="c" items="${categories}">

			<a href="./food/index.htm?id_category=${c.categoryId}"
				class="list-group-item list-group-item-action ${category.categoryId == c.categoryId ? 'active' : ''}"
				aria-current="true"><img class="rounded-circle mr-10"
				src="${c.logo}" width="50px" height="50px" /> ${c.name} <%-- <span class="badge bg-warning rounded-pill ml-10">${c.foods.size()}</span> --%>
			</a>
			
		</c:forEach>

	</div>
</div>
<br />