package org.example.taskservice.service;

import org.example.taskservice.classe.Project;
import org.example.taskservice.criteria.TaskSpecification;
import org.example.taskservice.model.Task;
import org.example.taskservice.openfeign.ProjetRest;
import org.example.taskservice.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private ProjetRest projetRest;

    public Task save(Task task) {
        task.setHeurs(new Time(System.currentTimeMillis()));
        return taskRepo.save(task);
    }

    public Task update(Integer id, Task task) {
        Task taskToUpdate = taskRepo.findById(id).orElseThrow();
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setHeurs(task.getHeurs());
        taskToUpdate.setStartDate(task.getStartDate());
        taskToUpdate.setEndDate(task.getEndDate());
        taskToUpdate.setProject_id(task.getProject_id());
        taskToUpdate.setStatus(task.getStatus());

        return taskRepo.save(taskToUpdate);
    }

    public Task findById(Integer id) {
        Task task= taskRepo.findById(id).orElseThrow();
        Project project=projetRest.getById(task.getProject_id());
        task.setProject(project);
        return task;
    }

    public List<Task> getTaskByProjectId(Integer project_id) {
        return taskRepo.findByProjectId(project_id);
    }
    public List<Task> getAllTask() {
        return taskRepo.findAll();
    }
    public List<Task> findAll() {
        List<Task> taskList= taskRepo.findAll();
        for (Task task:taskList){
          Project project=projetRest.getById(task.getProject_id());
          task.setProject(project);
        }
        return taskList;
    }

    public void deleteById(Integer id) {
        taskRepo.deleteById(id);
    }

    public Page<Task> getTasks(int page, int size) {
        return taskRepo.findAll(PageRequest.of(page, size));
    }
    public Page<Task> getFilteredTasks(String name, String status, int page, int size, String sortBy, String sortOrder) {
        // Create Specification for filtering
        Specification<Task> spec = Specification.where(TaskSpecification.hasName(name))
                .and(TaskSpecification.hasStatus(status));

        // Determine sorting direction
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);  // Default to ascending
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }

        // Create PageRequest with sorting
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // Return the paginated, sorted, and filtered results
        return taskRepo.findAll(spec, pageRequest);
    }
//    public String DeleteTaskByProjectId(Integer id){
//        taskRepo.deleteTaskByProjectId(id);
//        return "the project deleted ";
//    }
}
