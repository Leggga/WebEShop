package eShop.exception;

import javax.servlet.http.HttpServletResponse;

public class ValidationException extends AbstractAppException {

	private static final long serialVersionUID = 8171550525020154678L;

	public ValidationException() {
		super(HttpServletResponse.SC_BAD_REQUEST);
	}

	public ValidationException(String s) {
		super(s, HttpServletResponse.SC_BAD_REQUEST);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause, HttpServletResponse.SC_BAD_REQUEST);
	}

	public ValidationException(Throwable cause) {
		super(cause, HttpServletResponse.SC_BAD_REQUEST);
	}

}
