package pl.chmielewski.LeavePlanner.Authentication.api.exception;

public class TokenNotFoundByCookieException extends RuntimeException {
    public TokenNotFoundByCookieException() {
        super("Could not find cookie with token");
    }
}
