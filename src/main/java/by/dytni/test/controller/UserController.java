package by.dytni.test.controller;

import by.dytni.test.models.User;
import by.dytni.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getByUsername")
    public User getUserByUsername(@RequestParam(name = "username") String username) {
       return userService.getByUsername(username).orElseThrow(() -> new
               NoSuchElementException("No user found with username " + username));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update")
    public void updateUser( @RequestParam(name = "userId") Long userId,
                            @RequestParam(name = "username", required = false) String username,
                            @RequestParam(name = "password", required = false) String password,
                            @RequestParam(name = "role", required = false) String role
    ){
        userService.updateUser(userId, username, password, role);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam(name = "userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public void addUser(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password) {
        userService.saveUser(username, password);
    }
}
