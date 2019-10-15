package exceptions;

public class InvalidMonetaryAmountException extends ArithmeticException{

  public InvalidMonetaryAmountException(String message) {
        super(message);
    }
}
