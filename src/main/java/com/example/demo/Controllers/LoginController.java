//package com.example.demo.Controllers;
//
//import com.cloudinary.utils.ObjectUtils;
//import com.example.demo.CloudinaryConfig;
//import com.example.demo.Home;
//import com.example.demo.security.User;
//import com.example.demo.security.UserService;
//import com.example.demo.security.UserRepository;
//import com.example.demo.Repositories.HomeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.validation.Valid;
//import java.io.IOException;
//import java.security.Principal;
//import java.util.Map;
//
//
//@Controller
//public class LoginController {
//        @Autowired
//        private HomeRepository homeRepository;
//        @Autowired
//        UserService userService;
//
//        @Autowired
//        UserRepository userRepository;
//
//        @Autowired
//        CloudinaryConfig cloudc;
//
//
//        @GetMapping("/register")
//        public String showRegistrationPage(Model model) {
//                model.addAttribute("page_title", "New User Registration");
//                model.addAttribute("user", new User());
//                return "registration";
//        }
//
//        @PostMapping("/register")
//        public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
//
//                if (result.hasErrors()) {
//                        return "registration";
//                } else {
//                        userService.saveUser(user);
//                        model.addAttribute("message", "User Account Created");
//                }
//                return "redirect:/";
//        }
//}

//
//@PostMapping("/register")
//public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
//        BindingResult result,
//        Model model/*,
//                                          @RequestParam("password") String password*/
//        ) {
//        model.addAttribute("page_title", "Update Profile");
//        if (result.hasErrors()) {
//        return "registration";
//        } else {
//        //Update User and Admin
//        boolean isUser = userRepository.findById(user.getId()).isPresent();
//        if (isUser) {
//        //updating with existed username
//        if (userRepository.findByUsername(user.getUsername()) != null &&
//        //current user
//        !userRepository.findByUsername(user.getUsername()).equals(user)) {
//        model.addAttribute("home",
//        "We already have a username called " + user.getUsername() + "!" + " Try something else.");
//        return "registration";
//        }
//
//        User userInDB = userRepository.findById(user.getId()).get();
//        userInDB.setFirstName(user.getFirstName());
//        userInDB.setLastName(user.getLastName());
//        userInDB.setEmail(user.getEmail());
//        userInDB.setUsername(user.getUsername());
//        userInDB.setPassword(userService.encode(user.getPassword()));
//        userInDB.setEnabled(true);
//        userRepository.save(userInDB);
//        model.addAttribute("home", "User Account Successfully Updated");
//        }
//        //New User
//        else {
//        //Registering with existed username
//        if (userRepository.findByUsername(user.getUsername()) != null) {
//        model.addAttribute("home",
//        "We already have a username called " + user.getUsername() + "!" + " Try something else.");
//        return "registration";
//        } else {
//        user.setPassword(userService.encode(user.getPassword()));
//            userService.saveUser(user);
//            model.addAttribute("home", "User Account Created");
//
//        }
//        }
//        }
//        return "login";
//        }
//
//
//        }
