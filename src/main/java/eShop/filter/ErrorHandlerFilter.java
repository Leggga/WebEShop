package eShop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.json.JSONObject;

import eShop.Constants;
import eShop.exception.AbstractAppException;
import eShop.exception.AccessDeniedExceptioin;
import eShop.exception.InternalServerException;
import eShop.exception.ResourceNotFoundException;
import eShop.exception.ValidationException;
import eShop.utils.RoutingUtils;
import eShop.utils.UrlUtils;

@WebFilter(filterName = "ErrorHandler")
public class ErrorHandlerFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		try {
			chain.doFilter(req, new ThrowExceptionInsteadOfError(resp));
		} catch (Throwable t) {
			String requestURI = req.getRequestURI();

			if (t instanceof ValidationException) {
				LOGGER.warn("Validation exception occured!", t);
			} else {
				LOGGER.error("Requested URI: " + requestURI + " Threw an error!!! ", t);
			}
			handleException(requestURI, t, req, resp);
		}
	}

	private int getStatusCode(Throwable th) {
		if (th instanceof AbstractAppException) {
			return ((AbstractAppException) th).getStatusCode();
		} else {
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
	}

	private void handleException(String url, Throwable th, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int statusCode = getStatusCode(th);
		resp.setStatus(statusCode);

		if (UrlUtils.isAjaxJsonUrl(url)) {
			JSONObject json = new JSONObject();
			json.put("message", th instanceof ValidationException ? th.getMessage() : Constants.INTERNAL_ERROR);
			RoutingUtils.sendJsonObject(json, req, resp);
		} else if (UrlUtils.isAjaxHtmlUrl(url)) {
			RoutingUtils.sendHTMLFragment(Constants.INTERNAL_ERROR, req, resp);
		} else {
			req.setAttribute("statusCode", statusCode);
			RoutingUtils.forwardToPage("error.jsp", req, resp);
		}
	}

	private static class ThrowExceptionInsteadOfError extends HttpServletResponseWrapper {
		public ThrowExceptionInsteadOfError(HttpServletResponse response) {
			super(response);
		}

		@Override
		public void sendError(int sc) throws IOException {
			super.sendError(sc, Constants.INTERNAL_ERROR);
		}

		@Override
		public void sendError(int sc, String msg) throws IOException {
			switch (sc) {
				case 404: {
					throw new ResourceNotFoundException(msg);
				}
				case 403: {
					throw new AccessDeniedExceptioin(msg);
				}
				case 400: {
					throw new ValidationException(msg);
				}
				default:
					throw new InternalServerException(msg);
			}
		}
	}
}
