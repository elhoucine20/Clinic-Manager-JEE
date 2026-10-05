package com.tulisko.clinicmangaer.repository.impl;

import com.tulisko.clinicmangaer.model.Specialty;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImplSpecialtyRepository {
    public void save(Specialty specialty);

    public Optional<Specialty> findById(UUID id);
    public Optional<Specialty> findByName(String name);
    public List<Specialty> findAll();
}
