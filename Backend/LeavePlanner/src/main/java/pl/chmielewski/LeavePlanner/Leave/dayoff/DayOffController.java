package pl.chmielewski.LeavePlanner.Leave.dayoff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;
import pl.chmielewski.LeavePlanner.Leave.api.request.AddDayOffRequest;
import pl.chmielewski.LeavePlanner.Leave.api.request.DateRequest;
import pl.chmielewski.LeavePlanner.Leave.api.response.AllYearsWithHolyCount;
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

    @GetMapping("/all-years")
    public List<AllYearsWithHolyCount> getAllYears(){
        return dayOffService.getAllYears();
    }

    @PostMapping("/add")
    public ResponseEntity<AbstractApiResponse> addDayOff(@RequestBody AddDayOffRequest request) {
        AbstractApiResponse abstractApiResponse = dayOffService.addNewDayOff(request);
        if(abstractApiResponse instanceof DayOffAddedResponse){
            return new ResponseEntity<>(abstractApiResponse, HttpStatusCode.valueOf(201));
        }
        return new ResponseEntity<>(abstractApiResponse, HttpStatusCode.valueOf(409));
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> deleteHolyById(@RequestParam Long id){
        return new ResponseEntity<>(dayOffService.deleteHolyById(id), HttpStatus.OK);
    }

    @PostMapping("/get-by-date")
    public ResponseEntity<DayOff> getDayOffByDate(@RequestBody DateRequest dateRequest) {
        return new ResponseEntity<>(dayOffService.getDayOffByDate(dateRequest.date()), HttpStatus.OK);
    }

    @GetMapping("/get-by-year/{year}")
    public long getHolyCountByYear(@PathVariable("year") int year){
        return dayOffService.getHolidaysNumberByYear(year);
    }

    @GetMapping("/year-exists")
    public boolean isYearExisting(Integer year){
        return dayOffService.isYearExisting(year);
    }
}
