package pl.chmielewski.LeavePlanner.Authentication.user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundByEmailException;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundByIdException;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundByUuidException;
import pl.chmielewski.LeavePlanner.Authentication.api.request.RegisterUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.api.request.UpdateUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserDataResponse;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserProfileData;
import pl.chmielewski.LeavePlanner.Authentication.token.Token;
import pl.chmielewski.LeavePlanner.Authentication.token.TokenService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User getUserByToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return tokenService.getUserByToken(authorizationHeader.substring(7));
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserDataResponse> getAllUsersToTable() {
        List<UserDataResponse> users = new ArrayList<>();
        userRepository.findEnabledUsers().forEach(u -> {
            users.add(new UserDataResponse(
                    u.getId(),
                    u.getUuid(),
                    u.getFirstname(),
                    u.getLastname(),
                    u.getEmail(),
                    u.getDepartment().name(),
                    u.getRole().name()
            ));
        });
        return users;
    }

    public UserProfileData getUserProfileDate(HttpServletRequest request) {
        User userByToken = getUserByToken(request);
        return new UserProfileData(
                userByToken.getFirstname() + " " + userByToken.getLastname(),
                userByToken.getEmail(),
                userByToken.getRole().name(),
                userByToken.getDepartment().name(),
                userByToken.isEnabled(),
                userByToken.getCreatedAt(),
                userByToken.getDeactivatedAt()
        );
    }

    public List<User> getUsersByDepartment(Department department) {
        return userRepository.findByDepartment(department);
    }


    public User getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundByIdException(id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundByEmailException(email));
    }

    public User getUserByUuid(String uuid) {
        return userRepository.findUserByUuid(uuid).orElseThrow(() -> new UserNotFoundByUuidException(uuid));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Long setRoleAdmin(Long id) {
        User userById = getUserById(id);
        userById.setRole(Role.USER);
        userById.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userById);
        return userById.getId();
    }

    public Long setRoleUser(Long id) {
        User userById = getUserById(id);
        userById.setRole(Role.ADMIN);
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
                LocalDateTime.now(),
                null);
        user.setUuid(UUID.randomUUID().toString());
        user.setDepartment(Department.valueOf(createUserDTO.department()));
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
        userById.setDepartment(Department.valueOf(updateUserDTO.department()));
        userById.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userById);
    }

    public void disableUser(Long id) {
        User userById = getUserById(id);
        userById.setEnabled(false);
        userRepository.save(userById);
    }

    public void deleteUser(Long id) {
        Optional<List<Token>> allByUser = tokenService.findAllByUser(getUserById(id));
        allByUser.ifPresent(tokenService::deleteAll);
        userRepository.delete(getUserById(id));
    }

    public void changePassword(String password, String uuid) {
        User userByUuid = getUserByUuid(uuid);
        userByUuid.setPassword(passwordEncoder.encode(password));
        userByUuid.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userByUuid);
    }

    public List<String> getDepartments() {
        return Arrays.stream(Department.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
