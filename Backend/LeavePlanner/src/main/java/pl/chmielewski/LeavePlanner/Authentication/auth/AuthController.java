package pl.chmielewski.LeavePlanner.Authentication.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserLogoutResponse;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserCreatedResponse;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserLoginSuccessedResponse;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserUpdatedResponse;
import pl.chmielewski.LeavePlanner.Authentication.request.LoginUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.request.RegisterUserDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginSuccessedResponse> loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        UserLoginSuccessedResponse login = authService.login(loginUserDTO);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserCreatedResponse> addNewUser(@RequestBody RegisterUserDTO registerUserDTO) {
        return new ResponseEntity<>(new UserCreatedResponse(authService.register(registerUserDTO)), HttpStatus.CREATED);
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity<UserUpdatedResponse> setUserRoleAdmin(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new UserUpdatedResponse(authService.setAdminRole(id)), HttpStatus.OK);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<UserUpdatedResponse> setUserRoleUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new UserUpdatedResponse(authService.setUserRole(id)), HttpStatus.OK);
    }

//    @GetMapping("/logout")
//    public UserLogoutResponse logoutUser(){
//        authService.logout();
//    }
}
