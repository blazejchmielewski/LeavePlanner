package pl.chmielewski.LeavePlanner.Leave.api.exception;

public class LeaveNotFoundByIdException extends RuntimeException {

    public LeaveNotFoundByIdException(Long id) {

        super("Leave not found by id: " + id);
    }
}
