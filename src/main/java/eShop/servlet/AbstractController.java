package eShop.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import eShop.form.ProductForm;
import eShop.form.SearchForm;
import eShop.service.OrderService;
import eShop.service.ProductService;
import eShop.service.SocialService;
import eShop.service.impl.ServiceManager;

public abstract class AbstractController extends HttpServlet {

	private static final long serialVersionUID = -1633085722032900758L;
	
	protected final Logger LOGGER = Logger.getLogger(getClass());

	private ProductService productService;
	private OrderService orderService;
	private SocialService socialService;

	@Override
	public final void init() throws ServletException {
		productService = ServiceManager.getInstance(getServletContext()).getProductService();
		orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
		socialService = ServiceManager.getInstance(getServletContext()).getSocialService();
	}

	public final OrderService getOrderService() {
		return orderService;
	}

	public final ProductService getProductService() {
		return productService;
	}
	
	public final SocialService getSocialService() {
		return socialService;
	}
	
	public final int getPageCount(int total, int itemsPerPage) {
		int res = total / itemsPerPage;
		
		if (res * itemsPerPage != total) {
			res++;
		}
		return res;
	}
	
	public final int getPage(HttpServletRequest req) {
		try {
			return Integer.parseInt(req.getParameter("page"));
		}catch (NumberFormatException e) {
			return 1;
		}
	}
	
	public final SearchForm createSearchForm(HttpServletRequest req) {
		return new SearchForm(req.getParameter("query"), req.getParameterValues("category"), req.getParameterValues("producer"));
	}

	public final ProductForm createProductForm(HttpServletRequest req) {
		return new ProductForm(Integer.parseInt(req.getParameter("idProduct")),
								Integer.parseInt(req.getParameter("count")));
	}
}
