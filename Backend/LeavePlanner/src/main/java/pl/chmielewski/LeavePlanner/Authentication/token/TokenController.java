package pl.chmielewski.LeavePlanner.Authentication.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenController(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("")
    public List<Token> getAllTokens(){
        return tokenRepository.findAll();
    }

}
