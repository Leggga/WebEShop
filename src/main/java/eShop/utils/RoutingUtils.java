package eShop.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import eShop.Constants;

public final class RoutingUtils {
	
	public static void forwardToFragment(String fragment, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/JSP/fragment/" + fragment).forward(req, resp);
	}

	public static void forwardToPage(String jspPage, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("currentPage", "page/" + jspPage);
		req.getRequestDispatcher("/WEB-INF/JSP/page-template.jsp").forward(req, resp);
	}

	public static void sendHTMLFragment(String text, HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println(text);
		resp.getWriter().close();
	}

	public static void sendJsonObject(JSONObject json, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("application/json");
		resp.getWriter().print(json.toString());
		resp.getWriter().close();
	}
	
	public static void redirect(String url, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(url);
	}
	
	public static void redirectToRefererPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String url = req.getSession().getAttribute(Constants.REFERER_PAGE) == null ? "/WebShop/products" : (String)req.getSession().getAttribute(Constants.REFERER_PAGE);
		resp.sendRedirect(url);
	}
}