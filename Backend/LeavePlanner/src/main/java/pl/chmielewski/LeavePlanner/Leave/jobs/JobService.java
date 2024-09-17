package pl.chmielewski.LeavePlanner.Leave.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.user.UserService;

@Service
public class JobService {

    private final UserService userService;

    @Autowired
    public JobService(UserService userService) {
        this.userService = userService;
    }

    // Codzinnie o 19:40 - Dodanie wolnych dni pracownikom
    @Scheduled(cron = "0 40 19 * * *")
    public void addDays(){
        userService.addAvailableDats(1L);
        System.out.println("Wykonuję zadanie codziennie o 19:40");
    }
}
