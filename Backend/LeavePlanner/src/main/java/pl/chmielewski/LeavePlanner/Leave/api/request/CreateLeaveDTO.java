package pl.chmielewski.LeavePlanner.Leave.api.request;

import pl.chmielewski.LeavePlanner.Leave.leave.LeaveType;

import java.time.LocalDateTime;

public record CreateLeaveDTO(
        LocalDateTime startDate,
        LocalDateTime endDate,
        LeaveType type,
        String userUuid
) {
}
