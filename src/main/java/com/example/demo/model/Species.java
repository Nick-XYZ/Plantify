package com.example.demo.model;

import jakarta.persistence.*;
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
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String latinName;
    private String sunlight;
    private String soil;
    private Integer water;
    private Integer repot;
    private Integer nutrition;
    private Integer readyToEat;
    private String shortInfo;
  /*  private String fullInfo;*/

    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL)
    private List<Plant> plants = new ArrayList<>();




    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }




}

