package pl.chmielewski.LeavePlanner.Authentication.api.exception;

public class UserExistsByEmailException extends RuntimeException{
    public UserExistsByEmailException(String email) {
        super("User with the email: " + email + " exist");
    }
}
