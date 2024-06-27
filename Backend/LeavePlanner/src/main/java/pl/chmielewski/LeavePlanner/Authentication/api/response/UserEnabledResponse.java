package pl.chmielewski.LeavePlanner.Authentication.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class UserEnabledResponse extends AbstractApiResponse {
    public UserEnabledResponse() {
        super("Twoje konto zostało aktywowane", 200);
    }
}
