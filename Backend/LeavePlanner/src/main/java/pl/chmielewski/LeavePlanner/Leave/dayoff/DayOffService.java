package pl.chmielewski.LeavePlanner.Leave.dayoff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.api.AbstractApiResponse;
import pl.chmielewski.LeavePlanner.Leave.api.request.AddDayOffRequest;
import pl.chmielewski.LeavePlanner.Leave.api.response.AllYearsWithHolyCount;
import pl.chmielewski.LeavePlanner.Leave.api.response.DayOffAddedResponse;
import pl.chmielewski.LeavePlanner.Leave.api.response.DayOffNotAddedResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    AbstractApiResponse addNewDayOff(AddDayOffRequest request) {
        if (!dayOffRepository.existsByDayOff(request.dayOff())) {
            dayOffRepository.save(new DayOff(request.holyName(), request.dayOff().getYear(), request.dayOff()));
            return new DayOffAddedResponse();
        }
        return new DayOffNotAddedResponse();
    }

    // TODO 1. Błąd własny
    DayOff getDayOffByDate(LocalDate date) {
        return dayOffRepository.findByDayOff(date).orElseThrow(() -> new RuntimeException("Nie znaleziono"));
    }

    // TODO 2. Błąd własny
    List<DayOff> getDaysoffsByYear(int year) {
        return dayOffRepository.findAllByYear(year).orElseThrow(() -> new RuntimeException("Nie znaleziono"));
    }

    Long getHolidaysNumberByYear(int year) {
        return dayOffRepository.findAllByYear(year).stream().count();
    }

    //TODO return -> jako rekord
    String deleteHolyById(Long id) {
        Optional<DayOff> byId = dayOffRepository.findById(id);
        dayOffRepository.deleteById(id);
        return "Usunieto święto";
    }

    List<AllYearsWithHolyCount> getAllYears() {
        return dayOffRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(DayOff::getYear, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new AllYearsWithHolyCount(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toList());
    }

    public boolean isYearExisting(Integer year) {
        if (year == null) {
            return false;
        }
        return dayOffRepository.findAllByYear(year).isPresent();
    }
}
