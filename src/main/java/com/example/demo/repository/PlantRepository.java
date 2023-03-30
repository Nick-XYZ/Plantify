package com.example.demo.repository;

import com.example.demo.model.Plant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends CrudRepository<Plant, Long> {

    List<Plant> findAllByAdminId(Long id);
}
