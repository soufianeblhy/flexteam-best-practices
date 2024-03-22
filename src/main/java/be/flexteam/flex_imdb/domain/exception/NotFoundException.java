package be.flexteam.flex_imdb.domain.exception;

/**
 * Exception to prevent duplicate entity creation
 */
public class NotFoundException extends GenericDomainException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

}