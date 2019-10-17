package exceptions;


public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = -5026953989613636855L;

	public InsufficientFundsException(String message) {
        super(message);
    }
}
