package com.tulisko.clinicmangaer.model;

import com.tulisko.clinicmangaer.model.enums.RoleUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    protected UUID id;
    protected String fullName;
    @Column(nullable = false,unique = true)
    protected  String email;
    protected String telephone;
    protected String passwordHash;
    protected String salt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected RoleUser role;
    protected boolean active = true;


    protected User() {
    }
    public User(UUID id, String fullName, String email, String telephone, String passwordHash, String salt,RoleUser role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.role = role;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
