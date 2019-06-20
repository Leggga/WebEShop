<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="alert alert-danger hidden-print" role="alert">
	<h1>Status code: ${statusCode }</h1>
	<c:choose>
		<c:when test="${statusCode == 403}">
			You don't have permissions to view this resource
		</c:when>
		<c:when test="${statusCode == 404}">
			Page not found
		</c:when>
		<c:otherwise>
			Ooops. Something went wrong!!! Try again
		</c:otherwise>
	</c:choose>
</div>