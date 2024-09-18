package pl.chmielewski.LeavePlanner.Leave.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.user.UserService;
import pl.chmielewski.LeavePlanner.Leave.leave.LeaveService;
import pl.chmielewski.LeavePlanner.Leave.leave.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class jobServiceImpl implements JobService {

    private final UserService userService;
    private final LeaveService leaveService;

    @Autowired
    public jobServiceImpl(UserService userService, LeaveService leaveService) {
        this.userService = userService;
        this.leaveService = leaveService;
    }

    @Override
    public String addDays() {
        userService.getAllUsers()
                .forEach(u -> {
                    if (u.getYearsOfWork() >= 10) {
                        u.setAvailableDays(u.getAvailableDays() + 26);
                    } else {
                        u.setAvailableDays(u.getAvailableDays() + 20);
                    }
                    userService.saveUser(u);
                });
        return LocalDateTime.now().toString() + " | 01-01-XXXX | Dodano dni urlopowe | JOBID:1 |";
    }

    @Override
    public String reduceAvailableDaysAfterLeave() {
        leaveService.getAllLeaves().stream()
                .filter(l -> l.getStatus() == Status.COMPLETED)
                .forEach(l -> {
                    userService.reduceUserAvailableDays(l.getUser(), l.getDays());
                    System.out.println("Użytkownikowi: " + l.getUser().getEmail() + " odebrano " + l.getDays());
                    l.setStatus(Status.CALCULATED);
                });
        return LocalDateTime.now().toString() + " | Codziennie | Zmieniono dostępne dni urlopu | JOBID:2 |";
    }

    @Override
    public String setLeaveStatusPending() {
        LocalDate today = LocalDate.now();
        leaveService.getAllLeaves().stream()
                .filter(l ->
                        l.getStatus() == Status.APPROVED_BY_ACCEPTOR ||
                        l.getStartDate().toLocalDate().isEqual(today))
                .forEach(
                        l -> l.setStatus(Status.PENDING)
                );
        return LocalDateTime.now().toString() + " | Codziennie | Ustawia status urlopu na 'trwa' | JOBID:3 |";
    }
}
