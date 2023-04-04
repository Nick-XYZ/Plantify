package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


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

    /*@Column(updatable = false)*/
    @CreationTimestamp
    private LocalDate created;

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private Species species;


    /*public String getPlantStageImg() {
        LocalDate now = LocalDate.now();
        Long dif = ChronoUnit.DAYS.between(this.getCreated(), now);
        if (dif < 10) {
            // /1/images/t1.png
            return "/images/" + this.species.getId().toString() + "/t1.png";
        } else if (dif > 10 && dif < 18) {
            return "/images/" + this.species.getId().toString() + "/t2.png";
        } else if (dif > 18 && dif < 26) {
            return "/images/" + this.species.getId().toString() + "/t3.png";
        } else if (dif > 26 && dif < 32) {
            return "/images/" + this.species.getId().toString() + "/t4.png";
        } else {
            return "/images/" + this.species.getId().toString() + "/t5.png";
        }
    }*/

    public String getPlantStageImgResponsive() {
        LocalDate now = LocalDate.now();
        Long dif = ChronoUnit.DAYS.between(this.getCreated(), now);
        Long readyToEat = Long.valueOf(this.species.getReadyToEat());
        Long phase= readyToEat/5;

        if (dif < phase) {
            return "/images/" + this.species.getId().toString() + "/t1.png";
        } else if (dif > phase && dif <= phase * 2) {
            return "/images/" + this.species.getId().toString() + "/t2.png";
        } else if (dif > phase * 2 && dif <= phase * 3) {
            return "/images/" + this.species.getId().toString() + "/t3.png";
        } else if (dif > phase * 3 && dif <= phase * 4) {
            return "/images/" + this.species.getId().toString() + "/t4.png";
        } else {
            return "/images/" + this.species.getId().toString() + "/t5.png";
        }
    }
}