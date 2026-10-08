package com.tulisko.clinicmangaer.mapper.absenceMappers;

import com.tulisko.clinicmangaer.dto.absenceDTOs.AbsenceDTO;
import com.tulisko.clinicmangaer.model.Absence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AbsenceMapper {

    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM:yyyy");

    private AbsenceMapper(){}

    public static AbsenceDTO toDto(Absence absence){
        LocalDate today = LocalDate.now();
        String status;
        if (absence.getEndDate().isBefore(today)){status = "passee";}
        else if (absence.getStartDate().isBefore(today)){status="A venir";}
        else status = "En cours";
        return new AbsenceDTO(absence.getId(),absence.getStartDate().format(DATE),absence.getEndDate().format(DATE),absence.getReason(),status);
    }

    public static List<AbsenceDTO> toDtiList(List<Absence> absences){
        return absences.stream().map(AbsenceMapper::toDto).toList();
    }
}
