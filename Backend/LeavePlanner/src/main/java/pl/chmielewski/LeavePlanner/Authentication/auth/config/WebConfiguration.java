package pl.chmielewski.LeavePlanner.Authentication.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.chmielewski.LeavePlanner.Authentication.auth.LogoutConfiguration;
import pl.chmielewski.LeavePlanner.Authentication.user.Role;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
public class WebConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final AuthFilterConfiguration authFilterConfiguration;
    private final LogoutConfiguration logoutService;
    private final UserDetailsService userDetailsService;

    private final String[] WHITE_LIST_URL = {
            "/auth/**",
            "/users/departments"
    };

    private final String[] USER_LIST_URL = {
            "/test/razem",
            "/test/user",
            "/users/all",
            "/users/get/**",
            "/users/user-details",
            "/leave/users-to-switch",
            "/leave/add/**",
            "/leave/all-user-leaves",
            "/leave/get-by-uuid/**",
            "/leave/add",
            "/leave/all-replacements",
            "/leave/accept-replacement",
            "/leave/reject-replacement",
    };

    @Autowired
    public WebConfiguration(AuthenticationProvider authenticationProvider, AuthFilterConfiguration authFilterConfiguration, LogoutConfiguration logoutService, UserDetailsService userDetailsService) {
        this.authenticationProvider = authenticationProvider;
        this.authFilterConfiguration = authFilterConfiguration;
        this.logoutService = logoutService;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers(USER_LIST_URL).hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers("/**").hasAnyAuthority(Role.ADMIN.name())
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
