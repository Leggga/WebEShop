package eShop.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.Constants;
import eShop.entity.Order;
import eShop.model.CurrentAccount;
import eShop.model.ShoppingCart;
import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;
import eShop.utils.SessionUtils;

@WebServlet("/order")
public class OrderController extends AbstractController {

	private static final long serialVersionUID = 1087884870179130104L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ShoppingCart cart = SessionUtils.getCurrentShoppingCart(req);
		CurrentAccount account = SessionUtils.getCurrentAccount(req);
		
		long idOrder = getOrderService().makeOrder(cart, account);
		SessionUtils.clearCurrentShoppingCart(req, resp);
		req.getSession().setAttribute(Constants.CLIENT_MESSAGE, "Thanks for your purchase. Soon an operator will call you!");
		RoutingUtils.redirect("/WebShop/order?id=" + idOrder, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = (String) req.getSession().getAttribute(Constants.CLIENT_MESSAGE);
		req.getSession().removeAttribute(Constants.CLIENT_MESSAGE);
		req.setAttribute(Constants.CLIENT_MESSAGE, message);
		Order order = getOrderService().getOrderById(Long.parseLong(req.getParameter("id")), SessionUtils.getCurrentAccount(req));
		
		req.setAttribute("order", order);
		RoutingUtils.forwardToPage("order.jsp", req, resp);
	}
}
