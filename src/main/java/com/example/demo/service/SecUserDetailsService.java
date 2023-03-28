package com.example.demo.service;

import com.example.demo.SecurityUserPrincipal;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)  {
        List<User> users = userRepository.findByEmail(email);
        if (users.size() == 0) {
            throw new UsernameNotFoundException(email);
        }
        return new SecurityUserPrincipal(users.get(0));
    }
}


  /*  User user = userRepository.findByEmail(email);
        if (user == null){
                throw new UsernameNotFoundException("User not found");
                }
                return new UserDetails(user);*/