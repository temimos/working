package com.example.demo.Controllers;

import com.cloudinary.utils.ObjectUtils;
import com.example.demo.CloudinaryConfig;
import com.example.demo.Home;
import com.example.demo.security.User;
import com.example.demo.security.UserService;
import com.example.demo.security.UserRepository;
import com.example.demo.Repositories.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class HomePageController {
@Autowired
 private   HomeRepository homeRepository;
@Autowired
 UserService userService;

@Autowired
 UserRepository userRepository;

@Autowired
CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listCourses(Model model){
        model.addAttribute("homes", homeRepository.findAll());
        if(userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
//        model.addAttribute("mD5Util", new MD5Util());

        return "list";
    }
    @GetMapping("/add")
    public String homeForm(Principal principal, Model model) {
        model.addAttribute("imageLabel", "Upload Image");
        model.addAttribute("user", userService.getUser());
        model.addAttribute("home", new Home());
        return "homeform";
    }

 

    @PostMapping("/process")
    public String processActor(@ModelAttribute Home home,
                               @RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return "redirect:add/";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            home.setPic(uploadResult.get("url").toString());
            homeRepository.save(home);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/add";
        }
        homeRepository.save(home);
        return "redirect:/";
    }
//    @PostMapping("/process")
//    public String processForm(@Valid @ModelAttribute("home") Home home,
//                              BindingResult result,
//                              @RequestParam("file") MultipartFile file,
//                              Model model) {
//        model.addAttribute("imageLabel", "Upload Image");
//        model.addAttribute("user", userService.getUser());
//        //check for errors on the form
//        if (result.hasErrors()) {
//            for (ObjectError e : result.getAllErrors()) {
//                System.out.println(e);
//            }
//            return "homeform";
//        }
//
//        if (!file.isEmpty()) {
//            try {
//                Map uploadResult = cloudc.upload(
//                        file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
//                String url = uploadResult.get("url").toString();
//                String uploadedName = uploadResult.get("public_id").toString();
//                String transformedImage = cloudc.createUrl(uploadedName, 150, 150);
//                home.setPic(transformedImage);
//                home.setUser(userService.getUser());
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "redirect:/add";
//            }
//        } else {
//            //if file is empty and there is a picture path then save item
//            if (home.getPic().isEmpty()) {
//                return "homeform";
//            }
//        }
//        homeRepository.save(home);
//        return "redirect:/";
//    }

    @RequestMapping("/detail/{id}")
    public String showHome(@PathVariable("id") long id, Model model) {
        model.addAttribute("home", homeRepository.findById(id).get());
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateHome(@PathVariable("id") long id, Model model) {
        model.addAttribute("home", homeRepository.findById(id).get());
        if (userService.getUser() != null) {
            model.addAttribute("user", userService.getUser());
        }
        return "homeform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteHome(@PathVariable("id") long id) {
        homeRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/myprofile")
    public String getProfile(Principal principal, Model model) {
        User user = userService.getUser();
        model.addAttribute("user", user);
        model.addAttribute("myuser", user);
        return "profile";
    }

    @RequestMapping("/user/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        model.addAttribute("myuser", userService.getUser());
        return "profile";
    }
    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }
}





