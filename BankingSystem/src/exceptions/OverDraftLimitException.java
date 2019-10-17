package exceptions;


public class OverDraftLimitException extends Exception {

	private static final long serialVersionUID = -7884455250678189770L;

	public OverDraftLimitException(String message) {
        super(message);
    }
}
