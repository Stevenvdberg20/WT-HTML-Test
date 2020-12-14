package com.example.demo.controller;

import com.example.demo.domein.Uitleen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class uitleenController {

    @Autowired
    uitleenRepository ur;
    /*
    public void maakUitleen(Uitleen ul) {
        ur.save(ul);
    }
    */


    public void uitleenOpslaan(Uitleen ul) {
        ul.setBeginDatum(LocalDate.now());
        ur.save(ul);
    }

    public Iterable<Uitleen> alleUitleen() {
        return ur.findAll();
    }
}
