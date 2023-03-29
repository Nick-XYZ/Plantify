package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface SpeciesRepository extends CrudRepository<Species, Long> {


}