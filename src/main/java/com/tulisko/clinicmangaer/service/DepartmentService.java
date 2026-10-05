package com.tulisko.clinicmangaer.service;

import com.tulisko.clinicmangaer.model.Department;
import com.tulisko.clinicmangaer.repository.DepartmentRepository;
import com.tulisko.clinicmangaer.repository.impl.ImplDepartmentRepository;

import java.util.List;

public class DepartmentService {
    private final ImplDepartmentRepository departmentRepository = new DepartmentRepository();

    public Department create(String name){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom de departement est obligatoire.");
        }
        String cleanName = name.trim();
        if (departmentRepository.findByName(cleanName).isPresent()) {
            throw new IllegalArgumentException("Ce departement existe deja.");
        }
        Department department = new Department(cleanName);
        departmentRepository.save(department);
        return department;
    }

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }
}
