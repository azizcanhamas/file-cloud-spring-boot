package com.h2group.h2example;

import jakarta.persistence.*;

@Entity
@Table(name = "citizens")
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "citizen_number")
    private String citizen_number;

    public Citizen() {

    }
    public Citizen(String name, String surname, String citizen_number) {
        this.name = name;
        this.surname = surname;
        this.citizen_number = citizen_number;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getCitizen_number() {
        return citizen_number;
    }
    public void setCitizen_number(String citizen_number) {
        this.citizen_number = citizen_number;
    }

    @Override
    public String toString() {
        return "Citizen [id=" + id + ", name=" + name + ", surname=" + surname + ", citizen_number=" + citizen_number + "]";
    }

}