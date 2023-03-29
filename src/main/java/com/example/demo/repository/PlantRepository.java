package com.example.demo.repository;

import com.example.demo.model.Plant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends CrudRepository<Plant, Long> {


}
