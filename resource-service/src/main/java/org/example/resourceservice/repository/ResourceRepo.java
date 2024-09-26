package org.example.resourceservice.repository;

import org.example.resourceservice.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Integer>, JpaSpecificationExecutor<Resource> {
    @Query(value = "select * from resource where task_id=?", nativeQuery = true)
    List<Resource> getAllResourceByTaskId(Integer taskId);
}
