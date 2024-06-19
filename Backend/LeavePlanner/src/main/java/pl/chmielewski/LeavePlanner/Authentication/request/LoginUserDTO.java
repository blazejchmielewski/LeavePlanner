package pl.chmielewski.LeavePlanner.Authentication.request;

public record LoginUserDTO (
        String email,
        String password
) {
}
