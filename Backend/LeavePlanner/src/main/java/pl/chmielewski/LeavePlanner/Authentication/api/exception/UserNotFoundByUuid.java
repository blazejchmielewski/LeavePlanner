package pl.chmielewski.LeavePlanner.Authentication.api.exception;

public class UserNotFoundByUuid extends RuntimeException {
    public UserNotFoundByUuid(String uuid) {
        super("Could not find user with uuid: " + uuid);
    }
}
