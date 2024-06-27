package pl.chmielewski.LeavePlanner.Authentication.api.response;

public record UserRegisterSuccessedDTO(
        String token,
        String email,
        String uuid
) {
}
