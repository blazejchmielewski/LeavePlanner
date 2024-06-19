package pl.chmielewski.LeavePlanner.Authentication.request;

public record RegisterUserDTO(
        String firstname,
        String lastname,
        String email,
        String password
) {
}
