package pl.chmielewski.LeavePlanner.Authentication.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class UserDeletedResponse extends AbstractApiResponse {

    public UserDeletedResponse(Long id) {
        super("User deleted with id: " + id, 201);
    }
}
