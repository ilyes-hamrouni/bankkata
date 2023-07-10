package Exceptions;

public class BalanceNotSufficientException extends RuntimeException {
    public  BalanceNotSufficientException(final String message) {
        super(message);
    }
}
