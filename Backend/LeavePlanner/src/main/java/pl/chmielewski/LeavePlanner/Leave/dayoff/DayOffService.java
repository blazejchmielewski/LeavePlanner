package pl.chmielewski.LeavePlanner.Leave.dayoff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Leave.api.request.AddDayOffRequest;
import pl.chmielewski.LeavePlanner.Leave.api.response.DayOffAddedResponse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DayOffService {

    private final DayOffRepository dayOffRepository;

    @Autowired
    public DayOffService(DayOffRepository dayOffRepository) {
        this.dayOffRepository = dayOffRepository;
    }

    List<DayOff> getAllDayOffs() {
        return dayOffRepository.findAll();
    }

    DayOffAddedResponse addNewDayOff(AddDayOffRequest[] addDayOffRequest) {
        List<LocalDate> allDayOffs = getAllDayOffs().stream().map(DayOff::getDayOff).toList();

        List<DayOff> dayOffsToSave = Arrays.stream(addDayOffRequest)
                .filter(d -> !allDayOffs.contains(d.dayOff()))
                .map(r -> new DayOff(r.holyName(), r.dayOff()))
                .collect(Collectors.toList());
        dayOffRepository.saveAll(dayOffsToSave);

        return new DayOffAddedResponse();
    }

    List<DayOff> getDaysoffsByYear(int year) {
        return getAllDayOffs()
                .stream()
                .filter(dayOff -> dayOff.getDayOff().toString().startsWith(String.valueOf(year)))
                .sorted(Comparator.comparing(DayOff::getDayOff))
                .collect(Collectors.toList());
    }
}
