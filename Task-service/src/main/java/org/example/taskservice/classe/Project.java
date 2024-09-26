package org.example.taskservice.classe;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class Project {

    private int id;
    private String name;
    private double budget;
    private LocalTime heurs;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

}
