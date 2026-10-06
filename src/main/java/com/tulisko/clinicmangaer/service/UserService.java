package com.tulisko.clinicmangaer.service;

import com.tulisko.clinicmangaer.dto.doctorDTOs.DoctorCreateDTO;
import com.tulisko.clinicmangaer.dto.staffDTOs.StaffCreateDTO;
import com.tulisko.clinicmangaer.exception.DuplicateEmailException;
import com.tulisko.clinicmangaer.exception.InvalidCredentialsException;
import com.tulisko.clinicmangaer.model.*;
import com.tulisko.clinicmangaer.repository.SpecialtyRepository;
import com.tulisko.clinicmangaer.repository.UserRepository;
import com.tulisko.clinicmangaer.repository.impl.ImplSpecialtyRepository;
import com.tulisko.clinicmangaer.repository.impl.ImplUserRepository;
import com.tulisko.clinicmangaer.util.PasswordUtil;

import java.util.List;
import java.util.UUID;

public class UserService {

    private ImplUserRepository userRepository = new UserRepository();
    private ImplSpecialtyRepository specialtyRepository = new SpecialtyRepository();

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

    public Doctor createDoctor(DoctorCreateDTO dto) {

        if (dto.getSpecialtyId() == null) {
            throw new IllegalArgumentException("Choisissez une spécialité.");
        }
        Specialty specialty = specialtyRepository.findById(dto.getSpecialtyId())
                .orElseThrow(() -> new IllegalArgumentException("Choisissez une specialite"));

        String normalizedEmail = checkAndNormalize(dto.getEmail(), dto.getPassword());
        String salt = PasswordUtil.generateSlat();
        String hash = PasswordUtil.hashPassword(dto.getPassword(), salt);

        Doctor doctor = new Doctor(UUID.randomUUID(), dto.getFullName().trim(),
                normalizedEmail, dto.getTelephone().trim(), hash, salt, dto.getMatricule().trim(), dto.getTitre().trim());
        doctor.setSpecialty(specialty);
        userRepository.save(doctor);
        return doctor;
    }

    public Staff createStaff(StaffCreateDTO dto) {
        String normalizedEmail = checkAndNormalize(dto.getEmail(), dto.getPassword());
        String salt = PasswordUtil.generateSlat();
        String hash = PasswordUtil.hashPassword(dto.getPassword(), salt);

        Staff staff = new Staff(UUID.randomUUID(), dto.getFullName().trim(),
                normalizedEmail, dto.getTelephone().trim(), hash, salt);
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


    public List<Doctor> findAllDoctors() {
        return userRepository.findAllDoctors();
    }
    public List<Staff> findAllStaff() {
        return userRepository.findAllStaff();
    }
}
