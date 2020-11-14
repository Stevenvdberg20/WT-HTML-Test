package com.example.demo.view;

import com.example.demo.controller.gebruikerController;
import com.example.demo.domein.gebruiker;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class biebEP {

    @Autowired
    gebruikerController gc;

    @GetMapping("gebTest")
    public gebruiker gebTest() {
        System.out.println("gebTest functie");
        gebruiker geb = new gebruiker();
        geb.setNaam("Steve");
        geb.setWw("ww");
        geb.setEmail("email");
        // LocalDate geboorte = LocalDate.of(1234, 12, 31);
        LocalDate geboorte = LocalDate.parse("2020-07-31");
        geb.setGeboorte(geboorte);
        gc.maakGebruiker(geb);
        return geb;
    }

    @GetMapping("nieuwe/{naam}/{ww}/{email}") // TODO: geboorte hier toevoegen
    public gebruiker nieuweGebruiker(@PathVariable("naam") String naam, @PathVariable("ww") String ww, @PathVariable("email") String email) { // TODO: Vergeet hier niet weer geboorte toe te voegen als parameter
        System.out.println("nieuweGebruiker functie");
        gebruiker geb = new gebruiker();
        geb.setNaam(naam);
        geb.setWw(ww);
        geb.setEmail(email);
        // geb.setGeboorte(geboorte);
        // TODO: Geboorte, de rest doet het
        gc.maakGebruiker(geb);
        return geb;
    }
}
