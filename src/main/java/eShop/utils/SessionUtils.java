package eShop.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.Constants;
import eShop.model.CurrentAccount;
import eShop.model.ShoppingCart;

final public class SessionUtils {
	
	private SessionUtils() {}

	public static ShoppingCart getCurrentShoppingCart(HttpServletRequest req) {
		ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART);
		if (cart == null) {
			cart = new ShoppingCart();
			setCurrentShoppingCart(req, cart);
		}
		return cart;
	}

	public static void setCurrentShoppingCart(HttpServletRequest req, ShoppingCart cart) {
		req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, cart);
	}

	public static void clearCurrentShoppingCart(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute(Constants.CURRENT_SHOPPING_CART);
		WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(), null, 0, resp);
	}

	public static void updateCurrentShoppingCart(String cookieValue, HttpServletResponse resp) {
		WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(), cookieValue,
				Constants.Cookie.SHOPPING_CART.getTtl(), resp);
	}

	public static Cookie findShoppingCart(HttpServletRequest req) {
		return WebUtils.findCookie(req, Constants.Cookie.SHOPPING_CART.getName());
	}

	public static boolean isCurrentShoppingCart(HttpServletRequest req) {
		return req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART) != null;
	}

	public static CurrentAccount getCurrentAccount(HttpServletRequest req) {
		return (CurrentAccount) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
	}
	
	public static void setCurrentAccount(HttpServletRequest req, CurrentAccount account) {
		req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, account);
	}
	
	public static boolean isCurrentAccount(HttpServletRequest req) {
		return req.getSession().getAttribute(Constants.CURRENT_ACCOUNT) != null;
	}
	
}
