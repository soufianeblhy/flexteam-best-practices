package be.flexteam.flex_imdb.domain.exception;

/**
 * Exception to prevent duplicate entity creation
 */
public class DeleteException extends UnexceptedDomainException {

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

}