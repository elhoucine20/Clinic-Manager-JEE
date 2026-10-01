package com.tulisko.clinicmangaer.model;

import com.tulisko.clinicmangaer.model.enums.RoleUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "patients")
public class Patient extends User{
    @Column(unique = true)
    private String cin;
    private LocalDate dateNaissance;
    private String adresse;

    protected Patient(){}

    public Patient(UUID id, String fullName, String email, String telephone, String passwordHash, String salt, String cin, LocalDate dateNaissance, String adresse) {
        super(id, fullName, email, telephone, passwordHash, salt, RoleUser.PATIENT);
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
    }

    public Patient(UUID id, String fullName, String email, String telephone,
                   String passwordHash, String salt) {
        super(id, fullName, email, telephone, passwordHash, salt,RoleUser.PATIENT);
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
