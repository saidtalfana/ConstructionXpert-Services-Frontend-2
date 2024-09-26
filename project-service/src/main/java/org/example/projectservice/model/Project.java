package org.example.projectservice.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.config.Task;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "budget")
    private double budget;

    @Column(name = "heurs")
    @DateTimeFormat(pattern = "HH:mm")
    private Time heurs;

    @Column(name = "startDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "endDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "description")
    private String description;


}