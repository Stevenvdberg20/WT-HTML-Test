package com.example.demo.controller;

import com.example.demo.domein.Uitleen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class uitleenController {

    @Autowired
    uitleenRepository ur;
    public void uitleenOpslaan(Uitleen ul) {
        ul.setBeginDatum(LocalDate.now());
        
        ur.save(ul);
    }
    
    public void updateUitleen(Uitleen ul) {
        ul.setEindDatum(LocalDate.now());
        
        ur.save(ul);
    }

    public Uitleen getUitleen(String wtid) {
        return ur.getUitleen(wtid);
    }
    public Iterable<Uitleen> alleUitleen() {
        return ur.findAll();
    }
}
