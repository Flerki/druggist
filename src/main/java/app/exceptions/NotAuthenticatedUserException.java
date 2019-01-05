package app.exceptions;

public class NotAuthenticatedUserException extends RuntimeException {
    private static final String PATTERN = "Imposible decoded JWT.";

    public NotAuthenticatedUserException() {
        super(String.format(PATTERN));
    }
}
