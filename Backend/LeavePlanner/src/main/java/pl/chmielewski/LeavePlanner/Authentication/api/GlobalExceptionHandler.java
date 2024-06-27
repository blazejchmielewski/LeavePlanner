package pl.chmielewski.LeavePlanner.Authentication.api;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AbstractApiResponse userNotFoundByIdHandler(UserNotFoundByIdException ex) {
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundByEmailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse userNotFoundByEmailHandler(UserNotFoundByEmailException ex){
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundByUuid.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse userNotFoundByUuidHandler(UserNotFoundByUuid ex){
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(TokenNotFoundByUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse tokenNotFoundByUserHandler(TokenNotFoundByUserException ex){
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(UserExistsByEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse userExistsWithEmailHandler(UserExistsByEmailException ex){
        return new ApiResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
    }

    @ResponseBody
    @ExceptionHandler(TokenNotFoundByTokenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse tokenNotFoundByTokenHandler(TokenNotFoundByTokenException ex){
        return new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }


}
