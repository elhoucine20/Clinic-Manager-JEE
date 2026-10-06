package com.tulisko.clinicmangaer.dto.doctorDTOs;

import java.util.UUID;

public class DoctorCreateDTO {

    private final String fullName;
    private final String email;
    private final String telephone;
    private final String password;
    private final String matricule;
    private final String titre;
    private final UUID specialtyId;

    public DoctorCreateDTO(String fullName, String email, String telephone, String password,
                           String matricule, String titre, UUID specialtyId) {
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.matricule = matricule;
        this.titre = titre;
        this.specialtyId = specialtyId;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getPassword() { return password; }
    public String getMatricule() { return matricule; }
    public String getTitre() { return titre; }
    public UUID getSpecialtyId() { return specialtyId; }
}