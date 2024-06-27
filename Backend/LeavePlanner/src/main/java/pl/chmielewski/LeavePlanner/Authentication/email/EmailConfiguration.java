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
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("blazejchmielewski01@gmail.com");
        mailSender.setPassword("jmlpssqydbeigwbo");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.proxy.host", "proxy.grupabps.local");
        props.put("mail.smtp.proxy.port", "8080");
        props.put("mail.smtp.proxy.user", "blazej.chmielewski");
        props.put("mail.smtp.proxy.password", "Czerwiec2024!");
        return mailSender;
    }
}
