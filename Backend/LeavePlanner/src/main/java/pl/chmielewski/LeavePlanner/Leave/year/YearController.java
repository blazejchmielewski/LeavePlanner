package pl.chmielewski.LeavePlanner.Leave.year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.LeavePlanner.Leave.api.request.AddNewYearRequest;

import java.util.List;

@RestController
@RequestMapping("/year")
public class YearController {

    private final YearService yearService;

    @Autowired
    public YearController(YearService yearService) {
        this.yearService = yearService;
    }

    @GetMapping("/get/all")
    ResponseEntity<List<Year>> getAllYears(){
        return new ResponseEntity<>(yearService.getAllYears(), HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<String> addNewYear(@RequestBody AddNewYearRequest request){
        return new ResponseEntity<>(yearService.addYear(request.year()), HttpStatus.CREATED);
    }
}
