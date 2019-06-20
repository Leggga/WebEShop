<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>


<div id="shoppingCart">
	<c:if test="${CURRENT_ACCOUNT == null }">
		<div class="alert alert-warning hidden-print" role="alert">To make order, please sign in</div>
	</c:if> 
	<tags:product-cart></tags:product-cart>
	<div class="row hidden-print">
		<div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
			<c:choose >
				<c:when test="${CURRENT_ACCOUNT == null }"><tags:sign-in-btn/></c:when>
				<c:otherwise><tags:make-order-btn/></c:otherwise>
			</c:choose>
			
		</div>
	</div>
</div>