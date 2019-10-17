package exceptions;


public class AccountDoesNotExistException extends NullPointerException {

	private static final long serialVersionUID = 2810920101367229995L;

	public AccountDoesNotExistException(String message) {
        super(message);
    }

}
