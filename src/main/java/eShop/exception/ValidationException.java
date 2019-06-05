package eShop.exception;

public class ValidationException extends IllegalArgumentException {

	private static final long serialVersionUID = 8171550525020154678L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message) {
		super(message);
	}
}
