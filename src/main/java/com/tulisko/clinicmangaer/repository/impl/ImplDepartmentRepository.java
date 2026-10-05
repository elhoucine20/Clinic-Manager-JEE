package com.tulisko.clinicmangaer.repository.impl;

import com.tulisko.clinicmangaer.model.Department;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImplDepartmentRepository {
    public void save(Department department);
    public Optional<Department> findById(UUID id);
    public Optional<Department> findByName(String name);
    public List<Department> findAll();
}
