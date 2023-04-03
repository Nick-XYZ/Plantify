package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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


    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public Integer getRepot() {
        return repot;
    }

    public void setRepot(Integer repot) {
        this.repot = repot;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

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



}

