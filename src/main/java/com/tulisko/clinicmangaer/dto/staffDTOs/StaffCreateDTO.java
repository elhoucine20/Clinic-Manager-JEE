package com.tulisko.clinicmangaer.dto.staffDTOs;

public class StaffCreateDTO {

    private final String fullName;
    private final String email;
    private final String telephone;
    private final String password;

    public StaffCreateDTO(String fullName, String email, String telephone, String password) {
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getPassword() { return password; }
}