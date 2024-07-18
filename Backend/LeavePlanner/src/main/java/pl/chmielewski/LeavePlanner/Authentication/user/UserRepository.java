package pl.chmielewski.LeavePlanner.Authentication.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUuid(String uuid);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.isEnabled = true")
    List<User> findEnabledUsers();

    List<User> findByDepartment(Department department);
}
