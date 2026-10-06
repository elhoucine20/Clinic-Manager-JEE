package com.tulisko.clinicmangaer.dto.doctorDTOs;

import java.util.UUID;

public class DoctorDTO {

    private final UUID id;
    private final String fullName;
    private final String email;
    private final String telephone;
    private final String matricule;
    private final String titre;
    private final String specialtyName;
    private final String departmentName;
    private final boolean active;

    public DoctorDTO(UUID id, String fullName, String email, String telephone,
                     String matricule, String titre, String specialtyName,
                     String departmentName, boolean active) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.matricule = matricule;
        this.titre = titre;
        this.specialtyName = specialtyName;
        this.departmentName = departmentName;
        this.active = active;
    }

    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getMatricule() { return matricule; }
    public String getTitre() { return titre; }
    public String getSpecialtyName() { return specialtyName; }
    public String getDepartmentName() { return departmentName; }
    public boolean isActive() { return active; }
}