package pl.chmielewski.LeavePlanner.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppInitializer {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public ApplicationRunner initializerRunner() {
        return args -> {
            try {
                restTemplate.getForObject("http://localhost:8080/auth/initialize", String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
