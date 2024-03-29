package eShop.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;
import eShop.utils.SessionUtils;
@WebServlet("/shopping-cart")
public class ShowShoppingCartController extends AbstractController{

	private static final long serialVersionUID = 2395910652232194050L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (SessionUtils.isCurrentShoppingCart(req)) {
			RoutingUtils.forwardToPage("shopping-cart.jsp", req, resp);			
		} else {
			RoutingUtils.redirect("/WebShop/products", resp);
		}
	}
	
}
