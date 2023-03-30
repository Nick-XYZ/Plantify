package com.example.demo.model;

import com.example.demo.model.Plant;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sunlight;
    private Integer water;
    private Integer nutrition;
    private Integer readyToEat;
    private String shortInfo;
    private String fullInfo;

    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL)
    private List<Plant> plants = new ArrayList<>();



    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSunlight() {
        return sunlight;
    }

    public void setSunlight(String sunlight) {
        this.sunlight = sunlight;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public Integer getNutrition() {
        return nutrition;
    }

    public void setNutrition(Integer nutrition) {
        this.nutrition = nutrition;
    }

    public Integer getReadyToEat() {
        return readyToEat;
    }

    public void setReadyToEat(Integer readyToEat) {
        this.readyToEat = readyToEat;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getFullInfo() {
        return fullInfo;
    }

    public void setFullInfo(String fullInfo) {
        this.fullInfo = fullInfo;
    }
}
