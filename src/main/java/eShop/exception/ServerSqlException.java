package eShop.exception;

public class ServerSqlException extends RuntimeException {

	private static final long serialVersionUID = -4651622406254526283L;

	public ServerSqlException() {
		super();
	}

	public ServerSqlException(String message) {
		super(message);
	}

	public ServerSqlException(Throwable th) {
		super(th);
	}

	public ServerSqlException(String message, Throwable th) {
		super(message, th);
	}
}
