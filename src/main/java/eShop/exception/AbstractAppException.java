package eShop.exception;

public class AbstractAppException extends IllegalArgumentException {

	private static final long serialVersionUID = -4023273103771629432L;

	private final int statusCode;

	public AbstractAppException(int statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public AbstractAppException(String s, int statusCode) {
		super(s);
		this.statusCode = statusCode;
	}

	public AbstractAppException(Throwable cause, int statusCode) {
		super(cause);
		this.statusCode = statusCode;
	}

	public AbstractAppException(String message, Throwable cause, int statusCode) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
