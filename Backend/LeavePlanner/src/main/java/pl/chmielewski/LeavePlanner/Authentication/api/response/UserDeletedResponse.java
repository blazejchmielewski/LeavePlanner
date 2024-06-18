package pl.chmielewski.LeavePlanner.Authentication.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractResponse;

public class UserDeletedResponse extends AbstractResponse {

    public UserDeletedResponse(Long id) {
        super("User deleted with id: " + id, 201);
    }
}
