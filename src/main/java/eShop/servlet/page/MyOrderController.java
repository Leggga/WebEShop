package eShop.servlet.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.Constants;
import eShop.entity.Order;
import eShop.model.CurrentAccount;
import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;
import eShop.utils.SessionUtils;

@WebServlet("/my-orders")
public class MyOrderController extends AbstractController {

	private static final long serialVersionUID = -3684129632869918197L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(SessionUtils.isCurrentAccount(req)) {
			CurrentAccount account = SessionUtils.getCurrentAccount(req);
			List<Order> orders = getOrderService().listMyOrders(account, 1, Constants.MAX_ORDERS_PER_PAGE);
			req.setAttribute("orders", orders);
			
			int total = getOrderService().countMyOrders(account);
			req.setAttribute("total", total);
			req.setAttribute("pages", getPageCount(total, Constants.MAX_ORDERS_PER_PAGE));
			
			RoutingUtils.forwardToPage("my-orders.jsp", req, resp);
		}else {
			//TODO redirect to login page
		}
	}

}
