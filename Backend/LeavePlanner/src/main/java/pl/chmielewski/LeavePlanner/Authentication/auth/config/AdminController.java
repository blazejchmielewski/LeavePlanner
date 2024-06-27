package pl.chmielewski.LeavePlanner.Authentication.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserUpdatedResponse;
import pl.chmielewski.LeavePlanner.Authentication.user.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity<UserUpdatedResponse> setUserRoleAdmin(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new UserUpdatedResponse(userService.setRoleAdmin(id)), HttpStatus.OK);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<UserUpdatedResponse> setUserRoleUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new UserUpdatedResponse(userService.setRoleUser(id)), HttpStatus.OK);
    }

}
