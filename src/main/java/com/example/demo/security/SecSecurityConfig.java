
package com.example.demo.security;

import com.example.demo.security.SecUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/") //URL GETMAPPING
                .defaultSuccessUrl("/home", false)
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



/*
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().anyRequest().authenticated().and().formLogin().loginPage("/").loginProcessingUrl("/perform_login").defaultSuccessUrl("/home.html", true).failureUrl("/login.html?error=true").failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler()); //httpBasic
        return http.build();
    }
*//*



}*/
