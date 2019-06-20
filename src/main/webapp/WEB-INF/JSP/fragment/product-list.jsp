<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:forEach var="product" items="${products}">
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-xlg-2">
			<!-- PRODUCT DATA -->
			<div id="product${product.id}" class="panel panel-default product">
				<div class="panel-body">
					<div class="thumbnail">
						<img src="/WebShop/media/${product.imageLink}" alt="${product.name }">
						<div class="desc">
							<div class="cell">
								<p>
									<span class="title">Details</span>${product.description}
								</p>
							</div>
						</div>
					</div>
					<h4 class="name">${product.name }</h4>
					<div class="code">Code: ${product.id }</div>
					<div class="price">$ ${product.price }</div>
					<a class="btn btn-primary pull-right buy-btn" data-id-product="${product.id }">Buy</a>
					<div class="list-group">
						<span class="list-group-item">
							<small>Category: </small><span class="category">${product.category }</span>
						</span>
						<span class="list-group-item">
							<small>Producer: </small><span class="producer">${product.producer}</span>
						</span>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
