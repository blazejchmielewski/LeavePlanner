package pl.chmielewski.LeavePlanner.Authentication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserDeletedResponse;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserUpdatedResponse;
import pl.chmielewski.LeavePlanner.Authentication.request.UpdateUserDTO;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AbstractApiResponse> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserDTO updateUser) {
        userService.updateUser(id, updateUser);
        return new ResponseEntity<>(new UserUpdatedResponse(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AbstractApiResponse> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new UserDeletedResponse(id), HttpStatus.OK);
    }
}
