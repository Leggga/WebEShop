package eShop.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "TrimHtml")
public class RemoveCharactersFromHtmlFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		RemoveCharacterWrapper responseWrapper = new RemoveCharacterWrapper(response);
		chain.doFilter(request, responseWrapper);

		out.write(responseWrapper.toString());
		out.flush();
		out.close();
	}

}
