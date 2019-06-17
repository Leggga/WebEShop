package eShop.servlet.page;

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

@WebServlet("/products")
public class ProductsController extends AbstractController {

	private static final long serialVersionUID = 8009345845054851398L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> productsList = getProductService().allProductsList(1, Constants.MAX_PRODUCTS_PER_PAGE);
		req.setAttribute("products", productsList);
		
		int total = getProductService().countAllProducts();
		req.setAttribute("pages", getPageCount(total, Constants.MAX_PRODUCTS_PER_PAGE));
		
		RoutingUtils.forwardToPage("products.jsp", req, resp);
	}

}
