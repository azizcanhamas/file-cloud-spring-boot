package com.users.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(path = "/user")
public class CitizenController {
    @Autowired
    private CitizenRepository citizenRepository;
    @PostMapping(path = "/add")
    public @ResponseBody String addUser(@RequestParam String name, @RequestParam String surname) {
        Citizen user = new Citizen();
        user.setName(name);
        user.setSurname(surname);
        citizenRepository.save(user);
        return "Kayit islemi basariyla gerceklestirilmistir!";
    }
    @PostMapping(path = "/getAll")
    public ResponseEntity<Object> getAll() {
        Iterator<Citizen> citizens= citizenRepository.findAll().iterator();
        String[]tmp_arr;
        Map<String, String[]> data = new HashMap<>();
        while(citizens.hasNext()){
            Citizen user=(Citizen)citizens.next();
            tmp_arr=new String[3];
            tmp_arr[0]=user.getName();
            tmp_arr[1]=user.getSurname();
            tmp_arr[2]=user.getCitizen_number();
            data.put(String.valueOf(user.getId()),tmp_arr);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
