package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

  /*  @Query("SELECT u FROM User u WHERE u.email = ?1") //custom query, because email is unique, so you want one value not a list.
    User findByEmail(String email);*/


}
