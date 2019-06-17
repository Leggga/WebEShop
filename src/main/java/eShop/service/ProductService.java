package eShop.service;

import java.util.List;

import eShop.entity.Category;
import eShop.entity.Producer;
import eShop.entity.Product;
import eShop.form.SearchForm;

public interface ProductService {

	List<Product> allProductsList(int page, int limit);
	
	List<Product> productsByCategoryList(String categoryUrl, int page, int limit);

	List<Category> allCategoriesList();

	List<Producer> allProducersList();

	int countAllProducts();
	
	int countProductsByCategory(String category);
	
	List<Product> productsBySearch(SearchForm searchForm, int page, int limit);
	
	int countProductsBySearchForm(SearchForm searchForm);
}
