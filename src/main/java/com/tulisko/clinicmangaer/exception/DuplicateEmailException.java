package com.tulisko.clinicmangaer.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("email deja utiliser : " + email);
    }
}
