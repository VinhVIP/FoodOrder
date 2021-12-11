<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="resources/ckeditor/ckeditor.js"></script>

<div class="row ml-10">
	<div class="col-lg-3">
		<%@include file="/WEB-INF/views/include/sidebar_admin.jsp"%>
	</div>
	<div class="col-lg-1"></div>
	<div class="col-lg-7">
		${message}
		<form action="admin/mail/send.htm" method="post">
			<label class="form-label"><b>Tiêu đề</b></label> <input name="subject" 
				class="form-control" /> <label class="form-label mt-10"><b>Nội
					dung</b></label>
			<textarea name="body" id="body" rows="10" cols="80">
                Khuyến mãi siêu to 12/12, giảm giá 50% tất cả món ăn
            </textarea>
			<div class="text-center mt-10">
				<button type="submit" class="btn btn-primary">Gửi</button>
			</div>

		</form>

	</div>

</div>


<script>
	CKEDITOR.replace('body');
</script>

<%@include file="/WEB-INF/views/include/footer.jsp"%>