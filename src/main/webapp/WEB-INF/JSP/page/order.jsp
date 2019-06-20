<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>


<div id="order">
 <c:if test="${CLIENT_MESSAGE != null }">
	<div class="alert alert-success hidden-print" role="alert">${CLIENT_MESSAGE }</div>
 </c:if>
 
 <table class="table table-bordered">
		<thead>
			<tr>
				<th>Product</th>
				<th>Price</th>
				<th>Count</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${order.items }">
			<tr id="product${item.product.id }" class="item">
				<td class="text-center"><img class="small" src="/WebShop/media/${item.product.imageLink }" alt="${item.product.name}">
				<br>${item.product.name}</td>
				<td class="price">${item.product.price} $</td>
				<td class="count">${item.countProduct}</td>
			</tr>
		
		</c:forEach>
			<tr>
				<td colspan="2" class="text-right"><strong>Total:</strong></td>
				<td colspan="2" class="total">${order.totalCost} $</td>
			</tr>
		</tbody>
	</table>
</div>