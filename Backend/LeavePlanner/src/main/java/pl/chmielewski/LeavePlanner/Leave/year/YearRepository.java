package pl.chmielewski.LeavePlanner.Leave.year;

import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Year, Long> {

    Year findYearByYear(int year);
}
