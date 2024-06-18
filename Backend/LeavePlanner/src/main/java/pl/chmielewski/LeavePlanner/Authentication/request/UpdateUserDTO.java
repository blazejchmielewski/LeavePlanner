package pl.chmielewski.LeavePlanner.Authentication.request;

public record UpdateUserDTO(
        String firstname,
        String lastname,
        String email
) {
}
