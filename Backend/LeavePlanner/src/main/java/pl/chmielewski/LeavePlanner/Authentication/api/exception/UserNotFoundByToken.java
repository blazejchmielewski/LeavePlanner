package pl.chmielewski.LeavePlanner.Authentication.api.exception;

public class UserNotFoundByToken extends RuntimeException{
    public UserNotFoundByToken(String token) {
        super("User was not found with token: " + token);
    }
}
