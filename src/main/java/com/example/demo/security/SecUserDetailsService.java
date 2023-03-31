package com.example.demo.security;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)  {
        Admin admin = userRepository.findByEmail(email);
        if (admin == null) {
            throw new UsernameNotFoundException(email);
        }
        return new SecurityUserPrincipal(admin);
    }

}