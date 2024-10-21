package by.dytni.test.service;

import by.dytni.test.config.UserDetailsImpl;
import by.dytni.test.models.User;
import by.dytni.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;
    public void save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    ///
    /// пока под сомнением
    ///
    public UserDetails findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
    public void delete(User user) {
        userRepository.delete(user);
    }
}
