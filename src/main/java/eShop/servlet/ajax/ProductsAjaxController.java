package eShop.servlet.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.Constants;
import eShop.entity.Product;
import eShop.form.SearchForm;
import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;

@WebServlet("/ajax/html/more/products")
public class ProductsAjaxController extends AbstractController {

	private static final long serialVersionUID = 4339551731615038616L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SearchForm searchForm = createSearchForm(req);
		List<Product> products = getProductService().productsBySearch(searchForm, getPage(req), Constants.MAX_PRODUCTS_PER_PAGE);
		
		req.setAttribute("products", products);
		RoutingUtils.forwardToFragment("product-list.jsp", req, resp);
	}
}
