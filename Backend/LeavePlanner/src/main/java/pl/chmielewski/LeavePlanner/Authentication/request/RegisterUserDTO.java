package pl.chmielewski.LeavePlanner.Authentication.request;

import pl.chmielewski.LeavePlanner.Authentication.user.Role;

public record RegisterUserDTO(
        String firstname,
        String lastname,
        String email,
        String password
) {
}
