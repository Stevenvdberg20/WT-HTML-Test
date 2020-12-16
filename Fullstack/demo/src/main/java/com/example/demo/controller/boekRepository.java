package com.example.demo.controller;

import com.example.demo.domein.Boek;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface boekRepository extends CrudRepository<Boek, Long> {
    List<Boek> findByTitel(String titel);


    @Query("SELECT DISTINCT b.titel FROM Boek b")
    List<String> getDistinctTitel();

    @Query("SELECT b.titel FROM Boek b")
    List<String> getTitel();

    @Query("SELECT b FROM Boek b WHERE b.wtId = ?1")
    Boek getBoek(String wtid);
    /* @Query("SELECT b.id, b.title FROM Book b")
    List<Object[]> getIdAndTitle(); */
    
}