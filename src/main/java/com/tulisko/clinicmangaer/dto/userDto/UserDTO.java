package com.tulisko.clinicmangaer.dto.userDto;

import java.util.UUID;

public class UserDTO {

    private final UUID id;
    private final String fullName;
    private final String email;
    private final String telephone;
    private final String role;
    private final boolean active;

    public UserDTO(UUID id, String fullName, String email, String telephone,
                   String role, boolean active) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
        this.active = active;
    }

    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getRole() { return role; }
    public boolean isActive() { return active; }
}