package eShop.exception;

import javax.servlet.http.HttpServletResponse;

public class AccessDeniedExceptioin extends AbstractAppException {

	private static final long serialVersionUID = -2412629814438691979L;

	public AccessDeniedExceptioin() {
		super(HttpServletResponse.SC_FORBIDDEN);
	}

	public AccessDeniedExceptioin(String s) {
		super(s, HttpServletResponse.SC_FORBIDDEN);
	}

	public AccessDeniedExceptioin(String message, Throwable cause) {
		super(message, cause, HttpServletResponse.SC_FORBIDDEN);
	}

	public AccessDeniedExceptioin(Throwable cause) {
		super(cause, HttpServletResponse.SC_FORBIDDEN);
	}

}
