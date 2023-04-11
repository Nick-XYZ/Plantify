package com.example.demo;

import com.example.demo.model.Admin;
import com.example.demo.model.Plant;
import com.example.demo.model.PlantLog;
import com.example.demo.repository.PlantLogRepository;
import com.example.demo.repository.PlantRepository;
import com.example.demo.repository.SpeciesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PlantService;
import org.junit.jupiter.api.Assertions;
import com.example.demo.model.Admin;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
	@Autowired
	PlantService plantService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testFindByEmail(){
		Admin admin = userRepository.findByEmail("demo@gmail.com");
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

	@Test
	void waterEventLogger() {
		Plant plant = new Plant();
		plantRepository.save(plant);
		PlantLog plantlog = new PlantLog();
		plantlog.setPlant(plant);
		plantlog.setEvent("water");
		plantLogRepository.save(plantlog);

		Assertions.assertEquals(1, plantLogRepository.count());
		Assertions.assertEquals(true, plantService.isWaterEventDone(plant.getId(), LocalDate.now()));
	}

	@Test
	void waterTimelineTest() {
		Plant plant = new Plant();
		plant.setSpecies(speciesRepository.findById(3L).get());
		plantRepository.save(plant);
		List<LocalDate> wt = plantService.plantWaterTimeline(plant.getId());

		//Species with ID "3" needs water every 2 days
		//plantWaterTimeline should return the next 5 "watering" event dates
		Assertions.assertEquals(wt.get(0), LocalDate.now().plusDays(2));
		Assertions.assertEquals(wt.get(1), LocalDate.now().plusDays(4));
		Assertions.assertEquals(wt.get(2), LocalDate.now().plusDays(6));
		Assertions.assertEquals(wt.get(3), LocalDate.now().plusDays(8));
		Assertions.assertEquals(wt.get(4), LocalDate.now().plusDays(10));
	}
}
