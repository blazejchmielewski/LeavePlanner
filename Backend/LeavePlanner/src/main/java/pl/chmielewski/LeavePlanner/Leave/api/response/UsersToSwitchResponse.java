package pl.chmielewski.LeavePlanner.Leave.api.response;

public record UsersToSwitchResponse(
        String uuid,
        String firstname,
        String lastname
) {
}
