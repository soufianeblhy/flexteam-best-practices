package be.flexteam.flex_imdb.domain.exception;

public class UnexceptedDomainException extends RuntimeException {

    public UnexceptedDomainException(String message) {
        super(message);
    }

    public UnexceptedDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexceptedDomainException(Throwable cause) {
        super(cause);
    }
}