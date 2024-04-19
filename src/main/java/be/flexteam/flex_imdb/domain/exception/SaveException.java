package be.flexteam.flex_imdb.domain.exception;

/**
 * Exception to prevent duplicate entity creation
 */
public class SaveException extends UnexceptedDomainException {

    public SaveException(String message) {
        super(message);
    }

    public SaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveException(Throwable cause) {
        super(cause);
    }
}