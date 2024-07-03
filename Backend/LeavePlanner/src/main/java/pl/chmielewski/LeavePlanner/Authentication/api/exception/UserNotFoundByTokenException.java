package pl.chmielewski.LeavePlanner.Authentication.api.exception;

public class UserNotFoundByTokenException extends RuntimeException{
    public UserNotFoundByTokenException(String token) {
        super("User was not found with token: " + token);
    }
}
