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

    public Iterable<Gebruiker> alleGebruikers() {
        return gr.findAll();
    }
    public void verwijderGebruikers(Iterable<Long> ids) {
        for (long id: ids) {
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

    public String autorisatieLevel(String email) {
        List<Gebruiker> eenAutor = gr.findByEmail(email);
        return eenAutor.get(0).getAutorisatie();
    }

    /*public boolean magInloggen(Gebruiker login) {
        List<Gebruiker> gebruikerGegevens = gr.findByEmail_Ww(login.getEmail()); //vindt gebruiker op basisvan email en ww returns gebruiker -- nodig voor gebruikersprofiel
        System.out.println(gebruikerGegevens);
        return true;
    }*/

    public boolean magInloggen(String em, String ww){
        List<String> loginGegevens = gr.getLoginDetails();
        List<String> loginCheck = new ArrayList<String>();
        for(String s: loginGegevens){
            loginCheck.add(s.split(",")[0]);  
            loginCheck.add(s.split(",")[1]);         
        }
        
        System.out.println("em + ww: " + em + " + " + ww);

        for(int i = 0; i < loginCheck.size(); i = i+2) {
            if(loginCheck.get(i).equals(em)){
                if(loginCheck.get(i+1).equals(ww)){
                    return true;
                }
            }
        }

        return false;


    }

}
