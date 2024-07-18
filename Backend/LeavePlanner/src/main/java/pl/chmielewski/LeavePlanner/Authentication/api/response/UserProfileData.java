package pl.chmielewski.LeavePlanner.Authentication.api.response;

import java.time.LocalDateTime;

public record UserProfileData(
        String name,
        String email,
        String role,
        String department,
        boolean status,
        LocalDateTime createdAt,
        LocalDateTime deactivatedAt
) {}
