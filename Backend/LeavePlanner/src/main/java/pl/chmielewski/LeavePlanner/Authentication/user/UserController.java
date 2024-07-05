package pl.chmielewski.LeavePlanner.Authentication.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;
import pl.chmielewski.LeavePlanner.Authentication.api.request.UpdateUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.api.response.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<String>> getAllDepartments(){
        return new ResponseEntity<>(userService.getDepartments(), HttpStatus.OK) ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/table/all")
    public ResponseEntity<List<UserDataResponse>> getAllUsersToTable() {
        return new ResponseEntity<>(userService.getAllUsersToTable(), HttpStatus.OK);
    }

    @PutMapping("/expire/{id}")
    public ResponseEntity<AbstractApiResponse> disableUser(@PathVariable("id") Long id){
        userService.disableUser(id);
        return new ResponseEntity<>(new UserDisabledResponse(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AbstractApiResponse> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserDTO updateUser) {
        userService.updateUser(id, updateUser);
        return new ResponseEntity<>(new UserUpdatedResponse(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AbstractApiResponse> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new UserDeletedResponse(id), HttpStatus.NO_CONTENT);
    }
}
