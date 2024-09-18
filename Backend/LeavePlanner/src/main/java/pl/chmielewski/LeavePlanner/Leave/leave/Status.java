package pl.chmielewski.LeavePlanner.Leave.leave;

public enum Status {
    PENDING,
    APPROVED_BY_REPLACER,
    APPROVED_BY_ACCEPTOR,
    REJECTED_BY_REPLACER,
    REJECTED_BY_ACCEPTOR,
    CANCELLED,
    IN_PROGRESS,
    COMPLETED,
    CALCULATED
}
