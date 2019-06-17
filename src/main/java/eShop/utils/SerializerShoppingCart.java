package eShop.utils;

import eShop.exception.ValidationException;
import eShop.form.ProductForm;
import eShop.model.ShoppingCart;
import eShop.model.ShoppingCartItem;
import eShop.service.OrderService;

public class SerializerShoppingCart {

	
	
	public static String shoppingCartToString(ShoppingCart shoppingCart) {
		StringBuilder sb = new StringBuilder();

		for (ShoppingCartItem item : shoppingCart.getItems()) {
			sb.append(item.getProduct().getId() + "&" + item.getCount() + "/");
		}

		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

	public static ShoppingCart shoppingCartFromString(String cookieValue, OrderService orderService) throws ValidationException {
		String[] arr = cookieValue.split("/");
		ShoppingCart cart = new ShoppingCart();

		for (String str : arr) {
			String[] product = str.split("&");
			int idProduct = Integer.parseInt(product[0]);
			int count = Integer.parseInt(product[1]);

			orderService.addProductToCart(new ProductForm(idProduct, count), cart);
		}
		return cart;
	}

}
