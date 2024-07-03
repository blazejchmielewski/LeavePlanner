package pl.chmielewski.LeavePlanner.Leave.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class LeaveUpdatedResponse extends AbstractApiResponse {
    public LeaveUpdatedResponse() {
        super("Leave has been updated", 200);
    }
}
