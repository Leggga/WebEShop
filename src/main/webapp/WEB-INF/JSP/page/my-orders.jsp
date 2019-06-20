<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="order-table">
	<div class="alert alert-success hidden-print" role="alert">You have ${total } order(s).</div>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID order</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody id="table-orders" data-page-count="${pages}" data-page-num="1">
			<jsp:include page="../fragment/order-list.jsp"/>	
		</tbody>
	</table>
	<c:if test="${pages > 1}">
		<div class="text-center hidden-print">
			<a id="loadMoreOrders" class="btn btn-success">Load more</a>
		</div>
	</c:if>
</div>