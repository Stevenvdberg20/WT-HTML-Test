package com.example.demo.view;

import com.example.demo.controller.gebruikerController;
import com.example.demo.controller.uitleenController;
import com.example.demo.domein.Gebruiker;
import com.example.demo.domein.Uitleen;
import com.example.demo.dto.GebruikerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.controller.boekController;
import com.example.demo.domein.Boek;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import java.time.LocalDate;

@RestController
public class biebEP {

    @Autowired
    gebruikerController gc;

    @Autowired
    boekController bc;

    @Autowired
    uitleenController uc;


    // Aanmaken nieuwe uitleen (= interactie tussen gebruiker en boek)
    @PostMapping("nieuweuitleen")
    public Boek nieuweUitleen(@RequestBody Uitleen uitleen)  {
        System.out.println("Nieuwe Uitleen: " + uitleen.getLener()+ " - " + uitleen.getBoekTitel());
        Boek boek = bc.getBoek(uitleen.getWtId());
        boek.setStatus("uitgeleend");
        bc.updateBoek(boek);
        uc.uitleenOpslaan(uitleen);
        return boek;
    }

    // Aanmaken nieuwe gebruiker
    @PostMapping("nieuwegebruiker") 
    public void nieuweGebruiker(@RequestBody Gebruiker gebruiker) { 
        gc.gebruikerOpslaan(gebruiker);
    }

    // Aanmaken nieuw boek
    @PostMapping("nieuwboek") 
    public void nieuwBoek(@RequestBody Boek boek) {
        bc.boekOpslaan(boek);
    }

    // Aanmaken nieuw exemplaar van boek dat al bekend is in database
    @PostMapping("nieuwexemplaar") 
    public void nieuwExemplaar(@RequestBody Boek boektitel) { // @Requestbody
        bc.nieuwExemplaar(boektitel);
    }

    // Geeft alle objecten gebruikers in een list
    @PostMapping("allegebruikers")
    public Iterable<Gebruiker> alleGebruikers() {
        return gc.alleGebruikers();
    }

    // Verwijder geselecteerde gebruikers
    @PostMapping("verwijdergebruikers")
    public void verwijderGebruikers(@RequestBody Iterable<Long> lijst){
        gc.verwijderGebruikers(lijst);
    }

    /* // Test DTO met Felix (wordt niet actief gebruikt)
    @PostMapping("eengebruiker")
    public GebruikerDTO eenGebruiker() {
        Gebruiker gebruiker = gc.eenGebruiker(35);
        GebruikerDTO gdt = new GebruikerDTO(gebruiker);
        return gdt;
    }
    */

    // Geeft alle objecten boeken in een list
    @PostMapping("alleboeken")
    public Iterable<Boek> alleBoeken() {
        return bc.alleBoeken();
    }

    // Geeft alle objecten uitleningen in een list
    @PostMapping("alleuitleningen")
    public Iterable<Uitleen> alleUitleningen() {
        return uc.alleUitleen();
    }

    // Geeft de naam van iedere bestaande gebruiker in een list
    @PostMapping("allegebruikersnamen")
    public Iterable<String> alleGebruikersNamen() {
        return gc.alleGebruikersNamen();
    }

    @PostMapping("gebruikersnamenSort")
    public Iterable<Gebruiker> gebruikersnamenSort() {
        return gc.sorteerGebruiker();
    }

    @PostMapping("boekexemplaarSort")
    public Iterable<Boek> boekexemplaarSort() {
        return bc.sorteerBoek();
    }

    @PostMapping("alleboektitels/{test}")
    public Iterable<String> alleBoekTitels(@PathVariable("test") boolean test) {
        return bc.alleBoekTitels(test);
    }
    
    @PostMapping("autorisatie/{email}")
    public String autorLevel(@PathVariable("email") String email) {
        //getAutorisatie(email);
        return gc.autorisatieLevel(email);
    }
    
    // Verifieert ingevoerde inloggegevens en bepaalt authorisatie niveau
    @PostMapping("inlog/{email}/{ww}")
    public boolean magInloggen(@PathVariable("email") String email, @PathVariable("ww") String ww) {
        return gc.magInloggen(email, ww);
    }

    // Zet de einddatum van een uitleen op de huidige datum, en zet boekstatus naar beschikbaar
    @PostMapping("boekinleveren")
    public Boek boekInleveren(@RequestBody String wtid)  {
        Boek boek = bc.getBoek(wtid);
        Uitleen uitleen = uc.getUitleen(wtid);
        boek.setStatus("beschikbaar");
        bc.updateBoek(boek);
        uc.updateUitleen(uitleen);
        return boek;
    }

    // Verwijdert geselecteerde boeken
    @PostMapping("verwijderboeken")
    public void verwijderBoeken(@RequestBody Iterable<Long> lijst){
        System.out.println(lijst.toString());
        bc.verwijderBoeken(lijst);
    }

}