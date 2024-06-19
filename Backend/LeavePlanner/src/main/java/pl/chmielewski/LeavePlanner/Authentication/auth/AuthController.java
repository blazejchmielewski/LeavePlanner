package pl.chmielewski.LeavePlanner.Authentication.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.chmielewski.LeavePlanner.Authentication.api.response.TokenResponse;
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
    public TokenResponse loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        return new TokenResponse(authService.login(loginUserDTO));
    }

    @PostMapping("/register")
    public TokenResponse addNewUser(@RequestBody RegisterUserDTO registerUserDTO) {
        return new TokenResponse(authService.register(registerUserDTO));
    }
}
