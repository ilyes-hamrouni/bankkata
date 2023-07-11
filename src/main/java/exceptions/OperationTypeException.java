package exceptions;

public class OperationTypeException extends RuntimeException{
    public OperationTypeException(final String message){
        super(message);
    }
}
