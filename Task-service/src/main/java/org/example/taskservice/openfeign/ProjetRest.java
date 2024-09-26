package org.example.taskservice.openfeign;

import org.example.taskservice.classe.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("PROJECT-SERVICE")
public interface ProjetRest {

    @GetMapping("/get+id/{id}")
    Project getById(@PathVariable Integer id);

}
