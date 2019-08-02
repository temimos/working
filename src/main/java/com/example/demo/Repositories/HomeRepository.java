package com.example.demo.Repositories;

import com.example.demo.Home;
import com.example.demo.security.User;
import org.springframework.data.repository.CrudRepository;

public interface HomeRepository extends CrudRepository<Home, Long> {
    Iterable<Home> findAllByUser(User user);

    Iterable<Home> findAllByOrderByPostedDateTimeDesc();
}