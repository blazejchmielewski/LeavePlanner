package pl.chmielewski.LeavePlanner.Leave;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.user.Department;
import pl.chmielewski.LeavePlanner.Authentication.user.User;
import pl.chmielewski.LeavePlanner.Authentication.user.UserService;
import pl.chmielewski.LeavePlanner.Leave.api.exception.LeaveNotFoundByIdException;
import pl.chmielewski.LeavePlanner.Leave.api.request.CreateLeaveDTO;
import pl.chmielewski.LeavePlanner.Leave.api.request.UpdateLeaveDTO;
import pl.chmielewski.LeavePlanner.Leave.api.response.UsersToSwitchResponse;
import pl.chmielewski.LeavePlanner.Leave.leave.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final UserService userService;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository, UserService userService) {
        this.leaveRepository = leaveRepository;
        this.userService = userService;
    }

    public Leave getLeaveById(Long id) {
        return leaveRepository.findLeaveById(id).orElseThrow(() -> new LeaveNotFoundByIdException(id));
    }

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public void createLeave(String uuid, CreateLeaveDTO dto) {
        User userByUuid = userService.getUserByUuid(uuid);
        Leave leave = new Leave(
                dto.startDate(),
                dto.endDate(),
                dto.type(),
                Status.PENDING,
                LocalDateTime.now(),
                LocalDateTime.now(),
                userByUuid
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

    public List<UsersToSwitchResponse> usersToSwitch(HttpServletRequest http){
        User userByToken = userService.getUserByToken(http);
        return userService.getUsersByDepartment(userByToken.getDepartment())
                .stream()
                .map(u -> new UsersToSwitchResponse(u.getFirstname(), u.getLastname()))
                .toList();
    }
}
