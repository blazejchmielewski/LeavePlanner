package pl.chmielewski.LeavePlanner.Leave.dayoff;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dayoffs", schema = "leave")
public class DayOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String holyName;
    @Column(unique = true)
    private LocalDate dayOff;
    public DayOff() {
    }

    public DayOff(String holyName, LocalDate dayOff) {
        this.holyName = holyName;
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
