package eShop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.Constants;

@WebFilter(filterName = "AutoReturnPage")
public class AutoReturnPageAfterSignInFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		String referer_page = req.getHeader("Referer");
		req.getSession().setAttribute(Constants.REFERER_PAGE, referer_page);
		System.out.println("test: " + referer_page);
		chain.doFilter(req, resp);
	}

}
