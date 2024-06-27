package pl.chmielewski.LeavePlanner.Authentication.api.response;

public record UserLoginSuccessedDTO(
        String email,
        String role,
        String token
){
}
