package app.exceptions;

public class WrongPasswordException extends RuntimeException {
    private static final String PATTERN = "Wrong password";


    public WrongPasswordException() {
        super(String.format(PATTERN));
    }
}
