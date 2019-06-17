<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Web e-Shop</title>
	    <link href="/WebShop/static/css/bootstrap.css" rel="stylesheet">
	    <link href="/WebShop/static/css/bootstrap-theme.css" rel="stylesheet">
	    <link href="/WebShop/static/css/font-awesome.css" rel="stylesheet">
	    <link href="/WebShop/static/css/app.css" rel="stylesheet">
	    <link rel="shortcut icon" href="/WebShop/static/img/favicon.ico" type="image/x-icon">
		<link rel="icon" href="/WebShop/static/img/favicon.ico" type="image/x-icon">
	</head>
	<body>
		<header>
			<jsp:include page="fragment/header.jsp"></jsp:include>
		</header>
		<div class="container-fluid">
			<div class="row">
				<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
					<jsp:include page="fragment/aside.jsp"></jsp:include>
				</aside>
				<main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
					<jsp:include page="${currentPage}"></jsp:include>
				</main>
			</div>
		</div>
		<footer class="footer">
			<jsp:include page="fragment/footer.jsp"></jsp:include>
		</footer>
		<script src="/WebShop/static/js/jquery.js"></script>
		<script src="/WebShop/static/js/app.js"></script>
		<script src="/WebShop/static/js/bootstrap.js"></script>
	</body>
</html>