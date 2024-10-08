package pl.chmielewski.LeavePlanner.Leave.dayoff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {


    Optional<DayOff> findByDayOff(LocalDate dayOff);
    Optional<List<DayOff>> findAllByYear(int year);
    boolean existsByDayOff(LocalDate dayOff);

}
