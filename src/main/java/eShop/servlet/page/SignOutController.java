package eShop.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;
import eShop.utils.SessionUtils;

@WebServlet("/sign-out")
public class SignOutController extends AbstractController {

	private static final long serialVersionUID = -975953455203080295L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (SessionUtils.isCurrentAccount(req)) {
			req.getSession().invalidate();
			RoutingUtils.redirect("/WebShop/products", resp);
		} else {
			// TODO exception or modal window
			RoutingUtils.redirect("/WebShop/products", resp);
		}
	}
}
