package com.example.demo.repository;

import com.example.demo.model.Species;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface SpeciesRepository extends CrudRepository<Species, Long> {


}