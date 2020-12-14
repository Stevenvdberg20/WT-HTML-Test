package com.example.demo.controller;

import com.example.demo.domein.Uitleen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface uitleenRepository extends CrudRepository<Uitleen, Long> {
}
