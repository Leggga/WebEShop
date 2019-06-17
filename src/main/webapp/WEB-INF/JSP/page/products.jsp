<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="productList" data-page-count="${pages}" data-page-num="1">
	<div class="row">
		<jsp:include page="../fragment/product-list.jsp"></jsp:include>
	</div>
	<c:if test="${pages > 1}">
		<div class="text-center hidden-print">
			<a id="loadMore" class="btn btn-success">Load more</a>
		</div>
	</c:if>
</div>
<tags:add-product-popup></tags:add-product-popup>