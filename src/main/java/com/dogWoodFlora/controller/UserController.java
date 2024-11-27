package com.dogWoodFlora.controller;

import com.dogWoodFlora.entity.UserEntity;
import com.dogWoodFlora.repository.UserRepository;
import com.dogWoodFlora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    // Inject the special key from application.properties
    @Value("${admin.special.key}")
    private String adminSpecialKey;
    @Autowired
    private  UserRepository userRepository;
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserEntity()); // Bind empty user for the form
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(
            @ModelAttribute("user") UserEntity user,
            @RequestParam(value = "specialKey", required = false) String specialKey,
            BindingResult result,
            Model model) {

        // Validate the role
        if (user.getRole() == UserEntity.Role.ROLE_ADMIN) {
            if (specialKey == null || !specialKey.equals(adminSpecialKey)) {
                model.addAttribute("error", "Invalid special key for Admin role.");
                return "register"; // Return back to the form with an error
            }
        }

        if (result.hasErrors()) {
            return "register";
        }

        // Hash the password and save the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role if none provided
        if (user.getRole() == null) {
            user.setRole(UserEntity.Role.ROLE_USER);
        }

        userRepository.save(user);
        return "redirect:/login?registered"; // Redirect to login page
    }

    @GetMapping("/user/home")
    public String userHome() {
        return "event";
    }

    // This method handles requests to the "/customers/index" path
    @GetMapping("/users/index")
    public String showCustomerIndex() {
        return "forward:/customerIndex.html"; // Forward to the static HTML file
    }

    @GetMapping("/users/view-event")
    public String showCustomerEvent() {
        return "forward:/customerEvent.html"; // Forward to the static HTML file
    }

    @GetMapping("/users/product page")
    public String showCustomerProducts() {
        return "cProductPage";
    }
}