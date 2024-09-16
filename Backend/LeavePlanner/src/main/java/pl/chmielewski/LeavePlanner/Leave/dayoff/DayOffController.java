package pl.chmielewski.LeavePlanner.Leave.dayoff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.LeavePlanner.Leave.api.request.AddDayOffRequest;
import pl.chmielewski.LeavePlanner.Leave.api.request.DateRequest;
import pl.chmielewski.LeavePlanner.Leave.api.response.DayOffAddedResponse;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dayoff")
public class DayOffController {

    private final DayOffService dayOffService;

    @Autowired
    public DayOffController(DayOffService dayOffService) {
        this.dayOffService = dayOffService;
    }

    @GetMapping("/all")
    public List<DayOff> getAllDayOffs() {
        return dayOffService.getAllDayOffs();
    }

    @GetMapping("/daysoff/{year}")
    public List<DayOff> getDaysoffsByYear(@PathVariable("year") String yearRequest) {
        return dayOffService.getDaysoffsByYear(Integer.parseInt(yearRequest));
    }

    @PostMapping("/add")
    public ResponseEntity<DayOffAddedResponse> addDayOff(@RequestBody AddDayOffRequest[] request) {
        dayOffService.addNewDayOff(request);
        return new ResponseEntity<>(new DayOffAddedResponse(), HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> deleteHolyById(@RequestParam Long id){
        return new ResponseEntity<>(dayOffService.deleteHolyById(id), HttpStatus.OK);
    }

    @PostMapping("/get-by-date")
    public ResponseEntity<DayOff> getDayOffByDate(@RequestBody DateRequest dateRequest) {
        LocalDate date = dateRequest.date();
        return new ResponseEntity<>(dayOffService.getDayOffByDate(date), HttpStatus.OK);
    }
}
