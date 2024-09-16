package pl.chmielewski.LeavePlanner.Leave.year;

import jakarta.persistence.*;

@Entity
@Table(name = "years", schema = "leave")
public class Year {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private int year;
    private int holyCount;

    public Year() {
    }

    public Year(int year, int holyCount) {
        this.year = year;
        this.holyCount = holyCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHolyCount() {
        return holyCount;
    }

    public void setHolyCount(int holyCount) {
        this.holyCount = holyCount;
    }
}
