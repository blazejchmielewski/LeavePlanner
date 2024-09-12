package pl.chmielewski.LeavePlanner.Leave.dayoff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.LeavePlanner.Leave.api.request.AddDayOffRequest;
import pl.chmielewski.LeavePlanner.Leave.api.response.DayOffAddedResponse;

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
}
