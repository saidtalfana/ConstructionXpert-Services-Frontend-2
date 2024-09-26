package org.example.userservice.repository;

import org.example.userservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByUsername(String username);
}
