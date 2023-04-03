package com.example.demo.model;

import jakarta.persistence.*;

//Lombok annotation - makes it possible to create getters/setters/constructors without fully writing them.
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45) //unique makes sure that an email can only be entered once.
    private String email;

    @Column(nullable = false, length = 64) //This number because it is encrypted.
    private String password;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

   @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Plant> plant= new ArrayList<>();

   //Constructor used to test for account creation:
    public Admin(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
          }

   /*public Long getUserId() {
        return id;
    }*/

}
