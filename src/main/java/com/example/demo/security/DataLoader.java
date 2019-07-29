package com.example.demo.security;

import com.example.demo.Home;
import com.example.demo.Repositories.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;


@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    HomeRepository homeRepository;


    @Override
    public void run(String... strings) throws Exception{
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("jim@jim.com", "Pa$$word2019", "jim", "jimmerson",true,"jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);
        user = new User("admin@admin.com","Pa$$word2019","Admin","User",true,"admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);


        User dave = new User("dave45678@gmail.com",
                "password",
                "David",
                "Wolf",
                true,
                "dave");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(dave);

        Home home = new Home("Mother's Day",
                LocalDateTime.of(2019, 07, 15, 14, 15), "Happy mother day to the most loving mom in the world",
              "https://res.cloudinary.com/queentemi/image/upload/v1564077655/q24gb3dfmqjbgrogcpvc.jpg",user);

        homeRepository.save(home);

        home = new Home("Fathers Day",
                LocalDateTime.of(2019, 06, 15, 14, 15), "Happy fathers day to the most father mom in the world",
                "https://res.cloudinary.com/queentemi/image/upload/v1564077852/jlmzcsoftxcni4rjcjzp.jpg",user);
//
//        homeRepository.save(home);
    }
}

