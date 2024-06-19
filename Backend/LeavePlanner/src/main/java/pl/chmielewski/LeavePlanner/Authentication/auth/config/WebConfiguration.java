package pl.chmielewski.LeavePlanner.Authentication.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.chmielewski.LeavePlanner.Authentication.user.Role;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static pl.chmielewski.LeavePlanner.Authentication.user.Permission.*;
import static pl.chmielewski.LeavePlanner.Authentication.user.Role.ADMIN;
import static pl.chmielewski.LeavePlanner.Authentication.user.Role.MANAGER;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final AuthFilterConfiguration authFilterConfiguration;

    private final String[] WHITE_LIST_URL = {
            "/auth/register",
            "/auth/login"
    };

    @Autowired
    public WebConfiguration(AuthenticationProvider authenticationProvider, AuthFilterConfiguration authFilterConfiguration) {
        this.authenticationProvider = authenticationProvider;
        this.authFilterConfiguration = authFilterConfiguration;
    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/users/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                                .requestMatchers(GET, "/users/**").hasAnyAuthority(USER_READ.name(), MANAGER_READ.name())
                                .requestMatchers(POST, "/users/**").hasAnyAuthority(USER_CREATE.name(), MANAGER_CREATE.name())
                                .requestMatchers(PUT, "/users/**").hasAnyAuthority(USER_UPDATE.name(), MANAGER_UPDATE.name())
                                .requestMatchers(DELETE, "/users/**").hasAnyAuthority(USER_DELETE.name(), MANAGER_DELETE.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilterConfiguration, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
