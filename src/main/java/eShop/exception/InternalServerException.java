package eShop.exception;

import javax.servlet.http.HttpServletResponse;

public class InternalServerException extends AbstractAppException {

	private static final long serialVersionUID = -9122848721128050625L;
	
	public InternalServerException() {
		super(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public InternalServerException(String s) {
		super(s, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public InternalServerException(String message, Throwable cause) {
		super(message, cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public InternalServerException(Throwable cause) {
		super(cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	
	
}


