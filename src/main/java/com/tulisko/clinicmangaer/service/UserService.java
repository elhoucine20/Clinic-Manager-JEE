package com.tulisko.clinicmangaer.service;

import com.tulisko.clinicmangaer.exception.DuplicateEmailException;
import com.tulisko.clinicmangaer.exception.InvalidCredentialsException;
import com.tulisko.clinicmangaer.model.Admin;
import com.tulisko.clinicmangaer.model.User;
import com.tulisko.clinicmangaer.repository.UserRepository;
import com.tulisko.clinicmangaer.util.PasswordUtil;
import com.tulisko.clinicmangaer.model.Doctor;
import com.tulisko.clinicmangaer.model.Patient;
import com.tulisko.clinicmangaer.model.Staff;

import java.util.UUID;

public class UserService {

    private UserRepository userRepository = new UserRepository();


    public Admin createAdmin(String fullname,String email,String phone, String password){
        if (password == null || password.length() < 8){
            throw new IllegalArgumentException("password doit contenir au moins 8 caracteres");
        }
        String normalizedEmail = email.trim().toLowerCase();
        if (userRepository.findByEmail(normalizedEmail).isPresent()) {
            throw new DuplicateEmailException(normalizedEmail);
        }
        String salt = PasswordUtil.generateSlat();
        String hash = PasswordUtil.hashPassword(password, salt);

        Admin admin = new Admin(UUID.randomUUID(), fullname.trim(),
                normalizedEmail, phone.trim(), hash, salt);
        userRepository.save(admin);
        return admin;
    }

    public User login(String email,String password){
        User user = userRepository.findByEmail(email.trim().toLowerCase()).orElseThrow(InvalidCredentialsException::new);
        if (!user.isActive() || !PasswordUtil.mathces(password,user.getSalt(),user.getPasswordHash())){
            throw new InvalidCredentialsException();
        }
        return user;
    }

    public Patient createPatient(String fullName, String email, String phone, String password) {
        String normalizedEmail = checkAndNormalize(email, password);
        String salt = PasswordUtil.generateSlat();
        String hash = PasswordUtil.hashPassword(password, salt);

        Patient patient = new Patient(UUID.randomUUID(), fullName.trim(),
                normalizedEmail, phone.trim(), hash, salt);
        userRepository.save(patient);
        return patient;
    }

    public Doctor createDoctor(String fullName, String email, String phone, String password,
                               String matricule, String titre) {
        String normalizedEmail = checkAndNormalize(email, password);
        String salt = PasswordUtil.generateSlat();
        String hash = PasswordUtil.hashPassword(password, salt);

        Doctor doctor = new Doctor(UUID.randomUUID(), fullName.trim(),
                normalizedEmail, phone.trim(), hash, salt, matricule.trim(), titre.trim());
        userRepository.save(doctor);
        return doctor;
    }

    public Staff createStaff(String fullName, String email, String phone, String password) {
        String normalizedEmail = checkAndNormalize(email, password);
        String salt = PasswordUtil.generateSlat();
        String hash = PasswordUtil.hashPassword(password, salt);

        Staff staff = new Staff(UUID.randomUUID(), fullName.trim(),
                normalizedEmail, phone.trim(), hash, salt);
        userRepository.save(staff);
        return staff;
    }

    private String checkAndNormalize(String email, String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("password doit contenir au moins 8 caracteres");
        }
        String normalizedEmail = email.trim().toLowerCase();
        if (userRepository.findByEmail(normalizedEmail).isPresent()) {
            throw new DuplicateEmailException(normalizedEmail);
        }
        return normalizedEmail;
    }
}
