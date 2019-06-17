<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<div id="shoppingCart">
	<div class="alert alert-warning hidden-print" role="alert">To make order, please sign in</div>
	<tags:product-cart></tags:product-cart>
	<div class="row hidden-print">
		<div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
			<a class="btn btn-primary btn-block">
			<i class="fa fa-facebook-official" aria-hidden="true"></i> Sign in</a>
		</div>
	</div>
</div>