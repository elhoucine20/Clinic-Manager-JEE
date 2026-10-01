package com.tulisko.clinicmangaer.model;

import com.tulisko.clinicmangaer.model.enums.RoleUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "staff")
public class Staff extends User {

    protected Staff() {
    }

    public Staff(UUID id, String fullName, String email, String telephone,
                 String passwordHash, String salt) {
        super(id, fullName, email, telephone, passwordHash, salt, RoleUser.STAFF);
    }
}