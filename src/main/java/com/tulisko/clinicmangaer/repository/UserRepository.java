package com.tulisko.clinicmangaer.repository;

import com.tulisko.clinicmangaer.model.User;
import com.tulisko.clinicmangaer.repository.impl.ImplUserRepository;
import com.tulisko.clinicmangaer.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;

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
}
