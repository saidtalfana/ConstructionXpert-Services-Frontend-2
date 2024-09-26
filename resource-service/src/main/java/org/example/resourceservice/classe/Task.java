package org.example.resourceservice.classe;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class Task {

    private int id;

    private Integer project_id;


    private LocalTime heurs;


    private LocalDate startDate;



    private LocalDate endDate;


    private String status;


    private String description;

}
