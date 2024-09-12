package pl.chmielewski.LeavePlanner.Leave.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;

public class DayOffAddedResponse extends AbstractApiResponse {
    public DayOffAddedResponse() {
        super("DayOff has been added", 201);
    }
}
