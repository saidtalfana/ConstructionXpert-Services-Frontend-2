package org.example.taskservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.taskservice.classe.Project;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private Integer project_id;

    @Column(name = "heurs")
    @DateTimeFormat(pattern = "HH:mm")
    private Time heurs;

    @Column(name = "startDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;


    @Column(name = "endDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Transient
    private Project project;

}