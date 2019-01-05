package app.exceptions;

public class UserWithSuchEmailExists extends RuntimeException {

    private static final String PATTERN = "User with email = %s is already registered.";

    public UserWithSuchEmailExists(String email) {
        super(String.format(PATTERN, email));
    }
}
