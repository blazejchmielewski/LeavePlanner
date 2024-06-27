package pl.chmielewski.LeavePlanner.Authentication.auth;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.LeavePlanner.Authentication.api.response.*;
import pl.chmielewski.LeavePlanner.Authentication.email.EmailService;
import pl.chmielewski.LeavePlanner.Authentication.request.ChangePasswordRequest;
import pl.chmielewski.LeavePlanner.Authentication.request.LoginUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.request.RegisterUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.request.ResetPasswordRequest;
import pl.chmielewski.LeavePlanner.Authentication.user.User;

import java.time.LocalDate;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    @Autowired
    public AuthController(AuthService authService, EmailService emailService) {
        this.authService = authService;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginSuccessedDTO> loginUser(@RequestBody LoginUserDTO loginUserDTO, HttpServletResponse response) {
        UserLoginSuccessedDTO login = authService.login(loginUserDTO, response);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserCreatedResponse> addNewUser(@RequestBody RegisterUserDTO registerUserDTO, HttpServletResponse response) throws MessagingException {
        UserRegisterSuccessedDTO register = authService.register(registerUserDTO, response);
        emailService.sendMail(
                register.email(),
                "Account activation",
                "http://localhost:4200/aktywuj/" + register.uuid(),
                false);
        return new ResponseEntity<>(new UserCreatedResponse(), HttpStatus.CREATED);
    }

    @GetMapping("/activate")
    public ResponseEntity<UserEnabledResponse> enableUser(@RequestParam("uid") String uuid) {
        authService.enableUser(uuid);
        return new ResponseEntity<>(new UserEnabledResponse(), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<UserLogoutResponse> logoutUser(HttpServletResponse response) {
        authService.logout(response);
        return new ResponseEntity<>(new UserLogoutResponse(), HttpStatus.OK);
    }

    // Służy do wysłania maila z linkiem do formularza zmiany
    @PostMapping("/reset-password")
    public ResponseEntity<ResetPasswordEmailResponse> resetPassword(@RequestBody ResetPasswordRequest request) throws MessagingException {
        User userByEmail = authService.getUserByEmail(request.email());
        emailService.sendMail(request.email(), "Reset-password", "http://localhost:4200/odzyskaj-haslo/" + userByEmail.getUuid() , false);
        return new ResponseEntity<>(new ResetPasswordEmailResponse(), HttpStatus.OK);
    }

    // Służy do obsługi danych pobranych z formularza zmiany hasła
    @PutMapping("/change-password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request){
        authService.changePassword(request.password(), request.uid());
        return new ResponseEntity<>(new ChangePasswordResponse(), HttpStatus.OK);
    }

    @GetMapping("/auto-login")
    public ResponseEntity<UserLoginSuccessedDTO> autoLogin(HttpServletRequest request, HttpServletResponse response){
        UserLoginSuccessedDTO userLoginSuccessedDTO = authService.autoLogin(request, response);
        return new ResponseEntity<>(userLoginSuccessedDTO, HttpStatus.OK);
    }

    @GetMapping("/logged-in")
    public ResponseEntity<UserIsLoggedInDTO> isUserLoggedIn(HttpServletRequest request, HttpServletResponse response){
        boolean userLoggedIn = authService.isUserLoggedIn(request, response);
        return new ResponseEntity<>(new UserIsLoggedInDTO(userLoggedIn, 200, LocalDate.now()),HttpStatus.OK);
    }
}
