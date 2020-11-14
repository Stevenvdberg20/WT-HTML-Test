package com.example.demo.domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    long id;
    String naam;
    String ww;
    String email;
    LocalDate geboorte;


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
