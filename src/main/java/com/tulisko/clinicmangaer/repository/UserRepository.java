package com.tulisko.clinicmangaer.repository;

import com.tulisko.clinicmangaer.model.Doctor;
import com.tulisko.clinicmangaer.model.Staff;
import com.tulisko.clinicmangaer.model.User;
import com.tulisko.clinicmangaer.repository.impl.ImplUserRepository;
import com.tulisko.clinicmangaer.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository implements ImplUserRepository {


    public void save(User user){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }finally {
            em.close();
        }
    }

    public Optional<User> findByEmail(String email){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM  User u WHERE u.email = :email",User.class).setParameter("email",email).getResultStream().findFirst();
        }finally {
            em.close();
        }
    }

    public boolean existByEmail(String email){
        return findByEmail(email).isPresent();
    }

    public List<Doctor> findAllDoctors(){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Doctor d ORDER BY d.fullName", Doctor.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Staff> findAllStaff(){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Staff s ORDER BY s.fullName", Staff.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u ORDER BY u.fullName", User.class).getResultList();
        }finally {
            em.close();
        }
    }

    @Override
    public void updateActive(UUID id, boolean active) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class,id);
            if (user == null) throw new IllegalArgumentException("utilisateur introuvable !!");
            user.setActive(active);
            em.getTransaction().commit();


        }catch (RuntimeException e){
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }finally {
            em.close();
        }
    }

    public Optional<Doctor> findDoctorById(UUID id){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Doctor.class,id));
        }finally {
            em.close();
        }
    }

}
