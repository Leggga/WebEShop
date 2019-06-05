package eShop.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;

@WebServlet("/products/*")
public class ProductsByCategoryController extends AbstractController{

	private static final long serialVersionUID = -5023930927502827070L;
	private static final int SUBSTRING_INDEX = "/products/".length(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String category = req.getRequestURI().substring(SUBSTRING_INDEX);
		RoutingUtils.forwardToFragment("product.jsp", req, resp);
	}
	
}
