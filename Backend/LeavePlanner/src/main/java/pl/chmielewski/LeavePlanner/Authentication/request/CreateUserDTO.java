package pl.chmielewski.LeavePlanner.Authentication.request;

public record CreateUserDTO(
        String firstname,
        String lastname,
        String email,
        String password
) {
}
