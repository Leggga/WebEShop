<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#ishopNav" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span> <span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/WebShop/products">WebShop</a>
		</div>
		<div class="collapse navbar-collapse" id="ishopNav">
			<ul id="currentShoppingCart" class="nav navbar-nav navbar-right ${CURRENT_SHOPPING_CART == null ? 'hidden' : ''}">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<i class="fa fa-shopping-cart"aria-hidden="true"></i> Shopping cart (<span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span>)
						<span class="caret"></span>
					</a>
					<div class="dropdown-menu shopping-cart-desc">
						Total count: <span class="total-count">${CURRENT_SHOPPING_CART.totalCount }</span> pcs<br>
						Total cost: <span class="total-cost">${CURRENT_SHOPPING_CART.totalCost }</span> $<br> 
						<a href="/WebShop/shopping-cart" class="btn btn-primary btn-block">View cart</a>
					</div>
				</li>
			</ul>
			<c:choose>
				<c:when test="${CURRENT_ACCOUNT == null }">
					<tags:sign-in-btn/>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav navbar-right">
						<li><a>Welcome ${CURRENT_ACCOUNT.description}</a></li>
						<li><a href="/WebShop/my-orders">My orders</a></li>
						<li>
							<tags:sign-out-btn/>
						</li>
					</ul>
				</c:otherwise>			
			</c:choose>
		</div>
	</div>
</nav>
