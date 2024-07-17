package pl.chmielewski.LeavePlanner.Leave.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.ApiResponse;

public class LeaveAcceptedResponse extends ApiResponse {
    public LeaveAcceptedResponse() {
        super("Leave has been accepted", 200);
    }
}
