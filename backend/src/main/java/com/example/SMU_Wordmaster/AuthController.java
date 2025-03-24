package com.example.SMU_Wordmaster;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html로 이동
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // register.html로 이동
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.findByUsername(username).isPresent()) {
            model.addAttribute("error", "이미 존재하는 사용자입니다.");
            return "register";
        }
        userService.registerUser(username, password);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // home.html로 이동
    }
}
