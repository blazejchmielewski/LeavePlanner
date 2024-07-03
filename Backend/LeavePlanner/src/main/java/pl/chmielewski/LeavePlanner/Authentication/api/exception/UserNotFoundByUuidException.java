package pl.chmielewski.LeavePlanner.Authentication.api.exception;

public class UserNotFoundByUuidException extends RuntimeException {
    public UserNotFoundByUuidException(String uuid) {
        super("Could not find user with uuid: " + uuid);
    }
}
