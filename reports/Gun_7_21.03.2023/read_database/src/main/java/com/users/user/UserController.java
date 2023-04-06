package com.users.user;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Element;
import java.util.*;

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

    @PostMapping(path = "/getAll")
    public ResponseEntity<Object> getAll() {

//        Iterator<User> citizens=userRepository.findAll().iterator();
//        StringBuilder tmp= new StringBuilder();
//        while(citizens.hasNext()){
//            User user=(User)citizens.next();
//            tmp.append(user.getId()).append("   ").append(user.getName()).append(user.getSurname()).append(user.getCitizen_number()).append("\n");
//        }
//        return tmp.toString();



        Iterator<User> citizens=userRepository.findAll().iterator();
        String[]tmp_arr;
        Map<String, String[]> data = new HashMap<>();
        while(citizens.hasNext()){
            User user=(User)citizens.next();
            tmp_arr=new String[3];
            tmp_arr[0]=user.getName();
            tmp_arr[1]=user.getSurname();
            tmp_arr[2]=user.getCitizen_number();
            data.put(String.valueOf(user.getId()),tmp_arr);
        }

        return new ResponseEntity<>(data, HttpStatus.OK);

    }


}
