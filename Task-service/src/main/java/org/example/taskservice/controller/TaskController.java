package org.example.taskservice.controller;

import org.example.taskservice.model.Task;
import org.example.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("save")
    public String saveTask(@RequestBody Task task) {
        taskService.save(task);
        return "The Task has been saved";
    }

    @PutMapping("update/{id}")
    public String updateTask(Task task, @PathVariable Integer id) {
        taskService.update(id, task);
        return "The Task has been updated";
    }
    @GetMapping("get_all_project_id/{project_id}")
    public List<Task> getAllTasks(@PathVariable Integer project_id) {
        return taskService.getTaskByProjectId(project_id);
    }
    @GetMapping("get_all_task")
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }

    @GetMapping("get+all")
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("get+id/{id}")
    public Task getTaskById(@PathVariable Integer id) {
        return taskService.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskService.deleteById(id);
        return "The Task has been deleted";
    }
    @GetMapping("/page")
    public Page<Task> getPaginatedTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return taskService.getTasks(page, size);
    }


//    @GetMapping("/filter")
//    public Page<Task> getTasks(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String status,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        return taskService.getFilteredTasks(name, status, page, size);
//    }

    @GetMapping("/filter")
    public Page<Task> getTasks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,  // Default sorting by 'id'
            @RequestParam(defaultValue = "asc") String sortOrder) {  // Default ascending sort

        return taskService.getFilteredTasks(name, status, page, size, sortBy, sortOrder);
    }
//    @DeleteMapping("delete+task+by+project/{id}")
//    public String deleteTaskByProjectId(@PathVariable Integer id) {
//        return taskService.DeleteTaskByProjectId(id);
//    }
}
