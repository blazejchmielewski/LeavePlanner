package pl.chmielewski.LeavePlanner.Authentication.api.request;

public record ChangePasswordRequest(
        String password,
        String uid
) {
}
