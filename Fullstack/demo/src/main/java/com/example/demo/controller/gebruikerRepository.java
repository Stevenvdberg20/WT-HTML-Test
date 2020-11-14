package com.example.demo.controller;

import com.example.demo.domein.gebruiker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface gebruikerRepository extends CrudRepository<gebruiker, Long> {
}
