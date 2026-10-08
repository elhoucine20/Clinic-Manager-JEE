package com.tulisko.clinicmangaer.controller.adminServlets;

import com.tulisko.clinicmangaer.dto.doctorDTOs.DoctorCreateDTO;
import com.tulisko.clinicmangaer.dto.staffDTOs.StaffCreateDTO;
import com.tulisko.clinicmangaer.exception.DuplicateEmailException;
import com.tulisko.clinicmangaer.mapper.specialityMappers.SpecialtyMapper;
import com.tulisko.clinicmangaer.service.SpecialtyService;
import com.tulisko.clinicmangaer.service.UserService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final SpecialtyService specialtyService = new SpecialtyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        show(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String role = param(request, "role");
        String fullname = param(request, "fullname");
        String email = param(request, "email");
        String telephone = param(request, "telephone");
        String password = param(request, "password");
        String matricule = param(request, "matricule");
        String titre = param(request, "titre");
        String specialtyId = param(request, "specialtyId");

        try {
            if (fullname.isBlank() || email.isBlank() || telephone.isBlank()) {
                throw new IllegalArgumentException("tous les champ est oblegatoires");
            }
            switch (role) {
                case "DOCTOR" -> {
                    if (matricule.isBlank() || titre.isBlank()) {
                        throw new IllegalArgumentException("le matricule et le titre est oblegatoires");
                    }
                    userService.createDoctor(new DoctorCreateDTO(fullname, email, telephone, password, matricule, titre, parseId(specialtyId)));
                }
                case "STAFF" -> userService.createStaff(new StaffCreateDTO(fullname, email, telephone, password));
                default -> throw new IllegalArgumentException("role invalid");
            }
            response.sendRedirect(request.getContextPath() + "/admin/dashboard?success=1");

        } catch (DuplicateEmailException e) {
            request.setAttribute("error", "cet email est deja utiliser");
            show(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            show(request, response);
        } catch (PersistenceException e) {
            request.setAttribute("error", "enregistrement impossible : le matricule peut-etre exist deja");
            show(request, response);
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("specialties", SpecialtyMapper.toDTOList(specialtyService.findAll()));
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }

    private String param(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? "" : value;
    }

    private UUID parseId(String value) {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Choisiser une specialite");
        }
    }
}