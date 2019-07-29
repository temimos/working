package com.example.demo.Controllers;

import com.example.demo.security.User;
import com.example.demo.security.UserRepository;
import com.example.demo.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value = "/register", method= RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
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
        return "index";
        // return resistration;
    }

//        @RequestMapping("/")
//  public String index()
//   {
//       return "index";
//    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        String username= principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }


}

