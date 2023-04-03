package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Getter
@Setter
@AllArgsConstructor
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


    public String getPlantStageImg() {
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
    }

   /* public String getPlantStageImgResponsive() {
        LocalDate now = LocalDate.now();
//ready to eat delat på 5 in i en phase som sedan används nedan.
        Long dif = ChronoUnit.DAYS.between(this.getCreated(), now);
        Long readyToEat = species.getReadyToEat();
        Long phase=
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
}