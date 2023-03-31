package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;


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



}