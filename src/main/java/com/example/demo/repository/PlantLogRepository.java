package com.example.demo.repository;

import com.example.demo.model.PlantLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantLogRepository extends CrudRepository<PlantLog, Long> {

}
