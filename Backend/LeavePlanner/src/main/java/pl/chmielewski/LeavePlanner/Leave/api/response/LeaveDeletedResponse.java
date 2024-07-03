package pl.chmielewski.LeavePlanner.Leave.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class LeaveDeletedResponse extends AbstractApiResponse {
    public LeaveDeletedResponse(Long id) {
        super("Leave has been deleted with id: " + id, 204);
    }
}
