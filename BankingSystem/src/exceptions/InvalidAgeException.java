package exceptions;


public class InvalidAgeException extends Exception {

	private static final long serialVersionUID = 1107183660205137348L;

	public InvalidAgeException(String message) {
        super(message);
    }
}
