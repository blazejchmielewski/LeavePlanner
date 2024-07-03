package pl.chmielewski.LeavePlanner.Authentication.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class UserDeletedResponse extends AbstractApiResponse {

    public UserDeletedResponse(Long id) {
        super("User has been deleted with id: " + id, 204);
    }
}
