<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-bordered">
		<thead>
			<tr>
				<th>Product</th>
				<th>Price</th>
				<th>Count</th>
				<th class="hidden-print">Action</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${CURRENT_SHOPPING_CART.items }">
			<tr id="product${item.product.id }" class="item">
				<td class="text-center"><img class="small" src="/WebShop/media/${item.product.imageLink }" alt="${item.product.name}">
				<br>${item.product.name}</td>
				<td class="price">${item.product.price} $</td>
				<td class="count">${item.count}</td>
				<td class="hidden-print">
					<a class="btn btn-danger remove-product" data-id-product="${item.product.id }" data-count="1">Remove one</a>
					<c:if test="${item.count > 1}">
						<a class="btn btn-danger remove-product all" data-id-product="${item.product.id }" data-count="${item.count}">Remove all</a>					
					</c:if>
				</td>
			</tr>
		
		</c:forEach>
			<tr>
				<td colspan="2" class="text-right"><strong>Total:</strong></td>
				<td colspan="2" class="total">${CURRENT_SHOPPING_CART.totalCost} $</td>
			</tr>
		</tbody>
	</table>
