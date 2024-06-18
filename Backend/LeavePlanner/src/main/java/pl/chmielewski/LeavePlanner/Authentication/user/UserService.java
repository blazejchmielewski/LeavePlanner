package pl.chmielewski.LeavePlanner.Authentication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.api.exception.UserNotFoundException;
import pl.chmielewski.LeavePlanner.Authentication.request.CreateUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.request.UpdateUserDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findUserById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    public User createUser(CreateUserDTO createUserDTO){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.USER);

        User user = new User(createUserDTO.firstname(),
                createUserDTO.lastname(),
                createUserDTO.email());
        user.setUuid(UUID.randomUUID().toString());
        user.setDepartment(Department.BAIO);
        user.setPassword(passwordEncoder.encode(createUserDTO.password()));
        user.setEnabled(true);
        user.setRole(roleSet);
        return userRepository.save(user);
    }

    public void updateUser(Long id, UpdateUserDTO updateUserDTO){
        User userById = getUserById(id);
        userById.setEmail(updateUserDTO.email());
        userById.setFirstname(updateUserDTO.firstname());
        userById.setLastname(updateUserDTO.lastname());
        userRepository.save(userById);
    }

    public void deleteUser(Long id){
        userRepository.delete(getUserById(id));
    }
}
