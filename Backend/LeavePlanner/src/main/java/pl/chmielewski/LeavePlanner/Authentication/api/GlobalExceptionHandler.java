package pl.chmielewski.LeavePlanner.Authentication.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse UserNotFoundHandler(UserNotFoundException ex){
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
    }
}
