package com.example.demo.controller;

import com.example.demo.domein.Gebruiker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class gebruikerController {

    @Autowired
    gebruikerRepository gr;

    public void gebruikerOpslaan(Gebruiker gebruiker) {
        gr.save(gebruiker);
    }

    // Geeft alle gebruikers in een list
    public Iterable<Gebruiker> alleGebruikers() {
        return gr.findAll();
    }

    // Geeft alle gebruikers in een list, gesorteerd op naam
    public Iterable<Gebruiker> sorteerGebruiker() {
        return gr.sorteerGebruiker();
    }

    //
    public void verwijderGebruikers(Iterable<Long> ids) {
        for (long id: ids) { // Voor elk id in lijst ids, verwijder gebruikers op basis van dat id. De lijst komt uit frontend geselecteerde items
            gr.deleteById(id);
            System.out.println(id + " is verwijderd uit de DB");
        }
    }

    public List<String> alleGebruikersNamen() {
        List<String> gebruikersnamen = gr.getNaam();
        for(String s: gebruikersnamen) {
            System.out.println(s);
        }
        return gebruikersnamen;
    }
    
    public Gebruiker eenGebruiker(long gebruikerId) {
        return gr.findById(gebruikerId).get();
    }

    // Bepalen autoristaie niveau voor laten zien van juiste inhoud op webpagina's
    public String autorisatieLevel(String email) {
        List<Gebruiker> eenAutor = gr.findByEmail(email); // eenAutor = een autorisatie
        return eenAutor.get(0).getAutorisatie();
    }

    public boolean magInloggen(String em, String ww){
        List<String> loginGegevens = gr.getLoginDetails(); // Alle logingegevens ophalen van iedere gebruiker
        List<String> loginCheck = new ArrayList<String>(); // Lege lijst voor selecteren van juiste gegevens gebruiker die probeert in te loggen
        for(String s: loginGegevens){ // loginCheck bestaat uit email en wachtwoord (element 0 en 1). Split de string voor individuele elemnenten
            loginCheck.add(s.split(",")[0]);  
            loginCheck.add(s.split(",")[1]);         
        }

        // Controle of ingevoerde logingegevens overeenkomen met een login uit database voor verificatie
        for(int i = 0; i < loginCheck.size(); i = i+2) {
            if(loginCheck.get(i).equals(em)){
                if(loginCheck.get(i+1).equals(ww)){
                    return true; // Als alles overeenkomt, return true anders false
                }
            }
        }
        return false;


    }

}
