package pl.chmielewski.LeavePlanner.Authentication.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/guest")
    public String getGuestRole(){
        return "hello from guest";
    }

    @GetMapping("/user")
    public String getUserRole(){
        return "hello from user";
    }

    @GetMapping("/admin")
    public String getAdminRole(){
        return "hello from admin";
    }
}
