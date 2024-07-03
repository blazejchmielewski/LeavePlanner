package pl.chmielewski.LeavePlanner.Authentication.api.request;

public record LoginUserDTO (
        String email,
        String password
) {
}
