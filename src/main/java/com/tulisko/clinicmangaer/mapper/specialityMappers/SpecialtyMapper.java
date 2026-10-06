package com.tulisko.clinicmangaer.mapper.specialityMappers;

import com.tulisko.clinicmangaer.dto.specialityDTOs.SpecialtyDTO;
import com.tulisko.clinicmangaer.model.Specialty;

import java.util.List;

public class SpecialtyMapper {

    private SpecialtyMapper() {
    }

    public static SpecialtyDTO toDTO(Specialty specialty) {
        return new SpecialtyDTO(
                specialty.getId(),
                specialty.getName(),
                specialty.getDepartment().getName());
    }

    public static List<SpecialtyDTO> toDTOList(List<Specialty> specialties) {
        return specialties.stream().map(SpecialtyMapper::toDTO).toList();
    }
}