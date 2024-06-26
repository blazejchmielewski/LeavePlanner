package pl.chmielewski.LeavePlanner.Authentication.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class UserLogoutResponse extends AbstractApiResponse {
    public UserLogoutResponse() {
        super("Wylogowano poprawnie", 200);
    }
}
