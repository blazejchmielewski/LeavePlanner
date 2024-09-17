package pl.chmielewski.LeavePlanner.Leave.dayoff;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dayoffs", schema = "leave")
public class DayOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doff_id")
    private Long id;
    private String holyName;

    @Column(name = "holiday_year")
    private int year;
    @Column(unique = true)
    private LocalDate dayOff;
    public DayOff() {
    }

    public DayOff(String holyName, int year, LocalDate dayOff) {
        this.holyName = holyName;
        this.year = year;
        this.dayOff = dayOff;
    }

    public String getHolyName() {
        return holyName;
    }

    public void setHolyName(String holyName) {
        this.holyName = holyName;
    }

    public DayOff(LocalDate dayOff) {
        this.dayOff = dayOff;
    }

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDayOff() {
        return dayOff;
    }

    public void setDayOff(LocalDate dayOff) {
        this.dayOff = dayOff;
    }
}
