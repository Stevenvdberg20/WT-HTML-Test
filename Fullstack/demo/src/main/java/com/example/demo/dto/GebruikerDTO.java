package com.example.demo.dto;

import com.example.demo.domein.Gebruiker;
import com.example.demo.domein.Boek;
import java.util.List;
import java.util.ArrayList;

public class GebruikerDTO {
    
    String naam;
    List<String> boekTitels = new ArrayList<String>();
    
    public GebruikerDTO(Gebruiker gebruiker) {
        
        this.naam = gebruiker.getNaam();
        
        for (Boek boek: gebruiker.getBoeken()) {
            boekTitels.add(boek.getTitel());
        }
    }

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<String> getBoekTitels() {
        return this.boekTitels;
    }

    public void setBoekTitels(List<String> boekTitels) {
        this.boekTitels = boekTitels;
    }
}