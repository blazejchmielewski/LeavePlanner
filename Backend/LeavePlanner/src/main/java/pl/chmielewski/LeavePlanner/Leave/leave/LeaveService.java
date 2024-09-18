package pl.chmielewski.LeavePlanner.Leave.leave;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.user.User;
import pl.chmielewski.LeavePlanner.Authentication.user.UserService;
import pl.chmielewski.LeavePlanner.Leave.api.exception.LeaveNotFoundByIdException;
import pl.chmielewski.LeavePlanner.Leave.api.exception.LeaveNotFoundByUuidException;
import pl.chmielewski.LeavePlanner.Leave.api.request.CreateLeaveDTO;
import pl.chmielewski.LeavePlanner.Leave.api.request.UpdateLeaveDTO;
import pl.chmielewski.LeavePlanner.Leave.api.response.LeaveDataExtendResponse;
import pl.chmielewski.LeavePlanner.Leave.api.response.LeaveDataResponse;
import pl.chmielewski.LeavePlanner.Leave.api.response.UsersToSwitchResponse;
import pl.chmielewski.LeavePlanner.Leave.dayoff.DayOff;
import pl.chmielewski.LeavePlanner.Leave.dayoff.DayOffService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final UserService userService;
    private final DayOffService dayOffService;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository, UserService userService, DayOffService dayOffService) {
        this.leaveRepository = leaveRepository;
        this.userService = userService;
        this.dayOffService = dayOffService;
    }

    public Leave getLeaveById(Long id) {
        return leaveRepository.findLeaveById(id).orElseThrow(() -> new LeaveNotFoundByIdException(id));
    }

    private Leave getLeaveByUuid(String uuid) {
        return leaveRepository.findLeaveByUuid(uuid).orElseThrow(() -> new LeaveNotFoundByUuidException(uuid));
    }

    public LeaveDataExtendResponse getLeaveByUuidMapped(String uuid) {
        Leave leave = getLeaveByUuid(uuid);
        return new LeaveDataExtendResponse(
                leave.getUuid(), leave.getStartDate(), leave.getEndDate(), leave.getType().name(),
                leave.getUser().getFirstname() + " " + leave.getUser().getLastname(),
                leave.getReplacementUser().getFirstname() + " " + leave.getReplacementUser().getLastname(),
                leave.getStatus().name(), leave.getCreatedAt(), leave.getUpdatedAt(), leave.getSettledByReplacerAt(), leave.getSettledByAcceptorsAt()
        );
    }

    public List<LeaveDataExtendResponse> getAll() {
        List<Leave> all = leaveRepository.findAll();
        return all.stream().map(leave -> new LeaveDataExtendResponse(
                leave.getUuid(), leave.getStartDate(), leave.getEndDate(), leave.getType().name(),
                leave.getUser().getFirstname() + " " + leave.getUser().getLastname(),
                leave.getReplacementUser().getFirstname() + " " + leave.getReplacementUser().getLastname(),
                leave.getStatus().name(), leave.getCreatedAt(), leave.getUpdatedAt(), leave.getSettledByReplacerAt(), leave.getSettledByAcceptorsAt()
        )).collect(Collectors.toList());
    }

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public void createLeave(CreateLeaveDTO dto, HttpServletRequest http) {
        User userTakingLeave = userService.getUserByToken(http);
        User replacingUser = userService.getUserByUuid(dto.userUuid());
        int days = calculateDays(dto.startDate(), dto.endDate());
        Leave leave = new Leave(
                dto.startDate(),
                dto.endDate(),
                dto.type(),
                Status.PENDING,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                days,
                null,
                userTakingLeave,
                replacingUser,
                UUID.randomUUID().toString()
        );
        leaveRepository.save(leave);
    }


    public void updateLeave(Long id, UpdateLeaveDTO dto) {
        Leave leave = getLeaveById(id);
        leave.setStartDate(dto.startDate());
        leave.setEndDate(dto.endDate());
        leave.setStatus(dto.status());
        leave.setType(dto.type());
        leave.setUpdatedAt(LocalDateTime.now());

        leaveRepository.save(leave);
    }

    public void deleteLeave(Long id) {
        Leave leaveById = getLeaveById(id);
        leaveRepository.delete(leaveById);
    }

    public List<UsersToSwitchResponse> usersToSwitch(HttpServletRequest http) {
        User userByToken = userService.getUserByToken(http);
        return userService.getUsersByDepartment(userByToken.getDepartment())
                .stream()
                .map(u -> new UsersToSwitchResponse(u.getUuid(), u.getFirstname(), u.getLastname()))
                .filter(u -> !u.uuid().equals(userByToken.getUuid()))
                .toList();
    }

    public List<LeaveDataResponse> getAllUserLeaves(HttpServletRequest http) {
        User userByToken = userService.getUserByToken(http);
        return leaveRepository.findLeaveByUser(userByToken)
                .orElseGet(List::of)
                .stream()
                .map(l -> new LeaveDataResponse(
                        l.getUuid(),
                        l.getStartDate(),
                        l.getEndDate(),
                        l.getType().name(),
                        l.getUser().getFirstname() + " " + l.getUser().getLastname(),
                        l.getReplacementUser().getFirstname() + " " + l.getReplacementUser().getLastname()
                ))
                .collect(Collectors.toList());
    }

    public List<LeaveDataExtendResponse> getAllReplacements(HttpServletRequest http) {
        User userByToken = userService.getUserByToken(http);
        return leaveRepository.findLeaveByReplacementUser(userByToken)
                .orElseGet(List::of)
                .stream()
                .map(leave -> new LeaveDataExtendResponse(
                        leave.getUuid(), leave.getStartDate(), leave.getEndDate(), leave.getType().name(),
                        leave.getUser().getFirstname() + " " + leave.getUser().getLastname(),
                        leave.getReplacementUser().getFirstname() + " " + leave.getReplacementUser().getLastname(),
                        leave.getStatus().name(), leave.getCreatedAt(), leave.getUpdatedAt(), leave.getSettledByReplacerAt(), leave.getSettledByAcceptorsAt()
                )).collect(Collectors.toList());
    }

    public void acceptReplacement(String uuid) {
        Leave leave = getLeaveByUuid(uuid);
        leave.setStatus(Status.APPROVED_BY_REPLACER);
        leave.setSettledByReplacerAt(LocalDateTime.now());
        leave.setUpdatedAt(LocalDateTime.now());
        leaveRepository.save(leave);
    }

    public void rejectReplacement(String uuid) {
        Leave leave = getLeaveByUuid(uuid);
        leave.setStatus(Status.REJECTED_BY_REPLACER);
        leave.setSettledByReplacerAt(LocalDateTime.now());
        leave.setUpdatedAt(LocalDateTime.now());
        leaveRepository.save(leave);

    }

    public void rejectLeave(String uuid) {
        Leave leave = getLeaveByUuid(uuid);
        leave.setStatus(Status.REJECTED_BY_ACCEPTOR);
        leave.setSettledByAcceptorsAt(LocalDateTime.now());
        leave.setUpdatedAt(LocalDateTime.now());
        leaveRepository.save(leave);

    }

    public void acceptLeave(String uuid) {
        Leave leave = getLeaveByUuid(uuid);
        leave.setStatus(Status.APPROVED_BY_ACCEPTOR);
        leave.setSettledByAcceptorsAt(LocalDateTime.now());
        leave.setUpdatedAt(LocalDateTime.now());
        leaveRepository.save(leave);
    }

    private int calculateDays(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        LocalDate startDate = startDateTime.toLocalDate();
        LocalDate endDate = endDateTime.toLocalDate();
        List<DayOff> allDayOffs = dayOffService.getAllDayOffs();

        Set<LocalDate> dayOffDates = allDayOffs.stream()
                .map(DayOff::getDayOff)
                .collect(Collectors.toSet());

        if (startDate.isAfter(endDate)) {
            throw new RuntimeException("Data początku nie może być datą poźniejszą niż data końca urlopu");
        }

        int days = 0;
        LocalDate currentDate = startDate;
        while (currentDate.isBefore(endDate)) {
            DayOfWeek dayOffWeek = currentDate.getDayOfWeek();
            if (dayOffWeek != DayOfWeek.SUNDAY &&
                    dayOffWeek != DayOfWeek.SATURDAY &&
                    !dayOffDates.contains(currentDate)
            ) {
                days++;
                System.out.println("Dodaje dla dnia : " + currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }
        if (days == 0) {
            return 0;
        } else
            return days + 1; // (+1) wynika z tego że jeżeli jest isBefore to nie uwzględnia ostaniego dnia
    }
}
