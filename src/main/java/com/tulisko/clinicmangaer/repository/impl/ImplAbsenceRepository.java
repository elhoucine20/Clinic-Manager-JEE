package com.tulisko.clinicmangaer.repository.impl;

import com.tulisko.clinicmangaer.model.Absence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImplAbsenceRepository{

    public void save(Absence absence);
    public Optional<Absence> findById(UUID id);
    public List<Absence> findByDoctor(UUID doctorId);
    public boolean existsCoveringDate(UUID doctorId, LocalDate date);
    public void delete(UUID id);

}
