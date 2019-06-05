package eShop.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import eShop.service.OrderService;
import eShop.service.ProductService;
import eShop.service.impl.ServiceManager;

public abstract class AbstractController extends HttpServlet {

	private static final long serialVersionUID = -1633085722032900758L;

	private ProductService productService;
	private OrderService orderService;

	@Override
	public final void init() throws ServletException {
		productService = ServiceManager.getInstance(getServletContext()).getProductService();
		orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
	}

	public final OrderService getOrderService() {
		return orderService;
	}

	public final ProductService getProductService() {
		return productService;
	}
}
