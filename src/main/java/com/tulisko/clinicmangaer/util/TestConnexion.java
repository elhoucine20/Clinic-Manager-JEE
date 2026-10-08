package com.tulisko.clinicmangaer.util;

import com.tulisko.clinicmangaer.model.Patient;
import com.tulisko.clinicmangaer.repository.UserRepository;
import com.tulisko.clinicmangaer.service.AbsenceService;

import java.time.LocalDate;
import java.util.UUID;

public class TestConnexion {
    public static void main(String[] args) {
        JpaUtil.getEntityManager().close();
        System.out.println("Connexion OK");

        UUID doctorId = new UserRepository().findByEmail("lahcen@gmail.com").orElseThrow().getId();
        AbsenceService service = new AbsenceService();

        service.create(doctorId, LocalDate.of(2026, 10, 20), LocalDate.of(2026, 10, 22), "Congé");

        System.out.println("19/10 absent ? " + service.isAbsent(doctorId, LocalDate.of(2026, 10, 19)));
        System.out.println("20/10 absent ? " + service.isAbsent(doctorId, LocalDate.of(2026, 10, 20)));
        System.out.println("22/10 absent ? " + service.isAbsent(doctorId, LocalDate.of(2026, 10, 22)));
        System.out.println("23/10 absent ? " + service.isAbsent(doctorId, LocalDate.of(2026, 10, 23)));

        try {
            service.create(doctorId, LocalDate.of(2026, 10, 25), LocalDate.of(2026, 10, 24), null);
        } catch (IllegalArgumentException e) {
            System.out.println("Refusé : " + e.getMessage());
        }
        try {
            service.create(doctorId, LocalDate.of(2026, 9, 1), LocalDate.of(2026, 9, 5), null);
        } catch (IllegalArgumentException e) {
            System.out.println("Refusé : " + e.getMessage());
        }        JpaUtil.close();
    }
}