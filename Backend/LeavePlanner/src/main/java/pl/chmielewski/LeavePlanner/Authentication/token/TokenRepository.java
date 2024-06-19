package pl.chmielewski.LeavePlanner.Authentication.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.chmielewski.LeavePlanner.Authentication.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<List<Token>> findAllByUser(User user);
    Optional<Token> findByToken(String token);
}
