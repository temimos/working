package com.example.demo.security;

import com.example.demo.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository <User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);


    Set<User> findAllByFollowers(User user);

    Set<User> findAllByFollowings(User user);
}

