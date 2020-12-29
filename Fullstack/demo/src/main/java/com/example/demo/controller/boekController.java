package com.example.demo.controller;

import com.example.demo.domein.Boek;
import com.example.demo.domein.Gebruiker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class boekController {

    @Autowired
    boekRepository br;

    @Autowired
    gebruikerRepository gr;

    /*
    public void leenBoek(long gebruikId, long boekId) {
        Boek boekEen = br.findById(boekId).get();
        Gebruiker gebruikerEen = gr.findById(gebruikId).get();
        gebruikerEen.addBoek(boekEen);
        System.out.println("We hebben een boek toegevoegd.");
        gr.save(gebruikerEen);
    }
    */

    // Update een bestaand boek (minimalistische versie van boekOpslaan)
    public void updateBoek(Boek boek) {
        br.save(boek);
    }

    public Iterable<Boek> sorteerBoek() {
        return br.sorteerBoek();
    }
    
    // Aanmaken nieuw boek
    public void boekOpslaan(Boek boek) {
        bepaalExemplaar(boek);
        bepaalTitelCode(boek);
        bepaalWtId(boek);
        boek.setStatus("beschikbaar");
        br.save(boek);
    }

    // Logica voor bepalen van exemplaarnummer
    public void bepaalExemplaar(Boek boek) {
        List<Boek> boeken = br.findByTitel(boek.getTitel()); // Maakt nieuwe lijst met exemplaren met dezelfde titel
        boek.setExemplaar(boeken.size()+1);                  // geeft nieuwe exemplaar een nummer
    }

    // Logica voor bepalen titelCode (= hoeveelste unieke titel in database)
    public void bepaalTitelCode(Boek boek) {
        int titelCode = 0;
        List<Boek> zelfdeBoeken = br.findByTitel(boek.getTitel());
        if (zelfdeBoeken.isEmpty()) {   // Boektitel is nieuw
            List<String> uniekeBoeken = alleUniekeBoeken();    // Tel aantal unieke boeken in database
            titelCode = uniekeBoeken.size() + 1; // +1 wordt nieuwe titelCode
        } else {   // Als Boektitel al in database
            titelCode = zelfdeBoeken.get(0).getTitelCode();
        }
        boek.setTitelCode(titelCode);
    }

    // Logica voor bepalen uniek wtId
    public void bepaalWtId(Boek boek) {
        String wtid = boek.getTitelCode() + "." + boek.getExemplaar();
        boek.setWtId(wtid);
    }

    // Toevoegen nieuw exemplaar van bestaand boek
    public void nieuwExemplaar(Boek boek){
        List<Boek> boeken = br.findByTitel(boek.getTitel());
        Boek refboek = boeken.get(0);
        boek.setIsbn(refboek.getIsbn());
        boekOpslaan(boek);
    }

    //
    public List<String> alleUniekeBoeken() {
        List<String> uniekeBoeken = new ArrayList<String>();    // Lege lijst aanmaken van Strings
        for (Boek t: br.findAll()) {                            // Gaat alle boeken langs
            if (!uniekeBoeken.contains(t.getTitel())) {         // Checkt of de titel al in lijst uniekeBoeken zit
                uniekeBoeken.add(t.getTitel());                 // Voeg unieke TITEL toe aan uniekeBoeken lijst
            }
        }

        return uniekeBoeken;
    }

    public Iterable<Boek> alleBoeken() {
        return br.findAll();
    }

    public List<String> alleBoekTitels(boolean distinct) {

        List<String> boektitels = new ArrayList<String>();
        if(distinct == true){   // geeft alle unieke boektitels
            boektitels = br.getDistinctTitel();
        } else if(distinct == false){ //geeft alle boek exemplaren
            boektitels = br.getTitel();
        }
        
        return boektitels;
    }

    public Boek getBoek(String wtid) {
        return br.getBoek(wtid);
    }

    public void verwijderBoeken(Iterable<Long> ids) {
        for (long id: ids) {
            br.deleteById(id);
            System.out.println(id + " is verwijderd uit de boeken DB");
        }
    }


}
