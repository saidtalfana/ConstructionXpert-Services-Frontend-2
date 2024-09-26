package org.example.resourceservice.service;

import org.example.resourceservice.classe.Task;
import org.example.resourceservice.criteria.ResourceSpecification;
import org.example.resourceservice.model.Resource;
import org.example.resourceservice.openfeign.TaskRest;
import org.example.resourceservice.repository.ResourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepo resourceRepo;

    @Autowired
    private TaskRest taskRest;

    public Resource save(Resource resource) {
        return resourceRepo.save(resource);
    }
    public List<Resource> getAllResourcesByTaskId(Integer taskId) {
        return resourceRepo.getAllResourceByTaskId(taskId);
    }
    public Resource findById(Integer id) {
        Resource resource = resourceRepo.findById(id).orElseThrow();
        Task task = taskRest.getTaskById(resource.getTask_id());
        resource.setTask(task);
        return resource;
    }

    public List<Resource> findAll() {
        List<Resource> resources = resourceRepo.findAll();
        for (Resource resource : resources) {
            Task task = taskRest.getTaskById(resource.getTask_id());
            resource.setTask(task);
        }
        return resources;
    }

    public Resource update(Integer id , Resource resource) {
        Resource updateResource = resourceRepo.findById(id).orElseThrow();
        updateResource.setTask_id(resource.getTask_id());
        updateResource.setStartDate(resource.getStartDate());
        updateResource.setResourceName(resource.getResourceName());
        updateResource.setResourceType(resource.getResourceType());

        return resourceRepo.save(updateResource);
    }

    public void delete(Integer id) {
        resourceRepo.deleteById(id);
    }


    public Page<Resource> getResource(int page, int size) {
        return resourceRepo.findAll(PageRequest.of(page, size));
    }
//    public Page<Resource> getFilteredResources(String Resource_Name, String Resource_Type, int page, int size, String sortBy, String sortOrder) {
//        // Create Specification for filtering
//        Specification<Resource> spec = Specification.where(ResourceSpecification.hasName(Resource_Name))
//                .and(ResourceSpecification.hasStatus(Resource_Type));
//
//        // Determine sorting direction
//        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);  // Default to ascending
//        if (sortOrder.equalsIgnoreCase("desc")) {
//            sort = Sort.by(Sort.Direction.DESC, sortBy);
//        }
//
//        // Create PageRequest with sorting
//        PageRequest pageRequest = PageRequest.of(page, size, sort);
//
//        // Return the paginated, sorted, and filtered results
//        return resourceRepo.findAll(spec, pageRequest);
//    }
//public List<Resource> getFilteredResources(String name, String type) {
//    Specification<Resource> spec = Specification.where(ResourceSpecification.hasName(name))
//            .and(ResourceSpecification.hasStatus(type));
//    return resourceRepo.findAll(spec);
//}


    public Page<Resource> getFilteredResources(String name, String type, String sortBy, String sortOrder, int page, int size) {
        Specification<Resource> spec = Specification.where(ResourceSpecification.hasName(name))
                .and(ResourceSpecification.hasStatus(type));

        // Determine the sort direction
        Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        // Create a Pageable object
        Pageable pageable = PageRequest.of(page, size, sort);

        // Use the repository with the specification and pageable
        return resourceRepo.findAll(spec, pageable);
    }
}

