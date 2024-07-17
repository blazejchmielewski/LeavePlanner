package pl.chmielewski.LeavePlanner.Leave.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.ApiResponse;

public class LeaveRejectedResponse extends ApiResponse {
    public LeaveRejectedResponse() {
        super("Leave has been rejected", 200);
    }
}
