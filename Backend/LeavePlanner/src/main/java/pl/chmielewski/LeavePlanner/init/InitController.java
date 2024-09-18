package pl.chmielewski.LeavePlanner.init;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.chmielewski.LeavePlanner.Authentication.api.request.RegisterUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserRegisterSuccessedDTO;
import pl.chmielewski.LeavePlanner.Authentication.auth.AuthService;
import pl.chmielewski.LeavePlanner.Authentication.user.Department;

@RestController
@RequestMapping("/auth/initialize")
public class InitController {

    private final AuthService authService;

    @Autowired
    public InitController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void initialize(HttpServletResponse response) {

        RegisterUserDTO registerUserDTO = new RegisterUserDTO(
                "Justyna",
                "Ciemińska",
                "justyna.cieminska@cfsa.pl",
                "haslo123",
                Department.DWWDP.name()
        );

        RegisterUserDTO registerUserDTO1 = new RegisterUserDTO(
                "Weronika",
                "Zinów",
                "weronika.zinow@cfsa.pl",
                "haslo123",
                Department.DWWDP.name()
        );

        RegisterUserDTO registerUserDTO2 = new RegisterUserDTO(
                "Agata",
                "Farańczuk",
                "agata.faranczuk@cfsa.pl",
                "haslo123",
                Department.DWWD.name()
        );

        RegisterUserDTO registerUserDTO3 = new RegisterUserDTO(
                "Beata",
                "Komorowska",
                "beata.komorowska@cfsa.pl",
                "haslo123",
                Department.DWWD.name()
        );

        RegisterUserDTO registerUserDTO4 = new RegisterUserDTO(
                "Karolina",
                "Chrzanowska",
                "karolina.chrzanowska@cfsa.pl",
                "haslo123",
                Department.ZWSWD.name()
        );

        RegisterUserDTO registerUserDTO5 = new RegisterUserDTO(
                "Patrycja",
                "Filipiak",
                "patrycja.filipiak@cfsa.pl",
                "haslo123",
                Department.ZWSWD.name()
        );

        RegisterUserDTO registerUserDTO6 = new RegisterUserDTO(
                "Martyna",
                "Ksepka",
                "martyna.ksepka@cfsa.pl",
                "haslo123",
                Department.ZWSWZ.name()
        );

        RegisterUserDTO registerUserDTO7 = new RegisterUserDTO(
                "Aleksandra",
                "Pułciennik-Pietrak",
                "aleksandra.pluciennik-pietrak@cfsa.pl",
                "haslo123",
                Department.ZWSWZ.name()
        );

        RegisterUserDTO registerUserDTO8 = new RegisterUserDTO(
                "Milena",
                "Podsybirska",
                "milena.podsybirska@cfsa.pl",
                "haslo123",
                Department.BFIR.name()
        );

        RegisterUserDTO registerUserDTO9 = new RegisterUserDTO(
                "Przemysław",
                "Rutkowski",
                "przemyslaw.rutkowski@cfsa.pl",
                "haslo123",
                Department.BFIR.name()
        );

        RegisterUserDTO registerUserDTO10 = new RegisterUserDTO(
                "Błażej",
                "Chmielewski",
                "blazej.chmielewski@cfsa.pl",
                "haslo123",
                Department.BI.name()
        );
        RegisterUserDTO registerUserDTO11 = new RegisterUserDTO(
                "Hubert",
                "Pioro",
                "hubert.pioro@cfsa.pl",
                "haslo123",
                Department.BI.name()
        );

        RegisterUserDTO registerUserDTO12 = new RegisterUserDTO(
                "Anna",
                "Nolbrzak-Jasińska",
                "anna.nolbrzak-jasinska@cfsa.pl",
                "haslo123",
                Department.BWIA.name()
        );

        RegisterUserDTO registerUserDTO13 = new RegisterUserDTO(
                "Maria",
                "Tarlaga-Drężek",
                "maria.tarlaga-drezek@cfsa.pl",
                "haslo123",
                Department.BWIA.name()
        );

        RegisterUserDTO registerUserDTO14 = new RegisterUserDTO(
                "Adam",
                "Gryziak",
                "adam.gryziak@cfsa.pl",
                "haslo123",
                Department.DWWZ.name()
        );

        RegisterUserDTO registerUserDTO15 = new RegisterUserDTO(
                "Dariusz",
                "Kolaj",
                "dariusz.kolaj@cfsa.pl",
                "haslo123",
                Department.DWWZ.name()
        );

        RegisterUserDTO registerUserDTO16 = new RegisterUserDTO(
                "Maria",
                "Wierzbicka",
                "maria.wierzbicka@cfsa.pl",
                "haslo123",
                Department.BAIO.name()
        );

        RegisterUserDTO registerUserDTO17 = new RegisterUserDTO(
                "Martyna",
                "Zapadka",
                "martyna.zapadka@cfsa.pl",
                "haslo123",
                Department.BAIO.name()
        );

        RegisterUserDTO registerUserDTO18 = new RegisterUserDTO(
                "Beata",
                "Borowiecka",
                "beata.borowiecka@cfsa.pl",
                "haslo123",
                Department.ZARZAD.name()
        );

        RegisterUserDTO registerUserDTO19 = new RegisterUserDTO(
                "Piotr",
                "Szynalski",
                "piotr.szynalski@cfsa.pl",
                "haslo123",
                Department.ZARZAD.name()
        );

        try {
            UserRegisterSuccessedDTO register = authService.register(registerUserDTO, response);
            UserRegisterSuccessedDTO register1 = authService.register(registerUserDTO1, response);
            UserRegisterSuccessedDTO register2 = authService.register(registerUserDTO2, response);
            UserRegisterSuccessedDTO register3 = authService.register(registerUserDTO3, response);
            UserRegisterSuccessedDTO register4 = authService.register(registerUserDTO4, response);
            UserRegisterSuccessedDTO register5 = authService.register(registerUserDTO5, response);
            UserRegisterSuccessedDTO register6 = authService.register(registerUserDTO6, response);
            UserRegisterSuccessedDTO register7 = authService.register(registerUserDTO7, response);
            UserRegisterSuccessedDTO register8 = authService.register(registerUserDTO8, response);
            UserRegisterSuccessedDTO register9 = authService.register(registerUserDTO9, response);
            UserRegisterSuccessedDTO register10 = authService.register(registerUserDTO10, response);
            UserRegisterSuccessedDTO register11 = authService.register(registerUserDTO11, response);
            UserRegisterSuccessedDTO register12 = authService.register(registerUserDTO12, response);
            UserRegisterSuccessedDTO register13 = authService.register(registerUserDTO13, response);
            UserRegisterSuccessedDTO register14 = authService.register(registerUserDTO14, response);
            UserRegisterSuccessedDTO register15 = authService.register(registerUserDTO15, response);
            UserRegisterSuccessedDTO register16 = authService.register(registerUserDTO16, response);
            UserRegisterSuccessedDTO register17 = authService.register(registerUserDTO17, response);
            UserRegisterSuccessedDTO register18 = authService.register(registerUserDTO18, response);
            UserRegisterSuccessedDTO register19 = authService.register(registerUserDTO19, response);

            authService.enableUser(register.uuid());
            authService.enableUser(register1.uuid());
            authService.enableUser(register2.uuid());
            authService.enableUser(register3.uuid());
            authService.enableUser(register4.uuid());
            authService.enableUser(register5.uuid());
            authService.enableUser(register6.uuid());
            authService.enableUser(register7.uuid());
            authService.enableUser(register8.uuid());
            authService.enableUser(register9.uuid());
            authService.enableUser(register10.uuid());
            authService.enableUser(register11.uuid());
            authService.enableUser(register12.uuid());
            authService.enableUser(register13.uuid());
            authService.enableUser(register14.uuid());
            authService.enableUser(register15.uuid());
            authService.enableUser(register16.uuid());
            authService.enableUser(register17.uuid());
            authService.enableUser(register18.uuid());
            authService.enableUser(register19.uuid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}