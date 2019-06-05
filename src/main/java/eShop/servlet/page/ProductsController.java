package eShop.servlet.page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;

@WebServlet("/products")
public class ProductsController extends AbstractController {

	private static final long serialVersionUID = 8009345845054851398L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		List<?> products = getBusinessService().getProducts(); // get products from database
//
//		req.setAttribute("products", products);
		RoutingUtils.forwardToPage("products.jsp", req, resp);
	}

}
