package pl.chmielewski.LeavePlanner.Authentication.api;

import java.time.LocalDateTime;

public record ApiResponse (
        String message,
        Integer code,
        LocalDateTime timestamp
){
}
