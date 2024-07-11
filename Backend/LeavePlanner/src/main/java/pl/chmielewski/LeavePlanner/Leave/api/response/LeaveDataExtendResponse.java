package pl.chmielewski.LeavePlanner.Leave.api.response;

import java.time.LocalDateTime;

public record LeaveDataExtendResponse(
        String leaveUuid,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String type,
        String declaringUser,
        String replacementUser,
        String status,
        LocalDateTime creationDate,
        LocalDateTime lastUpdateDate
) {
}
