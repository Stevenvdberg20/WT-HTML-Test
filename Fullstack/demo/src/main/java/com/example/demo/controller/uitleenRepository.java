package com.example.demo.controller;

import com.example.demo.domein.Uitleen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.Query;

@Component
public interface uitleenRepository extends CrudRepository<Uitleen, Long> {

    @Query("SELECT u FROM Uitleen u WHERE u.wtId = ?1")
    Uitleen getUitleen(String wtid);
}
