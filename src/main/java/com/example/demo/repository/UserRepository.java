package com.example.demo.repository;

import com.example.demo.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Admin, Long> {

    Admin findByEmail(String email);

  /*  @Query("SELECT u FROM User u WHERE u.email = ?1") //custom query, because email is unique, so you want one value not a list.
    User findByEmail(String email);*/


}
