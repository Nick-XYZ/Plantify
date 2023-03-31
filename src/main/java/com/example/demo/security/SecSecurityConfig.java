
package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {

    @Autowired
    private SecUserDetailsService userDetailsService;

    @Bean
    @Order(1)
    public SecurityFilterChain auth0FilterChain(HttpSecurity http) throws Exception {

        //disabling some security features to make the h2 console work
        http.csrf().disable();
        http.headers().frameOptions().disable();
        //Making requests to the h2 console not being secured by Spring Security
        http.authorizeHttpRequests().requestMatchers(AntPathRequestMatcher.antMatcher("/h2/**")).permitAll();
        //Setting the autowired userDetailsService object to be the userDetailService used by Spring Security.
        http.userDetailsService(userDetailsService);

        http
                .authorizeHttpRequests()
                .requestMatchers("/", "/createUser", "/images/**").permitAll()
                .requestMatchers("/home").hasRole("USER")
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/") //URL GETMAPPING
                .defaultSuccessUrl("/home", true)
                .permitAll();

        http.logout().logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}




