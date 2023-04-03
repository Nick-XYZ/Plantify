package com.example.demo;

import com.example.demo.model.Admin;
import com.example.demo.model.Plant;
import com.example.demo.model.PlantLog;
import com.example.demo.repository.PlantLogRepository;
import com.example.demo.repository.PlantRepository;
import com.example.demo.repository.SpeciesRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	PlantRepository plantRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SpeciesRepository speciesRepository;
	@Autowired
	PlantLogRepository plantLogRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void addPlantLog() {
		Plant plant = new Plant();
		plant.setPlantName("TestPlant");
		plantRepository.save(plant);
		PlantLog test = new PlantLog();
		Long count = plantLogRepository.count();
		plantLogRepository.save(test);
		Long count2 = plantLogRepository.count();
		System.out.println(plantLogRepository.findById(1L));

		Assertions.assertEquals(count + 1, count2);
	}
}
