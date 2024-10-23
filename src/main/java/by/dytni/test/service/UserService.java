package by.dytni.test.service;

import by.dytni.test.models.Role;
import by.dytni.test.models.User;
import by.dytni.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository usersRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void saveUser(String username,String password) {
        User user = new User();
        user.setUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        user.setRoles(Set.of(Role.USER));
        usersRepository.save(user);
    }

    public Optional<User> getByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
    public List<User> getUsers() {
        return usersRepository.findAll();
    }
    public void deleteUser(Long id) {
        if(usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        }
    }
    public void updateUser(Long id, String username, String password, String role) {
        User user = usersRepository.findById(id).orElse(null);
        if (user == null) {
            return;
        }
        if(!(username == null || username.isEmpty())) {
            user.setUsername(username);
        }
        if(!(password == null || password.isEmpty())) {
            user.setPassword(encoder.encode(password));
        }
        if(!(role == null || role.isEmpty())) {
            user.setRoles(Set.of(Role.valueOf(role)));
        }
    }

}