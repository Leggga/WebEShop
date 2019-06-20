package eShop.service;

import java.util.List;

import eShop.entity.Order;
import eShop.form.ProductForm;
import eShop.model.CurrentAccount;
import eShop.model.ShoppingCart;
import eShop.model.SocialAccount;

public interface OrderService {
	
	void addProductToCart(ProductForm productForm, ShoppingCart cart);
	
	void removeProductFromCart(ProductForm productForm, ShoppingCart cart);
	
	String shoppingCartToString(ShoppingCart shoppingCart);
	
	ShoppingCart shoppingCartFromString(String cookieValue);
	
	CurrentAccount authentificate(SocialAccount account);
	
	long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount);
	
	Order getOrderById(long id, CurrentAccount account);
	
	List<Order> listMyOrders(CurrentAccount account, int page, int limit);
	
	int countMyOrders(CurrentAccount account);
}
