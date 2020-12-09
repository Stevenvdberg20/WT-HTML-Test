package com.example.demo.domein;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    long id;
    String naam;
    String ww;
    String email;
    LocalDate geboorte;
   
    @OneToMany
    List<Boek> boeken;


    public void addBoek(Boek boek) {
        boeken.add(boek);
    }

    public List<Boek> getBoeken() {
        return boeken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @JsonIgnore
    public String getWw() {
        return ww;
    }

    public void setWw(String ww) {
        this.ww = ww;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getGeboorte() {
        return geboorte;
    }

    public void setGeboorte(LocalDate geboorte) {
        this.geboorte = geboorte;
    }

}
