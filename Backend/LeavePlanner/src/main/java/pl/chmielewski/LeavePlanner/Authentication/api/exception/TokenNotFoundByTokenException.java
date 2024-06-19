package pl.chmielewski.LeavePlanner.Authentication.api.exception;

public class TokenNotFoundByTokenException extends RuntimeException {
    public TokenNotFoundByTokenException(String token) {
        super("Token was not found with token: " + token);
    }
}
