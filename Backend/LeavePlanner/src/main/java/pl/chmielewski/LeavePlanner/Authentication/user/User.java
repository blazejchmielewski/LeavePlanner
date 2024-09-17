package pl.chmielewski.LeavePlanner.Authentication.user;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.chmielewski.LeavePlanner.Authentication.token.Token;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "users", schema = "leave")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private Long id;

    @Column(name = "us_firstname")
    private String firstname;

    @Column(name = "us_lastname")
    private String lastname;

    @Column(name = "us_email")
    private String email;

    @Column(name = "us_password")
    private String password;

    @Column(name = "us_uuid")
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "us_role")
    private Role role;

    @Column(name = "us_available_days")
    private int availableDays;

    @Column(name = "us_years_of_work")
    private int yearsOfWork;

    @Column(name = "us_department")
    private Department department;

    @Column(name = "us_enabled")
    private boolean isEnabled;

    @Column(name = "us_created_at")
    private LocalDateTime createdAt;

    @Column(name = "us_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "us_deactivated_at")
    private LocalDateTime deactivatedAt;

    public User() {
    }

    public User(String firstname, String lastname, String email, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deactivatedAt) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.yearsOfWork = 0;
        this.availableDays = 0;
        this.deactivatedAt = deactivatedAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public int getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(int availableDays) {
        this.availableDays = availableDays;
    }

    public int getYearsOfWork() {
        return yearsOfWork;
    }

    public void setYearsOfWork(int yearsOfWork) {
        this.yearsOfWork = yearsOfWork;
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

    public LocalDateTime getDeactivatedAt() {
        return deactivatedAt;
    }

    public void setDeactivatedAt(LocalDateTime deactivatedAt) {
        this.deactivatedAt = deactivatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uuid='" + uuid + '\'' +
                ", role=" + role +
                ", availableDays=" + availableDays +
                ", yearsOfWork=" + yearsOfWork +
                ", department=" + department +
                ", isEnabled=" + isEnabled +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deactivatedAt=" + deactivatedAt +
                '}';
    }
}
