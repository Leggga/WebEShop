package eShop.exception;

import javax.servlet.http.HttpServletResponse;

public class ResourceNotFoundException extends AbstractAppException {

	private static final long serialVersionUID = 6990116883918454068L;

	public ResourceNotFoundException() {
		super(HttpServletResponse.SC_NOT_FOUND);
	}

	public ResourceNotFoundException(String s) {
		super(s, HttpServletResponse.SC_NOT_FOUND);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause, HttpServletResponse.SC_NOT_FOUND);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause, HttpServletResponse.SC_NOT_FOUND);
	}



}
