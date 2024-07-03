package pl.chmielewski.LeavePlanner.Authentication.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.TokenNotFoundByTokenException;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundByTokenException;
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

    public Token getTokenByToken(String token){
        return tokenRepository.findByToken(token).orElseThrow(()-> new TokenNotFoundByTokenException(token));
    }

    public User getUserByToken(String token){
        System.out.println(tokenRepository.findUserByToken(token).orElseThrow(() -> new UserNotFoundByTokenException(token)).toString());
        return tokenRepository.findUserByToken(token).orElseThrow(() -> new UserNotFoundByTokenException(token));
    }
}
