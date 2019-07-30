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

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/forgot-password")
    public String forgetPassword() {
        return "/";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("page_title", "New User Registration");
        model.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping(value="/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        if(result.hasErrors())
        {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "redirect:/";

//    @PostMapping("/register")
//    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
//                                          BindingResult result,
//                                          Model model/*,
//                                          @RequestParam("password") String password*/
//    ) {
//        model.addAttribute("page_title", "Update Profile");
//        if (result.hasErrors()) {
//            return "register";
//        } else {
//            //Update User and Admin
//            boolean isUser = userRepository.findById(user.getId()).isPresent();
//            if (isUser) {
//                //updating with existed username
//                if (userRepository.findByUsername(user.getUsername()) != null &&
//                        //current user
//                        !userRepository.findByUsername(user.getUsername()).equals(user)) {
//                    model.addAttribute("message",
//                            "We already have a username called " + user.getUsername() + "!" + " Try something else.");
//                    return "registration";
//                }
//
//                User userInDB = userRepository.findById(user.getId()).get();
//                userInDB.setFirstName(user.getFirstName());
//                userInDB.setLastName(user.getLastName());
//                userInDB.setEmail(user.getEmail());
//                userInDB.setUsername(user.getUsername());
//                userInDB.setPassword(userService.encode(user.getPassword()));
//                userInDB.setEnabled(true);
//                userRepository.save(userInDB);
//                model.addAttribute("message", "User Account Successfully Updated");
//            }
//            //New User
//            else {
//                //Registering with existed username
//                if (userRepository.findByUsername(user.getUsername()) != null) {
//                    model.addAttribute("message",
//                            "We already have a username called " + user.getUsername() + "!" + " Try something else.");
//                    return "registration";
//                } else {
//                    user.setPassword(userService.encode(user.getPassword()));
//                    userService.saveUser(user);
//                    model.addAttribute("message", "User Account Successfully Created");
//                }
//            }
//        }
//        return "login";
    }

    @GetMapping("/termsandconditions")
    public String getTermsAndCondition() {
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
