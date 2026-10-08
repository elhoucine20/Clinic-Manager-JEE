package com.tulisko.clinicmangaer.service;

import com.tulisko.clinicmangaer.model.Absence;
import com.tulisko.clinicmangaer.model.Doctor;
import com.tulisko.clinicmangaer.repository.AbsenceRepository;
import com.tulisko.clinicmangaer.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AbsenceService {

    private final AbsenceRepository absenceRepository = new AbsenceRepository();
    private final UserRepository userRepository = new UserRepository();

    public Absence create(UUID doctorId, LocalDate startDate, LocalDate endDate, String reason) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Les dates de debut et de fin sont obligatoires !!");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("La date de fin ne peut pas etre avant la date de debut !!");
        }
        if (endDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cette absence est deja passe !!");
        }

        String cleanReason = (reason == null || reason.isBlank()) ? null : reason.trim();
        if (cleanReason != null && cleanReason.length() > 255) {
            throw new IllegalArgumentException("Le motif est trop long (255 caracteres maximum).");
        }

        Doctor doctor = userRepository.findDoctorById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Médecin introuvable."));

        Absence absence = new Absence(doctor, startDate, endDate, cleanReason);
        absenceRepository.save(absence);
        return absence;
    }

    public List<Absence> findByDoctor(UUID doctorId) {
        return absenceRepository.findByDoctor(doctorId);
    }

    public boolean isAbsent(UUID doctorId, LocalDate date) {
        return absenceRepository.existsCoveringDate(doctorId, date);
    }

    public void delete(UUID doctorId, UUID absenceId) {
        Absence absence = absenceRepository.findById(absenceId)
                .orElseThrow(() -> new IllegalArgumentException("Absence introuvable."));
        if (!absence.getDoctor().getId().equals(doctorId)) {
            throw new IllegalArgumentException("Absence introuvable.");
        }
        absenceRepository.delete(absenceId);
    }
}