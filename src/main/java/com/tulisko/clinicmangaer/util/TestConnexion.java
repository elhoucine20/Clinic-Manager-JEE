package com.tulisko.clinicmangaer.util;

import com.tulisko.clinicmangaer.model.Department;
import com.tulisko.clinicmangaer.model.Patient;
import com.tulisko.clinicmangaer.repository.UserRepository;
import com.tulisko.clinicmangaer.service.AvailabilityService;
import com.tulisko.clinicmangaer.service.DepartmentService;
import com.tulisko.clinicmangaer.service.SpecialtyService;
import com.tulisko.clinicmangaer.service.UserService;

import java.time.LocalDate;
import java.util.UUID;

public class TestConnexion {
    public static void main(String[] args) {
        JpaUtil.getEntityManager().close();
        System.out.println("Connexion OK");

        UUID doctorId = new UserRepository().findByEmail("lahcen@gmail.com").orElseThrow().getId();
        AvailabilityService service = new AvailabilityService();

        service.findByDoctor(doctorId).forEach(a ->
                System.out.println(a.getDayOfWeek() + " " + a.appliesTo(LocalDate.of(2026, 11, 2))));
        JpaUtil.close();
    }
}