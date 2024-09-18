package pl.chmielewski.LeavePlanner.Authentication.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Ustawienia serwera SMTP
        mailSender.setHost("poczta.it-bps.com.pl"); // Serwer SMTP
        mailSender.setPort(587); // Port SMTP

        // Ustawienia autoryzacji
        mailSender.setUsername("blazej.chmielewski@cfsa.pl");
        mailSender.setPassword("Wrzesien2024!Wrzesien2024!");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "poczta.it-bps.com.pl");

        return mailSender;
    }
}
