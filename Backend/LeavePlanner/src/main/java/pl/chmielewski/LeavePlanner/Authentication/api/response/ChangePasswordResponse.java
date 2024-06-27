package pl.chmielewski.LeavePlanner.Authentication.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class ChangePasswordResponse extends AbstractApiResponse {
    public ChangePasswordResponse() {
        super("Password has been changed", 200);
    }
}
