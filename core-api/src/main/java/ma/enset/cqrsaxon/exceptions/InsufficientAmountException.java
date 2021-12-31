package ma.enset.cqrsaxon.exceptions;

public class InsufficientAmountException extends RuntimeException{
    private String message;

    public InsufficientAmountException(String message) {
        super(message);
        this.message = message;
    }
}
