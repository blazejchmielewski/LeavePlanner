package pl.chmielewski.LeavePlanner.Authentication.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class UserCreatedResponse extends AbstractApiResponse {

    public UserCreatedResponse() {
        super("User created", 201);
    }
}
