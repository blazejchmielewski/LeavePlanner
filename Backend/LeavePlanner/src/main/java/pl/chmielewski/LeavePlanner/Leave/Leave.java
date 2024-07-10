package pl.chmielewski.LeavePlanner.Leave;

import jakarta.persistence.*;
import pl.chmielewski.LeavePlanner.Authentication.user.User;
import pl.chmielewski.LeavePlanner.Leave.leave.LeaveType;
import pl.chmielewski.LeavePlanner.Leave.leave.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leaves", schema = "leave")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lv_id")
    private Long id;
    @Column(name = "lv_start_date")
    private LocalDateTime startDate;
    @Column(name = "lv_end_date")
    private LocalDateTime endDate;
    @Column(name = "lv_type")
    private LeaveType type;
    @Column(name = "lv_status")
    private Status status;
    @Column(name = "lv_created_at")
    private LocalDateTime createdAt;
    @Column(name = "lv_updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "lv_us_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lv_replacing_us_id")
    private User replacementUser;

    public Leave() {
    }

    public Leave(LocalDateTime startDate, LocalDateTime endDate, LeaveType type, Status status, LocalDateTime createdAt, LocalDateTime updatedAt, User user, User replacementUser) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.replacementUser = replacementUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getReplacementUser() {
        return replacementUser;
    }

    public void setReplacementUser(User replacementUser) {
        this.replacementUser = replacementUser;
    }
}
