package eShop.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;

@WebServlet("/search")
public class SearchController extends AbstractController{

	private static final long serialVersionUID = -569664003260569583L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("countProductFound", 12);
		RoutingUtils.forwardToPage("search-result.jsp", req, resp);
	}
	
}
