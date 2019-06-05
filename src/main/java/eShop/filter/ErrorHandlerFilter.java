package eShop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.utils.RoutingUtils;

@WebFilter("/*")
public class ErrorHandlerFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		try {
			chain.doFilter(req, resp);
		} catch (Throwable t) {
			String requestURI = req.getRequestURI();
			LOGGER.error("Requested URI: " + requestURI + " Not Found!!!");
			RoutingUtils.forwardToPage("error.jsp", req, resp);
		}
	}

}
