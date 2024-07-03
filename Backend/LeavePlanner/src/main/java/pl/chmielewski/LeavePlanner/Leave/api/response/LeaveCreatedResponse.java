package pl.chmielewski.LeavePlanner.Leave.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class LeaveCreatedResponse extends AbstractApiResponse {
    public LeaveCreatedResponse() {
        super("Leave has been created", 201);
    }
}
