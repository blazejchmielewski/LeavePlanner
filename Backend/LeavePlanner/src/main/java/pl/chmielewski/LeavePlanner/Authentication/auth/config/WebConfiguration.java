package pl.chmielewski.LeavePlanner.Authentication.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.chmielewski.LeavePlanner.Authentication.auth.LogoutConfiguration;
import pl.chmielewski.LeavePlanner.Authentication.user.UserDetailsServiceImpl;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final AuthFilterConfiguration authFilterConfiguration;
    private final UserDetailsServiceImpl userDetailsService;
    private final LogoutConfiguration logoutService;

    private final String[] WHITE_LIST_URL = {
            "/auth/register",
            "/auth/login",
    };

    private final String[] ADMIN_LIST_URL = {
            "/users/**",
            "/tokens/**",
    };

    private final String[] USER_LIST_URL = {
            "/auth/register",
            "/auth/login",
    };

    @Autowired
    public WebConfiguration(AuthenticationProvider authenticationProvider, AuthFilterConfiguration authFilterConfiguration, UserDetailsServiceImpl userDetailsService, LogoutConfiguration logoutService) {
        this.authenticationProvider = authenticationProvider;
        this.authFilterConfiguration = authFilterConfiguration;
        this.userDetailsService = userDetailsService;
        this.logoutService = logoutService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers(ADMIN_LIST_URL).hasAuthority("ADMIN")
                        .requestMatchers(USER_LIST_URL).hasAuthority("USER")
                        .anyRequest()
                        .authenticated()
                )
                .userDetailsService(userDetailsService)
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .addFilterBefore(authFilterConfiguration, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(e ->
                        e.accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(403)))
                .logout(l ->
                        l.logoutUrl("/logout")
                        .addLogoutHandler(logoutService)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

        return http.build();
    }
}
