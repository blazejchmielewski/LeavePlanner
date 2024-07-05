package pl.chmielewski.LeavePlanner.Authentication.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class UserDisabledResponse extends AbstractApiResponse {
    public UserDisabledResponse(Long id) {
        super("User was disabled with id: " + id, 200);
    }
}
