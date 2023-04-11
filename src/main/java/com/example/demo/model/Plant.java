package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plantName;
    @CreationTimestamp
    private LocalDate created;
    @ManyToOne
    private Admin admin;
    @ManyToOne
    private Species species;
    @Transient
    private List<String> doTask;

    public String getPlantStageImgResponsive() {
        LocalDate now = LocalDate.now();
        Long dif = ChronoUnit.DAYS.between(this.getCreated(), now);
        Long readyToEat = Long.valueOf(this.species.getReadyToEat());
        Long phase= readyToEat/5;

        if (dif < phase) {
            return "/images/" + this.species.getId().toString() + "/t1.svg";
        } else if (dif > phase && dif <= phase * 2) {
            return "/images/" + this.species.getId().toString() + "/t2.svg";
        } else if (dif > phase * 2 && dif <= phase * 3) {
            return "/images/" + this.species.getId().toString() + "/t3.svg";
        } else if (dif > phase * 3 && dif <= phase * 4) {
            return "/images/" + this.species.getId().toString() + "/t4.svg";
        } else {
            return "/images/" + this.species.getId().toString() + "/t5.svg";
        }
    }
}