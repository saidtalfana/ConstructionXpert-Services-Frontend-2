package org.example.projectservice.service;

import org.example.projectservice.criteria.ProjectSpecification;
import org.example.projectservice.model.Project;
import org.example.projectservice.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;



    public Project save(Project project) {
        project.setHeurs(new Time(System.currentTimeMillis()));
        return projectRepo.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Project getProjectById(int id) {
        return projectRepo.findById(id).orElseThrow();
    }

    public void deleteProjectById(int id) {
        projectRepo.deleteById(id);
    }

    public Project updateProjectById(int id, Project project) {
        Project updatedProject = projectRepo.findById(id).orElseThrow();
        updatedProject.setName(project.getName());
        updatedProject.setDescription(project.getDescription());
        updatedProject.setBudget(project.getBudget());
        updatedProject.setStartDate(project.getStartDate());
        updatedProject.setEndDate(project.getEndDate());
        updatedProject.setHeurs(project.getHeurs());

        return projectRepo.save(updatedProject);
    }

    public Page<Project> getProject(int page, int size) {
        return projectRepo.findAll(PageRequest.of(page, size));
    }
    public Page<Project> getFilteredProject(String name, String budget, int page, int size, String sortBy, String sortOrder) {
        // Create Specification for filtering
        Specification<Project> spec = Specification.where(ProjectSpecification.hasName(name))
                .and(ProjectSpecification.hasbudget(budget));

        // Determine sorting direction
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);  // Default to ascending
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }

        // Create PageRequest with sorting
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // Return the paginated, sorted, and filtered results
        return projectRepo.findAll(spec, pageRequest);
    }


}
