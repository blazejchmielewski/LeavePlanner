package pl.chmielewski.LeavePlanner.Authentication.api.exception;

public class UserNotFoundByIdException extends RuntimeException{

    public UserNotFoundByIdException(Long id) {
        super("User not found with id: " + id);
    }
}
