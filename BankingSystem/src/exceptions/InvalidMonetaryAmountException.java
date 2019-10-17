package exceptions;


public class InvalidMonetaryAmountException extends ArithmeticException{

	private static final long serialVersionUID = -3444952704630066025L;

	public InvalidMonetaryAmountException(String message) {
        super(message);
    }
}
