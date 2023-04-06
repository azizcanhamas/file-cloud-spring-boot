package com.users.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping(path = "/add")
    public @ResponseBody String addUser(@RequestParam String name, @RequestParam String surname) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        userRepository.save(user);
        return "Kayit islemi basariyla gerceklestirilmistir!";
    }
}
