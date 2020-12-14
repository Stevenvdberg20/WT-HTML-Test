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

    @Query("SELECT g.email, g.ww FROM Gebruiker g") // NIEUW CHECK DEZE!
    List<String> getLoginDetails();
    
    @Query("SELECT g.email, g.autorisatie FROM Gebruiker g") // NIEUW CHECK DEZE!
    List<String> getAutorisatie();

   /* @Query("SELECT g.email, g.ww FROM Gebruiker g") // returns idd de gebruiker met alle info, want je wilt deze doorsturen naar nieuwe pagina
    List<Gebruiker> findByEmail_Ww(String email);
    //List<Person> findByAddress_ZipCode(ZipCode zipCode);*/

    //@Query("SELECT g.email, g.ww FROM Gebruiker g")
    //List<String> getEmailAndWw();
}
 