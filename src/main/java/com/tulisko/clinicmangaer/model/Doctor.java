package com.tulisko.clinicmangaer.model;

import com.tulisko.clinicmangaer.model.enums.RoleUser;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "doctors")
public class Doctor extends User {

    @Column(unique = true, nullable = false)
    private String matricule;
    private String titre;
    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    protected Doctor() {
    }

    public Doctor(UUID id, String fullName, String email, String telephone,
                  String passwordHash, String salt, String matricule, String titre) {
        super(id, fullName, email, telephone, passwordHash, salt, RoleUser.DOCTOR);
        this.matricule = matricule;
        this.titre = titre;
    }
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}