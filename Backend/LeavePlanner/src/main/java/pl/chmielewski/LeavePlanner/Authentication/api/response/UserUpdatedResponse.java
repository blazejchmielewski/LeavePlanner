package pl.chmielewski.LeavePlanner.Authentication.api.response;


import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class UserUpdatedResponse extends AbstractApiResponse {
    public UserUpdatedResponse(Long id) {
        super("User was updated with id: " + id, 201);
    }
}
