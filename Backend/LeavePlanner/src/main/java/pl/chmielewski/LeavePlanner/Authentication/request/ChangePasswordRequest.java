package pl.chmielewski.LeavePlanner.Authentication.request;

public record ChangePasswordRequest(
        String password,
        String uid
) {
}
