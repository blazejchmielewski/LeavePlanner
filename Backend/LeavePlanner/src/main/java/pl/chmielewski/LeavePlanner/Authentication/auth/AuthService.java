package pl.chmielewski.LeavePlanner.Authentication.auth;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.TokenNotFoundByCookieException;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserExistsByEmailException;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserLoginSuccessedDTO;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserRegisterSuccessedDTO;
import pl.chmielewski.LeavePlanner.Authentication.api.request.LoginUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.api.request.RegisterUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.token.JwtService;
import pl.chmielewski.LeavePlanner.Authentication.token.TokenService;
import pl.chmielewski.LeavePlanner.Authentication.user.User;
import pl.chmielewski.LeavePlanner.Authentication.user.UserService;

import java.util.Arrays;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;
    private final JwtService jwtService;
    private final CookieService cookieService;

    final int exp = 7 * 24 * 60 * 60 * 1000;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService, JwtService jwtService, CookieService cookieService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
        this.jwtService = jwtService;
        this.cookieService = cookieService;
    }

    public UserLoginSuccessedDTO login(LoginUserDTO loginUserDTO, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.email(),
                        loginUserDTO.password()
                ));
        User user = userService.getUserByEmail(loginUserDTO.email());
        tokenService.revokeAllUserTokens(user);
        String token = jwtService.generateToken(user.getUsername(), exp);
        tokenService.saveTokenForUser(token, user);
        cookieService.addCookie(response, "Authorization", token, exp);
        return new UserLoginSuccessedDTO(user.getEmail(), user.getRole().name(), token);
    }

    public boolean isUserLoggedIn(HttpServletRequest request, HttpServletResponse response) {
        try {
            validateToken(request, response);
            return true;
        } catch (ExpiredJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public UserLoginSuccessedDTO autoLogin(HttpServletRequest request, HttpServletResponse response){
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie value : request.getCookies()) {
                if ("Authorization".equals(value.getName())) {
                    token = value.getValue();
                    break;
                }
            }
        } else {
            throw new TokenNotFoundByCookieException();
        }
        User userByToken = tokenService.getUserByToken(token);
        boolean tokenValid = jwtService.isTokenValid(token, userByToken);
        if(tokenValid){
            return new UserLoginSuccessedDTO(userByToken.getEmail(), userByToken.getRole().name(), token);
        }
        return null;
    }

    public UserRegisterSuccessedDTO register(RegisterUserDTO registerUserDTO, HttpServletResponse response) {
        if (userService.userExistsByEmail(registerUserDTO.email())) {
            throw new UserExistsByEmailException(registerUserDTO.email());
        }
        User user = userService.createUser(registerUserDTO);
        String token = jwtService.generateToken(user.getUsername(), exp);
        tokenService.saveTokenForUser(token, user);
        cookieService.addCookie(response, "Authorization", token, exp);
        return new UserRegisterSuccessedDTO(token, user.getEmail(), user.getUuid());
    }

    public void changePassword(String password, String uuid) {
        userService.changePassword(password, uuid);
    }

    public void enableUser(String uuid) {
        User userByUuid = userService.getUserByUuid(uuid);
        userByUuid.setEnabled(true);
        userService.saveUser(userByUuid);
    }

    public void logout(HttpServletResponse response) {
        cookieService.removeCookie(response, "Authorization");
    }

    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    private void validateToken(HttpServletRequest request, HttpServletResponse response) throws ExpiredJwtException, IllegalArgumentException {
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie value : request.getCookies()) {
                if ("Authorization".equals(value.getName())) {
                    token = value.getValue();
                    break;
                }
            }
        } else {
            throw new TokenNotFoundByCookieException();
        }
        Cookie cookie = cookieService.generateCookie("Authorization", token, exp);
        response.addCookie(cookie);
        jwtService.isTokenExpired(token);
    }
}
