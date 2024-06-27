package pl.chmielewski.LeavePlanner.Authentication.api.response;

import java.time.LocalDate;

public record UserIsLoggedInDTO(
        boolean message,
        int code,
        LocalDate timestamp
) {
}
