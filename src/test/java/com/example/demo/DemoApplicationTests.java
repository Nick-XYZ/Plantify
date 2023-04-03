package com.example.demo;

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


}
