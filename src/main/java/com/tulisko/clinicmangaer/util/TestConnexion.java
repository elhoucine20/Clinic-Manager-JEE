package com.tulisko.clinicmangaer.util;

import com.tulisko.clinicmangaer.model.Department;
import com.tulisko.clinicmangaer.model.Patient;
import com.tulisko.clinicmangaer.repository.UserRepository;
import com.tulisko.clinicmangaer.service.DepartmentService;
import com.tulisko.clinicmangaer.service.SpecialtyService;
import com.tulisko.clinicmangaer.service.UserService;

public class TestConnexion {
    public static void main(String[] args) {
        JpaUtil.getEntityManager().close();
        System.out.println("Connexion OK");

       // UserService service = new UserService();
        //service.createAdmin("elhoucine", "elhoucine@gmail.com", "0600000000", "11111111");
        //System.out.println("Admin cree : " + service.login("elhoucine@gmail.com", "11111111").getRole());
        //DepartmentService departmentService = new DepartmentService();
        //SpecialtyService specialtyService = new SpecialtyService();

        //Department dep = departmentService.create("Medecine");
        //specialtyService.create("Cardiologie", dep.getId());

        //specialtyService.findAll().forEach(s ->
        //System.out.println(s.getName() + " (" + s.getDepartment().getName() + ")"));

        JpaUtil.close();
    }
}