package pl.chmielewski.LeavePlanner.Authentication.token;

import jakarta.persistence.*;

@Entity
@Table(name = "tokens", schema = "leave")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
