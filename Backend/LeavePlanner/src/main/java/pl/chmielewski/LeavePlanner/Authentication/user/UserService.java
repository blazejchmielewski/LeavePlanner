package pl.chmielewski.LeavePlanner.Authentication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundByEmailException;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundByIdException;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundByUuidException;
import pl.chmielewski.LeavePlanner.Authentication.api.request.RegisterUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.api.request.UpdateUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.token.Token;
import pl.chmielewski.LeavePlanner.Authentication.token.TokenRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundByIdException(id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundByEmailException(email));
    }

    public User getUserByUuid(String uuid){
        return userRepository.findUserByUuid(uuid).orElseThrow(()-> new UserNotFoundByUuidException(uuid));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public Long setRoleAdmin(Long id){
        User userById = getUserById(id);
        userById.setRole(Role.ADMIN);
        userById.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userById);
        return userById.getId();
    }

    public Long setRoleUser(Long id){
        User userById = getUserById(id);
        userById.setRole(Role.USER);
        userById.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userById);
        return userById.getId();
    }

    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User createUser(RegisterUserDTO createUserDTO) {
        User user = new User(createUserDTO.firstname(),
                createUserDTO.lastname(),
                createUserDTO.email(),
                LocalDateTime.now(),
                LocalDateTime.now());
        user.setUuid(UUID.randomUUID().toString());
        user.setDepartment(Department.BAIO);
        user.setPassword(passwordEncoder.encode(createUserDTO.password()));
        user.setEnabled(false);
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    public void updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User userById = getUserById(id);
        userById.setEmail(updateUserDTO.email());
        userById.setFirstname(updateUserDTO.firstname());
        userById.setLastname(updateUserDTO.lastname());
        userById.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userById);
    }

    public void deleteUser(Long id) {
        Optional<List<Token>> allByUser = tokenRepository.findAllByUser(getUserById(id));
        allByUser.ifPresent(tokenRepository::deleteAll);
        userRepository.delete(getUserById(id));
    }

    public void changePassword(String password, String uuid){
        User userByUuid = getUserByUuid(uuid);
        userByUuid.setPassword(passwordEncoder.encode(password));
        userByUuid.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userByUuid);
    }
}
