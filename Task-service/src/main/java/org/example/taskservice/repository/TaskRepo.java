package org.example.taskservice.repository;

import org.example.taskservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer >, JpaSpecificationExecutor<Task> {


    @Query(value = "select * from task where project_id=?" , nativeQuery = true)
    List<Task> findByProjectId(Integer projectId);

//    void deleteTaskByProjectId(Integer projectId);
}
