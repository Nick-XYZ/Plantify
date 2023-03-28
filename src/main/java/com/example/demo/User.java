package com.example.demo;

import jakarta.persistence.*;

//Lombok annotation - makes it possible to create getters/setters/constructors without fully writing them.
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64) //This number because it is encrypted.
    private String password;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

   /* @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Plant> plant= new ArrayList<>();*/

}
