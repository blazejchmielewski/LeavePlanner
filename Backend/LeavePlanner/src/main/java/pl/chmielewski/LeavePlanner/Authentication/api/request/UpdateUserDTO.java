package pl.chmielewski.LeavePlanner.Authentication.api.request;

public record UpdateUserDTO(
        String firstname,
        String lastname,
        String email
) {
}
