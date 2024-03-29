package eShop.servlet.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.Constants;
import eShop.entity.Product;
import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;

@WebServlet("/ajax/html/more/products/*")
public class ProductsByCategoryAjaxController extends AbstractController{

	private static final long serialVersionUID = 1661066390760236748L;
	private static final int SUBSTRING_INDEX = "/ajax/html/more/products".length();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String category = req.getRequestURI().substring(SUBSTRING_INDEX);
		List<Product> products = getProductService().productsByCategoryList(category, getPage(req), Constants.MAX_PRODUCTS_PER_PAGE);
		req.setAttribute("products", products);
		RoutingUtils.forwardToFragment("product-list.jsp", req, resp);
	}
}
