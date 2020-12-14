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

    public void leenBoek(long gebruikId, long boekId) {
        Boek boekEen = br.findById(boekId).get();
        Gebruiker gebruikerEen = gr.findById(gebruikId).get();
        gebruikerEen.addBoek(boekEen);
        System.out.println("We hebben een boek toegevoegd.");
        gr.save(gebruikerEen);
    }
    
    public void maakBoek(Boek boek) {
        br.save(boek);
        /*
        List<Boek> boeken = br.findByTitel("The Hobbit");
        for (Boek bk: boeken) {
            System.out.println(bk);
        }

         */
    }
    

    public void boekOpslaan(Boek boek) {
        bepaalExemplaar(boek);
        bepaalTitelCode(boek);
        bepaalWtId(boek);
        br.save(boek);
    }
    
    public void bepaalExemplaar(Boek boek) {
        List<Boek> boeken = br.findByTitel(boek.getTitel()); // Maakt nieuwe lijst met exemplaren met dezelfde titel
        boek.setExemplaar(boeken.size()+1);                  // geeft nieuwe exemplaar een nummer
    }

    public void bepaalTitelCode(Boek boek) {
        int titelCode = 0;
        List<Boek> zelfdeBoeken = br.findByTitel(boek.getTitel());
        if (zelfdeBoeken.isEmpty()) {   // Boektitel is nieuw
            List<String> uniekeBoeken = alleUniekeBoeken();                    
            titelCode = uniekeBoeken.size() + 1;
        } else {                                            // Boektitel al in database
            titelCode = zelfdeBoeken.get(0).getTitelCode();
        }
        boek.setTitelCode(titelCode);
    }

    public void bepaalWtId(Boek boek) {
        boek.setWtId(boek.getTitelCode() + "." + boek.getExemplaar());
    }


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
        for(String s: boektitels) {
           // System.out.println(s);
        }
        
        return boektitels;
    }
}
