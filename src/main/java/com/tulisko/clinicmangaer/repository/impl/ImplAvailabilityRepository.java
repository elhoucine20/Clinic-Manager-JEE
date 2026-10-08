package com.tulisko.clinicmangaer.repository.impl;

import com.tulisko.clinicmangaer.model.Availability;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImplAvailabilityRepository {

    public void save(Availability availability);
    public Optional<Availability> findById(UUID id);
    public List<Availability> findByDoctor(UUID doctorId);
    public List<Availability> findActiveByDoctorAndDay(UUID doctorId, DayOfWeek day);
    public void delete(UUID id);

}
