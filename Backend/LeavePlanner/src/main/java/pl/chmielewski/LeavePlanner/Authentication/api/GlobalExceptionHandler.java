package pl.chmielewski.LeavePlanner.Authentication.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.*;
import pl.chmielewski.LeavePlanner.Leave.api.exception.LeaveNotFoundByIdException;
import pl.chmielewski.LeavePlanner.Leave.api.exception.LeaveNotFoundByUuidException;

@ControllerAdvice
public class GlobalExceptionHandler {


    // <----- User ----->

    @ResponseBody
    @ExceptionHandler(UserNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AbstractApiResponse userNotFoundByIdHandler(UserNotFoundByIdException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundByEmailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse userNotFoundByEmailHandler(UserNotFoundByEmailException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundByUuidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse userNotFoundByUuidHandler(UserNotFoundByUuidException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundByTokenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse userNotFoundByTokenHandler(UserNotFoundByTokenException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    // <----- Token ----->

    @ResponseBody
    @ExceptionHandler(TokenNotFoundByUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse tokenNotFoundByUserHandler(TokenNotFoundByUserException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(TokenNotFoundByCookieException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse tokenNotFoundByCookieHandler(TokenNotFoundByCookieException ex) {
        return new ApiResponse(ex.getMessage(), 401);
    }

    @ResponseBody
    @ExceptionHandler(UserExistsByEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse userExistsWithEmailHandler(UserExistsByEmailException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
    }

    @ResponseBody
    @ExceptionHandler(TokenNotFoundByTokenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse tokenNotFoundByTokenHandler(TokenNotFoundByTokenException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    // <----- Leave ----->

    @ResponseBody
    @ExceptionHandler(LeaveNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse leaveNotFoundById(LeaveNotFoundByIdException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(LeaveNotFoundByUuidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse leaveNotFoundByUuid(LeaveNotFoundByUuidException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
