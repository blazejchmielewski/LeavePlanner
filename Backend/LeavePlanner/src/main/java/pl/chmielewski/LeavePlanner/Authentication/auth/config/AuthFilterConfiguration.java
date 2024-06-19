package pl.chmielewski.LeavePlanner.Authentication.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.chmielewski.LeavePlanner.Authentication.token.JwtService;
import pl.chmielewski.LeavePlanner.Authentication.token.Token;
import pl.chmielewski.LeavePlanner.Authentication.token.TokenService;
import pl.chmielewski.LeavePlanner.Authentication.user.UserService;

import java.io.IOException;

@Configuration
public class AuthFilterConfiguration extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtService jwtService;
    private final TokenService tokenService;

    public AuthFilterConfiguration(UserService userService, JwtService jwtService, TokenService tokenService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if (request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmailFromComingToken;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);
        userEmailFromComingToken = jwtService.extractUsername(jwtToken);

        if (userEmailFromComingToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userService.loadUserByUsername(userEmailFromComingToken);
            Token foundedToken = tokenService.getTokenByToken(jwtToken);
            boolean isTokenValid = (!foundedToken.isRevoked() || !foundedToken.isExpired());
            if (jwtService.isTokenValid(foundedToken.getToken(), userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
