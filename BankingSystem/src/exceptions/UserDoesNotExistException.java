package exceptions;


public class UserDoesNotExistException extends NullPointerException {

	private static final long serialVersionUID = -6367411307771681928L;

	public UserDoesNotExistException(String message) {
        super(message);
    }
}
