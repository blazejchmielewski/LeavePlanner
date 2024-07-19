package pl.chmielewski.LeavePlanner.Leave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.chmielewski.LeavePlanner.Authentication.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

    Optional<Leave> findLeaveById(Long id);

    Optional<Leave> findLeaveByUuid(String uuid);

    Optional<List<Leave>> findLeaveByUser(User user);

    Optional<List<Leave>> findLeaveByReplacementUser(User replecamentUser);
}
