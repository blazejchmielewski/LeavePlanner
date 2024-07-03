package pl.chmielewski.LeavePlanner.Authentication.api.response;

public record UserToTableResponse(
        String firstname,
        String lastname,
        String email,
        String department
) {
}
