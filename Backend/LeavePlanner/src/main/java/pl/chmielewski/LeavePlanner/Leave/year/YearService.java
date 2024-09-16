package pl.chmielewski.LeavePlanner.Leave.year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearService {

    private final YearRepository yearRepository;

    @Autowired
    public YearService(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    public List<Year> getAllYears(){
        return yearRepository.findAll();
    }

    public String addYear(int year){
        yearRepository.save(new Year(year, 0));
        return "Utworzono poprawnie";
    }

    public void addToHolyCount(int year){
        Year yearByYear = yearRepository.findYearByYear(year);
        int i = yearByYear.getHolyCount() + 1;
        yearByYear.setHolyCount(i);
        yearRepository.save(yearByYear);
    }
    public void reduceHolyCount(int year){
        Year yearByYear = yearRepository.findYearByYear(year);
        int i = yearByYear.getHolyCount() - 1;
        yearByYear.setHolyCount(i);
        yearRepository.save(yearByYear);
    }
}
