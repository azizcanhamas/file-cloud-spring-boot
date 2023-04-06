package com.h2group.h2example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/citizenApi")
public class CitizenController {
    @Autowired
    CitizenRepository citizenRepository;

    @GetMapping("/citizens")
    public ResponseEntity<List<Citizen>> getAllCitizens(@RequestParam(required = false) String name) {
        try {
            List<Citizen> citizens = new ArrayList<Citizen>();

            if (name == null)
                citizenRepository.findAll().forEach(citizens::add);
            else
                citizenRepository.findByName(name).forEach(citizens::add);

            if (citizens.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(citizens, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/citizens/{id}")
    public ResponseEntity<Citizen> getCitizenById(@PathVariable("id") long id) {
        Optional<Citizen> citizenData = citizenRepository.findById(id);

        if (citizenData.isPresent()) {
            return new ResponseEntity<>(citizenData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/citizens")
    public ResponseEntity<Citizen> createCitizen(@RequestBody Citizen citizen) {
        try {
            Citizen _citizen = citizenRepository
                    .save(new Citizen(citizen.getName(), citizen.getSurname(), citizen.getCitizen_number()));
            return new ResponseEntity<>(_citizen, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/citizens/{id}")
    public ResponseEntity<Citizen> updateCitizen(@PathVariable("id") long id, @RequestBody Citizen citizen) {
        Optional<Citizen> citizenData = citizenRepository.findById(id);
        if (citizenData.isPresent()) {
            Citizen _citizen = citizenData.get();
            _citizen.setName(citizen.getName());
            _citizen.setSurname(citizen.getSurname());
            _citizen.setCitizen_number(citizen.getCitizen_number());
            return new ResponseEntity<>(citizenRepository.save(_citizen), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/citizens/{id}")
    public ResponseEntity<HttpStatus> deleteCitizen(@PathVariable("id") long id) {
        try {
            citizenRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/citizens")
    public ResponseEntity<HttpStatus> deleteAllCitizens() {
        try {
            citizenRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
