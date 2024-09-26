package org.example.userservice.service;

import org.example.userservice.model.Admin;
import org.example.userservice.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
