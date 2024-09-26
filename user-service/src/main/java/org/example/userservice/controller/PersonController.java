package org.example.userservice.controller;


import org.example.userservice.config.JwtAuth;
import org.example.userservice.dto.LoginDto;
import org.example.userservice.enums.Role;
import org.example.userservice.model.Person;
import org.example.userservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class PersonController {


    @Autowired
    private PersonService personService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public Person register(@RequestBody Person person ) {
        return personService.addPerson(person);
    }





    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        Person person1 = personService.findPersonByUsername(loginDto.getUsername());
        Role role= person1.getRole();
        String token = JwtAuth.generateToken(loginDto.getUsername(),role);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

}
