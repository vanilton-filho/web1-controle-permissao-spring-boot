package web.domain.exception;

public class DataInUseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataInUseException(String message) {
		super(message);
	}
}
