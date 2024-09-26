package org.example.projectservice.controller;

import org.example.projectservice.model.Project;
import org.example.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("save")
    public String save(@RequestBody Project project) {
        projectService.save(project);
        return "The Project Saved Successfully";
    }

    @PutMapping("update/{id}")
    public String update(@RequestBody Project project, @PathVariable Integer id) {
        projectService.updateProjectById(id, project);
        return "The Project Updated Successfully";
    }

    @GetMapping("get+all")
    public List<Project> getAll() {
        return projectService.getAllProjects();
    }

    @GetMapping("get+id/{id}")
    public Project getById(@PathVariable Integer id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Integer id ) {
        projectService.deleteProjectById(id);
        return "The Project Deleted Successfully";
    }

    @GetMapping("/page")
    public Page<Project> getPaginatedproject(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return projectService.getProject(page, size);
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
    public Page<Project> getProjects(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String budget,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,  // Default sorting by 'id'
            @RequestParam(defaultValue = "asc") String sortOrder) {  // Default ascending sort

        return projectService.getFilteredProject(name, budget, page, size, sortBy, sortOrder);
    }
}
