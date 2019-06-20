package eShop.servlet.ajax;

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

@WebServlet("/ajax/html/more/my-orders")
public class MyOrdersAjaxController extends AbstractController {
	
	private static final long serialVersionUID = -5282598211017794750L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CurrentAccount account = SessionUtils.getCurrentAccount(req);
		if (account != null) {
			List<Order> orders  = getOrderService().listMyOrders(account, getPage(req), Constants.MAX_ORDERS_PER_PAGE);
			req.setAttribute("orders", orders);
			RoutingUtils.forwardToFragment("order-list.jsp", req, resp);
		}
		//TODO exception if not authorize user
	}
}
