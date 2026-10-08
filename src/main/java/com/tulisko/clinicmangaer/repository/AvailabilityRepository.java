package com.tulisko.clinicmangaer.repository;

import com.tulisko.clinicmangaer.model.Availability;
import com.tulisko.clinicmangaer.model.enums.AvailabilityStatus;
import com.tulisko.clinicmangaer.repository.impl.ImplAvailabilityRepository;
import com.tulisko.clinicmangaer.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AvailabilityRepository implements ImplAvailabilityRepository {
    @Override
    public void save(Availability availability) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(availability);
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Availability> findById(UUID id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Availability.class,id));
        }finally {
            em.close();
        }
    }

    @Override
    public List<Availability> findByDoctor(UUID doctorId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("select a from Availability a where a.doctor.id = :doctorId", Availability.class)
                    .setParameter("doctorId",doctorId).getResultList();
        }finally {
            em.close();
        }
    }

    @Override
    public List<Availability> findActiveByDoctorAndDay(UUID doctorId, DayOfWeek day) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            return  em.createQuery("select a from Availability a where a.doctor.id = :doctorId and a.dayOfWeek = :day and a.status = :status", Availability.class)
                    .setParameter("doctorId",doctorId).setParameter("day",day).setParameter("status", AvailabilityStatus.ACTIVE).getResultList();
        }finally {
            em.close();
        }
    }

    @Override
    public void delete(UUID id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Optional<Availability> availability = Optional.ofNullable(em.find(Availability.class,id));
            if (availability.isPresent())
                em.remove(availability);
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;
        }finally {
            em.close();
        }
    }
}
