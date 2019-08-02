package com.example.demo.Controllers;

import com.example.demo.security.User;
import com.example.demo.security.UserRepository;
import com.example.demo.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
     UserService userService;

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "redirect:/";
    }


    @RequestMapping("/login")
    public String login() {
        return "login";
    }



    @PostMapping("/forgot-password")
    public String forgetPassword() {
        return "/";
    }

    @GetMapping("/terms")
    public String getTerms() {
        return "termsandconditions";
    }

    @RequestMapping("/updateUser")
    public String viewUser(Model model,
                           HttpServletRequest request,
                           Authentication authentication,
                           Principal principal) {
       /* Boolean isAdmin = request.isUserInRole("ADMIN");
        Boolean isUser = request.isUserInRole("USER");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();*/
//        String username = principal.getName();
        model.addAttribute("page_title", "Update Profile");
        model.addAttribute("user", userService.getUser());
        return "registration";
    }

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        String username= principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }
}
