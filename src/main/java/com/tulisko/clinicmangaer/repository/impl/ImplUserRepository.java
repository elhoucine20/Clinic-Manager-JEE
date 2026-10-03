package com.tulisko.clinicmangaer.repository.impl;

import com.tulisko.clinicmangaer.model.User;

import java.util.Optional;

public interface ImplUserRepository {
    public void save(User user);
    public Optional<User> findByEmail(String email);
    public boolean existByEmail(String email);
}
