package com.tulisko.clinicmangaer.service;

import com.tulisko.clinicmangaer.model.Department;
import com.tulisko.clinicmangaer.model.Specialty;
import com.tulisko.clinicmangaer.repository.DepartmentRepository;
import com.tulisko.clinicmangaer.repository.SpecialtyRepository;
import com.tulisko.clinicmangaer.repository.impl.ImplDepartmentRepository;
import com.tulisko.clinicmangaer.repository.impl.ImplSpecialtyRepository;

import java.util.List;
import java.util.UUID;

public class SpecialtyService {
    private final ImplSpecialtyRepository specialtyRepository = new SpecialtyRepository();
    private final ImplDepartmentRepository departmentRepository = new DepartmentRepository();

    public Specialty create(String name, UUID departmentId){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom de specialite est obligatoire.");
        }
        String cleanName = name.trim();
        if (specialtyRepository.findByName(cleanName).isPresent()) {
            throw new IllegalArgumentException("Cette specialite existe deja");
        }
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Departement introuvable."));

        Specialty specialty = new Specialty(cleanName, department);
        specialtyRepository.save(specialty);
        return specialty;
    }

    public List<Specialty> findAll(){
        return specialtyRepository.findAll();
    }
}
