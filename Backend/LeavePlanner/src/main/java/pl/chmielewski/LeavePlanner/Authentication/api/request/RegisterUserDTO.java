package pl.chmielewski.LeavePlanner.Authentication.api.request;

public record RegisterUserDTO(
        String firstname,
        String lastname,
        String email,
        String password,
        String department
) {
}
