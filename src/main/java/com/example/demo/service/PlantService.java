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
    LoginService loginService;

    //Creates a list of the next 5 dates a plant needs water
    public List<LocalDate> plantWaterTimeline(Long plantId) {
        Plant plant = plantRepository.findById(plantId).get();
        Species species = plant.getSpecies();
        LocalDate now = LocalDate.now();
        List<LocalDate> waterSchedule = new ArrayList<>();
        Long dif = ChronoUnit.DAYS.between(plant.getCreated(), now);
        dif = dif % species.getWater();
        if (dif == 0 && !plant.getCreated().equals(now)) {
            waterSchedule.add(now);
        }
        for (int i = 0; i < 5; i++) {
            waterSchedule.add(now.plusDays(species.getWater() * (i + 1L) - dif));
        }
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
        if (dif == 0 && !plant.getCreated().equals(now)) {
            nutritionSchedule.add(now);
        }
        for (int i = 0; i < 5; i++) {
            nutritionSchedule.add(now.plusDays(species.getNutrition() * (i + 1L) - dif));
        }
        return nutritionSchedule;
    }
    public List<LocalDate> plantRepotTimeline(Long plantId) {
        Plant plant = plantRepository.findById(plantId).get();
        Species species = plant.getSpecies();
        LocalDate now = LocalDate.now();
        List<LocalDate> repotSchedule = new ArrayList<>();
        Long dif = ChronoUnit.DAYS.between(plant.getCreated(), now);
        dif = dif % species.getRepot();
        if (dif == 0 && !plant.getCreated().equals(now)) {
            repotSchedule.add(now);
        }
        for (int i = 0; i < 5; i++) {
            repotSchedule.add(now.plusDays(species.getRepot() * (i + 1L) - dif));
        }
        return repotSchedule;
    }

    public Map<LocalDate, List<String>> sortedTimeline(Long plantId) {
        List<LocalDate> water = plantWaterTimeline(plantId);
        List<LocalDate> nutrition = plantNutritionTimeline(plantId);
        List<LocalDate> repot = plantRepotTimeline(plantId);
        Map<LocalDate, List<String>> timeline = new HashMap<>();
        for (LocalDate date : water) {
            if (!isWaterEventDone(plantId, date)) {
                timeline.computeIfAbsent(date, k -> new ArrayList<>()).add("/images/water-button.png");
            }
        }
        for (LocalDate date : nutrition) {
            if (!isNutritionEventDone(plantId, date)) {
                timeline.computeIfAbsent(date, k -> new ArrayList<>()).add("/images/näring.jpg");
            }
        }
        for (LocalDate date : repot) {
            if (!isRepotEventDone(plantId, date)) {
                timeline.computeIfAbsent(date, k -> new ArrayList<>()).add("/images/Jord.jpg");
            }
        }
        Map<LocalDate, List<String>> sortedTimeline = new TreeMap<>(timeline);
        return sortedTimeline;
    }

    public boolean isWaterEventDone(Long plantId, LocalDate date) {
        List<PlantLog> log = plantLogRepository.findAllByPlantId(plantId);
        for (PlantLog event : log) {
            if (event.getEvent().equals("water") && event.getEventDate().equals(date)) {
                return true;
            }
        }
        return false;
    }
    public boolean isNutritionEventDone(Long plantId, LocalDate date) {
        List<PlantLog> log = plantLogRepository.findAllByPlantId(plantId);
        for (PlantLog event : log) {
            if (event.getEvent().equals("nutrition") && event.getEventDate().equals(date)) {
                return true;
            }
        }
        return false;
    }
    public boolean isRepotEventDone(Long plantId, LocalDate date) {
        List<PlantLog> log = plantLogRepository.findAllByPlantId(plantId);
        for (PlantLog event : log) {
            if (event.getEvent().equals("repot") && event.getEventDate().equals(date)) {
                return true;
            }
        }
        return false;
    }
     public Map<LocalDate, List<String>> nextFiveTimeline(Long plantId) {
         Map<LocalDate, List<String>> sortedTimeline = sortedTimeline(plantId);
         TreeMap<LocalDate, List<String>> firstFiveDates = sortedTimeline.entrySet().stream()
                 .limit(5)
                 .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
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


    public List<String> todaysTimeline(Long plantId) {
        List<LocalDate> water = plantWaterTimeline(plantId);
        List<LocalDate> nutrition = plantNutritionTimeline(plantId);
        List<LocalDate> repot = plantRepotTimeline(plantId);
        List<String> timLin = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (LocalDate date : water) {
            if (date.equals(today) && !isWaterEventDone(plantId, date)) {
                timLin.add("/images/water-button.png");
            }
        }
        for (LocalDate date : nutrition) {
            if (date.equals(today) && !isNutritionEventDone(plantId, date)) {
                timLin.add("/images/näring.jpg");
            }
        }
        for (LocalDate date : repot) {
            if (date.equals(today) && !isRepotEventDone(plantId, date)) {
                timLin.add("/images/Jord.jpg");
            }
        }
        return timLin;
    }
    public List<String> eventDayValue(Long plantId) {
        List<String> eventList = new ArrayList<>();
        if (todaysTimeline(plantId) != null) {
            eventList = todaysTimeline(plantId);
        }
        List<String> eventString = new ArrayList<>();

        if (eventList.contains("/images/water-button.png")) {
            eventString.add("water");
        } else {
            eventString.add("notwater");
        }
        if (eventList.contains("/images/näring.jpg")) {
            eventString.add("nutrition");
        } else {
            eventString.add("notnutrition");
        }
        if (eventList.contains("/images/Jord.jpg")) {
            eventString.add("repot");
        } else {
            eventString.add("notrepot");
        }
        return eventString;
    }

    public int counter(Admin admin, String event) {
        List<Plant> userPlants = plantRepository.findAllByAdminId(admin.getId());
        int count = 0;
        // for loop av userplants för att få med in i plants
        for (Plant plant : userPlants) {
            plant.setDoTask(eventDayValue(plant.getId()));
            if (plant.getDoTask().contains(event)) {
                count++;
            }
        }
        return count;
    }

    //Delete Plant
    public void deletePlant(Long id) throws Exception {
        plantRepository.delete(plantRepository.findById(id).orElseThrow(()-> {
            return new Exception("No Plant with that ID");
        }));
    }
}
