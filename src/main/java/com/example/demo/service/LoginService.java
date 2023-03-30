package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

   @Autowired
   UserRepository userRepository;


    public List<Admin> getUsers() {
        return (List<Admin>) userRepository.findAll();
    }

    public Admin addUser(Admin admin) {
        return userRepository.save(admin);
    }


}
