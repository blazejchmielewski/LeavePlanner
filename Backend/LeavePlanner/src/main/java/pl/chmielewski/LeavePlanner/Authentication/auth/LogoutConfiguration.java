package pl.chmielewski.LeavePlanner.Authentication.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.TokenNotFoundByTokenException;
import pl.chmielewski.LeavePlanner.Authentication.token.Token;
import pl.chmielewski.LeavePlanner.Authentication.token.TokenRepository;

@Configuration
public class LogoutConfiguration implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Autowired
    public LogoutConfiguration(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = authHeader.substring(7);
        Token storedToken = tokenRepository.findByToken(token).orElseThrow(() -> new TokenNotFoundByTokenException(token));
        if (storedToken != null) {
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
    }
}
