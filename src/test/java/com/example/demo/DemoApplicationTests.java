package com.example.demo;

import com.example.demo.model.Admin;
import com.example.demo.model.Plant;
import com.example.demo.model.PlantLog;
import com.example.demo.repository.PlantLogRepository;
import com.example.demo.repository.PlantRepository;
import com.example.demo.repository.SpeciesRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import com.example.demo.model.Admin;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.security.config.http.MatcherType.mvc;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PlantRepository plantRepository;

	@Autowired
	SpeciesRepository speciesRepository;
	@Autowired
	PlantLogRepository plantLogRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testFindByEmail(){
		Admin admin = userRepository.findByEmail("Michelle@gmail.com");
		Assertions.assertEquals(1L,admin.getId());
	}

	@Test
	public void testAddUser(){
		Admin admin = new Admin("test@gmail.com", "123456", "Test", "Test");
		Long count =  userRepository.count();
		userRepository.save(admin);
		Long count2 = userRepository.count();
		Assertions.assertEquals(count+1, count2);
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
