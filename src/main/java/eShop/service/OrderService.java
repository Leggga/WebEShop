package eShop.service;

import eShop.form.ProductForm;
import eShop.model.ShoppingCart;

public interface OrderService {
	
	void addProductToCart(ProductForm productForm, ShoppingCart cart);
	
	void removeProductFromCart(ProductForm productForm, ShoppingCart cart);
}
