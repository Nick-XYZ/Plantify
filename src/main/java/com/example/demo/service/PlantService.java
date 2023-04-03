package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Plant;
import com.example.demo.model.PlantLog;
import com.example.demo.model.Species;
import com.example.demo.repository.PlantLogRepository;
import com.example.demo.repository.PlantRepository;
import com.example.demo.repository.SpeciesRepository;
import com.example.demo.repository.UserRepository;
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
    @Autowired
    PlantLogRepository plantLogRepository;
    @Autowired
    UserRepository userRepository;

    //Creates a list of the next 5 dates a plant needs water
    public List<LocalDate> plantWaterTimeline(Long plantId) {
        Plant plant = plantRepository.findById(plantId).get();
        Species species = plant.getSpecies();
        LocalDate now = LocalDate.now();
        List<LocalDate> waterSchedule = new ArrayList<>();
        Long dif = ChronoUnit.DAYS.between(plant.getCreated(), now);
        System.out.println(dif);
        dif = dif % species.getWater();
        if (dif == 0) {
            waterSchedule.add(now);
        }
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
        if (dif == 0) {
            nutritionSchedule.add(now);
        }
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
            if (!isEventDone(plantId, date)) {
                timeline.put(date, "/images/water-button.png");
                System.out.println(date);
            }
        }
        for (LocalDate date : nutrition) {
            timeline.put(date, "/images/näring.jpg");
        }
        Map<LocalDate, String> sortedTimeline = new TreeMap<>(timeline);
        //sortedTimeline.keySet() to get the dates only
        System.out.println("SORTED TIMELINES" + sortedTimeline);
        return sortedTimeline;
    }

    public boolean isEventDone(Long plantId, LocalDate date) {
        List<PlantLog> log = (List<PlantLog>) plantLogRepository.findAllByPlantId(plantId);
        boolean value = false;
        for (PlantLog event : log) {
            if (event.getEvent().equals("water") && event.getEventDate().equals(date)) {
                value = true;
            } else {
                value = false;
            }
        }
        return value;
    }
     public Map<LocalDate, String> nextFiveTimeline(Long plantId) {
         Map<LocalDate, String> sortedTimeline = sortedTimeline(plantId);
         TreeMap<LocalDate, String> firstFiveDates = sortedTimeline.entrySet().stream()
                 .limit(5)
                 .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
         System.out.println("ONLY FIRST 5 DATES " + firstFiveDates);
         return firstFiveDates;
    }

  /*  public void harvesting(Long plantId) {
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
    }*/


    public Map<LocalDate, String> todaysTimeline(Long plantId) {
        List<LocalDate> water = plantWaterTimeline(plantId);
        List<LocalDate> nutrition = plantNutritionTimeline(plantId);
        Map<LocalDate, String> timeline = new HashMap<>();
        LocalDate today = LocalDate.now();
        for (LocalDate date : water) {
            if (date.equals(today) && !isEventDone(plantId, date)) {
                timeline.put(date, "/images/water-button.png");
            }
        }
        for (LocalDate date : nutrition) {
            if (date.equals(today) && !isEventDone(plantId, date)) {
                timeline.put(date, "/images/näring.jpg");
            }
        }
        Map<LocalDate, String> sortedTodaysTimeline = new TreeMap<>(timeline);
        //sortedTimeline.keySet() to get the dates only
        System.out.println("SORTED TIMELINES" + sortedTodaysTimeline);
        return sortedTodaysTimeline;
    }
    public List<String> eventDayValue(Long plantId) {
        Map<LocalDate, String> eventList = todaysTimeline(plantId);
        LocalDate now = LocalDate.now();
        List<String> eventString = new ArrayList<>();
        if (eventList.containsKey(now) && eventList.containsValue("/images/water-button.png")) {
            eventString.add("water");
        } else {
            eventString.add("notwater");
        }
        if (eventList.containsKey(now) && eventList.containsValue("/images/näring.jpg")) {
            eventString.add("nutrition");
        } else {
            eventString.add("notnutrition");
        }
        if (eventList.containsKey(now) && eventList.containsValue("REPOT")) {
            eventString.add("repot");
        } else {
            eventString.add("notrepot");
        }
        return eventString;
    }


}
