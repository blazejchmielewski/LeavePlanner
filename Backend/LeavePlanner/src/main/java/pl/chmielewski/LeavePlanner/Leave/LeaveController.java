package pl.chmielewski.LeavePlanner.Leave;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.LeavePlanner.Leave.api.request.CreateAcceptLeave;
import pl.chmielewski.LeavePlanner.Leave.api.request.CreateLeaveDTO;
import pl.chmielewski.LeavePlanner.Leave.api.request.UpdateLeaveDTO;
import pl.chmielewski.LeavePlanner.Leave.api.response.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable("id") Long id) {
        Leave leaveById = leaveService.getLeaveById(id);
        return new ResponseEntity<>(leaveById, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LeaveDataExtendResponse>> getLeaveByUuid() {
        return new ResponseEntity<>(leaveService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-uuid")
    public ResponseEntity<LeaveDataExtendResponse> getLeaveByUuid(@RequestParam("uuid") String uuid) {
        return new ResponseEntity<>(leaveService.getLeaveByUuidMapped(uuid), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Leave>> getAllLeaves() {
        List<Leave> allLeaves = leaveService.getAllLeaves();
        return new ResponseEntity<>(allLeaves, HttpStatus.OK);
    }

    @GetMapping("/users-to-switch")
    public ResponseEntity<List<UsersToSwitchResponse>> getUsersToSwitch(HttpServletRequest http) {
        return new ResponseEntity<>(leaveService.usersToSwitch(http), HttpStatus.OK);
    }

    @GetMapping("/all-user-leaves")
    public ResponseEntity<List<LeaveDataResponse>> getAllUserLeaves(HttpServletRequest http) {
        return new ResponseEntity<>(leaveService.getAllUserLeaves(http), HttpStatus.OK);
    }

    @GetMapping("/all-replacements")
    public ResponseEntity<List<LeaveDataExtendResponse>> getAllReplacements(HttpServletRequest http){
        return new ResponseEntity<>(leaveService.getAllReplacements(http), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<LeaveCreatedResponse> createLeave(@RequestBody CreateLeaveDTO dto, HttpServletRequest request) {
        leaveService.createLeave(dto, request);
        return new ResponseEntity<>(new LeaveCreatedResponse(), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LeaveUpdatedResponse> updateLeave(@PathVariable("id") Long id, @RequestBody UpdateLeaveDTO dto) {
        leaveService.updateLeave(id, dto);
        return new ResponseEntity<>(new LeaveUpdatedResponse(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<LeaveDeletedResponse> deleteLeave(@PathVariable("id") Long id) {
        leaveService.deleteLeave(id);
        return new ResponseEntity<>(new LeaveDeletedResponse(id), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/accept-replacement")
    public ResponseEntity<LeaveAcceptedResponse> acceptReplacement(@RequestBody CreateAcceptLeave createAcceptLeave){
        leaveService.acceptReplacement(createAcceptLeave.uuid());
        return new ResponseEntity<>(new LeaveAcceptedResponse(), HttpStatus.OK);
    }

    @PostMapping("/reject-replacement")
    public ResponseEntity<LeaveRejectedResponse> rejectReplacement(@RequestBody CreateAcceptLeave createAcceptLeave){
        leaveService.rejectReplacement(createAcceptLeave.uuid());
        return new ResponseEntity<>(new LeaveRejectedResponse(), HttpStatus.OK);
    }

    @PostMapping("/accept")
    public ResponseEntity<LeaveAcceptedResponse> acceptLeave(@RequestBody CreateAcceptLeave createAcceptLeave){
        leaveService.acceptLeave(createAcceptLeave.uuid());
        return new ResponseEntity<>(new LeaveAcceptedResponse(), HttpStatus.OK);
    }

    @PostMapping("/reject")
    public ResponseEntity<LeaveRejectedResponse> rejectLeave(@RequestBody CreateAcceptLeave createAcceptLeave){
        leaveService.rejectLeave(createAcceptLeave.uuid());
        return new ResponseEntity<>(new LeaveRejectedResponse(), HttpStatus.OK);
    }
}

