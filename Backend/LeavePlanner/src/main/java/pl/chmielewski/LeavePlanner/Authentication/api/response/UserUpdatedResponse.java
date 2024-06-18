package pl.chmielewski.LeavePlanner.Authentication.api.response;


import pl.chmielewski.LeavePlanner.Authentication.api.AbstractResponse;

public class UserUpdatedResponse extends AbstractResponse {
    public UserUpdatedResponse(Long id) {
        super("User was updated with id: " + id, 201);
    }
}
