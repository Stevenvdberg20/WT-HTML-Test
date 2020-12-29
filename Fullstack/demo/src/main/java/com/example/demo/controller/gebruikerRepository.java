package com.example.demo.controller;

import com.example.demo.domein.Gebruiker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Component
public interface gebruikerRepository extends CrudRepository<Gebruiker, Long> {
    List<Gebruiker> findByEmail(String email);
    

    @Query("SELECT g.naam FROM Gebruiker g")
    List<String> getNaam();

    @Query("SELECT g.email, g.ww FROM Gebruiker g")
    List<String> getLoginDetails();
    
    @Query("SELECT g.email, g.autorisatie FROM Gebruiker g")
    List<String> getAutorisatie();

    @Query("SELECT g FROM Gebruiker g ORDER BY g.naam ASC")
    List<Gebruiker> sorteerGebruiker();
}
 

