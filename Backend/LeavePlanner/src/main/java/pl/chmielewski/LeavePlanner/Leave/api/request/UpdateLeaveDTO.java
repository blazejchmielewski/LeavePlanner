package pl.chmielewski.LeavePlanner.Leave.api.request;

import pl.chmielewski.LeavePlanner.Leave.leave.LeaveType;
import pl.chmielewski.LeavePlanner.Leave.leave.Status;

import java.time.LocalDateTime;

public record UpdateLeaveDTO(
        LocalDateTime startDate,
        LocalDateTime endDate,
        LeaveType type,
        Status status,
        LocalDateTime updatedAt
) {
}
