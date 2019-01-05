package app.exceptions;

public class UserWithSuchLoginExists extends RuntimeException {


    private static final String PATTERN = "User with login = %s is already registered.";


    public UserWithSuchLoginExists(String login) {
        super(String.format(PATTERN, login));
    }
}
