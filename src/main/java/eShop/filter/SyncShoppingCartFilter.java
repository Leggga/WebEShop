package eShop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.exception.ValidationException;
import eShop.model.ShoppingCart;
import eShop.service.OrderService;
import eShop.service.impl.ServiceManager;
import eShop.utils.SessionUtils;

@WebFilter(filterName = "SyncCart")
public class SyncShoppingCartFilter extends AbstractFilter {

	private final String SHOPPING_CART_SERIALIZATION_DONE = "SHOPPING_CART_SERIALIZATION_DONE";
	private OrderService orderService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		orderService = ServiceManager.getInstance(filterConfig.getServletContext()).getOrderService();
	}
	
	
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		if (req.getSession().getAttribute(SHOPPING_CART_SERIALIZATION_DONE) == null) {
			if (!SessionUtils.isCurrentShoppingCart(req)) {
				Cookie cookie = SessionUtils.findShoppingCart(req);
				if (cookie != null) {
					String strCart = cookie.getValue();
					ShoppingCart cart = null;

					try {
						cart = orderService.shoppingCartFromString(strCart);
					} catch (ValidationException e) {
						LOGGER.error("Validation exception, new shopping cart will return!", e);
						cart = new ShoppingCart();
					}
					SessionUtils.setCurrentShoppingCart(req, cart);
				}
			}
			req.getSession().setAttribute(SHOPPING_CART_SERIALIZATION_DONE, Boolean.TRUE);
		}

		chain.doFilter(req, resp);
	}

}
