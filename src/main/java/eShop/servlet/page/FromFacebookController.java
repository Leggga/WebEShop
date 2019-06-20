package eShop.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.model.CurrentAccount;
import eShop.model.SocialAccount;
import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;
import eShop.utils.SessionUtils;

@WebServlet("/from-socialfb")	
public class FromFacebookController extends AbstractController {

	private static final long serialVersionUID = 8265824299597997532L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		
		if (code != null) {
			SocialAccount account = getSocialService().getSoccialAccount(code);
			CurrentAccount currentAccount = getOrderService().authentificate(account);
			SessionUtils.setCurrentAccount(req, currentAccount);
			RoutingUtils.redirectToRefererPage(req, resp);
		} else {
			LOGGER.warn("Authorize token not found!!");
			RoutingUtils.redirect("/WebShop/sign-in", resp);
		}
	}
	
}
