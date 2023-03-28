package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String sunlight;
    // Måste ändras till date.
    private Integer water;
    private Integer nutrition;
    private Integer readyToEat;
    private String short_info;
    private String full_info;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getShort_info() {
        return short_info;
    }

    public void setShort_info(String short_info) {
        this.short_info = short_info;
    }

    public String getFull_info() {
        return full_info;
    }

    public void setFull_info(String full_info) {
        this.full_info = full_info;
    }
}
