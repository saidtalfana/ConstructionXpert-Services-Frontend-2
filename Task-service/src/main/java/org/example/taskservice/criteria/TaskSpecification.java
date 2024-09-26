package org.example.taskservice.criteria;

import org.example.taskservice.model.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {


    public static Specification<Task> hasName(String name) {
        return (task, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return null; // No filtering if name is null or empty
            }
            return criteriaBuilder.equal(task.get("name"), name);
        };
    }

    public static Specification<Task> hasStatus(String status) {
        return (task, query, criteriaBuilder) -> {
            if (status == null || status.isEmpty()) {
                return null; // No filtering if status is null or empty
            }
            return criteriaBuilder.equal(task.get("status"), status);
        };
    }
}
