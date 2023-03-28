package com.example.demo;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long speciesId;
    @CreationTimestamp
    private Timestamp created;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    private List<Species> species = new ArrayList<>();

    public Plant() {
    }



}