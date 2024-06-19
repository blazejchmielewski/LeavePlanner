package pl.chmielewski.LeavePlanner.Authentication.api.exception;

import pl.chmielewski.LeavePlanner.Authentication.user.User;

public class TokenNotFoundByUserException extends RuntimeException{
    public TokenNotFoundByUserException(User user) {
        super("Token was not found for user with id: " + user.getId());
    }
}
