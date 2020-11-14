package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domein.gebruiker;

@Service
public class gebruikerController {

    @Autowired
    gebruikerRepository gr;

    public void maakGebruiker(gebruiker geb) {
        gr.save(geb);
    }

    public Iterable<gebruiker> alleGebruikers() {
        return gr.findAll();
    }

}
