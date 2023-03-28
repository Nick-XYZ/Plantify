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
    private Integer id;
    private String plant_name;
    private Integer species_id;
    @CreationTimestamp
    private Timestamp created;



    public Plant() {
    }

}