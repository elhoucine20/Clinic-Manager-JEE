package com.tulisko.clinicmangaer.repository;

import com.tulisko.clinicmangaer.model.Absence;
import com.tulisko.clinicmangaer.repository.impl.ImplAbsenceRepository;
import com.tulisko.clinicmangaer.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AbsenceRepository implements ImplAbsenceRepository{

        public void save(Absence absence) {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(absence);
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

        public Optional<Absence> findById(UUID id) {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                return Optional.ofNullable(em.find(Absence.class, id));
            } finally {
                em.close();
            }
        }

        public List<Absence> findByDoctor(UUID doctorId) {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                return em.createQuery(
                                "SELECT a FROM Absence a WHERE a.doctor.id = :doctorId " +
                                        "ORDER BY a.startDate DESC", Absence.class)
                        .setParameter("doctorId", doctorId)
                        .getResultList();
            } finally {
                em.close();
            }
        }

        public boolean existsCoveringDate(UUID doctorId, LocalDate date) {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                Long count = em.createQuery(
                                "SELECT count(a) FROM Absence a WHERE a.doctor.id = :doctorId " +
                                        "AND a.startDate <= :date AND a.endDate >= :date", Long.class)
                        .setParameter("doctorId", doctorId)
                        .setParameter("date", date)
                        .getSingleResult();
                return count > 0;
            } finally {
                em.close();
            }
        }

        public void delete(UUID id) {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                em.getTransaction().begin();
                Absence absence = em.find(Absence.class, id);
                if (absence != null) {
                    em.remove(absence);
                }
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
}
