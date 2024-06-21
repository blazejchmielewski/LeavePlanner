package pl.chmielewski.LeavePlanner.Authentication.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/user")
    public String userEndpoint(){
        return "User";
    }

    @GetMapping("/razem")
    public String razemEndpoint(){
        return "razem";
    }

    @GetMapping("/admin")
    public String adminEndpoint(){
        return "Admin";
    }
}

