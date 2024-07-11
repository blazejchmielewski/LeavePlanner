package pl.chmielewski.LeavePlanner.Leave.api.response;

import java.time.LocalDateTime;

public record LeaveDataResponse(
        String leaveUuid,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String type,
        String declaringUser,
        String replacementUser
) {
}
