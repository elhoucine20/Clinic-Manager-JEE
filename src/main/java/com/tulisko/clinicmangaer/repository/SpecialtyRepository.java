package com.tulisko.clinicmangaer.repository;

import com.tulisko.clinicmangaer.model.Specialty;
import com.tulisko.clinicmangaer.repository.impl.ImplSpecialtyRepository;
import com.tulisko.clinicmangaer.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SpecialtyRepository implements ImplSpecialtyRepository {
    @Override
    public void save(Specialty specialty) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(specialty);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Specialty> findById(UUID id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Specialty.class, id));
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Specialty> findByName(String name) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT s FROM Specialty s WHERE lower(s.name) = :name", Specialty.class)
                    .setParameter("name", name.toLowerCase())
                    .getResultStream()
                    .findFirst();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Specialty> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Specialty s ORDER BY s.name", Specialty.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
