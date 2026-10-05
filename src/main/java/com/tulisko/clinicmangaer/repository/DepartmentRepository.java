package com.tulisko.clinicmangaer.repository;

import com.tulisko.clinicmangaer.model.Department;
import com.tulisko.clinicmangaer.repository.impl.ImplDepartmentRepository;
import com.tulisko.clinicmangaer.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DepartmentRepository implements ImplDepartmentRepository {
    @Override
    public void save(Department department) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(department);
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }finally {
            em.close();
        }
    }

    @Override
    public Optional<Department> findById(UUID id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return Optional.of(em.find(Department.class,id));
        }finally {
            em.close();
        }
    }

    @Override
    public Optional<Department> findByName(String name) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.createQuery("SELECT d FROM Department d WHERE lower(d.name) = :name", Department.class)
                    .setParameter("name",name.toLowerCase()).getResultStream().findFirst();
        }finally {
            em.close();
        }
        return Optional.empty();
    }

    @Override
    public List<Department> findAll(){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Department d ORDER BY d.name", Department.class).getResultList();
        }finally {
            em.close();
        }
    }
}
