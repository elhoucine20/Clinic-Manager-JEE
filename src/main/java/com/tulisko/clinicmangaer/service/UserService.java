package com.tulisko.clinicmangaer.service;

import com.tulisko.clinicmangaer.exception.DuplicateEmailException;
import com.tulisko.clinicmangaer.exception.InvalidCredentialsException;
import com.tulisko.clinicmangaer.model.Admin;
import com.tulisko.clinicmangaer.model.User;
import com.tulisko.clinicmangaer.repository.UserRepository;
import com.tulisko.clinicmangaer.util.PasswordUtil;

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
}
