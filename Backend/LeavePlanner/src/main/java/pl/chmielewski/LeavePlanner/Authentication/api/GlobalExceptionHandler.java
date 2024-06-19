package pl.chmielewski.LeavePlanner.Authentication.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.TokenNotFoundByUserException;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundByEmailException;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundByIdException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AbstractResponse userNotFoundByIdHandler(UserNotFoundByIdException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundByEmailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse userNotFoundByEmailHandler(UserNotFoundByEmailException ex){
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(TokenNotFoundByUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse tokenNotFoundByUserHandler(TokenNotFoundByUserException ex){
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

}
