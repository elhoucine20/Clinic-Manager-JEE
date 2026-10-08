package com.tulisko.clinicmangaer.service;

import com.tulisko.clinicmangaer.model.Availability;
import com.tulisko.clinicmangaer.model.Doctor;
import com.tulisko.clinicmangaer.repository.AvailabilityRepository;
import com.tulisko.clinicmangaer.repository.UserRepository;
import com.tulisko.clinicmangaer.repository.impl.ImplAvailabilityRepository;
import com.tulisko.clinicmangaer.repository.impl.ImplUserRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class AvailabilityService {

    private final ImplUserRepository userRepository = new UserRepository();
    private final ImplAvailabilityRepository availabilityRepository = new AvailabilityRepository();

    public Availability create(UUID doctorId, DayOfWeek day, LocalTime start , LocalTime end, LocalDate validFrom,LocalDate validTo){

        if (day == null || start == null || end == null || validFrom == null){
            throw new IllegalArgumentException("Le jour, les heures et la date de debut sont obligatoires !!");
        }
        if (day == DayOfWeek.SUNDAY){
            throw new IllegalArgumentException("La clinique est fermee le dimanche !!");
        }
        if (!end.isAfter(start)){
            throw new IllegalArgumentException("L'heure de fin doit etre apres l'heure de debut !!");
        }
        if (validTo != null && validTo.isBefore(validFrom)){
            throw new IllegalArgumentException("La fin de validite ne peut pas etre avant le debut.");
        }

        Doctor doctor = userRepository.findDoctorById(doctorId).orElseThrow(() -> new  IllegalArgumentException("doctor intouvable"));

        for (Availability other : availabilityRepository.findActiveByDoctorAndDay(doctorId,day)){
            if (overlaps(start,end,validFrom,validTo,other)){
                throw new IllegalArgumentException("cette date a ete deja remplier !!");
            }
        }
        Availability availability = new Availability(doctor,day,start,end,validFrom,validTo);
        availabilityRepository.save(availability);
        return availability;
    }

    private boolean overlaps(LocalTime start, LocalTime end, LocalDate from, LocalDate to, Availability other) {
        boolean timesOverlap = start.isBefore(other.getEndTime()) && other.getStartTime().isBefore(end);

        boolean periodsOverlap = (to == null || !other.getValidFrom().isAfter(to)) && (other.getValidTo() == null
                                             || !from.isAfter(other.getValidTo()));
        return timesOverlap && periodsOverlap;
    }

    public List<Availability> findByDoctor(UUID doctorId){
       return availabilityRepository.findByDoctor(doctorId).stream()
               .sorted(Comparator.comparing(Availability::getDayOfWeek).thenComparing(Availability::getStartTime)).toList();
    }


    public void delete(UUID doctorId,UUID availabilityId){
        Availability availability = availabilityRepository.findById(availabilityId).orElseThrow(()->new IllegalArgumentException("Horaire introuvable !!"));
        if (!availability.getDoctor().getId().equals(doctorId))
            throw new IllegalArgumentException("Horaire introuvable !!");
        availabilityRepository.delete(availabilityId);
    }

}
