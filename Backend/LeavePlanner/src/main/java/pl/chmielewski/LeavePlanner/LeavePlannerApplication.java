package pl.chmielewski.LeavePlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LeavePlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeavePlannerApplication.class, args);
	}

}
