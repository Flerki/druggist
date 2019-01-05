package app.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    private static final String PATTERN = "User doesn't exist.";

    public UserDoesNotExistException() {
        super(String.format(PATTERN));
    }
}
