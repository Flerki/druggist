package app.exceptions;

import app.domain.model.User;
import app.exceptions.UserWithSuchEmailExists;
import app.exceptions.UserWithSuchLoginExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {


    @ExceptionHandler(UserWithSuchEmailExists.class)
    public ResponseEntity<String> userWithSuchEmailAlreadyRegistered(UserWithSuchEmailExists exception){
        String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(UserWithSuchLoginExists.class)
    public ResponseEntity<String> userWithSuchLoginAlreadyRegistered(UserWithSuchLoginExists exception){
        String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
    @ExceptionHandler(NotAuthenticatedUserException.class)
    public ResponseEntity<String> notAuthenticatedUserException(NotAuthenticatedUserException exception){
        String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> wrongPasswordException(WrongPasswordException exception){
        String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<String> userDoesNotExistException(UserDoesNotExistException exception){
        String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

}
