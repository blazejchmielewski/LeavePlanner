package pl.chmielewski.LeavePlanner.Leave.dayoff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Leave.api.request.AddDayOffRequest;
import pl.chmielewski.LeavePlanner.Leave.api.response.DayOffAddedResponse;
import pl.chmielewski.LeavePlanner.Leave.year.YearService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DayOffService {

    private final DayOffRepository dayOffRepository;
    private final YearService yearService;

    @Autowired
    public DayOffService(DayOffRepository dayOffRepository, YearService yearService) {
        this.dayOffRepository = dayOffRepository;
        this.yearService = yearService;
    }

    List<DayOff> getAllDayOffs() {
        return dayOffRepository.findAll();
    }

    DayOffAddedResponse addNewDayOff(AddDayOffRequest[] addDayOffRequest) {

        List<LocalDate> allDayOffs = getAllDayOffs().stream().map(DayOff::getDayOff).toList();

        if (addDayOffRequest.length > 0) {
            int year = addDayOffRequest[0].dayOff().getYear();
            yearService.addToHolyCount(year);
        }

        List<DayOff> dayOffsToSave = Arrays.stream(addDayOffRequest)
                .filter(d -> !allDayOffs.contains(d.dayOff()))
                .map(r -> new DayOff(r.holyName(), r.dayOff()))
                .collect(Collectors.toList());

        dayOffRepository.saveAll(dayOffsToSave);

        return new DayOffAddedResponse();
    }

    DayOff getDayOffByDate(LocalDate date){
        return dayOffRepository.findByDayOff(date).orElseThrow(()->new RuntimeException("Nie znaleziono"));
    }

    List<DayOff> getDaysoffsByYear(int year) {
        return getAllDayOffs()
                .stream()
                .filter(dayOff -> dayOff.getDayOff().toString().startsWith(String.valueOf(year)))
                .sorted(Comparator.comparing(DayOff::getDayOff))
                .collect(Collectors.toList());
    }

    Long getHolidaysNumberByYear(int year){
        return getAllDayOffs().stream()
                .filter(d -> d.getDayOff().toString().startsWith(String.valueOf(year)))
                .count();
    }

    String deleteHolyById(Long id){
        Optional<DayOff> byId = dayOffRepository.findById(id);
        dayOffRepository.deleteById(id);
        byId.ifPresent(dayOff -> yearService.reduceHolyCount(dayOff.getDayOff().getYear()));
        return "Usunieto święto";
    }

}
