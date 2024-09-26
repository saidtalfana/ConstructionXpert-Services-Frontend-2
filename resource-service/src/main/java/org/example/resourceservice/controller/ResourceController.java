package org.example.resourceservice.controller;

import org.example.resourceservice.model.Resource;
import org.example.resourceservice.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping("save")
    public String save(@RequestBody Resource resource) {
        resourceService.save(resource);
        return "The resource has been saved";
    }

    @PutMapping("update/{id}")
    public String update(@PathVariable Integer id, @RequestBody Resource resource) {
        resourceService.update(id, resource);
        return "The resource has been updated";
    }

    @GetMapping("get+all")
    public List<Resource> getAll() {
        return resourceService.findAll();
    }

    @GetMapping("get+id/{id}")
    public Resource get(@PathVariable Integer id) {
        return resourceService.findById(id);
    }

    @GetMapping("get_all_resources_by_task_id/{task_id}")
    public List<Resource> getAllResources(@PathVariable Integer task_id) {
        return resourceService.getAllResourcesByTaskId(task_id);
    }
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        resourceService.delete(id);
        return "The resource has been deleted";
    }

    @GetMapping("/page")
    public Page<Resource> getPaginatedResources(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return resourceService.getResource(page, size);
    }




//    @GetMapping("/filter")
//    public Page<Resource> getresources(
//            @RequestParam(required = false) String Resource_Name,
//            @RequestParam(required = false) String Resource_Type,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "id") String sortBy,  // Default sorting by 'id'
//            @RequestParam(defaultValue = "asc") String sortOrder) {  // Default ascending sort
//
//        return resourceService.getFilteredResources(Resource_Name, Resource_Type, page, size, sortBy, sortOrder);
//    }
//        @GetMapping("/filter")
//    public List<Resource> getFilteredResources(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String type) {
//        return resourceService.getFilteredResources(name, type);
//    }

    @GetMapping("/filter")
    public Page<Resource> getFilteredResources(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "resourceName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page, // Default page number
            @RequestParam(defaultValue = "10") int size) { // Default page size

        return resourceService.getFilteredResources(name, type, sortBy, sortOrder, page, size);
    }

}
