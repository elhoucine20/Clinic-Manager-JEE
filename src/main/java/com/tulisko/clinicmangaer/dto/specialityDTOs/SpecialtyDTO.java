package com.tulisko.clinicmangaer.dto.specialityDTOs;

import java.util.UUID;

public class SpecialtyDTO {

    private final UUID id;
    private final String name;
    private final String departmentName;

    public SpecialtyDTO(UUID id, String name, String departmentName) {
        this.id = id;
        this.name = name;
        this.departmentName = departmentName;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}