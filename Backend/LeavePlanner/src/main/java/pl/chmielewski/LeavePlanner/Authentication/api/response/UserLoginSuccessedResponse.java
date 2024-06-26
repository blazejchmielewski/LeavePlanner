package pl.chmielewski.LeavePlanner.Authentication.api.response;

public record UserLoginSuccessedResponse (

        String email,
        String role,
        String token
){
}
