package pl.chmielewski.LeavePlanner.Authentication.api.response;

public record UserDataResponse(
        Long id,
        String uuid,
        String firstname,
        String lastname,
        String email,
        Integer yearsOfWork,
        Integer availableDays,
        String department,
        String role
) {
}
