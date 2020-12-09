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

    /*
    @PostMapping("Gebruiker/{naam}/{ww}/{email}/{geboorte}")
    public Gebruiker nieuweGebruiker(@PathVariable("naam") String naam, @PathVariable("ww") String ww, @PathVariable("email") String email, @PathVariable("geboorte") String geboorte) {
        System.out.println("nieuweGebruiker functie");
        Gebruiker geb = new Gebruiker();
        geb.setNaam(naam);
        geb.setWw(ww);
        geb.setEmail(email);
        LocalDate gebParse = LocalDate.parse(geboorte);
        geb.setGeboorte(gebParse);
        gc.maakGebruiker(geb);
        return geb;
    }
    */

    /*
    @PostMapping("Uitleen/{naam}/{titel}/{wtId}")
    public Uitleen nieuweUitleen(@PathVariable("naam") String naam, @PathVariable("titel") String titel, @PathVariable("wtId") String wtId)  {
        System.out.println("Nieuwe Uitleen aanmaken");
        Uitleen ul = new Uitleen();
        ul.setLener(naam);
        ul.setBoekTitel(titel);
        ul.setBeginDatum(LocalDate.now());
        ul.setWtId(wtId);
        uc.maakUitleen(ul);
        return ul;
    }
    */
    

    @PostMapping("nieuweuitleen")
    public void nieuweUitleen(@RequestBody Uitleen uitleen)  {
        System.out.println("Nieuwe Uitleen: " + uitleen.getLener()+ " - " + uitleen.getBoekTitel());
        uc.uitleenOpslaan(uitleen);
    }

    @PostMapping("nieuwegebruiker") 
    public void nieuweGebruiker(@RequestBody Gebruiker gebruiker) { // @Requestbody
        System.out.println("Hij doet het! " + gebruiker.getNaam() + " - " + gebruiker.getGeboorte()); 
        System.out.println("Type van geboorte: " + gebruiker.getGeboorte().getClass().getSimpleName());
        gc.gebruikerOpslaan(gebruiker);
    }

    @PostMapping("nieuwboek") 
    public void nieuwBoek(@RequestBody Boek boek) { // @Requestbody
        System.out.println("Hij doet het! " + boek.getTitel() + " " + boek.getIsbn()); // boek.getTitel()
        bc.boekOpslaan(boek);
    }

    @PostMapping("allegebruikers")
    public Iterable<Gebruiker> alleGebruikers() {
        return gc.alleGebruikers();
    }

    @PostMapping("eengebruiker")
    public GebruikerDTO eenGebruiker() {
        Gebruiker gebruiker = gc.eenGebruiker(35);
        GebruikerDTO gdt = new GebruikerDTO(gebruiker);
        return gdt;
    }

    @PostMapping("alleboeken")
    public Iterable<Boek> alleBoeken() {
        return bc.alleBoeken();
    }

    @PostMapping("alleuitleningen")
    public Iterable<Uitleen> alleUitleningen() {
        return uc.alleUitleen();
    }

    @PostMapping("allegebruikersnamen")
    public Iterable<String> alleGebruikersNamen() {
        return gc.alleGebruikersNamen();
    }

    @PostMapping("alleboektitels/{test}")
    public Iterable<String> alleBoekTitels(@PathVariable("test") boolean test) {
        return bc.alleBoekTitels(test);
    }
    
    /*
    @PostMapping("test/{gebruikerid}/{boekid}")
    public void test(@PathVariable("gebruikerid") int gebruikerid, @PathVariable("boekid") int boekid) {
        bc.leenBoek(gebruikerid, boekid);
    }
    */

    @PostMapping("test/{email}/{ww}")
    public boolean magInloggen(@PathVariable("email") String email, @PathVariable("ww") String ww) {
        return gc.magInloggen(email, ww);
    }
    
    /*
    @PostMapping("test")
    public boolean magInloggen(@RequestBody Gebruiker login){
        return gc.magInloggen(login);
    }
    */


}