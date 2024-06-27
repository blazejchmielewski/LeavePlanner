package pl.chmielewski.LeavePlanner.Authentication.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class ResetPasswordEmailResponse extends AbstractApiResponse {
    public ResetPasswordEmailResponse() {
        super("Email to reset-password has been sent", 200);
    }
}
