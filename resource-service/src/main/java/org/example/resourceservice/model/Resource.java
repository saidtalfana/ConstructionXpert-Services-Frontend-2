package org.example.resourceservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.resourceservice.classe.Task;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private Integer task_id;

    @Column(name = "Resource_Name", nullable = false)
    private String resourceName;

    @Column(name = "Resource_Type", nullable = false)
    private String resourceType;;

    @Column(name = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Transient
    private Task task;

}