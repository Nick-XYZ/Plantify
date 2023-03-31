package com.example.demo.service;

import com.example.demo.model.Plant;
import com.example.demo.model.Species;
import com.example.demo.repository.PlantRepository;
import com.example.demo.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class PlantService {

    @Autowired
    PlantRepository plantRepository;
    @Autowired
    SpeciesRepository speciesRepository;

    //Creates a list of the next 5 dates a plant needs water
    public List<LocalDate> plantWaterTimeline(Long plantId) {
        Plant plant = plantRepository.findById(plantId).get();
        Species species = plant.getSpecies();
        LocalDate now = LocalDate.now();
        List<LocalDate> waterSchedule = new ArrayList<>();
        Long dif = ChronoUnit.DAYS.between(plant.getCreated(), now);
        System.out.println(dif);
        dif = dif % species.getWater();
        for (int i = 0; i < 5; i++) {
            waterSchedule.add(now.plusDays(species.getWater() * (i + 1L) - dif));
        }
        System.out.println("DAYS SINCE LAST WATER" + dif);
        System.out.println("NEXT 5 WATERINGS" + waterSchedule);
        return waterSchedule;
    }

    //Creates a list of the next 5 dates a plant needs nutrition
    public List<LocalDate> plantNutritionTimeline(Long plantId) {
        Plant plant = plantRepository.findById(plantId).get();
        Species species = plant.getSpecies();
        LocalDate now = LocalDate.now();
        List<LocalDate> nutritionSchedule = new ArrayList<>();
        Long dif = ChronoUnit.DAYS.between(plant.getCreated(), now);
        dif = dif % species.getNutrition();
        for (int i = 0; i < 5; i++) {
            nutritionSchedule.add(now.plusDays(species.getNutrition() * (i + 1L) - dif));
        }
        System.out.println("DAYS SINCE LAST NUTRITION" + dif);
        System.out.println("NEXT 5 NUTRITION" + nutritionSchedule);
        return nutritionSchedule;
    }

    public Map<LocalDate, String> sortedTimeline(Long plantId) {
        List<LocalDate> water = plantWaterTimeline(plantId);
        List<LocalDate> nutrition = plantNutritionTimeline(plantId);
        Map<LocalDate, String> timeline = new HashMap<>();
        for (LocalDate date : water) {
            timeline.put(date, "WATER");
        }
        for (LocalDate date : nutrition) {
            timeline.put(date, "NUTRITION");
        }
        Map<LocalDate, String> sortedTimeline = new TreeMap<>(timeline);
        //sortedTimeline.keySet() to get the dates only
        System.out.println("SORTED TIMELINES" + sortedTimeline);
        return sortedTimeline;
    }
     public Map<LocalDate, String> nextFiveTimeline(Long plantId) {
         Map<LocalDate, String> sortedTimeline = sortedTimeline(plantId);
         TreeMap<LocalDate, String> firstFiveDates = sortedTimeline.entrySet().stream()
                 .limit(5)
                 .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
         System.out.println("ONLY FIRST 5 DATES " + firstFiveDates);
         return firstFiveDates;
    }

    public void harvesting(Long plantId) {
        Plant plant = plantRepository.findById(plantId).get();
        Species species = plant.getSpecies();
        LocalDate now = LocalDate.now();
        Long dif = ChronoUnit.DAYS.between(plant.getCreated(), now);
        System.out.println(dif);
        if (species.getReadyToEat() > dif) {
            System.out.println("NOT READY TO EAT");
            System.out.println("READY TO EAT IN" + (species.getReadyToEat() - dif));
        } else {
            System.out.println("READY TO EAT");
        }
    }

    public boolean isWateringDay(Long plantId) {
        List<LocalDate> waterList = plantWaterTimeline(plantId);
        LocalDate now = LocalDate.now();
        if (now.equals(waterList.get(0))) {
            System.out.println("TRUE");
            return true;
        } else {
            System.out.println("FALSE");
            return false;
        }
    }

}
