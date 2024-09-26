package org.example.resourceservice.criteria;

import org.example.resourceservice.model.Resource;
import org.springframework.data.jpa.domain.Specification;

public class ResourceSpecification {

    public static Specification<Resource> hasName(String resourceName) {
        return (root, query, criteriaBuilder) -> {
            if (resourceName == null || resourceName.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filtering if name is null or empty
            }
            return criteriaBuilder.equal(root.get("resourceName"), resourceName);
        };
    }

    public static Specification<Resource> hasStatus(String resourceType) {
        return (root, query, criteriaBuilder) -> {
            if (resourceType == null || resourceType.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filtering if type is null or empty
            }
            return criteriaBuilder.equal(root.get("resourceType"), resourceType);
        };
    }
}
