package com.tulisko.clinicmangaer.repository.impl;

import com.tulisko.clinicmangaer.model.Doctor;
import com.tulisko.clinicmangaer.model.Staff;
import com.tulisko.clinicmangaer.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImplUserRepository {
    public void save(User user);
    public Optional<User> findByEmail(String email);
    public boolean existByEmail(String email);
    public List<Doctor> findAllDoctors();
    public List<Staff> findAllStaff();
    public List<User> findAll();
    public void updateActive(UUID id, boolean active);
    }
