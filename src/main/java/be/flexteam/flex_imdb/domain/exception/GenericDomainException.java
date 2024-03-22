package be.flexteam.flex_imdb.domain.exception;

public class GenericDomainException extends Exception {

    public GenericDomainException(String message) {
        super(message);
    }

    public GenericDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericDomainException(Throwable cause) {
        super(cause);
    }
}