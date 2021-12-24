package ma.enset.cqrsaxon.commonapi.exceptions;

public class InsufficientAmountException extends RuntimeException{
    private String message;

    public InsufficientAmountException(String message) {
        super(message);
        this.message = message;
    }
}
