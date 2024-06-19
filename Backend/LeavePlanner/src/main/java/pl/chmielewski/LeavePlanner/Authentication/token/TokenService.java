package pl.chmielewski.LeavePlanner.Authentication.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.user.User;

import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void revokeAllUserTokens(User user) {
        Optional<List<Token>> allByUser = tokenRepository.findAllByUser(user);
        allByUser.ifPresent(tokens -> {
            for (Token token : tokens) {
                token.setRevoked(true);
                tokenRepository.save(token);
            }
        });
    }

    public void saveTokenForUser(String jwt, User user){
        Token token = new Token();
        token.setToken(jwt);
        token.setUser(user);
        token.setRevoked(false);
        token.setExpired(false);
        tokenRepository.save(token);
    }
}
