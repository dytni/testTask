package by.dytni.test.controller;


import by.dytni.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginSignUpController {

    private final UserService userService;

    @PostMapping("/login/signup")
    public String signUpUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            RedirectAttributes redirectAttributes)
    {
        if(userService.checkLogin(username)){
            redirectAttributes.addFlashAttribute("error", "This username is already in use!");
            return "redirect:/login/signup";
        }
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match!");
            return "redirect:/login/signup";
        }
        userService.saveUser(username,password);
        return "redirect:/login";
    }

    @GetMapping("login/signup")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}
