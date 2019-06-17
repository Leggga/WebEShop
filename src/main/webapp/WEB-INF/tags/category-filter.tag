<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="categories" required="true" type="java.util.Collection"%>
<%@ attribute name="searchForm" required="true" type="eShop.form.SearchForm"%>

<div class="panel-heading">Category filters</div>
<div class="panel-body producers">
	<label><input type="checkbox" id="allCategories">All</label>
	<c:forEach var="category" items="${categories}">
		<div class="form-group">
			<div class="checkbox">
				<label><input type="checkbox" name="category" value="${category.id }" ${searchForm.categories.contains(category.id)? 'checked' : '' } class="search-option">
					${category.name } (${category.productCount })
				</label>
			</div>
		</div>
	</c:forEach>
</div>