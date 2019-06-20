<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="order" items="${orders }">
	<tr class="order-row">
		<td class="order-item" ><a href="/WebShop/order?id=${order.id }">Order #${order.id }</a></td>
		<td class="date">${order.created}</td>
	</tr>
</c:forEach>