package org.example.userservice.service;

import org.example.userservice.enums.Role;
import org.example.userservice.model.Admin;
import org.example.userservice.model.Customer;
import org.example.userservice.model.Person;
import org.example.userservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    public PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CustomerService customerService;


    public Person findPersonByUsername(String username){
        return personRepository.findByUsername(username);
    }

    public Person addPerson(Person person){
        if (person.getRole() == Role.CUSTOMER) {
            Customer customer = new Customer();

            customer.setUsername(person.getUsername());
            customer.setEmail(person.getEmail());
            customer.setPassword(passwordEncoder.encode(person.getPassword()));
            customer.setRole(person.getRole());
            return personRepository.save(customer);

        } else if (person.getRole() == Role.ADMIN) {
            Admin admin = new Admin();
            admin.setUsername(person.getUsername());
            admin.setEmail(person.getEmail());
            admin.setPassword(passwordEncoder.encode(person.getPassword()));
            admin.setRole(person.getRole());
            return personRepository.save(admin);
        }
        return person;
    }
}
