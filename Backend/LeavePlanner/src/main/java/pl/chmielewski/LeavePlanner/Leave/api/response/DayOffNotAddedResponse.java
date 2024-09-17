package pl.chmielewski.LeavePlanner.Leave.api.response;

import pl.chmielewski.LeavePlanner.Authentication.api.ApiResponse;

public class DayOffNotAddedResponse extends ApiResponse {
    public DayOffNotAddedResponse() {
        super("Podane świeto już istnieje", 409);
    }
}
