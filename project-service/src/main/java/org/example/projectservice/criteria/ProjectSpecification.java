package org.example.projectservice.criteria;

import org.example.projectservice.model.Project;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecification {


    public static Specification<Project> hasName(String name) {
        return (project, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return null; // No filtering if name is null or empty
            }
            return criteriaBuilder.equal(project.get("name"), name);
        };
    }

    public static Specification<Project> hasbudget(String budget) {
        return (project, query, criteriaBuilder) -> {
            if (budget == null || budget.isEmpty()) {
                return null; // No filtering if status is null or empty
            }
            return criteriaBuilder.equal(project.get("budget"), budget);
        };
    }
}
