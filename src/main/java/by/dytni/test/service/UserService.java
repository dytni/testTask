package by.dytni.test.service;

import by.dytni.test.models.Role;
import by.dytni.test.models.User;
import by.dytni.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository usersRepository;
    public void saveUser(String username,String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(Set.of(Role.USER));
        usersRepository.save(user);
    }

    public boolean checkLogin(String username) {
        return usersRepository.existsByUsername(username);
    }

    public Optional<User> getByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}