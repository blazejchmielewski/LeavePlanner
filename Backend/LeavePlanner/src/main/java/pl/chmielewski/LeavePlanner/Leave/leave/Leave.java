package pl.chmielewski.LeavePlanner.Leave.leave;

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
    @Column(name = "lv_uuid")
    private String uuid;
    @Column(name = "lv_start_date")
    private LocalDateTime startDate;
    @Column(name = "lv_end_date")
    private LocalDateTime endDate;
    @Column(name = "lv_type")
    private LeaveType type;
    @Column(name = "lv_status")
    private Status status;
    @Column(name = "lv_days")
    private int days;
    @Column(name = "lv_created_at")
    private LocalDateTime createdAt;
    @Column(name = "lv_updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "lv_settled_by_replacer_at")
    private LocalDateTime settledByReplacerAt;
    @Column(name = "lv_settled_by_acceptor_at")
    private LocalDateTime settledByAcceptorsAt;

    @ManyToOne
    @JoinColumn(name = "lv_us_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lv_replacing_us_id")
    private User replacementUser;

    public Leave() {
    }

    public Leave(LocalDateTime startDate, LocalDateTime endDate, LeaveType type, Status status, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime settledByReplacerAt, int days, LocalDateTime settledByAcceptorsAt, User user, User replacementUser, String uuid) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.settledByReplacerAt = settledByReplacerAt;
        this.settledByAcceptorsAt = settledByAcceptorsAt;
        this.days = days;
        this.user = user;
        this.replacementUser = replacementUser;
        this.uuid = uuid;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getSettledByReplacerAt() {
        return settledByReplacerAt;
    }

    public void setSettledByReplacerAt(LocalDateTime settledByReplacerAt) {
        this.settledByReplacerAt = settledByReplacerAt;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public LocalDateTime getSettledByAcceptorsAt() {
        return settledByAcceptorsAt;
    }

    public void setSettledByAcceptorsAt(LocalDateTime settledByAcceptorsAt) {
        this.settledByAcceptorsAt = settledByAcceptorsAt;
    }
}
