package org.example.resourceservice.openfeign;


import org.example.resourceservice.classe.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("TASK-SERVICE")
public interface TaskRest {

    @GetMapping("/get+id/{id}")
    Task getTaskById(@PathVariable Integer id);
}
