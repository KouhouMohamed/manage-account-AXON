package ma.enset.cqrsaxon.commonapi.exceptions;

public class NegativeBalanceException extends RuntimeException{
    private String message;

    public NegativeBalanceException(String message) {
        super(message);
        this.message = message;
    }
}
