package com.tulisko.clinicmangaer.controller;

import com.tulisko.clinicmangaer.mapper.depertmentMappers.DepartmentMapper;
import com.tulisko.clinicmangaer.mapper.specialityMappers.SpecialtyMapper;
import com.tulisko.clinicmangaer.service.DepartmentService;
import com.tulisko.clinicmangaer.service.SpecialtyService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/admin/catalog")
public class AdminCatalogServlet extends HttpServlet {

    private final DepartmentService departmentService = new DepartmentService();
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

        String action = param(request, "action");
        String name = param(request, "name");

        try {
            switch (action) {
                case "department" -> departmentService.create(name);
                case "specialty" -> specialtyService.create(name, parseId(param(request, "departmentId")));
                default -> throw new IllegalArgumentException("Action invalide.");
            }
            response.sendRedirect(request.getContextPath() + "/admin/catalog?success=1");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            show(request, response);
        } catch (PersistenceException e) {
            request.setAttribute("error", "Enregistrement impossible.");
            show(request, response);
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("departments", DepartmentMapper.toDTOList(departmentService.findAll()));
        request.setAttribute("specialties", SpecialtyMapper.toDTOList(specialtyService.findAll()));
        request.getRequestDispatcher("/admin/catalog.jsp").forward(request, response);
    }

    private String param(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? "" : value;
    }

    private UUID parseId(String value) {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Choisissez un département.");
        }
    }
}