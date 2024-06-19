package pl.chmielewski.LeavePlanner.Authentication.api;

import java.time.LocalDateTime;

public class ApiResponse extends AbstractApiResponse {
    private String message;
    private Integer code;
    private LocalDateTime timestamp;

    public ApiResponse(String message, Integer code) {
        super(message, code);
    }
}
