package pl.chmielewski.LeavePlanner.Leave.api.exception;

public class LeaveNotFoundByUuidException extends RuntimeException{

    public LeaveNotFoundByUuidException(String uuid) {
        super("Could not find leave with uuid: " + uuid);
    }
}
