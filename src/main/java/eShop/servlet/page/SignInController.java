package eShop.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;
import eShop.utils.SessionUtils;

@WebServlet("/sign-in")
public class SignInController extends AbstractController {

	private static final long serialVersionUID = 7022064550505565099L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (SessionUtils.isCurrentAccount(req)) {
			RoutingUtils.redirectToRefererPage(req, resp);
		} else {
			RoutingUtils.forwardToPage("sign-in.jsp", req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (SessionUtils.isCurrentAccount(req)) {
			RoutingUtils.redirectToRefererPage(req, resp);
		} else {
			RoutingUtils.redirect(getSocialService().getAuthorizeUrl(), resp);
		}
	}

}
