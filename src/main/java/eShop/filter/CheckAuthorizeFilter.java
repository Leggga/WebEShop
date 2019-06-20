package eShop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.utils.RoutingUtils;
import eShop.utils.SessionUtils;
import eShop.utils.UrlUtils;

@WebFilter(filterName = "checkAuth")
public class CheckAuthorizeFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		if (SessionUtils.isCurrentAccount(req)) {
			chain.doFilter(req, resp);
		} else {
			String requestUrl = req.getRequestURI();
			if (UrlUtils.isAjaxUrl(requestUrl)) {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				RoutingUtils.sendHTMLFragment("401", req, resp);
			} else {
				RoutingUtils.forwardToPage("sign-in.jsp", req, resp);
			}
		}
	}

}
